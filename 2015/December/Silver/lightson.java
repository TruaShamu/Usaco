import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class lightson {
    public static PointList[][] switchGrid; //List of connectible switches
    public static boolean[][] lightOn; //Is the light on in this cell?
    public static boolean[][] visited;
    public static int[] dX = new int[]{1, 0, 0, -1}; //Directions
    public static int[] dY = new int[]{0, 1, -1, 0};
    public static int rows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lightson.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken());
        int switches = Integer.parseInt(st.nextToken());
        switchGrid = new PointList[rows][rows];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < rows; column++) {
                switchGrid[row][column] = new PointList();
            }
        }
        lightOn = new boolean[rows][rows];
        visited = new boolean[rows][rows];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < rows; column++) {
                lightOn[row][column] = false;
                visited[row][column] = false;
            }
        }
        lightOn[0][0] = true;


        //Record all switches
        for (int i = 0; i < switches; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            switchGrid[x][y].Points.add(new Point(a, b));
        }
        bfs(0, 0);
        int onLights = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < rows; column++) {
                if (lightOn[row][column]) {
                    onLights++;
                }
            }
        }
        System.out.println("ANSWER: " + onLights);
        pw.println(onLights);
        pw.close();


    }

    public static void bfs(int x, int y) {
        if (isVisited(x, y)) {
            return;
        }
        visited[x][y] = true;
        for (Point oPoint : switchGrid[x][y].Points) {
            //Turn on all the lights
            lightOn[oPoint.x][oPoint.y] = true;
            //Can we visit this new point?
            if (visitable(oPoint.x, oPoint.y)) {
                //System.out.println("");
                bfs(oPoint.x, oPoint.y);
            }
        }
        //Now, let's try to visit all the neighbors.
        for (int i = 0; i < dX.length; i++) {
            int neighborX = x + dX[i];
            int neighborY = y + dY[i];
            if (onGrid(neighborX, neighborY) && lightOn[neighborX][neighborY]) {
                bfs(neighborX, neighborY);
            }
        }

    }

    public static boolean onGrid(int x, int y) {
        if (x >= 0 && x < rows && y >= 0 && y < rows) {
            return true;
        }
        return false;
    }

    public static boolean isVisited(int x, int y) {
        if (!onGrid(x, y) || visited[x][y]) {
            return true;
        }
        return false;
    }

    public static boolean visitable(int x, int y) {
        //Is this cell (x, y) visitable?
        for (int i = 0; i < dX.length; i++) {
            int neighborX = x + dX[i];
            int neighborY = y + dY[i];
            if (onGrid(neighborX, neighborY) && isVisited(neighborX, neighborY) && lightOn[neighborX][neighborY]) {
                //The light is on, and the cell is reachable
                return true;
            }
        }
        return false;
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

    ArrayList<Point> Points = new ArrayList<Point>();

    public ArrayList<Point> GetPointss() {
        return this.Points;
    }

    PointList() {
    }

    public PointList(ArrayList<Point> oPoints) {
        this.Points = oPoints;
    }
}
