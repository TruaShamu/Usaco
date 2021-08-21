/*
TASK: skidesign
LANG: JAVA
 */
import java.io.*;

public class skidesign {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
        int N = Integer.parseInt(br.readLine());
        //Read input.
        int[] height = new int[N];
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(br.readLine());
        }
        int total = Integer.MAX_VALUE;
        for (int i = 0; i <= (100 - 17); i++) {
            int cost = 0;
            int diff;
            for (int hill = 0; hill < N; hill++) {

                //Change all hill with height h < i to h = i.
                if (i > height[hill]) {
                    diff = i - height[hill];
                } else if (i < height[hill] - 17) {
                    //Shorten hill to h == i + 17
                    diff = height[hill] - i - 17;
                } else {
                    diff = 0;
                }
                cost += diff * diff;
            }
            total = Math.min(cost, total);
        }

        pw.println(total);
        pw.close();
    }
}
