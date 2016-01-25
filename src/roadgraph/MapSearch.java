package roadgraph;

import geography.GeographicPoint;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class MapSearch {
    protected Map<MapModel, MapModel> parents;

    // Build parents GeographicalPoint list
    protected List<GeographicPoint> buildGeoPath(MapModel goal) {

        MapModel parent = parents.get(goal);
        LinkedList<GeographicPoint> results = new LinkedList<>();
        results.add(goal.getLocation());

        while (parent != null) {
            results.addFirst(parent.getLocation());
            parent = parents.get(parent);
        }

        return results;
    }
}