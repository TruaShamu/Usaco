import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
/*
@todo
    1. Given we have the alphabet, the answer is 1 + # pairs(i,j) where i appears later in the alphabet than j.
    2. We can solve this problem with DP on subsets, where each bit represents whether the letter has been used in the alphabet yet.
    Steps:
    1. Record 1st appearance of each letter in the map.
    2. Keep a matrix[i][j] of the number of pairs i,j where i's appears before j.
    3. Do dp on subsets. DP[subset] = min(#unique letters, dp[Not using current] +
 */
public class uddered {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] inp = br.readLine().toCharArray();

        //Save first appearance of each letter.
        Map<Character, Integer> index = new HashMap<>();
        for (char letter : inp) {
            if (!index.containsKey(letter)) {
                index.put(letter, index.size());
            }
        }

        //unique[i][j]  #  of pairs i,j where i appears before j.
        int unique = index.size();
        int[][] adj = new int[unique][unique];
        for (int j = 1; j < inp.length; j++) {
            adj[index.get(inp[j - 1])][index.get(inp[j])]++;

        }

        //DP of subsets. (1 means available, 0 means non-available)
        int[] dp = new int[1 << unique];
        dp[0] = 1;
        //Loop through all subsets.
        for (int mask = 1; mask < (1 << unique); mask++) {
            dp[mask] = inp.length; //Worst case is N.
            for (int j = 0; j < unique; j++) {
                //If jth word is available.
                if ((mask & (1 << j)) != 0) {
                    //Use it up.
                    int sum = dp[mask ^ (1 << j)];
                    //For each k, if there is a pair j,k, add it's occurrence.
                    for (int k = 0; k < unique; ++k) {
                        if ((mask & (1 << k)) != 0) {
                            sum += adj[j][k];
                        }
                    }
                    dp[mask] = Math.min(dp[mask], sum);
                }
            }
        }
        System.out.println(dp[(1 << unique) - 1]);
    }
}
