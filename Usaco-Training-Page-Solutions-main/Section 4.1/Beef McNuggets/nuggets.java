import java.io.*;
import java.util.HashSet;
import java.util.Set;

/*
TASK: nuggets
LANG: JAVA
 */
public class nuggets {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("nuggets.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));
        int N = Integer.parseInt(br.readLine());
        int[] values = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(br.readLine());
            sum += values[i];
        }
        int[] primesStart = new int[]{2, 3, 5};
        int[] primesRun = new int[]{2, 3};

        boolean done = false;
        for (int prime : primesStart) {
            if (done) {
                break;
            }
            int first = values[0] % prime;
            done = true;
            for (int value : values) {
                if (value % prime != first) {
                    done = false;
                    break;
                }
            }
        }
        int max = 0;
        if (!done) {
            Set<Integer> set = new HashSet<>();
            set.add(0);
            for (int i = 1; i <= 65024; i++) {
                for (int j : primesRun) {
                    if ((i % j == 0 && set.contains(i / j))) {
                        set.add(i);
                        break;
                    }
                }
                if (!set.contains(i)) {
                    for (int value : values) {
                        if (set.contains(i - value)) {
                            set.add(i);
                            break;
                        }
                    }
                }
                if (!set.contains(i)) {
                    max = i;
                }
            }
        }
        pw.println(max);
        pw.close();
    }
}
