import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
TASK: spin
LANG: JAVA
 */
public class spin {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("spin.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
        int[] speed = new int[5];
        ArrayList<Integer>[] cut = (ArrayList<Integer>[]) new ArrayList[5]; //Each wheel's start andle and extent

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            speed[i] = Integer.valueOf(st.nextToken()); //Wheel speed
            int num = Integer.valueOf(st.nextToken()); // Wedges
            cut[i] = new ArrayList<Integer>();
            for (int j = 0; j < num * 2; j++) {
                //Wedge info
                cut[i].add(Integer.valueOf(st.nextToken()));
            }
        }

        boolean[][] empty = new boolean[5][360];
        for (int i = 0; i < 5; i++) { //For each wheel
            for (int j = 0; j < cut[i].size(); j += 2) { //Wheel start angle
                System.out.println("Upperbound: " + cut[i].get(j + 1)); //Extent

                for (int k = 0; k <= cut[i].get(j + 1); k++) {
                    //System.out.println((cut[i].get(j) + k) % 360);
                    empty[i][(cut[i].get(j) + k) % 360] = true;
                }
            }
        }

        int ans = -1;
        done:
        for (int t = 0; t < 360; t++) {
            boolean[][] e = new boolean[5][360];
            for (int i = 0; i < 5; i++) {
                int at = (t * speed[i]) % 360;
                System.out.println(at);
                for (int j = 0; j < 360; j++) {
                    e[i][(j + at) % 360] = empty[i][j];
                }
            }
            for (int j = 0; j < 360; j++) {
                boolean good = true;
                for (int i = 0; i < 5; i++) {
                    good &= e[i][j];
                }
                if (good) {
                    ans = t;
                    break done;
                }
            }
        }
        if (ans == -1) {
            pw.println("none");
        } else {
            pw.println(ans);
        }

        pw.close();

        System.exit(0);
    }
}
