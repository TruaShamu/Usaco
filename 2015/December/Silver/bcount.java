import java.io.*;
import java.util.StringTokenizer;

/*
DATE:
SCORE:
"Spider bit the mouse, sleep deep..."
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cows = Integer.parseInt(st.nextToken());
        int queries = Integer.parseInt(st.nextToken());
        int[] hCows = new int[cows + 1]; //1 Holsteins
        int[] gCows = new int[cows + 1]; // 2 Gurnesy?
        int[] jCows = new int[cows + 1]; //3 Jersey
        for (int i = 1; i <= cows; i++) {
            int id = Integer.parseInt(br.readLine());
            if (id == 1) {
                hCows[i] = 1;
            }
            if (id == 2) {
                gCows[i] = 1;
            }
            if (id == 3) {
                jCows[i] = 1;
            }
        }
        for (int i = 1; i < hCows.length; i++) {
            gCows[i] += gCows[i - 1];
            hCows[i] += hCows[i - 1];
            jCows[i] += jCows[i - 1];
        }
        for (int query = 0; query < queries; query++) {
            st = new StringTokenizer(br.readLine());
            int lb = Integer.parseInt(st.nextToken());
            int ub = Integer.parseInt(st.nextToken());

            int h = hCows[ub] - hCows[lb - 1];
            int g = gCows[ub] - gCows[lb - 1];
            int j = jCows[ub] - jCows[lb - 1];

            pw.println(h + " " + g + " " + j);
            System.out.println(h + " " + g + " " + j);
        }
        pw.close();
    }
}
