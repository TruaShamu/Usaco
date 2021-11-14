import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
USACO 2018 January Contest, Gold Problem 2. Cow at Large
By TruaTheOrca ||  Date: 6/4/2020 || Score: 13/13

 */
public class Main {
    public static ArrayList<Integer>[] edges;
    public static int ans = 0;
    public static int maxN = (int) 10e5;
    public static int[] leafDist = new int[maxN];
    public static int[] bessieDist = new int[maxN];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("atlarge.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("atlarge.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodes = Integer.parseInt(st.nextToken());
        int bessie = Integer.parseInt(st.nextToken()) - 1;
        edges = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < nodes - 1; i++) {
            //Read in each edge
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            edges[node1].add(node2);
            edges[node2].add(node1);
        }
        for (int i = bessie; i < bessie + 1; i++) {
            //Dfs
            if (edges[i].size() == 1) {
                continue;
            }
            ans = 0;
            dfsDistances(i, -1);
            dfsDistances2(i, -1);
            dfs(i, -1);
        }
        System.out.println("ANSWER: " + ans);
        pw.println(ans);
        pw.close();
        System.exit(0);

    }

    public static void dfsDistances2(int i, int parent) {
        if (parent != -1) {
            leafDist[i] = Integer.min(leafDist[i], leafDist[parent] + 1);
        }
        for (int j = 0; j < edges[i].size(); j++) {
            if (parent != edges[i].get(j)) {
                dfsDistances2(edges[i].get(j), i);
            }
        }
    }

    public static void dfs(int i, int parent) {
        if ((parent != -1) && (leafDist[i] <= bessieDist[i]) && (leafDist[parent] > bessieDist[parent])) {
            //Possible solution
            ans++;
        }
        for (int j = 0; j < edges[i].size(); j++) {
            //Recursively find parent
            if (edges[i].get(j) != parent) {
                dfs(edges[i].get(j), i);
            }
        }
    }

    public static void dfsDistances(int i, int parent) {
        leafDist[i] = maxN + 1;
        //Find distance to bessie
        if (parent != -1) {
            bessieDist[i] = 1 + bessieDist[parent];
        } else {
            bessieDist[i] = 0;
        }
        boolean isLeaf = true;
        //Is the node a leaf
        for (int j = 0; j < edges[i].size(); j++) {
            if (parent != edges[i].get(j)) {
                dfsDistances(edges[i].get(j), i);
                leafDist[i] = Integer.min(leafDist[i], 1 + leafDist[edges[i].get(j)]);
                isLeaf = false;
            }
        }
        if (isLeaf) {
            leafDist[i] = 0;
        }
    }

}
