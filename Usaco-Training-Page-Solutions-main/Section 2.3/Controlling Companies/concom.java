import java.io.*;
import java.util.StringTokenizer;

/*
TASK: concom
LANG: JAVA
 */
public class concom {
    public static int max = 100;
    public static int[][] owns = new int[max + 1][max + 1];
    public static boolean[][] controls = new boolean[max + 1][max + 1];

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("concom.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
        for (int i = 0; i <= max; i++) {
            controls[i][i] = true;
        }
        int triples = Integer.parseInt(br.readLine());
        for (int i = 0; i < triples; i++) {
            StringTokenizer l = new StringTokenizer(br.readLine());
            addOwner(Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()));
        }
        for (int i = 0; i <= max; i++) {
            for (int j = 0; j <= max; j++) {
                if (i != j && controls[i][j]) {
                    pw.println(i + " " + j);
                }
            }
        }
        pw.close();

    }

    public static void addOwner(int i, int j, int percent) {
        for (int k = 0; k <= max; k++) {
            if (controls[k][i]) {
                //If k is i's father, add percentage to father.
                owns[k][j] += percent;
            }
        }
        // does anything now control j?
        for (int k = 0; k <= max; k++) {
            if (owns[k][j] > 50) {
                addController(k, j);
            }
        }
    }

    public static void addController(int i, int j) {
        if (controls[i][j]) {
            //Ignore if i is j's father
            return;
        }

        controls[i][j] = true;
        // Add everything from j's children
        for (int k = 0; k <= max; k++) {
            owns[i][k] += owns[j][k];
        }
        //i's fathers are now all j's fathers
        for (int k = 0; k <= max; k++) {
            if (controls[k][i]) {
                addController(k, j);
            }
        }
        // call addController on everything i not holds
        for (int k = 0; k <= max; k++) {
            if (owns[i][k] > 50) {
                addController(i, k);
            }
        }
    }

}
