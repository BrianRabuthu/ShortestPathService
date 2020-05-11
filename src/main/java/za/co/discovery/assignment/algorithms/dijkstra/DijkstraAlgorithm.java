package za.co.discovery.assignment.algorithms.dijkstra;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import za.co.discovery.assignment.model.Graph;
import za.co.discovery.assignment.model.Node;
@Slf4j
public class DijkstraAlgorithm {	
    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
    	log.info("############### start :: calculateShortestPathFromSource ##############");
        source.setDistance(0f);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry<Node, Float> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Float edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        log.info("############### end :: calculateShortestPathFromSource ##############");
        return graph;
    }

    private static void CalculateMinimumDistance(Node evaluationNode, Float edgeWeigh, Node sourceNode) {
    	log.info("############### start :: CalculateMinimumDistance ##############");
        Float sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
        log.info("############### end :: CalculateMinimumDistance ##############");
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
    	log.info("############### start :: getLowestDistanceNode ##############");
        Node lowestDistanceNode = null;
        Float lowestDistance = Float.MAX_VALUE;
        for (Node node : unsettledNodes) {
            Float nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        log.info("############### end :: getLowestDistanceNode ##############");
        return lowestDistanceNode;
    }
}
