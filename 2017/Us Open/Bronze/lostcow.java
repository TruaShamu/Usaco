import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lostcow.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lostcow.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int distance = y - x;
        int steps = log(distance);
        System.out.println("STEPS:" + steps);
        int answer = 0;

        for (int i = 0; i < steps - 1; i++) {
            answer += 2 * Math.pow(2, i);
            // System.out.println("ANS:" + answer);
        }
        answer += Math.abs(y - x);
        pw.println(answer);
        pw.close();


    }

    public static int log(int x) {

        for (int i = 0; ; i++) {
            int power = (int) Math.pow(-2, i);
            if (power > 0 && x > 0) {
                if (power >= x) {
                    return i + 1;
                }
            } else {
                if (power <= x & x < 0) {
                    return i + 1;
                }
            }
        }
    }
}
