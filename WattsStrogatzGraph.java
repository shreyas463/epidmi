package projectfi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import repast.simphony.space.continuous.NdPoint;

public class WattsStrogatzGraph {
    private int n; // number of nodes
    private int k; // average degree (assumed to be an even number)
    private double p; // rewiring probability
    private Map<Integer, Set<Integer>> adjacencyList; // To represent the graph
    private Map<Integer, NdPoint> nodePositions; // To store node positions

    public WattsStrogatzGraph(int n, int k, double p) {
        this.n = n;
        this.k = k;
        this.p = p;
        this.adjacencyList = new HashMap<>();
        this.nodePositions = new HashMap<>();
        generateGraph();
        assignNodePositions();
    }

    private void generateGraph() {
        // Initialize the graph with each node having an empty set of neighbors
        for (int i = 0; i < n; i++) {
            adjacencyList.put(i, new HashSet<>());
        }

        // Create a ring lattice (regular circular lattice)
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k / 2; j++) {
                int neighbor = (i + j) % n;
                adjacencyList.get(i).add(neighbor);
                adjacencyList.get(neighbor).add(i);
            }
        }

        // Rewire edges with probability p
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k / 2; j++) {
                if (random.nextDouble() < p) {
                    int neighbor = (i + j) % n;
                    int newNeighbor = random.nextInt(n);

                    // Avoid self-loops and duplicate edges
                    while (newNeighbor == i || adjacencyList.get(i).contains(newNeighbor)) {
                        newNeighbor = random.nextInt(n);
                    }

                    // Rewire the edge
                    adjacencyList.get(i).remove(neighbor);
                    adjacencyList.get(neighbor).remove(i);
                    adjacencyList.get(i).add(newNeighbor);
                    adjacencyList.get(newNeighbor).add(i);
                }
            }
        }
    }

    private void assignNodePositions() {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            double x = random.nextDouble() * 50; // Assuming 50 is the space boundary
            double y = random.nextDouble() * 50;
            nodePositions.put(i, new NdPoint(x, y));
        }
    }

    public Map<Integer, Set<Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    public NdPoint getNodePosition(int nodeId) {
        return nodePositions.get(nodeId);
    }

    // Additional methods to access and manipulate the graph...
}
