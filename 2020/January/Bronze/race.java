import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("race.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("race.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int distance = Integer.parseInt(st.nextToken());
        int numValues = Integer.parseInt(st.nextToken());
        int[] valueArray = new int[numValues]; //X
        for (int i = 0; i < numValues; i++) {
            valueArray[i] = Integer.parseInt(br.readLine());
        }
        for (int X : valueArray) {
            pw.println(check(X, distance));
        }
        pw.close();

    }

    public static int check(int X, int distance) {

        int peakLeft = 0;
        int peakRight = 0;
        int time = 0;
        for (int curSpeed = 1; ; curSpeed++) {

            peakLeft += curSpeed; //How many meters she runs until her maximum speed
            time++;
            if (peakLeft + peakRight >= distance) return time;
            if (curSpeed >= X) {
                peakRight += curSpeed;
                time++;
                if (peakLeft + peakRight >= distance) return time;
            }

        }

    }
}

