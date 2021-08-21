import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
TASK: milk2
LANG: JAVA
 */
public class milk2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
        int N = Integer.parseInt(br.readLine());
        event[] events = new event[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            events[i] = new event(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(events);


        int curStart = events[0].start;
        int curEnd = events[0].end;
        int maxMilk = curEnd - curStart;
        int maxNoMilk = 0;

        for (int i = 1; i < N; i++) {
            event cur = events[i];
            //Overlap;
            if (cur.start <= curEnd) {
                curEnd = Math.max(cur.end, curEnd);
            } else {
                maxMilk = Math.max(maxMilk, curEnd - curStart);
                maxNoMilk = Math.max(maxNoMilk, cur.start - curEnd);
                curStart = cur.start;
                curEnd = cur.end;
            }
        }

        pw.println(maxMilk + " " + maxNoMilk);
        pw.close();
    }

    public static class event implements Comparable<event> {
        public int start;
        public int end;

        public event(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(event other) {
            return Integer.compare(this.start, other.start);
        }
    }
}
