package api;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;


public class DWGraph_Algo implements dw_graph_algorithms {

    private directed_weighted_graph graph = new DWGraph_DS();



    public DWGraph_Algo() {

    }
    public DWGraph_Algo(directed_weighted_graph graph) {
        this.graph = graph;
    }


    @Override
    public void init(directed_weighted_graph g) {
        this.graph =g ;

    }

    @Override
    public directed_weighted_graph getGraph() {
        // TODO Auto-generated method stub
        return this.graph;
    }

    @Override
    public directed_weighted_graph copy() {
        directed_weighted_graph copyG = new DWGraph_DS();
        Collection <node_data> col = this.graph.getV();
        Iterator<node_data> it = col.iterator();
        //copy all the nodes 
        while(it.hasNext()) {
            copyG.addNode(it.next());
        }
        Iterator<node_data> m = col.iterator();
        //copy all nodes ni 
        while(m.hasNext()) {
            node_data m1 = m.next();
            if(this.graph.getE(m1.getKey())!=null) {
                Iterator<edge_data> ni = this.graph.getE(m1.getKey()).iterator();
                while(ni.hasNext()) {
                    edge_data ni2 = ni.next();
                    copyG.connect(m1.getKey(),ni2.getDest() ,ni2.getWeight());
                }
            }
        }

        return copyG;
    }

    @Override
    public boolean isConnected() {
        Boolean[] connect = new Boolean[this.graph.nodeSize()];

        DFS((DWGraph_DS) this.graph, 1, connect);
        for (int i = 0; i < connect.length; i++) {
            if (!connect[i]) return false;
        }
        DWGraph_DS tGraph = transpose();
        for (int i = 0; i < connect.length; i++) {
            connect[i] = false;
        }

        DFS(tGraph, 1, connect);
        for (int i = 0; i < connect.length; i++) {
            if (!connect[i]) return false;
        }

        return true;
    }



    @Override
    public double shortestPathDist(int src, int dest) {
        if(this.graph.getEdge(src, dest) == null ) {
            return -1;
        }
        if(this.graph.getNode(src) == null || this.graph.getNode(dest) == null ) {
            return -1 ;
        }
        if(this.graph.edgeSize() == 1 ) {
            return this.graph.getEdge(src, dest).getWeight();
        }
        node_data start = this.graph.getNode(src);
        start.setWeight(0);
        start.setInfo("N");

        Collection<node_data> nodes = this.graph.getV();
        PriorityQueue<node_data> gN = new PriorityQueue<>();

        for (node_data g : nodes) {
            if (g.getKey() != src) {
                g.setWeight(Double.MAX_VALUE);
                g.setInfo("n");
                g.setTag(-1);
            }
        }

        gN.add(start);

        while (!gN.isEmpty()) {
            node_data n = gN.remove();
            n.setInfo("v");
            Collection<edge_data> e = this.graph.getE(n.getKey());

            if (e != null) {
                for (edge_data gE : e) {
                    node_data allN = this.graph.getNode(gE.getDest());

                    if (n.getWeight() + gE.getWeight() < allN.getWeight()) {
                        allN.setWeight(n.getWeight() + gE.getWeight());
                        allN.setTag(n.getKey());

                        gN.add(allN);
                    }
                }

            }

        }
        return this.graph.getNode(dest).getWeight();
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        if(this.graph.getEdge(src, dest ) == null) {
            return null;
        }
        if(this.graph.getNode(src) == null || this.graph.getNode(dest) == null ) {
            return null ;
        }
        if (this.shortestPathDist(src, dest) == Double.MAX_VALUE)
            return null;

        LinkedList<node_data> nodes = new LinkedList<>();
        node_data n = this.graph.getNode(dest);

        while (n.getKey() != src) {
            nodes.add(n);
            n = this.graph.getNode(n.getTag());
        }

        nodes.add(n);
        LinkedList<node_data> all = new LinkedList<>();
        int i = 1;

        while (i <= nodes.size()) {
            all.add(nodes.get(nodes.size() - i));
            i++;
        }

        return all;



    }


