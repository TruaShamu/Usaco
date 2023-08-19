import java.io.*;

/*
TASK: checker
LANG: JAVA
 */
public class checker {
    public static PrintWriter pw;
    public static int queens;
    public static int[] row; //row[i] = column of the queen in row i
    public static int cnt = 0;
    public static int[] colUsed;
    public static int[] upDiagonalUsed;
    public static int[] downDiagonalUsed;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("checker.in"));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("checker.out")));
        queens = Integer.parseInt(br.readLine());
        row = new int[queens];
        colUsed = new int[queens];
        upDiagonalUsed = new int[2 * queens];  // i+j
        downDiagonalUsed = new int[2 * queens];    // i-j+N
        if (queens == 6) {
            dfs(0);
            // 7 has 40 solutions, so it's safe to bruteforce
        } else {
            for (int j = 0; j < queens / 2; j++) {
                //Only have to do half of the operations, because flipping the solution around, you can get another sol
                row[0] = j;
                addMarkIJ(0, j);
                dfs(1);
                removeMarkIJ(0, j);
            }
            cnt *= 2;
            if (queens % 2 != 0) {    // when number of queens is odd
                //There is another one in the middle
                row[0] = queens / 2;
                addMarkIJ(0, queens / 2);
                dfs(1);
                removeMarkIJ(0, queens / 2);
            }
        }
        pw.println(cnt);
        pw.close();
        System.exit(0);


    }

    private static void dfs(int checkerNum) {
        if (checkerNum == queens) {
            cnt++;
            StringBuilder sb = new StringBuilder();
            if (cnt <= 3) {
                //Print out the answer
                for (int col : row) {
                    sb.append(col + 1);
                    sb.append(" ");
                }
                sb.deleteCharAt(sb.length() - 1);
                pw.println(sb.toString());
            }
        } else
            //Recurse
            for (int j = 0; j < queens; j++) {
                if (colUsed[j] == 0 && upDiagonalUsed[checkerNum + j] == 0 && downDiagonalUsed[checkerNum - j + queens] == 0) {
                    //If this can be placed in this cell
                    row[checkerNum] = j; // checkernum is placed in row j
                    addMarkIJ(checkerNum, j);
                    dfs(checkerNum + 1); //recurse for next
                    removeMarkIJ(checkerNum, j);
                }
            }
    }


    private static void removeMarkIJ(int i, int j) {
        colUsed[j]--;
        upDiagonalUsed[i + j]--;
        downDiagonalUsed[i - j + queens]--;
    }

    private static void addMarkIJ(int i, int j) {
        colUsed[j]++;
        upDiagonalUsed[i + j]++;
        downDiagonalUsed[i - j + queens]++;
    }

}


