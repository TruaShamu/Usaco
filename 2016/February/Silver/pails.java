import java.io.*;
import java.util.StringTokenizer;

/*
DATE: 3/12
SCORE: 10/10
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("pails.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int pail1 = Integer.parseInt(st.nextToken()); //X
        int pail2 = Integer.parseInt(st.nextToken()); //Y
        int operations = Integer.parseInt(st.nextToken()); //K
        int amount = Integer.parseInt(st.nextToken()); //M
        boolean[][] works = new boolean[pail1 + 1][pail2 + 1];
        works[0][0] = true;
        for (int operation = 0; operation < operations; operation++) {
            boolean[][] next = new boolean[pail1 + 1][pail2 + 1];
            for (int i = 0; i < works.length; i++) {
                for (int j = 0; j < works[i].length; j++) {
                    if (!works[i][j]) {
                        continue;
                    }
                    next[i][j] = true; //Same state
                    next[0][j] = true; //Empty pail 1
                    next[pail1][j] = true;//Fill pail 1
                    next[i][0] = true; //Empty pail 2
                    next[i][pail2] = true; //Fill pail 2
                    int moveRight = Math.min(i, pail2 - j); // pour from pail 1 to pail 2
                    next[i - moveRight][j + moveRight] = true;

                    int moveLeft = Math.min(pail1 - i, j); //pour from pail2 to pail1/
                    next[i + moveLeft][j - moveLeft] = true;
                }
            }
            works = next;
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < works.length; i++) {
            for (int j = 0; j < works[i].length; j++) {
                if (!works[i][j]) continue;
                ret = Math.min(ret, Math.abs(i + j - amount));
            }
        }
        pw.println(ret);
        pw.close();
    }
}
