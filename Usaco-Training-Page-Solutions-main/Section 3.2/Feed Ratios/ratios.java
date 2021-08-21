import java.io.*;
import java.util.StringTokenizer;

/*
TASK: ratios
LANG: JAVA
 */
public class ratios {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ratios.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
        int[][] feeds = new int[4][3]; //Row 0 are
        for (int i = 0; i < 4; i++) {
            StringTokenizer l = new StringTokenizer(br.readLine());

            feeds[i] = new int[]{Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken()), Integer.parseInt(l.nextToken())};
        }
        boolean works = false;
        loop:
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 100; k++) {
                    double first = 1.0 * (i * feeds[1][0] + j * feeds[2][0] + k * feeds[3][0]) / feeds[0][0]; //1st digit
                    double second = 1.0 * (i * feeds[1][1] + j * feeds[2][1] + k * feeds[3][1]) / feeds[0][1]; //2nd digit
                    double third = 1.0 * (i * feeds[1][2] + j * feeds[2][2] + k * feeds[3][2]) / feeds[0][2];  //3rd digit

                    if (first != 0) {
                        if ((first == second || feeds[0][0] == 0 || feeds[0][1] == 0) && (second == third || feeds[0][1] == 0 || feeds[0][2] == 0)) {
                            if (first == (int) (first)) {
                                pw.println(i + " " + j + " " + k + " " + ((int) first));
                                works = true;
                                break loop;
                            }
                        }
                    }
                }
            }
        }

        if (!works) {
            pw.println("NONE");
        }
        pw.close();


    }
}
