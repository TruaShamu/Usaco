import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class fcolor {
    public static ArrayList<Integer>[] adj;
    public static ArrayList<Integer>[] rpar;
    public static int[] cnt = new int[200005];
    public static int[] par;
    public static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fcolor.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("fcolor.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];
        par = new int[200005];
        rpar = new ArrayList[200005];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
            rpar[i] = new ArrayList<>();
        }
        //Edges
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b); //admirers of 'a'
        }

        for (int i = 1; i <= N; i++) {
            par[i] = i;
            rpar[i].add(i);
            //This one has to be dealt with
            if (adj[i].size() > 1) {
                q.add(i);
            }
        }


        while (!q.isEmpty()) {
            int x = q.peek();
            //No longer have to deal withit
            if (adj[x].size() <= 1) {
                q.poll();
                continue;
            }


            int a = adj[x].get(adj[x].size() - 1);
            adj[x].remove(adj[x].size() - 1);
            if (par[a] == par[adj[x].get(adj[x].size() - 1)]) {
                continue;
            }
            merge(a, adj[x].get(adj[x].size() - 1));
        }

        int color = 0;
        for (int i = 1; i <= N; ++i) {
            if (cnt[par[i]] == 0) {
                cnt[par[i]] = ++color;
            }
            pw.println(cnt[par[i]]);
        }

        pw.close();


    }


    public static void merge(int a, int b) {
        a = par[a];
        b = par[b];
        if (rpar[a].size() < rpar[b].size()) {
            int tempA = par[a];
            a = par[b];
            b = tempA;
        }
        //Add everything from b to a
        for (int t : rpar[b]) {
            par[t] = a;
            rpar[a].add(t);
        }

        for (int i : adj[b]) {
            adj[a].add(i);
        }

        adj[b].clear();
        if (adj[a].size() > 1) {
            q.add(a);
        }
    }
}

