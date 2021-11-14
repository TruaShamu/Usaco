import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("speeding.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));
        int[] speedLimit = new int[101];
        int[] bessieSpeed = new int[101];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int limitEvents = Integer.parseInt(st.nextToken());
        int speedEvents = Integer.parseInt(st.nextToken());
        int curLimitTime = 1;
        int curSpeedTime = 1;
        for (int i = 0; i < limitEvents; i++) {
            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            int limit = Integer.parseInt(st.nextToken());
            for (int j = curLimitTime; j < (curLimitTime + length); j++) {
                speedLimit[j] = limit;
            }
            curLimitTime += length;
        }
        for (int i = 0; i < speedEvents; i++) {
            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            int limit = Integer.parseInt(st.nextToken());
            for (int j = curSpeedTime; j < (curSpeedTime + length); j++) {
                bessieSpeed[j] = limit;
            }
            curSpeedTime += length;
        }
        int maxDiff = 0;
        for (int i = 1; i < bessieSpeed.length; i++) {
            if (maxDiff > bessieSpeed[i] - speedLimit[i]) {
            }
            maxDiff = Integer.max(maxDiff, bessieSpeed[i] - speedLimit[i]);

        }
        pw.println(maxDiff);
        pw.close();


    }
}
