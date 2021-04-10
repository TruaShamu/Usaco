import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class convention2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("convention2.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("convention2.out"));
        int N = Integer.parseInt(br.readLine());
        cow[] cows = new cow[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            cows[i] = new cow(a, t, i);
        }

        Arrays.sort(cows);
        //Compare seniority.
        PriorityQueue<cow> pq = new PriorityQueue<>(new Comparator<cow>() {
            public int compare(cow a, cow b) {
                return a.i - b.i;
            }
        });

        int curTime = 0, res = 0; //res is the maximum waiting time
        int curCow = 0;

        while (curCow < N || !pq.isEmpty()) {
            if (curCow < N && cows[curCow].a <= curTime) {
                pq.add(cows[curCow++]);
            } else if (pq.isEmpty()) {
                cow cur = cows[curCow++];
                curTime = cur.a + cur.t;
            } else {
                cow cur = pq.poll();
                res = Math.max(res, (curTime - cur.a));
                curTime += cur.t;
            }
        }

        pw.println(res);
        pw.close();


    }
}

class cow implements Comparable<cow> {
    //a is the arrival, t is duration
    public int a, t, i;

    public cow(int a, int t, int i) {
        this.a = a;
        this.t = t;
        this.i = i;
    }

    public int compareTo(cow other) {
        return this.a - other.a;

    }


}
