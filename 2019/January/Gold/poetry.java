import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class poetry {
    public static int N, M, K;
    public static int MOD = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("poetry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        //@todo Read words.
        word[] words = new word[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            words[i] = new word(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }


        HashMap<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < M; i++) {
            char oChar = br.readLine().charAt(0);
            if (freq.containsKey(oChar)) {
                freq.put(oChar, freq.get(oChar) + 1);
            } else {
                freq.put(oChar, 1);
            }
        }
        System.out.println(freq);


        long[] dp = new long[K + 1];  //dp[x] = the number of ways to make a line with x syllables.
        long[] r = new long[K + 1]; //r[x] = the number of ways to form a full line that ends with rhyme scheme x

        dp[0] = 1L;

        for (int k = 0; k <= K; k++) {

            for (int j = 0; j < N; j++) {
                //Out of bounds
                if (words[j].len + k > K) {
                    continue;
                }
                // Reach end of line.
                if (words[j].len + k == K) {
                    r[words[j].group] = (r[words[j].group] + dp[k] + MOD) % MOD;
                }
                dp[words[j].len + k] = (dp[words[j].len + k] + dp[k] + MOD) % MOD;
            }
        }


        long answer = 1L;
        for (int curFreq : freq.values()) {
            long sum = 0L;


            for (int k = 0; k < r.length; k++) {
                if (r[k] == 0) {
                    continue;
                }
                sum = (sum + exp(r[k], curFreq) + MOD) % MOD;
            }

            answer = (answer * sum + MOD) % MOD;
        }

        pw.println(answer);
        pw.close();

    }

    public static long exp(long base, int power) {
        if (power == 0) {
            return 1;
        }
        if (power == 1) {
            return (base + MOD) % MOD;
        }
        long ans = exp(base, power / 2);
        ans = (ans * ans + MOD) % MOD;
        if (power % 2 == 1) {
            ans = (ans * base + MOD) % MOD;
        }
        return (ans + MOD) % MOD;
    }


    public static class word {
        int len;
        int group;

        public word(int len, int group) {
            this.len = len;
            this.group = group;
        }
    }


}
