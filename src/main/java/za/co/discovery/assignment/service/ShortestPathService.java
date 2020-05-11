package za.co.discovery.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import za.co.discovery.assignment.algorithms.dijkstra.DijkstraAlgorithm;
import za.co.discovery.assignment.entity.Planet;
import za.co.discovery.assignment.entity.PlanetRoute;
import za.co.discovery.assignment.entity.ShortestPath;
import za.co.discovery.assignment.model.Graph;
import za.co.discovery.assignment.model.Node;
import za.co.discovery.assignment.repository.IPlanetRepository;
import za.co.discovery.assignment.repository.IPlanetRouteRepository;
import za.co.discovery.assignment.repository.IShortestPathRepository;

@Slf4j
@Service
public class ShortestPathService {
	@Autowired
	IPlanetRepository planetRepository;

	@Autowired
	IPlanetRouteRepository planetRouteRepository;

	@Autowired
	IShortestPathRepository shortestPathRepository;

    public String shortestPath(String sourceNode, String destinationNode) {
    	log.info("###### start :: shortestPath ########");
        List<Planet> Planet = (List<Planet>)planetRepository.findAll();
        List<Node> listNode = new ArrayList<>();
        Planet.forEach(s -> {
            Node node = new Node(s.getPlanetNode());
            listNode.add(node);
        });

        List<PlanetRoute> routes = (List<PlanetRoute>)planetRouteRepository.findAll();
        listNode.forEach(n -> {
            addDestination(n, listNode, routes);
        });

        Graph graph1 = new Graph();
        for (Node node : listNode) {
            graph1.addNode(node);
        }
        graph1 = DijkstraAlgorithm.calculateShortestPathFromSource(graph1, listNode.get(0));

        System.out.println("####### after graph1 #########");
        StringBuffer sb = new StringBuffer();
        for( Node node:graph1.getNodes()) {

            if(node.getName().equalsIgnoreCase(destinationNode)) {
                for(Node n: node.getShortestPath()) {
                    System.out.println(" getShortestPath ##### >>>>>>>>>>> "+n.getName());
                    sb.append(n.getName()).append("->");
                }
            }

        }
        

        for (Planet planetName : Planet) {
            ShortestPath shortestPath = new ShortestPath();
            for (Node node : graph1.getNodes()) {
                if (node.getName().equalsIgnoreCase(planetName.getPlanetNode())) {
                    shortestPath.setId(planetName.getId());
                    shortestPath.setPlanetNode(node.getName());
                    shortestPath.setPlanetName(planetName.getPlanetSourceName());
                    shortestPath.setPath(node.getPath());
                }

            }
            shortestPathRepository.save(shortestPath);
        }
        log.info("###### end :: shortestPath ########");
        return sb.append(destinationNode).toString();
    }

    private void addDestination(Node n, List<Node> listNode, List<PlanetRoute> routes) {
    	log.info("###### start :: addDestination ########");
        routes.forEach(r -> {
            if (r.getPlanetOrigin().equalsIgnoreCase(n.getName())) {
                listNode.forEach(l -> {
                    if (l.getName().equalsIgnoreCase(r.getPlanetDestination())) {
                        n.addDestination(l, r.getDistance().floatValue());
                    }
                });
            }
        });
        log.info("###### end :: addDestination ########");
    }
}
