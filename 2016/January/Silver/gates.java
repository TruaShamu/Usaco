import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("gates.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));

        int a = Integer.parseInt(br.readLine());
        String b = br.readLine();

        int x = 0;
        int y = 0;
        int xpre;
        int ypre;

        Set edges = new HashSet();
        Set vertices = new HashSet();

        vertices.add(x + " " + y);

        for (int c = 0; c < a; c++) {
            xpre = x;
            ypre = y;

            if (b.charAt(c) == 'N') {
                x = xpre;
                y = ypre + 1;
            } else if (b.charAt(c) == 'S') {
                x = xpre;
                y = ypre - 1;
            } else if (b.charAt(c) == 'E') {
                x = xpre + 1;
                y = ypre;
            } else {
                x = xpre - 1;
                y = ypre;
            }

            vertices.add(x + " " + y);

            if (b.charAt(c) == 'N' || b.charAt(c) == 'E') {
                edges.add(xpre + " " + ypre + " " + x + " " + y);
            } else {
                edges.add(x + " " + y + " " + xpre + " " + ypre);
            }
        }
        pw.println(edges.size() - vertices.size() + 1);
        pw.close();
    }
}
