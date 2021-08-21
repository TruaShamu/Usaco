import java.io.*;
import java.util.StringTokenizer;

/*
LANG: JAVA
TASK: agrinet
*/
public class agrinet {
    public static int nodes;
    public static Node[] nodeArray;
    public static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("agrinet.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
        nodes = Integer.parseInt(br.readLine());
        nodeArray = new Node[nodes];
        dist = new int[nodes][nodes];
        for (int i = 0; i < nodes; i++) {
            String line = br.readLine();
            StringTokenizer l;
            while ((l = new StringTokenizer(line)).countTokens() < nodes) {
                line += " " + br.readLine();
            }
            //System.out.println(i);
            for (int j = 0; j < nodes; j++) {
                dist[i][j] = Integer.parseInt(l.nextToken());
                //System.out.println(j + "\t"+dist[i][j]);
            }
        }
        pw.println(solve());
        pw.close();


    }

    public static int solve() {
        //Distance is the distance from the tree to the node
        //Father is the closest node
        for (int i = 0; i < nodes; i++) {
            nodeArray[i] = new Node();
        }
        //Add the first node to the tree
        int treesize = 1;
        int treecost = 0;
        nodeArray[0].inTree = true;
        nodeArray[0].distance = 0;
        nodeArray[0].father = -1;
        for (int j = 1; j < nodes; j++) {
            //All the nodes set 0 as father
            if (dist[0][j] != -1) {
                nodeArray[j].distance = dist[0][j];
                nodeArray[j].father = 0;
            }
        }
        while (treesize < nodes) {
            int minNode = 0; //The node with the minimum dis to the tree
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < nodes; i++) {
                if (!nodeArray[i].inTree && nodeArray[i].distance < min) {
                    min = nodeArray[i].distance;
                    minNode = i;
                }
            }
            if (min == Integer.MAX_VALUE) {
                //Graph is broken
                return -1;
            }
            treesize++;
            treecost += min;
            //Add new node to the tree
            nodeArray[minNode].inTree = true;

            // update distance after node i added
            for (int j = 0; j < nodes; j++) {
                if (nodeArray[j].distance > dist[minNode][j]) {
                    nodeArray[j].distance = dist[minNode][j];
                    nodeArray[j].father = minNode;
                }
            }
        }
        return treecost;

    }
}

class Node {
    int distance;
    boolean inTree;
    int father;

    public Node(int distance, boolean inTree, int source) {
        this.distance = distance;
        this.inTree = inTree;
        this.father = source;
    }

    public Node() {
        this.distance = Integer.MAX_VALUE;
        this.inTree = false;
        this.father = -1;
    }


}
