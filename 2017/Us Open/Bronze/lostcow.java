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
        int steps = findSteps(distance);

        int totalTravel = 0;
        for (int i = 0; i < steps - 1; i++) {
            totalTravel += 2 * Math.pow(2, i);

        }
        totalTravel += Math.abs(distance);
       pw.println(totalTravel);
        pw.close();

    }

    public static int findSteps(int distance) {
        for (int steps = 0; ; steps++) {
            int dist = (int) Math.pow(-2, steps);
            if (distance < 0) {
                if (dist <= distance) {
                    return steps + 1;
                }
            }
            if (distance > 0) {
                if (dist >= distance) {
                    return steps + 1;
                }
            }
        }

    }
}
