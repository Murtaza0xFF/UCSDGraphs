package roadgraph;

import java.util.ArrayList;

import geography.GeographicPoint;

public class MapModel {
	private GeographicPoint location;
	private ArrayList<MapRoad> edge;
	

	public ArrayList<MapRoad> getEdge() {
		return edge;
	}

	public void setEdge(MapRoad road) {
		this.edge.add(road);
	}

	public MapModel(GeographicPoint location){
		this.location = location;
		edge = new ArrayList<MapRoad>();
	}

	public GeographicPoint getLocation() {
		return location;
	}

	public void setLocation(GeographicPoint location) {
		this.location = location;
	}
	
	
	public int getNumberOfEdges() {
		return edge.size();
	}

	
}
