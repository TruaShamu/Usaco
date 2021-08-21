import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
TASK: shopping
LANG: JAVA
 */
public class shopping {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shopping.in"));
        PrintWriter pw = new PrintWriter("shopping.out");
        int offers = Integer.parseInt(br.readLine());
        int[][] deals = new int[offers + 6][6];
        int[] codes = new int[1000];
        int numCodes = 0;
        //Initialize dpArray
        int[][][][][] dp = new int[6][6][6][6][6];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for (int k = 0; k < dp[j].length; k++) {
                    for (int l = 0; l < dp[k].length; l++) {
                        Arrays.fill(dp[i][j][k][l], 100000);
                    }
                }
            }
        }
        dp[0][0][0][0][0] = 0;

        for (int i = 6; i < deals.length; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            //The special offers
            System.out.println(Arrays.toString(arr));
            int[] deal = new int[6];
            for (int j = 1; j < arr.length - 1; j += 2) {
                int productCode = arr[j];
                if (codes[productCode] == 0) {
                    codes[productCode] = ++numCodes;
                }
                deal[codes[arr[j]]] += arr[j + 1];
            }
            deal[0] = arr[arr.length - 1];
            deals[i] = deal;
        }
        System.out.println(Arrays.deepToString(deals));
        int B = Integer.parseInt(br.readLine());
        int[] amounts = new int[10000];
        for (int i = 0; i < B; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (codes[arr[0]] == 0) codes[arr[0]] = ++numCodes;
            int c = codes[arr[0]];
            amounts[c] = arr[1];
            int[] deal = new int[6];
            deal[c] = 1;
            deal[0] = arr[2];
            deals[c - 1] = deal;
        }
        br.close();

        for (int i = 0; i < deals.length; i++) {
            int[] d = deals[i];
            //System.out.println(Arrays.toString(d));
            for (int a1 = 0; a1 + d[1] <= amounts[1]; a1++) {
                for (int a2 = 0; a2 + d[2] <= amounts[2]; a2++) {
                    for (int a3 = 0; a3 + d[3] <= amounts[3]; a3++) {
                        for (int a4 = 0; a4 + d[4] <= amounts[4]; a4++) {
                            for (int a5 = 0; a5 + d[5] <= amounts[5]; a5++) {
                                //System.out.println(i+" "+a1+" "+a2+" "+a3+" "+a4+" "+a5);
                                int old = dp[a1][a2][a3][a4][a5];
                                int change = dp[a1 + d[1]][a2 + d[2]][a3 + d[3]][a4 + d[4]][a5 + d[5]];
                                if (old + d[0] < change) {
                                    dp[a1 + d[1]][a2 + d[2]][a3 + d[3]][a4 + d[4]][a5 + d[5]] = old + d[0];
                                }
                            }
                        }
                    }
                }
            }
        }
        pw.println(dp[amounts[1]][amounts[2]][amounts[3]][amounts[4]][amounts[5]]);
        pw.close();

    }
}