    @Override
    public boolean save(String file) {
        if(graph.nodeSize() == 0 )return false;

        JsonArray node = new JsonArray();
        JsonArray edge = new JsonArray();
        Iterator<node_data> it = this.graph.getV().iterator();
        while(it.hasNext()){
            node_data m = it.next();
            JsonObject gson = new JsonObject();
            try {
                gson.addProperty("Key ID :" , m.getKey());
                gson.addProperty(" pos : ", m.getLocation().toString());
                gson.addProperty(" weight" , m.getWeight());
                node.add(gson);
                gson= new JsonObject();
                Iterator<edge_data> oth = graph.getE(m.getKey()).iterator();
                while(oth.hasNext()) {
                    edge_data k = oth.next();

                    gson.addProperty("src :" , k.getSrc());

                    gson.addProperty("dest : ", k.getDest());

                    gson.addProperty("weight :", k.getWeight());

                    edge.add(gson);
                    gson= new JsonObject();
                }
            }
            catch(JsonIOException e) {
                e.printStackTrace();
                return false;
            }
        }
        JSONObject all = new JSONObject();
        try {
            all.put("Nodes : " ,node);
            all.put("Egdes : " ,edge);

        } catch (JSONException e) {
            e.printStackTrace();
            return false;

        }

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(all.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;

        }
    }




    @Override
    public boolean load(String file) {
        try {
            try {

                FileReader reader = new FileReader(file);
                DWGraph_DS newG = new DWGraph_DS();
                JSONTokener n = new JSONTokener(reader);
                JSONObject curr = new JSONObject();

                curr.put("Graph", n.nextValue());
                JSONObject json_object = (JSONObject) curr.get("Graph");

                JSONArray edge = (JSONArray) json_object.get("Edges");
                JSONArray node = (JSONArray) json_object.get("Nodes");
                for(int i = 0 ; i < node.length() ; i++ ) {
                    node_data m = new NodeData(node.getJSONObject(i).getInt("id"));
                    m.setWeight(node.getJSONObject(i).getDouble("weigth"));
                    m.setTag(node.getJSONObject(i).getInt("tag"));
                    double x  = node.getJSONObject(i).getDouble("x");
                    double y  = node.getJSONObject(i).getDouble("y");
                    double z  = node.getJSONObject(i).getDouble("z");
                    geo_location l = new GeoLocation(x,y,z);
                    m.setLocation(l);
                    m.setInfo(node.getJSONObject(i).getString("info"));
                    m.setTag(node.getJSONObject(i).getInt("tag"));
                    newG.addNode(m);
                }
                for (int i = 0 ; i < edge.length() ; i++ ) {
                    int dest = edge.getJSONObject(i).getInt("dest");
                    int src = edge.getJSONObject(i).getInt("src");
                    int weight = edge.getJSONObject(i).getInt("weight");
                    newG.connect(src, dest, weight);
                    edge_data e = new EdgeData();

                }
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return true;
            }catch (FileNotFoundException e ) {
                e.printStackTrace();
                return false;
            }
        }catch (JSONException e) {
            e.printStackTrace();
            return false;
        }


    }

    private DWGraph_DS transpose() {
        DWGraph_DS g = new DWGraph_DS();
        Iterator<node_data> it = this.graph.getV().iterator();
        while(it.hasNext()){
            node_data m = it.next();
            Iterator<edge_data> oth = this.graph.getE(m.getKey()).iterator();
            while(oth.hasNext()) {
                edge_data k = oth.next();
                g.connect(k.getDest(),m.getKey() , k.getWeight());
            }
        }

        return g;
    }

    private void DFS(DWGraph_DS g, int node, Boolean[] connect) {
        if (connect.length < 1)
            return;
        connect[node - 1] = true;
        if (g.getE(node) != null) {
            Collection<edge_data> it = g.getE(node);
            Iterator<edge_data> oth = it.iterator();

            while (oth.hasNext()) {
                edge_data ed = oth.next();
                int des = ed.getDest();

                if (!connect[des - 1])
                    DFS(g,des, connect);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DWGraph_Algo that = (DWGraph_Algo) o;
        return Objects.equals(graph, that.graph);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graph);
    }
}
