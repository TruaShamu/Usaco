import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class exercise {
    public static int MAXP = 1234;
    public static int MAXN = 10005;
    public static long[][] res = new long[MAXP][MAXN];
    public static int N, M;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("exercise.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("exercise.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[] composite = new boolean[N + 1];
        ArrayList<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= N; i++) {
            if (!composite[i]) {
                primes.add(i);
                for (int j = (2 * i); j <= N; j += i) {
                    composite[j] = true;
                }
            }
        }

        
        if (primes.size() == 0) {
            System.out.println(1);
        }

        for (int j = 0; j <= N; j++) {
            res[0][j] = 1;
        }

        for (int i = 1; i <= primes.size(); i++) {
            for (int j = 0; j <= N; j++) {
                res[i][j] = res[i - 1][j];

                int primePower = primes.get(i - 1);
                while (primePower <= j) {
                    res[i][j] = add(res[i][j], mul(primePower, res[i - 1][j - primePower]));
                    primePower *= primes.get(i - 1);
                }
            }
        }


    }

    public static long mul(long x, long y) {
        return (x * y) % M;
    }

    public static long add(long x, long y) {
        x += y;
        if (x >= M) {
            x -= M;
        }
        return x;
    }

    public static long sub(long x, long y) {
        x -= y;
        if (x < 0) {
            x += M;
        }
        return x;
    }
}
