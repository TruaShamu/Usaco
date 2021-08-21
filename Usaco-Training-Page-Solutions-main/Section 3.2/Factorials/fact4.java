/*
TASK: fact4
LANG: JAVA
 */
import java.io.*;
import java.util.StringTokenizer;

public class fact4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fact4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int digit = 1;
        int twos = 0;
        int fives = 0;
        for (int i = 2; i <= N; i++) {
            int j = i;
            while (j % 2 == 0) {
                twos++;
                j /= 2;
            }
            while (j % 5 == 0) {
                fives++;
                j /= 5;
            }
            digit = (digit * j) % 10;
        }

        for (int i = 0; i < twos - fives; i++) {
            digit = (digit * 2) % 10;
        }

        pw.println(digit);
        pw.close();
    }
}
