import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
TASK: stall4
LANG: JAVA
 */
public class stall4 {
    public static int stalls, cows;
    public static int[] Next = new int[400];
    public static boolean[][] favStall = new boolean[402][402];
    public static boolean[] visited = new boolean[400];


    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("stall4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        cows = Integer.parseInt(st.nextToken()); //n
        stalls = Integer.parseInt(st.nextToken()); //m
        //Read input
        for (int i = 1; i <= cows; i++) {
            st = new StringTokenizer(br.readLine());
            int numStal = Integer.parseInt(st.nextToken());
            System.out.println(numStal);
            for (int j = 1; j <= numStal; j++) {
                int number = Integer.parseInt(st.nextToken());
                favStall[i][number] = true;
            }
        }
        int tot = 0;
        for (int i = 1; i <= cows; i++) {
            for (int j = 1; j <= stalls; j++) {
                visited[j] = false;
            }
            if (check(i)) {
                tot++;
            }

        }
        long curTime = System.currentTimeMillis();
        System.out.println("TIME: " + (curTime - startTime));
        pw.println(tot);
        pw.close();
        System.out.println(Arrays.toString(Next));
    }

    public static boolean check(int cow) {
        //Can the cow find a match
        for (int stall = 1; stall <= stalls; stall++)
            if (favStall[cow][stall] && (!visited[stall])) {
                visited[stall] = true;
                if (Next[stall] == 0 || check(Next[stall])) {
                    Next[stall] = cow;
                    return true;
                }
            }
        return false;
    }
}
