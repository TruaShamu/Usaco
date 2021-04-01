import java.io.*;
import java.util.StringTokenizer;

public class herding {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("herding.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cowArray = new int[3];
        for (int i = 0; i < 3; i++) {
            cowArray[i] = Integer.parseInt(st.nextToken());
        }
        int gap = findMaxGap(cowArray) - 1;
        if (cowArray[2] == cowArray[0] + 2) {
            pw.println(0);
        } else {
            if (cowArray[1] == cowArray[0] + 2 || cowArray[2] == cowArray[1] + 2) {
                pw.println(1);
            } else {
                pw.println(2);
            }
        }
        pw.println(gap);
        pw.close();
    }

    public static int findMinGap(int[] cowArray) {
        int minGap = Integer.MAX_VALUE;
        for (int i = 1; i < 3; i++) {
            if (cowArray[i] - cowArray[i - 1] != 1) {
                minGap = Integer.min(cowArray[i] - cowArray[i - 1], minGap);
            }
        }
        return minGap;
    }

    public static int findMaxGap(int[] cowArray) {
        int maxGap = Integer.MIN_VALUE;
        for (int i = 1; i < 3; i++) {
            maxGap = Integer.max(cowArray[i] - cowArray[i - 1], maxGap);
        }
        return maxGap;
    }
}

