package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import gameClient.util.Point3D;

public class NodeData  implements  node_data{

	private String data ;
	private int key;
	private double weight;
	private String info ;
	private int tag;
	private Point3D p ;


	public NodeData () {
		data = null ;
		key = 0 ;
		weight =0;
		info = null;
		tag = 0;
	}
	public NodeData(String data,int key , double weight ,String info , int tag , Point3D p ){
		this.data = data;
		this.key= key;
		this.weight = weight;
		this.info = info;
		this.tag = tag;
		this.p=p;
	}
	public NodeData(String data , int key) {
		this.data = data;
		this.key =key;

	}
	public NodeData(int key,Point3D p ) {
		this.key = key;
		this.p = p;
	}
	public NodeData(int key) {
		this.key = key;
	}

	public NodeData(int key, double weight) {
		this.key = key;
		this.weight = weight; 
	}
	@Override
	public int getKey() {
		return key;

	}


	@Override
	public geo_location getLocation() {
		return this.p ;
	}

	@Override
	public void setLocation(geo_location p) {
		this.p = new Point3D(p.x(), p.y(),p.z());



	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public void setWeight(double w) {
		if (w < 0 ) {
			return;
		}
		this.weight = w ;

	}

	@Override
	public String getInfo() {
		return info;
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
	public void setTag(int t) {
		this.tag = t;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NodeData nodeData = (NodeData) o;
		return key == nodeData.key &&
				Double.compare(nodeData.weight, weight) == 0 &&
				tag == nodeData.tag &&
				Objects.equals(data, nodeData.data) &&
				Objects.equals(info, nodeData.info) &&
				Objects.equals(p, nodeData.p);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, key, weight, info, tag, p);
	}


}
