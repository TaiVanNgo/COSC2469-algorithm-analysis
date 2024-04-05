package test1_sample.attmp_2nd;

public class JunctionMap {
    int[][] map;

    JunctionMap(int[][] map) {
        this.map = map;
    }

    // The complexity of this method is O(N^2)
    boolean hasOneWayStreet() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (x != y && map[x][y] == 1 && map[y][x] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean hasDeadEnd() {

        for (int x = 0; x < map.length; x++) {
            boolean isDeadEnd = true;

            for (int y = 0; y < map[x].length; y++) {
                if (x != y && map[x][y] == 1) {
                    isDeadEnd = false;
                }
            }

            if (isDeadEnd) {
                return isDeadEnd;
            }
        }

        return false;
    }

    String getShortPath(int source, int destination) {
        if(map[source][destination] == 1){//if we can access directly
            return source + " -> " + destination;
        }

        //if we can not
        //use the another path
        String path = "";
        return path = getShortPath(source + 1, destination);
    }

}
