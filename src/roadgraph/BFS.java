package roadgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import geography.GeographicPoint;

public class BFS {
	public List<GeographicPoint> bfsSearch(GeographicPoint start, GeographicPoint goal,
			Consumer<GeographicPoint> nodeSearched, Map<GeographicPoint, MapModel> nodes){
		
		if (start == null || goal == null)
			throw new NullPointerException("Cannot find route from or to null node");
		
		if (nodes.get(start) == null) {
			System.err.println("Start node " + start + " does not exist");
			return null;
		}
		if (nodes.get(goal) == null) {
			System.err.println("End node " + goal + " does not exist");
			return null;
		}
		
		ArrayList<GeographicPoint> visited = new ArrayList<>();
		ArrayList<MapModel> current = new ArrayList<>();
		HashMap<MapModel,MapModel> parentMap = new HashMap<MapModel,MapModel>();
		ArrayList<GeographicPoint> queue = new ArrayList<GeographicPoint>();
		
		MapModel c;
		c = nodes.get(start);
		visited.add(c.getLocation());
		queue.add(c.getLocation());
		
		while (!queue.isEmpty()) {
			c = nodes.get(queue.remove(0));
			nodeSearched.accept(c.getLocation());
			if (c.getLocation() == goal) {
				break;
			} else {
				for (int i = 0; i < c.getNumberOfEdges(); i++) {
					GeographicPoint neighbourNode = c.getEdge().get(i).getTo();
					if (!visited.contains(neighbourNode)) {
						queue.add(neighbourNode);
						visited.add(neighbourNode);
						parentMap.put(nodes.get(neighbourNode), c);
					}
				}

			}
		}
		
		if (!c.getLocation().equals(goal)) {
			System.out.println("No path found from " +start+ " to " + goal);
			return null;
		}
		
		List<GeographicPoint> p =
				reconstructPath(parentMap, nodes.get(start), nodes.get(goal));
		return p;
	}
	
	private List<GeographicPoint>
	reconstructPath(HashMap<MapModel,MapModel> parentMap,
			MapModel start, MapModel goal)
	{
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		MapModel current = goal;

		while (!current.equals(start)) {
			path.addFirst(current.getLocation());
			current = parentMap.get(current);
		}
		path.addFirst(start.getLocation());
		return path;
	}
}
