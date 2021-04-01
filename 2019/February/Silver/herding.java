import java.io.*;
import java.util.Arrays;

public class herding {
    public static int[] arr;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("herding.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("herding.out"));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        pw.println(min());
        pw.println(max());
        pw.close();
    }

    public static int min() {
        //The last element is not consecutive.
        if (arr[N - 2] - arr[0] == N - 2 && arr[N - 1] - arr[N - 2] > 2) return 2;
        //The first element is not consecutive.
        if (arr[N - 1] - arr[1] == N - 2 && arr[1] - arr[0] > 2) return 2;
        int j = 0, best = 0;
        for (int i = 0; i < N; i++) {
            while (j < N - 1 && (arr[j + 1] - arr[i]) <= N - 1) {
                j++;
            }
            best = Integer.max(best, j - i + 1);
        }
        return N - best;
    }


    public static int max() {
        //Use the larger of the two gaps (0,n-2) or (1,n-1) .
        return Math.max(arr[N - 2] - arr[0] - (N - 2), arr[N - 1] - arr[1] - (N - 2));
    }
}
