import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("reststops.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("reststops.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken());
        int stops = Integer.parseInt(st.nextToken());
        int fjSpeed = Integer.parseInt(st.nextToken());
        int bSpeed = Integer.parseInt(st.nextToken());
        long[] location = new long[stops];
        int[] tastiness = new int[stops];
        boolean[] isMax = new boolean[stops];
        for (int i = 0; i < stops; i++) {
            st = new StringTokenizer(br.readLine());
            location[i] = Integer.parseInt(st.nextToken());
            tastiness[i] = Integer.parseInt(st.nextToken());
        }
        int max = 0;
        for (int i = stops - 1; i >= 0; i--) {
            if (tastiness[i] > max) {
                isMax[i] = true;
                max = tastiness[i];
            }
        }
        long lastLoc = 0;
        long answer = 0;
        long timeFJ = 0;
        long timeB = 0;
        for (int i = 0; i < stops; i++) {
            if (isMax[i]) {
                timeFJ += (location[i] - lastLoc) * ((long) fjSpeed); //Time to fj to get to this stop
                timeB += (location[i] - lastLoc) * ((long) bSpeed); //Time for bessie to get to this stop
                answer += (timeFJ - timeB) * ((long) tastiness[i]); //Time bessie can stay times tastiness
                timeFJ = timeB; //Bessie has to wait for fj to catch up
                lastLoc = location[i]; //Update location of last stoop
            }
        }
        pw.println(answer);
        pw.close();
    }
}
