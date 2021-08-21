/*
LANG: JAVA
PROG: barn1
*/

import java.io.*;
import java.util.*;

public class barn1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter pw = new PrintWriter("barn1.out");
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int used, ans = C;

        int[] A = new int[C];
        for (int i = 0; i < C; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(A);

        PriorityQueue<gap> gaps = new PriorityQueue();
        for (int i = 1; i < C; i++) {
            if (A[i] - A[i - 1] > 1) {
                gaps.add(new gap(A[i - 1], A[i]));
            }
        }

        used = gaps.size() + 1;
        while (used > M) {
            ans += gaps.poll().size;
            used--;
        }

        pw.println(ans);
        pw.close();
    }

    static class gap implements Comparable<gap> {
        int start, end, size;

        public gap(int s, int e) {
            start = s;
            end = e;
            size = end - start - 1;
        }

        public int compareTo(gap other) {
            return this.size - other.size;
        }
    }
}
