import java.io.*;
/*
 LANG: JAVA
 PROG: heritage
  */

public class heritage {
    public static char[] preOrder, inOrder;
    public static int preIndex = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("heritage.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));
        inOrder = br.readLine().toCharArray();
        preOrder = br.readLine().toCharArray();
        Node n = tree(0, inOrder.length - 1);
        pw.println(n.toString());
        pw.close();
    }

    public static Node tree(int start, int end) {
        if (start > end) {
            return null;
        }
        Node node = new Node(preOrder[preIndex++]);
        if (start == end) {
            return node;
        }
        int inIndex = find(inOrder, node.data, start, end);
        node.left = tree(start, inIndex - 1);
        node.right = tree(inIndex + 1, end);
        return node;
    }

    public static int find(char[] arr, char f, int start, int end) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == f) {
                return i;
            }
        return -1;
    }

    public static class Node {
        public char data;
        public Node left, right;

        public Node(char d) {
            data = d;
            left = null;
            right = null;
        }

        public String toString() {
            return (left == null ? "" : left.toString()) + (right == null ? "" : right.toString()) + Character.toString(data);
        }
    }

}


