import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[][] inputGrid;
    public static int dr[] = {-1, 0, 1, 0};
    public static int dc[] = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dream.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());
        inputGrid = new int[rows][columns];

        for (int row = 0; row < rows; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < columns; column++) {
                inputGrid[row][column] = Integer.parseInt(st.nextToken());
            }
        }
       /* for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                System.out.print(inputGrid[row][column] + " ");
            }
            System.out.println();
        }

        */
        //Works
        Queue<Integer> queue = new LinkedList<>();
        int s = new state(0, 0, -1, false).pack();
        int[] vector = new int[10000000];
        Arrays.fill(vector, -1);
        vector[s] = 0;
        queue.add(s);
        // System.exit(12);
        while (!queue.isEmpty()) {
            state curState = state.unpack(queue.peek());
            queue.remove();
            if (curState.row == rows - 1 && curState.column == columns - 1) {
                //We have reached the end.
                //System.out.println(" Reached the end");
                pw.println(vector[curState.pack()]);
                pw.close();
                break;
            }
            if (inputGrid[curState.row][curState.column] == 4 && curState.slideDir != -1) {
                //System.out.println("Sliding");
                //Sliding
                int newColor = getcellColor(curState.row + dr[curState.slideDir], curState.column + dc[curState.slideDir]);
                if (newColor != 0 && newColor != 3) {
                    //Is it passable
                    state nextState = new state(curState.row + dr[curState.slideDir], curState.column + dc[curState.slideDir], curState.slideDir, newColor == 2);
                    if (vector[nextState.pack()] != -1) {
                        continue;
                    }
                    vector[nextState.pack()] = vector[curState.pack()] + 1;
                    queue.add(nextState.pack());
                    continue;
                }
            }
            for (int i = 0; i < 4; i++) {
                //Visit neighbors
                // System.out.println(" Visiting neighbors");
                int newColor = getcellColor(curState.row + dr[i], curState.column + dc[i]);
                if (newColor == 0 || (newColor == 3 && !curState.orange)) {
                    //Impassable
                    // System.out.println("impassable");
                    continue;
                }
                /*
                 state nst = state(st.r + dr[i], st.c + dc[i], i,
                        col == 2 ? true : col == 4 ? false : st.smell);
                 */
                state nextState = new state(curState.row + dr[i], curState.column + dc[i], i, newColor == 2 ? true : newColor == 4 ? false : curState.orange);
                if (vector[nextState.pack()] != -1) {
                    continue;
                }
                vector[nextState.pack()] = vector[curState.pack()] + 1;
                queue.add(nextState.pack());
            }

        }
        pw.println(-1);
        pw.close();

    }

    public static int getcellColor(int row, int column) {
        if (row < 0 || row >= inputGrid.length || column < 0 || column >= inputGrid[row].length) {
            //Out of bounds
            return 0;
        }
        return inputGrid[row][column];
    }
}

class state {
    int row, column;
    int slideDir;
    boolean orange;

    public state(int row, int column, int slideDir, boolean orange) {
        this.row = row;
        this.column = column;
        this.slideDir = slideDir;
        this.orange = orange;
    }

    int pack() {
        return (orange ? 1 : 0) + 2 * (slideDir + 1) + 10 * column + 10000 * row;
    }

    static state unpack(int x) {
        state s;
        if ((x & 1) == 1) {
            s = new state(x / 10000, (x / 10) % 1000, (x / 2) % 5 - 1, true);
        } else {
            s = new state(x / 10000, (x / 10) % 1000, (x / 2) % 5 - 1, false);
        }
        return s;
    }


}


