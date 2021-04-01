import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class haircut {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("haircut.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("haircut.out"));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        //Input
        int[] vals = new int[N];
        for (int i = 0; i < N; i++) {
            vals[i] = Integer.parseInt(st.nextToken());
        }

        //Index
        ArrayList<Integer>[] idx = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            idx[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            idx[vals[i]].add(i);
        }

        bit myBit = new bit(N);
        long[] res = new long[N];
        long sum = 0;

        for (int i = 0; i < N; i++) {
            res[i] = sum;
            for (Integer x : idx[i]) {
                sum += (x - myBit.sum(x));
                myBit.add(x + 1, 1);
            }
        }

        //Output
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < N - 1; i++) {
            sb.append(res[i] + "\n");
        }
        sb.append(res[N - 1]);

        pw.println(sb.toString());
        pw.close();
    }


}

class bit {

    public int n;
    public int[] vals;

    public bit(int orign) {

        // Set i to be the first power of 2 >= n+1.
        n = 1;
        while (n < orign + 1) n <<= 1;

        // Alloc space.
        vals = new int[n];
    }

    // Adds x to index idx of array.
    public void add(int idx, int x) {
        while (idx < n) {
            vals[idx] += x;
            idx += (idx & (-idx));
        }
    }

    // Returns sum of array[1..x]
    public int sum(int x) {
        int res = 0;
        while (x > 0) {
            res += vals[x];
            x -= (x & (-x));
        }
        return res;
    }
}
