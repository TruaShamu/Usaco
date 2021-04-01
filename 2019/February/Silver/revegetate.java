

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class revegetate {
    public static int[] type;
    public static boolean impossible;
    public static ArrayList<Integer>[] same;
    public static ArrayList<Integer>[] diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("revegetate.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        type = new int[N + 1];
        same = new ArrayList[N + 1];
        diff = new ArrayList[N + 1];

        //Initialize the adjacency lists.
        for (int i = 0; i < N + 1; i++) {
            same[i] = new ArrayList<>();
            diff[i] = new ArrayList<>();
        }
        int comps = 0; //The number of connected components.

        //Read in the edges.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //They share the same type of grass
            if (type == 'S') {
                same[a].add(b);
                same[b].add(a);

            }
            //They share different types of grass.
            if (type == 'D') {
                diff[a].add(b);
                diff[b].add(a);
            }
        }

        for (int i = 1; i <= N; i++) {
            //This node is unvisited, so try to color it with color '1'.
            if (type[i] == 0) {
                visit(i, 1);
                comps++;
            }
        }
        //It is impossible to color the fields.
        if (impossible) {
            pw.println(0);
        } else {
            //The solution is 2^(number of components).
            pw.print(1);
            for (int i = 0; i < comps; i++) {
                pw.print(0);
            }
            pw.println();
        }
        pw.close();

    }


    //Visit a node and color it.
    public static void visit(int node, int color) {
        type[node] = color;

        //Check all of the nodes that are supposed to share the same color.
        for (int next : same[node]) {
            //There is a contradiction here.
            if (type[next] == 3 - color) {
                impossible = true;
            }
            if (type[next] == 0) {
                visit(next, color);
            }
        }

        //Check all of the nodes that are supposed to have a different color.
        for (int next : diff[node]) {
            //There is a contradiction here.
            if (type[next] == color) {
                impossible = true;
            }
            if (type[next] == 0) {
                visit(next, 3 - color);
            }
        }
    }
}

