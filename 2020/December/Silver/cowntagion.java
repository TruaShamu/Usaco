import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class cowntagion {
    public static int ret = 0;
    public static int N;
    public static int[] visited;
    public static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new int[N];
        Arrays.fill(visited, -1);

        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        //Read edges.
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj[a].add(b);
            adj[b].add(a);
        }

        dfs(0);
        //System.out.println("// RET: " + ret);
        System.out.println(ret);


    }

    public static int calcTime(int child) {
        for (int i = 0; ; i++) {
            if (Math.pow(2, i) >= (child + 1)) {
                return i;
            }
        }
    }

    public static void dfs(int node) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int child = 0;
            for (int i = 0; i < adj[cur].size(); i++) {
                if (visited[adj[cur].get(i)] == -1) {
                    child++;
                }
            }
            //System.out.println("//NODE: " + cur + " CHILD: " + child);
            //Time to double.
            int doubleTime = calcTime(child);
            //System.out.println("//DOUBLE: " + doubleTime);
            ret += doubleTime;
            //Child traveling.
            ret += child;

            //Enqueue children
            for (int next : adj[cur]) {
                if (visited[next] == -1) {

                    queue.add(next);
                    visited[next] = visited[cur] + 1;
                }

            }
        }

    }


}
