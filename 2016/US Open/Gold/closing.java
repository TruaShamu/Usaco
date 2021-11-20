import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class closing {
    public static ArrayList<Integer>[] edges;
    public static int[] order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("closing.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("closing.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        //Read edges
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            edges[a].add(b);
            edges[b].add(a);
        }

        //Closing order
        order = new int[N];
        for (int i = 0; i < N; i++) {
            order[i] = Integer.parseInt(br.readLine()) - 1;
        }


        boolean[] res = new boolean[N]; //the result
        dset dj = new dset(N);
        res[N - 1] = true;
        boolean[] inGraph = new boolean[N];
        inGraph[order[N - 1]] = true;

        // Go backwards through the list of deletions.
        for (int i = N - 2; i >= 0; i--) {

            int item = order[i]; //Next to be added to graph
            for (int j = 0; j < edges[item].size(); j++) {
                int next = (edges[item].get(j));  //The farms linked to 'item'
                if (inGraph[next]) {
                    dj.union(item, next);
                }
            }

            res[i] = (dj.numTrees == i + 1);
            inGraph[item] = true;
        }

        for (int i = 0; i < N; i++) {
            if (res[i]) {
                pw.println("YES");
            } else {
                pw.println("NO");
            }
        }


        pw.close();
    }
}

class pair {
    public int parent;
    public int height;

    public pair(int a, int b) {
        parent = a;
        height = b;
    }
}

// Basic Disjoint Set without path compression.
class dset {

    private pair[] parents;
    public int numTrees;

    public dset(int n) {
        parents = new pair[n];
        for (int i = 0; i < n; i++) {
            parents[i] = new pair(i, 0);
        }
        numTrees = n;
    }

    public int find(int child) {
        while (parents[child].parent != child) {
            child = parents[child].parent;
        }
        return child;
    }

    public boolean union(int c1, int c2) {
        int root1 = find(c1);
        int root2 = find(c2);

        // Nothing to union.
        if (root1 == root2) {
            return false;
        }

        // Root 1 stays parent.
        if (parents[root1].height > parents[root2].height) {
            parents[root2].parent = root1;
        }

        // Tie case get assigned to root 1 also.
        else if (parents[root1].height == parents[root2].height) {
            parents[root2].parent = root1;
            parents[root1].height++;
        }

        // Must use root 2 here.
        else {
            parents[root1].parent = root2;
        }

        numTrees--;
        return true;
    }
}
