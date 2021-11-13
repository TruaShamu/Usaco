import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int[][] grid;
    public static int totalCells;
    public static int[] dr = {0, -1, 0, 1};
    public static int[] dc = {1, 0, -1, 0};
    public static int N;
    static final int infinity = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("distant.in"));
        PrintWriter pw = new PrintWriter(new File("distant.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        totalCells = N * N;

        //Read input grid
        grid = new int[N][N];
        for (int row = 0; row < N; row++) {
            String inp = br.readLine();
            for (int column = 0; column < N; column++) {
                grid[row][column] = inp.charAt(column) == '(' ? 0 : 1;
            }
        }

        //distances[i][j] is distance between ith cell and jth cell
        int[][] distances = new int[totalCells][totalCells];
        for (int i = 0; i < totalCells; i++) {
            for (int j = 0; j < totalCells; j++) {
                distances[i][j] = infinity;
                if (i == j) {
                    distances[i][j] = 0;
                }
            }
        }
        //What is the index of the grid
        int[][] numbers = new int[totalCells][totalCells];
        int number = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                numbers[i][j] = number++;
            }
        }
        // BFS
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int dir = 0; dir < 4; dir++) {
                    int newR = r + dr[dir];
                    int newC = c + dc[dir];
                    if (inBounds(newR, newC)) {
                        if (grid[r][c] == grid[newR][newC]) {
                            distances[numbers[r][c]][numbers[newR][newC]] = A;
                        } else {
                            distances[numbers[r][c]][numbers[newR][newC]] = B;
                        }
                    }
                }
            }
        }


        for (int k = 0; k < totalCells; k++) {
            for (int i = 0; i < totalCells; i++) {
                for (int j = i + 1; j < totalCells; j++) {
                    if (distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        distances[j][i] = distances[i][j];
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < totalCells; i++) {
            for (int j = 0; j < totalCells; j++) {
                max = Math.max(distances[i][j], max);
            }
        }

        pw.println(max);
        pw.close();
    }

    public static boolean inBounds(int row, int column) {
        return (row >= 0 && column >= 0 && row < N && column < N);

    }
}
