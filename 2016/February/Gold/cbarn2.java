import java.io.*;
import java.util.StringTokenizer;

public class cbarn2 {

    public static long res = 100000000000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cbarn2.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int open = Integer.parseInt(st.nextToken());
        //Read input
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = Integer.parseInt(br.readLine());
        }

        for (int start = 0; start < n; start++) {
            //
            int[] arr = new int[n];
            for (int i = start; i < start + n; i++) {
                arr[i - start] = vals[i % n];
            }

            //set up dp
            long[][] mat = new long[open][n];
            //one door open
            for (int i = 1; i < n; i++) {
                mat[0][i] = mat[0][i - 1] + i * arr[i];
            }


            for (int i = 1; i < open; i++) {
                for (int j = i + 1; j < n; j++) {

                    mat[i][j] = mat[i - 1][j];
                    int sum = 0, people = arr[j];
                    int k = j - 1;
                    //work backwards
                    while (k > 0 && sum < mat[i][j]) {
                        sum += people;
                        mat[i][j] = Math.min(mat[i][j], mat[i - 1][k - 1] + sum);
                        people += arr[k];
                        k--;
                    }
                }
            }
            res = Math.min(res, mat[open - 1][n - 1]);
            System.out.println(res);
        }
        pw.println(res);
        System.out.println("ANSWER: " + res);
        pw.close();
    }

}
