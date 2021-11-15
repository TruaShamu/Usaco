import java.io.*;
import java.util.LinkedList;

public class Main {
    //Directions

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int rows;
    static boolean[][] blocked;
    public static int startX, startY;
    public static int endX, endY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cownav.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));

        rows = Integer.parseInt(br.readLine());
        startX = rows - 1;
        startY = 0;
        endX = 0;
        endY = rows - 1;
        blocked = new boolean[rows][rows]; //True if blocked, else false.
        //Make grid
        for (int row = 0; row < rows; row++) {
            String inp = br.readLine();
            for (int column = 0; column < rows; column++) {
                if (inp.charAt(column) == 'H') {
                    blocked[row][column] = true;
                }
            }
        }

        int[][][][][][] bfs = new int[rows][rows][4][rows][rows][4];
        bfs[startX][startY][0][startX][startY][1] = 1;

        LinkedList<State> queue = new LinkedList<>();
        queue.add(new State(startX, startY, 0, startX, startY, 1));

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            if (done(curr.ax, curr.ay) && done(curr.bx, curr.by)) {
                pw.println(bfs[curr.ax][curr.ay][curr.ad][curr.bx][curr.by][curr.bd] - 1);
                break;
            }
            for (State next : curr.nextMoves()) {
                if (bfs[next.ax][next.ay][next.ad][next.bx][next.by][next.bd] == 0) {
                    bfs[next.ax][next.ay][next.ad][next.bx][next.by][next.bd] = bfs[curr.ax][curr.ay][curr.ad][curr.bx][curr.by][curr.bd] + 1;
                    queue.add(next);
                }
            }
        }
        pw.close();

    }

    public static boolean done(int x, int y) {
        if (x == 0 && y == rows - 1) {
            return true;
        }
        return false;
    }

    static class State {
        int ax, ay, ad;
        int bx, by, bd;

        public State(int ax, int ay, int ad, int bx, int by, int bd) {
            this.ax = ax;
            this.ay = ay;
            this.ad = ad;
            this.bx = bx;
            this.by = by;
            this.bd = bd;
        }

        public State[] nextMoves() {
            return new State[]{
                    turnLeft(),
                    turnRight(),
                    goForward(),
            };
        }

        private State turnLeft() {
            //Turn left
            return new State(ax, ay, (ad + 3) % 4, bx, by, (bd + 3) % 4);
        }

        private State turnRight() {
            //Turn right
            return new State(ax, ay, (ad + 1) % 4, bx, by, (bd + 1) % 4);
        }

        private State goForward() {
            //Go forward
            int nax = ax;
            int nay = ay;
            if (nax != 0 || nay != rows - 1) {
                if (nax + dx[ad] >= 0 && nax + dx[ad] < rows) {
                    //Is the new x value valid
                    nax += dx[ad];
                }
                if (nay + dy[ad] >= 0 && nay + dy[ad] < rows) {
                    //Is the new y value valid
                    nay += dy[ad];
                }
                if (blocked[nax][nay]) {
                    nax = ax;
                    nay = ay;
                }
            }
            int nbx = bx;
            int nby = by;
            if (nbx != 0 || nby != rows - 1) {
                if (nbx + dx[bd] >= 0 && nbx + dx[bd] < rows) {
                    //Is the new x value valid
                    nbx += dx[bd];
                }
                if (nby + dy[bd] >= 0 && nby + dy[bd] < rows) {
                    //Is the new y value valid
                    nby += dy[bd];
                }
                if (blocked[nbx][nby]) {
                    nbx = bx;
                    nby = by;
                }
            }
            return new State(nax, nay, ad, nbx, nby, bd);

        }

    }

}

/* 1. The indices for our matrix is different from the indices in the problem
        According to the problem, we would start at (1,1) and end at (N, N), but on an array this would be actually going from (N,0) to (0,N).
        So, we use global variables startX, startY, endX, endY.

        2. UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3
        We are going clockwise.

        3. What we are basically doing is doing a bfs to exhaust all possible paths, and then printing the answer. Since we are starting from 1,1, we do not need to check which one uses the least steps:
        whichever path finishes first is the shortest path and all other paths can be ignored. bfs[x1][y1][d1][x2][y2][d2] stores the number of steps used.

        4. turn left is basically a function to just turn the current direction left
        LEFT => DOWN => RIGHT => UP => LEFT....

        5. same for turn right, just the other way around.
        UP => RIGHT => DOWN => LEFT => UP..

        6. Go forward.
        For each bessie (the one going up and the one going right) check the next location it will reach given it's current direction.
        If that location is not out of bounds or already visited, then go there.

*/
