import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
DATE: 5/27/20
USACO Training Pages: 6.1.2 Rectangular Barn
SCORE: 10/10
TASK: rectbarn
LANG: JAVA
 */
public class rectbarn {
    public static int[][] map = new int[3010][3010]; //Dynamic Programming Matrix
    public static int rows, columns, barriers;
    public static int[] left = new int[3010];
    public static int[] right = new int[3010];
    public static int[] h = new int[3010];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("rectbarn.in"));
        PrintWriter pw = new PrintWriter("rectbarn.out");
        h[0] = 0;
        int Max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken());
        columns = Integer.parseInt(st.nextToken()); //n
        barriers = Integer.parseInt(st.nextToken()); //m
        for (int i = 0; i < barriers; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); //x coordinate of barrier
            int y = Integer.parseInt(st.nextToken()); //y  coordinate of barrier
            map[x][y] = 1;
        }
        for (int i = 1; i <= rows; ++i) {
            left[i] = 0;
            right[i] = columns + 1;
            for (int j = 1; j <= columns; ++j) {
                if (map[i][j] == 1) {
                    //Longest free distance/row on the right side
                    right[i] = j;
                    break;
                }
            }
        }
        //System.out.println(Arrays.toString(left));
        //System.out.println(Arrays.toString(right));
        for (int j = 1; j <= columns; ++j) {
            int len_l = Integer.MAX_VALUE;
            int len_r = Integer.MAX_VALUE;
            for (int i = 1; i <= rows; ++i) {
                if (map[i][j] == 1) {
                    //If there is a barrier in this loc
                    left[i] = j; // <-- empty spaces
                    h[i] = 0;
                    right[i] = columns + 1; // --> empty spaces
                    len_l = len_r = Integer.MAX_VALUE;
                    for (int k = j + 1; k <= columns; ++k) {
                        if (map[i][k] == 1) {
                            //Update right
                            right[i] = k;
                            break;
                        }
                    }
                } else {
                    h[i] = h[i - 1] + 1;
                    len_l = Integer.min(len_l, j - left[i]);
                    len_r = Integer.min(len_r, right[i] - j);
                    int s = (len_l + len_r - 1) * h[i];
                    if (s > Max) {
                        Max = s;
                    }
                }
            }
        }
        pw.println(Max);
        System.out.println("Answer:" + Max);
        pw.close();
    }
}
