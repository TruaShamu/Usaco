import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
        int points = Integer.parseInt(br.readLine());
        int[][] accessibleArray = new int[points][points];
        pointList pList = new pointList();
        //READ INPUT
        for (int i = 0; i < points; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int power = Integer.parseInt(st.nextToken());
            pList.points.add(new point(x, y, power, i));
        }
        //MAKE EDGES
        for (int i = 0; i < pList.points.size(); i++) {
            for (int j = 0; j < pList.points.size(); j++) {

                if (canReach(pList.points.get(i), pList.points.get(j))) {
                    accessibleArray[i][j] = 1;
                } else {
                    accessibleArray[i][j] = -1;
                }

            }
        }
        //NOW LOOP
        int ret = 1;
        for (int i = 0; i < points; i++) {
            boolean[] canHear = new boolean[points];
            ret = Math.max(ret, dfs(i, canHear, accessibleArray));
        }
        pw.println(ret);
        pw.close();


    }

    public static int dfs(int curr, boolean[] canHear, int[][] canTransmit) {
        if (canHear[curr]) {
            return 0;
        }
        canHear[curr] = true;
        int ret = 1;
        for (int i = 0; i < canTransmit[curr].length; i++) {
            if (canTransmit[curr][i] == 1) {
                ret += dfs(i, canHear, canTransmit);
            }
        }
        return ret;
    }


    public static boolean canReach(point p1, point p2) {
        //Can point 1 reach point 2?
        int xDis = Math.abs(p1.x - p2.x);
        int yDis = Math.abs(p1.y - p2.y);
        long distance;
        distance = (long) (Math.pow(xDis, 2) + Math.pow(yDis, 2));
        if (distance <= (p1.power * p1.power)) {
            return true;
        }
        return false;
    }
}

class point {
    public int x, y, power, id;

    public point(int x, int y, int power, int id) {
        this.x = x;
        this.y = y;
        this.power = power;
        this.id = id;
    }


}

class pointList {

    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<point> ascLocation;

    // We initialize static variables inside a static block.
    //1. Interface
    static {
        ascLocation = new Comparator<point>() {
            @Override
            public int compare(point p1, point p2) {
                return Integer.compare(p1.id, p2.id);
            }
        };

    }

    //2.Property

    ArrayList<point> points = new ArrayList<point>();

    //3. Method
    //a. Set /Get
    //b. General Function(Method>


    public ArrayList<point> Getpointss() {
        return this.points;
    }

    pointList() {

    }



    /*public Book[] getBooks() {
        return books;
    } */

    public void sortbylocation() {
        Collections.sort(points, ascLocation);
    }


    public pointList(ArrayList<point> opoints) {
        this.points = opoints;
    }
}





/*
    @Test
    public void sortBooks() {
        point[] points = {
                new point(0, 100, "S"),
                new point(1, 100, "E")
        };

        // 1. sort using Comparable
        Arrays.sort(points);
        System.out.println(Arrays.asList(Points));

        // 2. sort using comparator: sort by id
        Arrays.sort(Points, new Comparator<Points>() {
            @Override
            public int compare(Point o1, Point o2) {
                return (o1.Location - o2.Location);
            }
        });
        System.out.println(Arrays.asList(Points));
    }
*/
