package api;



import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;


public class DWGraph_DS implements directed_weighted_graph {
    private HashMap<Integer, node_data> graph ;
    private int MC;
    private HashMap<Integer,HashMap<Integer,edge_data>> edge  = new HashMap<>();
    private int nodeSize=0;
    private int edgeSize=0;


    public DWGraph_DS(){
        this.graph = new HashMap <Integer , node_data> ();

    }
    public DWGraph_DS(HashMap<Integer , node_data> graph){

        this.graph = graph;

    }



    @Override
    public node_data getNode(int key) {
        if(graph.containsKey(key)) {
            return graph.get(key);
        }
        return null ;
    }

    @Override
    public edge_data getEdge(int src, int dest) {
        if(!edge.containsKey(src)) return null;
        return edge.get(src).get(dest);
    }

    @Override
    public void addNode(node_data n) {
        int key = n.getKey();
        if(!graph.containsKey(key)) {
            graph.put(n.getKey(), n);
            MC++;
            nodeSize++;
        }

    }

    @Override
    public void connect(int src, int dest, double w) {
        if(w < 0 ) { throw new ExceptionInInitializerError("cant place negative weight");
        }else {

            if(this.getNode(src)==null || this.getNode(dest )==null) {
                System.out.println("Error one of the nodes is null ");

            }else {
                edge_data n = new EdgeData(src ,dest ,w);
                MC++;
                edgeSize++;
                if(this.getEdge(src, dest) == null) {
                    if(!edge.containsKey(src)) {
                        edge.put(src, new HashMap<Integer, edge_data>());
                        edge.get(src).put(dest, n);
                        if(getE(dest) != null ) {
                            Iterator<edge_data> it = edge.get(dest).values().iterator();
                            while(it.hasNext()) {
                                edge_data v = it.next();
                                edge.get(src).put(dest,v);

                            }
                        }else {
                            edge.get(src).put(dest,n);
                        }
                    }
                }
            }
        }
    }





    @Override
    public Collection<node_data> getV() {
        return graph.values();
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
        if(!edge.containsKey(node_id))return null;

        return edge.get(node_id).values();
    }

    @Override
    public node_data removeNode(int key) {
        if(!graph.containsKey(key)) return null;
        node_data m = new NodeData();
        m=getNode(key);
        graph.remove(key);
        nodeSize--;
        if(edge.containsKey(key)) {
            for(int n : graph.keySet()) {
                int newNode = n;
                if(getEdge(key, newNode)!=null) {
                    removeEdge(key, newNode);
                }
                if(getEdge(newNode, key)!=null) {
                    removeEdge(newNode, key);
                }
            }
        }

        return m;

    }

    @Override
    public edge_data removeEdge(int src, int dest) {
        if(getEdge(src, dest) == null) return null;
        MC++;
        edgeSize--;
        edge_data m = getEdge(src, dest);
        edge.get(src).remove(dest);

        return m;
    }

    @Override
    public int nodeSize() {
        return nodeSize;
    }

    @Override
    public int edgeSize() {
        return edgeSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DWGraph_DS that = (DWGraph_DS) o;
        return MC == that.MC &&
                nodeSize == that.nodeSize &&
                edgeSize == that.edgeSize &&
                Objects.equals(graph, that.graph) &&
                Objects.equals(edge, that.edge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graph, MC, edge, nodeSize, edgeSize);
    }

    @Override
    public int getMC() {
        return MC;
    }





}
