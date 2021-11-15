import java.io.*;
import java.util.StringTokenizer;

/*
TASK: USACO Gold Help Yourself
LINK: http://www.usaco.org/index.php?page=viewproblem2&cpid=810
DATE: 3/10
SCORE: 12/12
 */
public class Main {
    public static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("help.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("help.out")));
        int segments = Integer.parseInt(br.readLine());
        int[] start = new int[segments];
        int[] end = new int[segments];
        for (int i = 0; i < segments; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start[i] = Integer.parseInt(st.nextToken());
            end[i] = Integer.parseInt(st.nextToken());
        }
        int[] secPower = new int[segments];
        secPower[0] = 1;
        for (int i = 1; i < segments; i++) {
            secPower[i] = 2 * secPower[i - 1] % mod;
        }
        int[] prefixSum = new int[2 * segments + 1];
        for (int i = 0; i < segments; i++) {
            prefixSum[start[i]]++;
            prefixSum[end[i]]--;
        }

        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }
        int answer = 0;
        for (int i = 0; i < segments; i++) {

            answer = (answer + secPower[segments - 1 - prefixSum[start[i] - 1]]) % mod;
        }
        System.out.println(answer);
        pw.println(answer);
        pw.close();
    }
}
