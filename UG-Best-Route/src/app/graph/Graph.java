package app.graph;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Set;


public class Graph {
    protected final HashMap<Node, ArrayList<Node>> GRAPH = new HashMap<>();
    protected final ArrayList<Edge> EDGES = new ArrayList<>();
    private int nodeSize = 0;

    public void addNode(Node node){
        if (!GRAPH.containsKey(node)){
            GRAPH.put(node, new ArrayList<>());
            nodeSize++;
        }
    }

    public void addEdge(Edge edge){
        addNode(edge.getDestination());
        addNode(edge.getSource());

        if(EDGES.contains(edge)) return;

        this.EDGES.add(edge);
        Edge newEdge = edge.clone();
        newEdge.setSource(edge.getDestination());
        newEdge.setDestination(edge.getSource());
        this.EDGES.add(newEdge);
        for(Node node : GRAPH.keySet()){
            if (node == edge.getSource()){
                GRAPH.get(node).add(edge.getDestination());
            }
        } 

        for(Node node : GRAPH.keySet()){
            if (node == edge.getDestination()){
                GRAPH.get(node).add(edge.getSource());
            }
        } 
    }

    public int calculateDistance(ArrayList<Node> nodes){
        int distance = 0;
        for(int i=0; i<nodes.size()-1; i++){
            Edge edge = getEdge(nodes.get(i), nodes.get(i+1));
            distance += edge.getDistance();
        }
        return distance;
    }

    public ArrayList<Edge> getDestinationEdges(Node source){
        ArrayList<Edge> destinations = new ArrayList<>();
        for (Edge edge: this.EDGES){
            if (edge.getSource() == source){
                 destinations.add(edge);
            }
        }
        return destinations;
    }

    public ArrayList<Node> getNeighbourNodes(Node source){
       return GRAPH.get(source);
    }

    public Edge getEdge(Node source, Node destination) {
       for (Edge edge: this.EDGES){
           if (edge.getSource() == source && edge.getDestination() == destination){
               return edge;
           }
       }
       return null;
    }

    public Node getNodeByName(String name){
        for(Node node : GRAPH.keySet()){
            if (node.getName().toLowerCase().equals(name.toLowerCase())){
                return node;
            }
        }
        return null;
    }

    public Set<Node> getNodes(){
        return this.GRAPH.keySet();
    }

    public int getNodeSize() {
        return nodeSize;
    }

    public void printGraph(){
        System.out.println("\n          GRAPH: ADJACENCY LIST                ");
        System.out.println("              PLACES ON CAMPUS                 \n");
        for (HashMap.Entry<Node, ArrayList<Node>> entry : GRAPH.entrySet()) {
            Node node = entry.getKey();
            ArrayList<Node> destinations = entry.getValue();
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            boolean emptyList = true;
            for (Node destinatnion : destinations){
                if(emptyList)
                builder.append(destinatnion.getName());
                else
                builder.append(", "+destinatnion.getName());
                emptyList = false;
            }
            builder.append("]");
            System.out.println(node.getName() + "-->" + builder.toString());
            System.out.println("");
        }
    }

    public void listPlaces(Node except){
        int index = 1;
        for(Node node : GRAPH.keySet()){
            if (node != except){
                System.out.println(index + ". " + node.getName());
            }
            index++;
        }
    }
    
    // Albert Adzakpa edited this part start
    public void printNodes() {
    	 int i = 1;
    	 for(Entry<Node, ArrayList<Node>> entry: GRAPH.entrySet()) {
    		 System.out.println(i + ". "+ entry.getKey().getName());
    		 i = i +1;
    	 }
    }
    public Object selectNode(int i) {
    	return GRAPH.keySet().toArray()[i];
    }
    
    public int getSize() {
    	return GRAPH.size();
    }
 // Albert Adzakpa edited this part end
}
