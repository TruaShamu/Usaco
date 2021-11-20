import java.io.*;

public class cbarn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
        //Read input
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[2 * n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        //Double the array for looping
        for (int i = n; i < 2 * n; i++) {
            arr[i] = arr[i - n];
        }

        //prefix with smallest number of cows given length
        int min = arr[0], minI = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            sum--;
            if (sum < min) {
                min = sum;
                minI = i;
            }
        }

        // (more than one) at top, 0 at bottom
        int[] use = new int[n];
        for (int i = minI + 1; i <= minI + n; i++) {
            use[i - minI - 1] = arr[i];
        }


        long cost = 0;
        int last = n - 1;

        //go backwards
        for (int i = n - 1; i >= 0; i--) {


            // We have a cow here.
            if (use[i] != 0) {
                continue;
            }

            //"extra" cow.
            while (last >= i) {
                last--;
            }
            while (last >= 0 && use[last] == 0) {
                last--;
            }
            if (last == -1) {
                break;
            }

            // move cow, add cost
            use[last]--;
            use[i]++;
            cost = cost + (sq(last - i));
        }
        System.out.println(cost);
        pw.println(cost);
        pw.close();

    }

    public static long sq(int dist) {
        return dist * dist;
    }
}
