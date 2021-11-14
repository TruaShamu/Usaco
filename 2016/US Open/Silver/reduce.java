import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("reduce.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("reduce.out"));
        int cows = Integer.parseInt(br.readLine());
        point[] pointArray = new point[cows];
        for (int i = 0; i < cows; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pointArray[i] = new point(x, y);
        }
        Arrays.sort(pointArray);
        for (int i = 0; i < 3; i++) {
            pointArray[i].canDelete = true;
        }
        for (int i = cows - 3; i < cows; i++) {
            pointArray[i].canDelete = true;
        }
        Arrays.sort(pointArray, new compareY());
        for (int i = 0; i < 3; i++) {
            pointArray[i].canDelete = true;
        }
        for (int i = cows - 3; i < cows; i++) {
            pointArray[i].canDelete = true;
        }
        //Add all delete-able points to a list
        ArrayList<Integer> removable = new ArrayList<Integer>();
        for (int i = 0; i < cows; i++) {
            if (pointArray[i].canDelete) {
                removable.add(i);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int mask = 0; mask < (1 << (removable.size())); mask++) {

            // Only care about subsets of size 3...
            if (Integer.bitCount(mask) != 3) continue;

            // Add these three bits.
            ArrayList<Integer> exclude = new ArrayList<Integer>();
            for (int i = 0; i < removable.size(); i++)
                if ((mask & (1 << i)) > 0)
                    exclude.add(removable.get(i));
            // We try excluding these three pts, update if it improves our answer.
            res = Math.min(res, area(pointArray, exclude));
        }
        pw.println(res);
        pw.close();


    }

    public static int area(point[] pts, ArrayList<Integer> exclude) {

        int minX = 100000, maxX = 0;
        int minY = 100000, maxY = 0;

        // Go through all pts but the excluded one.
        for (int i = 0; i < pts.length; i++) {
            if (exclude.contains(i)) continue;
            minX = Math.min(minX, pts[i].x);
            maxX = Math.max(maxX, pts[i].x);
            minY = Math.min(minY, pts[i].y);
            maxY = Math.max(maxY, pts[i].y);
        }

        // Here is our box.
        return (maxX - minX) * (maxY - minY);
    }

    public static void combinations(int n, int[] arr, List<int[]> list) {
        // Calculate the number of arrays we should create
        int numArrays = (int) Math.pow(arr.length, n);
        // Create each array
        for (int i = 0; i < numArrays; i++) {
            list.add(new int[n]);
        }
        // Fill up the arrays
        for (int j = 0; j < n; j++) {
            // This is the period with which this position changes, i.e.
            // a period of 5 means the value changes every 5th array
            int period = (int) Math.pow(arr.length, n - j - 1);
            for (int i = 0; i < numArrays; i++) {
                int[] current = list.get(i);
                // Get the correct item and set it
                int index = i / period % arr.length;
                current[j] = arr[index];
            }
        }
    }
}

class point implements Comparable<point> {
    public int x, y;
    public boolean canDelete;

    public point(int x, int y) {
        this.x = x;
        this.y = y;
        canDelete = false;
    }

    public int compareTo(point other) {
        return this.x - other.x;
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
                return Integer.compare(p1.x, p2.x);
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

class compareY implements Comparator<point> {
    public int compare(point a, point b) {
        return a.y - b.y;
    }
}





