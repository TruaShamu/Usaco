import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class fenceplan {
    public static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("fenceplan.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] loc = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            loc[i][0] = x;
            loc[i][1] = y;
        }
        cc connectComp = new cc(N);
        //Find parent of each node
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            connectComp.union(a, b);
        }
        //Assign each node to group
        int[] group = new int[N];
        TreeSet<Integer> vals = new TreeSet<Integer>();
        for (int i = 0; i < N; i++) {
            group[i] = connectComp.find(i);
            vals.add(group[i]);
        }

        // Map old id's to new ones.
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int id = 0;
        //Number of nodes/group
        while (vals.size() > 0) {
            map.put(vals.pollFirst(), id++);
        }

        for (int i = 0; i < N; i++) {
            group[i] = map.get(group[i]);
        }

        // Keep track of each groups min/max.
        int[] minX = new int[id];
        Arrays.fill(minX, 1000000000);
        int[] maxX = new int[id];
        Arrays.fill(maxX, -1);
        int[] minY = new int[id];
        Arrays.fill(minY, 1000000000);
        int[] maxY = new int[id];
        Arrays.fill(maxY, -1);

        // Keep track of each group's min and max.
        for (int i = 0; i < N; i++) {
            minX[group[i]] = Math.min(minX[group[i]], loc[i][0]);
            maxX[group[i]] = Math.max(maxX[group[i]], loc[i][0]);
            minY[group[i]] = Math.min(minY[group[i]], loc[i][1]);
            maxY[group[i]] = Math.max(maxY[group[i]], loc[i][1]);
        }

        // Find the smallest bounding rectangle.
        int res = 1000000000;
        for (int i = 0; i < id; i++) {
            res = Math.min(res, 2 * (maxX[i] - minX[i]) + 2 * (maxY[i] - minY[i]));
        }

        // Ta da!
        pw.println(res);
        pw.close();

    }

    //Connected components
    static class cc {

        public int[] parent;
        public int n;

        public cc(int myn) {
            n = myn;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int a, int b) {
            int p1 = find(a);
            int p2 = find(b);
            if (p1 == p2) {
                return;
            }
            parent[p2] = p1;
        }

        public int find(int a) {
            if (a == parent[a]) {
                return a;
            }
            return parent[a] = find(parent[a]);
        }
    }

}



