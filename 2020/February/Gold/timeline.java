import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class timeline {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("timeline.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("timeline.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] inFlow = new int[N + 1];
        boolean[] vis = new boolean[N + 1];

        //read array
        int[] S = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        //read edges
        ArrayList<Integer[]>[] adj = new ArrayList[N + 1];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            adj[a].add(new Integer[]{b, x});
            inFlow[b]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inFlow[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            vis[x] = true;
            assert (S[x] <= M);

            for (Integer[] oEdge : adj[x]) {
                S[oEdge[0]] = Integer.max(S[oEdge[0]], S[x] + oEdge[1]);
                if ((--inFlow[oEdge[0]]) == 0) {
                    q.add(oEdge[0]);
                }
            }
        }

        System.out.println("ANSWER: ");

        for (int i = 1; i <= N; ++i) {
            assert (vis[i]);
            pw.println(S[i]);
            System.out.println(S[i]);
        }
        System.out.println("--------------------------");
        pw.close();
    }
}

