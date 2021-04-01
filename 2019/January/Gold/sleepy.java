import java.io.*;
import java.util.StringTokenizer;

public class sleepy {
    public static int MAXN = 100005;
    public static int[] T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("sleepy.out"));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        //Input array.
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        //The least efficient way would to be to just insert each element into the right position.
        int moves = N - 1;
        while (moves > 0 && (arr[moves - 1] < arr[moves])) {
            moves--;
        }


        T = new int[MAXN];
        pw.println(moves);
        for (int i = moves; i < N; i++) {
            increment(arr[i]);
        }

        for (int i = 0; i < moves; i++) {
            pw.print((moves - 1 - i) + getSum(arr[i]));
            if (i < moves - 1) {
                pw.print(' ');
            }
            increment(arr[i]);
        }
        pw.close();


    }



    public static void increment(int node) {
        for (node++; node < MAXN; node += (node & -node)) {
            T[node]++;
        }
    }

    public static int getSum(int node) {
        int sum = 0;
        for (node++; node > 0; node -= (node & -node)) {
            sum += T[node];
        }
        return sum;
    }

}
