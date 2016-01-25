package roadgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import geography.GeographicPoint;

public class BFS extends MapSearch{
	public List<GeographicPoint> bfsSearch(GeographicPoint start, GeographicPoint goal,
			Consumer<GeographicPoint> nodeSearched, Map<GeographicPoint, MapModel> nodes){
		
		ArrayList<GeographicPoint> visited = new ArrayList<>();
		ArrayList<MapModel> current = new ArrayList<>();
		ArrayList<GeographicPoint> queue = new ArrayList<GeographicPoint>();
		parents = new HashMap<>();
		
		MapModel c;
		c = nodes.get(start);
		visited.add(c.getLocation());
		queue.add(c.getLocation());
		parents.put(nodes.get(start), null);
		
		while (!queue.isEmpty()) {
			c = nodes.get(queue.remove(0));
			nodeSearched.accept(c.getLocation());
			if (c.getLocation() == goal) {
				
				return buildGeoPath(c);
			} else {
				for (int i = 0; i < c.getNumberOfEdges(); i++) {
					GeographicPoint neighbourNode = c.getEdge().get(i).getTo();
					if (!visited.contains(neighbourNode)) {
						queue.add(neighbourNode);
						visited.add(neighbourNode);
						parents.put(nodes.get(neighbourNode), c);
					}
				}

			}
		}
		return null;
	}
}
