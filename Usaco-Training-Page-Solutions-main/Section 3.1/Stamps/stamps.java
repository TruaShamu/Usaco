import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
LANG: JAVA
TASK: stamps
*/
public class stamps {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("stamps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxStamps = Integer.parseInt(st.nextToken());
        int stampValues = Integer.parseInt(st.nextToken());
        //Read stamp values
        int[] coins = new int[stampValues];
        String line;
        int counter = 0;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                coins[counter] = Integer.parseInt(st.nextToken());
                counter++;
            }
        }
        Arrays.sort(coins);
        int maxValues = maxStamps * coins[stampValues - 1] + 1;
        int[] minCoins = new int[maxValues];
        Arrays.fill(minCoins, Integer.MAX_VALUE); //Minimum number of coins to solve this amount
        minCoins[0] = 0;
        //System.out.println(Arrays.toString(coins));
        boolean finished = false;
        while (!finished) {
            finished = true;
            //for each coin
            for (int i = 0; i < stampValues; i++) {
                for (int j = 0; j + coins[i] < minCoins.length; j++) {
                    int newer = minCoins[j] + 1;
                    int old = minCoins[j + coins[i]];
                    //System.out.println("NEWER: " + newer + " OLD: " + old);
                    if (newer < old) {
                        finished = false;
                        minCoins[j + coins[i]] = newer;
                    }
                }
                //System.out.println(Arrays.toString(minCoins));
            }
        }

        int i;
        for (i = 0; i < minCoins.length; i++) {
            if (minCoins[i] > maxStamps) {
                break;
            }
        }
        pw.println(i - 1);
        pw.close();


    }


}
