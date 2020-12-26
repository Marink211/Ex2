package api;


import java.util.Objects;

public class EdgeData implements edge_data {

    private int src;
    private int dest ;
    private double  weight;
    private String info ;
    private int tag;

    public  EdgeData() {
        src = 0;
        dest= 0;
        weight = 0;
        info = null ;
        tag= 0;
    }
    public EdgeData(int src , int dest , double weight , String info , int tag) {
        this.src = src ;
        this.dest = dest;
        this.weight = weight ;
        this.info = info ;
        this.tag = tag;
    }
    public EdgeData(int src, int dest ,double weight ) {
        this.src = src ;
        this.dest= dest ;
        this.weight = weight;
    }

    @Override
    public int getSrc() {
        return src;
    }

    @Override
    public int getDest() {
        return dest;

    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getInfo() {
        return info ;
    }

    @Override
    public void setInfo(String s) {
        this.info = s ;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeData edgeData = (EdgeData) o;
        return src == edgeData.src &&
                dest == edgeData.dest &&
                Double.compare(edgeData.weight, weight) == 0 &&
                tag == edgeData.tag &&
                Objects.equals(info, edgeData.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dest, weight, info, tag);
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
        
    }
}
