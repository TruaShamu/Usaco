import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class milkorder {
    public static int N, M;
    public static int[][] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milkorder.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("milkorder.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        //Read the input.
        list = new int[N][];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cows = Integer.parseInt(st.nextToken());
            list[i] = new int[cows];
            for (int j = 0; j < cows; j++) {
                list[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        //Binary search for the answer.
        int[] res = null;
        int low = 0, high = M;

        while (low < high) {
            int mid = (low + high + 1) / 2;
            int[] temp = topSort(mid);

            if (temp == null)
                high = mid - 1;
            else {
                low = mid;
                res = temp;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(res[0]);
        for (int i = 1; i < N; i++) {
            sb.append(" " + res[i]);
        }
        pw.println(sb);
        pw.close();


    }

    //Topological sort
    public static int[] topSort(int nL) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] degree = new int[N];

        // Make the edges [cur ==> next]
        for (int i = 0; i < nL; i++) {
            for (int j = 0; j < list[i].length - 1; j++) {
                int cur = list[i][j];
                int next = list[i][j + 1];
                graph[cur].add(next);
                degree[next]++;
            }
        }

        // Set up the PQ.
        for (int i = 0; i < N; i++) {
            if (degree[i] == 0) {
                pq.add(i);
            }
        }

        //Topological sort.
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            if (pq.size() == 0) {
                return null;
            }
            int id = pq.poll();
            res[i] = id + 1; //Add it back.

            for (int node : graph[id]) {
                degree[node]--;
                if (degree[node] == 0) {
                    pq.add(node);
                }
            }
        }

        return res;
    }
}

