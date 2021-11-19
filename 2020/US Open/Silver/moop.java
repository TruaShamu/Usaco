import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class moop {
    public static PointList pList;
    public static int[] cId;
    public static int[] minl;
    public static int[] maxr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
        int N = Integer.parseInt(br.readLine());
        cId = new int[N];
        minl = new int[N];
        maxr = new int[N];
        pList = new PointList();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cId[i] = i;
            pList.Points.add(new Point(x, y));
        }
        pList.sortbylocation();
        minl[0] = pList.Points.get(cId[0]).y;
        for (int i = 1; i < N; i++) {
            minl[i] = Integer.min(minl[i - 1], pList.Points.get(cId[i]).y);
        }
        maxr[N - 1] = pList.Points.get(cId[N - 1]).y;

        for (int i = N - 2; i >= 0; i--) {
            maxr[i] = Integer.max(maxr[i + 1], pList.Points.get(cId[i]).y);
        }
        int ans = 1;
        for (int i = 0; i < N - 1; i++) {
            if (minl[i] > maxr[i + 1]) {
                ans++;
            }
        }
        System.out.println("=================");
        System.out.println(ans);
        pw.println(ans);
        pw.close();
    }
}

class Point {
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


}

class PointList {

    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<Point> ascLocation;

    // We initialize static variables inside a static block.
    //1. Interface
    static {
        ascLocation = new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.x != p2.x) {
                    return Integer.compare(p1.x, p2.x);
                } else {
                    return Integer.compare(p1.y, p2.y);
                }

            }
        };

    }

    //2.Property

    ArrayList<Point> Points = new ArrayList<Point>();

    //3. Method
    //a. Set /Get
    //b. General Function(Method>


    public ArrayList<Point> GetPointss() {
        return this.Points;
    }

    PointList() {

    }



    /*public Book[] getBooks() {
        return books;
    } */

    public void sortbylocation() {
        Collections.sort(Points, ascLocation);
    }


    public PointList(ArrayList<Point> oPoints) {
        this.Points = oPoints;
    }
}

