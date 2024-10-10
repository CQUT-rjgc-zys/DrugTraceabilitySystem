package com.curriculumdesign.drugtraceabilitysystem.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class StreamProcess {

    public static void exportTableLineageExcelGraph(TableExcelExport dto) {
        List<Node> nodes = dto.getGraph().getNodes();
        Node rootNode = nodes.stream().filter(Node::getIsCurrentSearchNode).collect(Collectors.toList()).get(0);
        Field rootData = rootNode.getProperties();
        String fileName = rootData.getTableFileName();
        splitUpstreamAndDownstream(rootNode, dto);
        try {
            SXSSFWorkbook workbook = new SXSSFWorkbook();
            setNodeSelfJoin(dto);
            List<Edge> upstreamEdges = dto.getUpstreamGraph().getEdges();
            upstreamEdges = upstreamEdges.stream().filter(edge -> edge.getSource().equals(rootNode.getId())).collect(Collectors.toList());
            rootNode.setEdges(upstreamEdges);
            SXSSFSheet upstream = workbook.createSheet("药品信息流向");
            ExcelTreeNode upstreamRoot = buildExcelTreeNode(dto.getUpstreamGraph(), rootNode);
            if (upstreamRoot != null) {
                detailSheet(workbook, upstream, upstreamRoot, true);
            }

            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void splitUpstreamAndDownstream(Node target, TableExcelExport dto) {
        Graph graph = dto.getGraph();
        List<Node> nodes = graph.getNodes();
        List<Edge> edges = graph.getEdges();

        ArrayList<Node> allNodes = new ArrayList<>(nodes);
        ArrayList<Edge> allEdges = new ArrayList<>(edges);

        List<Edge> rootRingEdge = allEdges.stream().filter(edge -> edge.getSource().equals(target.getId()) && edge.getSource().equals(edge.getTarget())).collect(Collectors.toList());

        if (!rootRingEdge.isEmpty()) {
            target.setSelfJoin(true);
            allEdges.removeAll(rootRingEdge);
        }
        allNodes.remove(target);

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (Edge edge : allEdges) {
            if (!map.containsKey(edge.getSource())) {
                map.put(edge.getSource(), new HashSet<>());
            }
            map.get(edge.getSource()).add(edge.getTarget());
        }

        Set<Integer> visited = new HashSet<>();

        List<Integer> downstreamNodeIds = new ArrayList<>();

        dfs(target.getId(), visited, downstreamNodeIds, map);

        Set<Integer> downstreamSet = new HashSet<>(downstreamNodeIds);

        Map<Integer, Node> nodeMap = allNodes.stream().collect(Collectors.toMap(Node::getId, Function.identity()));
        Map<Integer, List<Edge>> edgesMap = allEdges.stream().collect(Collectors.groupingBy(Edge::getSource));

        Graph upstreamGraph = new Graph();
        Graph downstreamGraph = new Graph();
        List<Node> downstreamNodes = new ArrayList<>();
        List<Edge> downstreamEdges = new ArrayList<>();
        downstreamSet.forEach(id -> {
            downstreamEdges.addAll(edgesMap.get(id));
            if (nodeMap.get(id) != null) {
                downstreamNodes.add(nodeMap.get(id));
            }
        });

        allEdges.removeAll(downstreamEdges);
        allNodes.removeAll(downstreamNodes);

        upstreamGraph.setNodes(allNodes);
        upstreamGraph.setEdges(allEdges);
        downstreamGraph.setNodes(downstreamNodes);
        downstreamGraph.setEdges(downstreamEdges);
        dto.setDownstreamGraph(downstreamGraph);
        dto.setUpstreamGraph(upstreamGraph);

    }

    public static void setNodeSelfJoin(TableExcelExport dto) {
        List<Edge> upstreamEdges = dto.getUpstreamGraph().getEdges();
        List<Edge> downstreamEdges = dto.getDownstreamGraph().getEdges();
        Map<Integer, Node> upstreamNodeMap = dto.getUpstreamGraph().getNodes().stream().collect(Collectors.toMap(Node::getId, Function.identity()));
        Map<Integer, Node> downstreamNodeMap = dto.getDownstreamGraph().getNodes().stream().collect(Collectors.toMap(Node::getId, Function.identity()));

        processEdges(dto, upstreamEdges, upstreamNodeMap, true);
        processEdges(dto, downstreamEdges, downstreamNodeMap, false);
    }

    public static void processEdges(TableExcelExport dto, List<Edge> edges, Map<Integer, Node> nodeMap, boolean isUpstream) {
        List<Edge> newEdges = new ArrayList<>();
        for (Edge edge : edges) {
            Integer source = edge.getSource();
            Integer target = edge.getTarget();
            Edge newEdge = new Edge(source, target);
            if (isUpstream) {
                newEdge.setSource(target);
                newEdge.setTarget(source);
            }

            if (source.equals(target)) {
                Node node = nodeMap.get(source);
                node.setSelfJoin(true);
            } else {
                newEdges.add(newEdge);
            }
        }

        if (isUpstream) {
            dto.getUpstreamGraph().setEdges(newEdges);
        } else {
            dto.getDownstreamGraph().setEdges(newEdges);
        }
    }

    public static ExcelTreeNode buildExcelTreeNode(Graph graph, Node target) {
        Map<Integer, Node> nodeMap = graph.getNodes().stream().collect(Collectors.toMap(Node::getId, Function.identity()));

        if (nodeMap.isEmpty()) {
            return null;
        }

        Map<Integer, List<Edge>> edgeMap = graph.getEdges().stream().collect(Collectors.groupingBy(Edge::getSource));

        nodeMap.forEach((id, node) -> {
            List<Edge> edges = edgeMap.get(id);
            if (edges == null) {
                node.setEdges(new ArrayList<>());
            } else {
                node.setEdges(edges);
            }
        });

        Queue<ExcelTreeNode> queue = new LinkedList<>();
        ExcelTreeNode rootNode = new ExcelTreeNode(target);
        queue.offer(rootNode);
        ExcelTreeNode preNode;
        while (!queue.isEmpty()) {
            preNode = queue.poll();

            List<Edge> edges = preNode.getNode().getEdges();

            List<ExcelTreeNode> nextLevelNodes = new ArrayList<>();

            if (preNode.getNode().isSelfJoin) {
                ExcelTreeNode sameNode = new ExcelTreeNode(preNode.getNode());
                sameNode.setPreLevelNode(preNode);
                sameNode.setNextLevelNodes(new ArrayList<>());
                nextLevelNodes.add(sameNode);
            }

            for (Edge edge : edges) {
                Node nextLevelNode = nodeMap.get(edge.getTarget());
                ExcelTreeNode excelTreeNode;
                if (nextLevelNode == null) {
                    excelTreeNode = new ExcelTreeNode(target);
                } else {
                    excelTreeNode = new ExcelTreeNode(nextLevelNode);
                }
                excelTreeNode.setPreLevelNode(preNode);

                ExcelTreeNode preLevelNode = preNode.getPreLevelNode();
                if (preLevelNode == null || nextLevelNode != null && !preLevelNode.getNode().equals(nextLevelNode)) {
                    queue.offer(excelTreeNode);
                } else {
                    excelTreeNode.setNextLevelNodes(new ArrayList<>());
                }

                nextLevelNodes.add(excelTreeNode);
            }
            preNode.setNextLevelNodes(nextLevelNodes);
        }
        return rootNode;
    }

    public static void detailSheet(SXSSFWorkbook workbook, SXSSFSheet sheet, ExcelTreeNode root, boolean table) {
        sheet.setDefaultColumnWidth(30);
        List<List<ExcelTreeNode>> nodeLists = bfsTraversal(root);
        root.setCol(0);
        root.setStartRow(0);
        root.setEndRow(0);
        initNodePosition(root);

        int rowNum = root.getEndRow() + 1;
        int colNum = nodeLists.size();
        SXSSFRow header = sheet.createRow(0);
        for (int i = 0; i < colNum; i++) {
            // 创建第一行的第一个单元格，并设置值
            SXSSFCell cell = header.createCell(i);
            cell.setCellValue(" " + i + "");
        }

        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, colNum - 1));

        List<List<SXSSFCell>> cellLists = new ArrayList<>();
        for (int i = 0; i < rowNum; i++) {
            SXSSFRow row = sheet.createRow(i + 1);
            List<SXSSFCell> cellList = new ArrayList<>();
            for (int j = 0; j < colNum; j++) {
                SXSSFCell cell = row.createCell(j);
                cellList.add(cell);
            }
            cellLists.add(cellList);
        }

        for (List<ExcelTreeNode> nodeList : nodeLists) {
            for (ExcelTreeNode node : nodeList) {
                List<SXSSFCell> cells = cellLists.get(node.getStartRow());
                SXSSFCell cell = cells.get(node.getCol());
                Field data = node.getNode().getProperties();
                cell.setCellValue(table ? data.getTableInfo() : data.getFieldInfo());
                if (node.getEndRow() > node.getStartRow()) {
                    sheet.addMergedRegion(new CellRangeAddress(node.getStartRow() + 1, node.getEndRow() + 1, node.getCol(), node.getCol()));
                }
            }
        }
    }

    public static List<List<ExcelTreeNode>> bfsTraversal(ExcelTreeNode root) {
        List<List<ExcelTreeNode>> result = new ArrayList<>();
        Queue<ExcelTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<ExcelTreeNode> currentLevel = new ArrayList<>(levelSize);
            ExcelTreeNode preNode = null;
            for (int i = 0; i < levelSize; i++) {
                ExcelTreeNode currentNode = queue.poll();
                assert currentNode != null;
                if (preNode != null) {
                    if (preNode.getPreLevelNode() == currentNode.getPreLevelNode()) {
                        currentNode.setPreNode(preNode);
                    }
                }
                preNode = currentNode;
                currentLevel.add(currentNode);
                List<ExcelTreeNode> nextLevelNodes = currentNode.getNextLevelNodes();
                nextLevelNodes.forEach(queue::offer);
            }
            result.add(currentLevel);
        }
        return result;
    }

    public static int initNodePosition(ExcelTreeNode node) {
        List<ExcelTreeNode> nextLevelNodes = node.getNextLevelNodes();
        int addRow = 0;
        for (ExcelTreeNode nextLevelNode : nextLevelNodes) {
            ExcelTreeNode preLevelNode = nextLevelNode.getPreLevelNode();
            ExcelTreeNode preNode = nextLevelNode.getPreNode();
            if (preNode == null) {
                int startRow = preLevelNode.startRow;
                nextLevelNode.setStartRow(startRow);
                nextLevelNode.setEndRow(startRow);
            } else {
                int endRow = preNode.endRow + 1;
                nextLevelNode.setStartRow(endRow);
                nextLevelNode.setEndRow(endRow);
            }
            nextLevelNode.setCol(node.col + 1);
            addRow += initNodePosition(nextLevelNode);
        }
        if (addRow > 1) {
            node.setEndRow(node.getStartRow() + addRow - 1);
        }
        return node.getEndRow() - node.getStartRow() + 1;
    }

    public static void dfs(Integer id, Set<Integer> visited, List<Integer> result, Map<Integer, Set<Integer>> map) {
        if (visited.contains(id)) return;

        visited.add(id);
        if (map.containsKey(id)) {
            for (Integer i : map.get(id)) {
                result.add(i);
                dfs(i, visited, result, map);
            }
        }
    }
}

