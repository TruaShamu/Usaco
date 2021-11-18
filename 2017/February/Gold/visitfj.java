import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] cost;
    public static int rows, roadCost;
    public static boolean[][] visited;
    final public static int[] DX = {-3, -2, -2, -1, -1, 0, 0, 1, 1, 2, 2, 3, -1, 0, 0, 1}; //Loc array
    final public static int[] DY = {0, -1, 1, -2, 2, -3, 3, -2, 2, -1, 1, 0, 0, -1, 1, 0};
    final public static long NOSOL = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("visitfj.in"));
        PrintWriter pw = new PrintWriter("visitfj.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken());
        roadCost = Integer.parseInt(st.nextToken());
        cost = new int[rows][rows];
        visited = new boolean[rows][rows];
        //Set up cost array
        for (int i = 0; i < rows; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < rows; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //Read input finish
        edge[][] edgeArray = dfs(0, 0);
        long res = NOSOL;
        for (int row = rows - 3; row < rows; row++) {
            for (int column = rows - 3; column < rows; column++) {

                // Out of bounds.
                if (row + column < (2 * rows) - 4) {
                    continue;
                }
                if (row < 0 && column < 0) {
                    continue;
                }
                // Invalid
                if (edgeArray[row][column].w == NOSOL) {
                    continue;
                }
                int distanceFJ = 2 * rows - 2 - row - column;
                long curCost = edgeArray[row][column].w + roadCost * (distanceFJ);
                res = Math.min(res, curCost);
            }
        }
        pw.println(res);
        pw.close();

    }

    public static boolean onGrid(int row, int column) {
        //Is point in the grid
        if (row < 0 || row >= cost.length || column < 0 || column >= cost.length) {
            return false;
        }
        return true;
    }

    public static edge[][] dfs(int r, int c) {

        // Put in default distances.
        edge[][] list = new edge[rows][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                list[i][j] = new edge(rows * i + j, NOSOL);
            }
        }
        list[r][c] = new edge((rows * r) + c, 0);  //Store cell loc

        // Initialize priority queue
        PriorityQueue<edge> pq = new PriorityQueue<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                pq.add(list[i][j]);
            }
        }


        while (!pq.isEmpty()) {

            edge cur = pq.poll();
            int curX = cur.to / rows;
            int curY = cur.to % rows;

            for (int i = 0; i < DX.length; i++) {
                int nextX = curX + DX[i]; //Next visitable cell
                int nextY = curY + DY[i];

                if (!onGrid(nextX, nextY)) {
                    //Not reachable
                    continue;
                }
                int nextW = cost[nextX][nextY] + (3 * roadCost); //New cost

                // Update cost for this node.
                if (cur.w + nextW < list[nextX][nextY].w) {
                    pq.remove(list[nextX][nextY]);
                    list[nextX][nextY].w = cur.w + nextW;
                    pq.add(list[nextX][nextY]);
                }
            }
        }

        return list;
    }

}

class edge implements Comparable<edge> {

    public int to;
    public long w;

    public edge(int myV, long myW) {
        to = myV;
        w = myW;
    }

    public int compareTo(edge other) {
        if (this.w < other.w) return -1;
        if (this.w > other.w) return 1;
        return 0;
    }
}
