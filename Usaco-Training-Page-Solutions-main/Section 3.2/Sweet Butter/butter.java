/*
LANG: JAVA
TASK: butter
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class butter {
    static int cows, pastures, paths;
    static List[] pastureList;
    static List<Integer> cowList;
    static int[][] distances;

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();

        BufferedReader br = new BufferedReader(new FileReader("butter.in"));
        PrintWriter pw = new PrintWriter("butter.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        //Input
        cows = Integer.parseInt(st.nextToken());
        pastures = Integer.parseInt(st.nextToken());
        paths = Integer.parseInt(st.nextToken());
        cowList = new ArrayList<>();
        pastureList = new List[pastures];
        for (int i = 0; i < pastures; i++) {
            pastureList[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < cows; i++) {
            cowList.add(Integer.parseInt(br.readLine()) - 1);
        }
        System.out.println(System.currentTimeMillis() - startTime);
        //Edges
        for (int i = 0; i < paths; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int distance = Integer.parseInt(st.nextToken());
            pastureList[node1].add(new int[]{node2, distance});
            pastureList[node2].add(new int[]{node1, distance});
        }
        distances = new int[pastures][pastures];
        System.out.println(System.currentTimeMillis() - startTime);


        pw.println(solve());
        pw.close();
        // print final time taken
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);
    }

    public static class Node implements Comparable {
        int distance;
        boolean visited;
        int id;

        @Override
        public int compareTo(Object other) {
            return this.distance - ((Node) other).distance;
        }
    }

    public static int solve() {
        int min = Integer.MAX_VALUE;
        for (int cow : cowList) {
            dijkstra(cow);
        }
        //Loop, setting each node as a beginning
        for (int i = 0; i < pastures; i++) {
            int result = 0;
            for (int cow : cowList) {
                result += distances[cow][i];
                if (result == Integer.MAX_VALUE) break;
            }
            min = Math.min(result, min);
        }
        return min;
    }

    public static void dijkstra(int begin) {
        Node[] nodes = new Node[pastures];
        for (int i = 0; i < pastures; i++) {
            //Initialize
            nodes[i] = new Node();
            nodes[i].id = i;
            nodes[i].distance = Integer.MAX_VALUE;
            nodes[i].visited = false;
        }

        PriorityQueue<Node> heap = new PriorityQueue<>();
        nodes[begin].distance = 0;
        nodes[begin].visited = true;
        Collections.addAll(heap, nodes);
        while (!heap.isEmpty()) {
            Node node = heap.poll();
            if (node.distance == Integer.MAX_VALUE) break;
            node.visited = true;
            for (Object object : pastureList[node.id]) {
                int[] arr = (int[]) object;
                if (nodes[arr[0]].distance > node.distance + arr[1]) {
                    nodes[arr[0]].distance = node.distance + arr[1];
                    if (!nodes[arr[0]].visited) {
                        heap.remove(nodes[arr[0]]);
                        heap.add(nodes[arr[0]]);
                    }
                }
            }
        }
        for (Node node : nodes) {
            distances[begin][node.id] = node.distance;
        }
    }
}
