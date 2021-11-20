import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int diamonds = Integer.parseInt(st.nextToken());
        int maxGap = Integer.parseInt(st.nextToken());
        int[] array = new int[diamonds];
        for (int i = 0; i < diamonds; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(array);
        int maxCount = 0;
        for (int i = 0; i < diamonds - 1; i++) {
            int min = array[i];
            int curCount = 1;
            for (int j = i + 1; j < diamonds; j++) {
                if (array[j] - min <= maxGap) {
                    curCount++;
                } else {
                    break;
                }
            }
            maxCount = Integer.max(curCount, maxCount);
        }
        System.out.println("Answer:" + maxCount);
        pw.println(maxCount);
        pw.close();

    }
}
