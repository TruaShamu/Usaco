import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        int lines = Integer.parseInt(br.readLine());
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < lines; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cow1 = Integer.parseInt(st.nextToken());
            int cow2 = Integer.parseInt(st.nextToken());
            if (cow1 == 1) {
                if (cow2 == 2) {
                    count1++;
                }
                if (cow2 == 3) {
                    count2++;
                }
            }
            if (cow1 == 2) {
                if (cow2 == 3) {
                    count1++;
                }
                if (cow2 == 1) {
                    count2++;
                }
            }
            if (cow1 == 3) {
                if (cow2 == 1) {
                    count1++;
                }
                if (cow2 == 2) {
                    count2++;
                }
            }

        }
        pw.println(Integer.max(count1, count2));
        pw.close();

    }
}
