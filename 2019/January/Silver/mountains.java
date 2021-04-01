import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mountains {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("mountains.out"));
        int N = Integer.parseInt(br.readLine());
        mountain[] arr = new mountain[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int s = x - y;
            int e = x + y;
            arr[i] = new mountain(s, e);
        }
        Arrays.sort(arr);

        int max = -1;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i].e > max) {
                ans++;
                max = arr[i].e;
            }
        }
        pw.println(ans);
        pw.close();

    }
}

class mountain implements Comparable<mountain> {

    public int s, e;

    public mountain(int s, int e) {
        this.s = s;
        this.e = e;
    }

    public int compareTo(mountain other) {
        if (this.s != other.s) {
            return Integer.compare(this.s, other.s);
        } else {
            return Integer.compare(other.e, this.e);
        }
    }
}

