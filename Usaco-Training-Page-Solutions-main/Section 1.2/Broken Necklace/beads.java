import java.io.*;

/*
TASK: beads
LANG: JAVA
 */

public class beads {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("beads.in"));
        PrintWriter pw = new PrintWriter("beads.out");
        int N = Integer.parseInt(br.readLine());

        String S = br.readLine();
        StringBuilder sb = new StringBuilder(S);
        sb.append(S);
        S = sb.toString();

        int res = 0, curAmt = 0;
        for (int i = 0; i < N; i++) {
            int state;
            char oChar = S.charAt(i);
            if (oChar == 'w') {
                state = 0;
            } else {
                state = 1;
            }
            int j = i;
            curAmt = 0;
            while (state <= 2) {
                while ((j < N + i) && ((S.charAt(j) == oChar) || (S.charAt(j) == 'w'))) {
                    curAmt++;
                    j++;
                }
                state++;
                oChar = S.charAt(j);
            }
            //Update
            if (curAmt > res) {
                res = curAmt;
            }
        }
        pw.println(res);
        pw.close();
    }
}
