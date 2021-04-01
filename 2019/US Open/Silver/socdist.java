import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class socdist {
    public static long MAX = (long) 1e18;
    public static int N, M;
    public static strip[] strips;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter pw = new PrintWriter("socdist.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        strips = new strip[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            strips[i] = new strip(s, e);
        }

        Arrays.sort(strips);
        long lower = 1;
        long upper = MAX;
        while (lower < upper) {
            long mid = (lower + upper + 1) / 2;
            if (valid(mid)) {
                lower = mid;
            } else {
                upper = mid - 1;
            }
        }

        pw.println(lower);
        pw.close();

    }

    public static boolean valid(long gapLen) {
        long curPos = strips[0].start;
        int stripIdx = 0;

        for (int cow = 1; cow < N; cow++) {
            long nextPos = curPos + gapLen;
            while ((stripIdx < M) && (strips[stripIdx].end < nextPos)) {
                stripIdx++;
            }
            if (stripIdx == M) {
                return false;
            }

            curPos = Math.max(nextPos, strips[stripIdx].start);
        }
        return true;
    }
}

class strip implements Comparable<strip> {
    public int start, end;

    public strip(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int compareTo(strip other) {
        if (this.start != other.start) {
            return this.start - other.start;
        } else {
            return this.end - other.end;
        }
    }
}

