import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static boolean[][] field;
    public static Queue<State> q;
    public static int dX, dY;
    public static int rows, columns;
    public static int[][] vis;
    public static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("gravity.in"));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("gravity.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken());
        columns = Integer.parseInt(st.nextToken());
        field = new boolean[rows][columns];
        vis = new int[rows][columns];
        int cX = 0;
        int cY = 0;
        dX = 0;
        dY = 0;
        for (int y = 0; y < rows; y++) {
            String inp = br.readLine();
            for (int x = 0; x < columns; x++) {
                char curChar = inp.charAt(x);
                if (curChar == '#') {
                    field[y][x] = false;//can't go here
                } else {
                    field[y][x] = true;//you can go here
                }
                if (curChar == 'C') {
                    cX = x;
                    cY = y;
                }
                if (curChar == 'D') {
                    dX = x;
                    dY = y;
                }

            }
        }

        State start = new State(cY, cX, true, 1);
        q = new LinkedList<>();
        q.add(fall(start));
        while (!q.isEmpty()) {
            State cur = q.peek();
            fill(cur);
            q.poll();
        }
        pw.println(-1);
        pw.close();


    }

    public static void end(State in) {//are we done?
        if (in.x == dX && in.y == dY) {
            System.out.println(in.dist - 1);
            pw.println(in.dist - 1);
            pw.close();
            System.exit(0);
        }
    }

    public static State fall(State in) {
        State cur = new State(in.y, in.x, in.gravity, in.dist);
        end(cur);
        if (cur.y < 0 || cur.x < 0 || cur.x > columns - 1 || cur.y > rows - 1) {
            cur.y = -1;
            return cur;
        }
        if (!field[cur.y][cur.x]) {
            cur.y = -1;
            return cur;
        }
        if (cur.gravity) {
            while (cur.y < rows - 1) {
                if (field[cur.y + 1][cur.x]) {
                    cur.y++;
                } else {
                    break;
                }
                end(cur);
            }
            if (cur.y >= rows - 1) {
                cur.y = -1;
            }
        } else {
            while (cur.y > 0) {
                if (field[cur.y - 1][cur.x]) {
                    cur.y--;
                } else {
                    break;
                }
                end(cur);
            }
            if (cur.y <= 0) {
                cur.y = -1;
            }
        }
        return cur;
    }

    public static void fill(State cur) {

        if (cur.y == -1 || cur.x == -1 || cur.y >= rows || cur.x >= columns) {
            return;
        }
        if (!field[cur.y][cur.x]) {
            return;
        }
        if (vis[cur.y][cur.x] != 0) {
            return;
        }
        vis[cur.y][cur.x] = cur.dist;
        end(cur);
        State next = new State(cur.y, cur.x, !cur.gravity, cur.dist + 1);
        q.add(fall(next));
        cur.x++;
        fill(fall(cur));
        cur.x -= 2;
        fill(fall(cur));
    }


}

class State implements Comparable<State> {
    public int x;
    public int y;
    public boolean gravity;
    public int dist;

    public State(int y, int x, boolean gravity, int dist) {
        this.x = x;
        this.y = y;
        this.gravity = gravity;
        this.dist = dist;
    }

    public int compareTo(State oState) {
        return this.x - oState.x;
    }

}
    
