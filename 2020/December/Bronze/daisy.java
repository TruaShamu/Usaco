import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class daisy {
    public static double[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new double[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                //System.out.println("i: " + i + " j: " + j);
                //System.out.println("AVERAGE: " + avg(i, j));
                answer += count(i, j, avg(i, j));
                //System.out.println();
            }
        }

        System.out.println(answer);
    }

    public static int count(int i, double j, double petals) {
        if ((int) petals != petals) {
            //System.out.println("decimal");
            return 0;
        }
        int ret = 0;
        for (int a = i; a <= j; a++) {
            if (arr[a] == petals) {
                ret++;
            }
        }
        if (ret > 0) {
            return 1;
        }
        return 0;
    }

    public static double avg(int i, int j) {
        double sum = 0;
        if (i == j) {
            return arr[i];
        }
        for (int a = i; a <= j; a++) {
            sum += arr[a];
        }
        double ans = (sum / (double) (j - i + 1));
        return ans;
    }
}
