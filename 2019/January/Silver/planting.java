import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class planting {
    public static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("planting.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("planting.out"));
        int N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adj[a].add(b);
            adj[b].add(a);
        }

        int max = 0;
        for (int i = 0; i < N - 1; i++) {
            max = Integer.max(max, adj[i].size());
        }

        max++;
        System.out.println("ANSWER: " + max);
        pw.println(max);
        pw.close();
    }
}
