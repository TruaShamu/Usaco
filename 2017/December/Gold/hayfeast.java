import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hayfeast.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long need = Long.parseLong(st.nextToken());
        long[] f = new long[n];
        long[] s = new long[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            f[i] = Long.parseLong(st.nextToken());
            s[i] = Long.parseLong(st.nextToken());
        }
        int left = 0;
        long ret = Long.MAX_VALUE;
        TreeMap<Long, Integer> seen = new TreeMap<Long, Integer>();
        long flavor = 0;
        for (int i = 0; i < n; i++) {
            flavor += f[i];
            update(seen, s[i], 1);
            while (flavor - f[left] >= need) {
                update(seen, s[left], -1);
                flavor -= f[left++];
            }
            if (flavor >= need) {
                ret = Math.min(ret, seen.lastKey());
            }
        }
        pw.println(ret);
        pw.close();
    }

    private static void update(Map<Long, Integer> m, long k, int v) {
        if (!m.containsKey(k)) {
            m.put(k, 0);
        }
        int nv = m.get(k) + v;
        if (nv == 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

}

