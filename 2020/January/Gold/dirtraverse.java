import java.io.*;
import java.util.*;

public class dirtraverse {
    public static int N;
    public static node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dirtraverse.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dirtraverse.out")));
        N = Integer.parseInt(br.readLine());

        tree = new node[N];
        // @todo Read contents.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nameLen = st.nextToken().length();
            int type = Integer.parseInt(st.nextToken());

            if (type == 0) {
                // File.
                tree[i] = new node(i, nameLen);
            } else {
                // Folder.
                ArrayList<Integer> li = new ArrayList<>();
                for (int j = 0; j < type; j++) {
                    li.add(Integer.parseInt(st.nextToken()) - 1);
                }
                tree[i] = new node(i, nameLen, li);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);


        //@todo BFS to find depth of each node.
        while (!q.isEmpty()) {
            int cur = q.poll();
            int curD = tree[cur].depth;

            if (tree[cur].kids == null) {
                continue;
            }

            for (Integer next : tree[cur].kids) {
                q.add(next);
                tree[next].depth = (curD + 1);
            }
        }

        // Sort by descending depth.
        node[] alt = new node[N];
        for (int i=0; i< N; i++) {
            alt[i] = tree[i];
        }
        Arrays.sort(alt);

        /*
        @todo
        @todo 1. Find number of leaves in each node subtree.
        @todo 2. Check downwards to find sum of distances to leaves.
         */
        for (int i = 0; i < N; i++) {
            int cur = alt[i].ID;

            if (tree[cur].kids == null) {
                continue;
            }

            for (int next : tree[cur].kids) {
                int add = (tree[next].kids == null) ? 0 : 1;
                tree[cur].numLeaf += tree[next].numLeaf; //Calculate number of leaves.
                tree[cur].distToLeaf += (tree[next].distToLeaf + (long) (tree[next].nameLen + add) * tree[next].numLeaf);
            }

        }

        for (node oNode : tree) {
            System.out.println(oNode.toString());
        }

        long res = go(0, tree[0].distToLeaf);
        System.out.println(res);
        pw.println(res);
        pw.close();

    }

    public static long go(int node, long curRes) {
        // No kids.
        if (tree[node].kids == null) {
            return curRes;
        }

        long myRes = curRes;

        for (int next : tree[node].kids) {
            long savings = (long) tree[next].numLeaf * (tree[next].nameLen + 1) - (tree[0].numLeaf - tree[next].numLeaf) * 3L;
            myRes = Math.min(myRes, go(next, curRes - savings));
        }

        return myRes;
    }

    static class node implements Comparable<node> {

        public int nameLen;
        public int ID;
        public int depth;
        public int numLeaf;
        public long distToLeaf;
        public ArrayList<Integer> kids;

        //@todo For folders.
        public node(int num, int name, ArrayList<Integer> next) {
            ID = num;
            nameLen = name;
            numLeaf = 0;
            distToLeaf = 0;
            kids = next;
            depth = 0;
        }

        public String toString() {
            return ID + " depth= " + depth + " nL= " + numLeaf + " distToLeaf= " + distToLeaf;
        }

        //@todo For files.
        public node(int num, int name) {
            ID = num;
            nameLen = name;
            numLeaf = 1;
            distToLeaf = 0;
            kids = null;
            depth = 0;
        }

        public int compareTo(node other) {
            return other.depth - this.depth;
        }
    }
}
