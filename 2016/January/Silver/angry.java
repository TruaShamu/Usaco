import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int bales = Integer.parseInt(st.nextToken());
        int cows = Integer.parseInt(st.nextToken());
        int[] locArray = new int[bales];
        for (int i = 0; i < bales; i++) {
            locArray[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(locArray);
        int lowerBound = 0;
        int upperBound = locArray[locArray.length - 1] / 2;
        while (lowerBound != upperBound) {
            int avg = (lowerBound + upperBound) / 2;
            int used = 0;
            int last = 0;
            while (last < bales) {
                used++;
                int curr = last + 1;
                while (curr < bales && locArray[curr] - locArray[last] <= 2 * avg) {
                    curr++;
                }
                last = curr;
            }
            if (used <= cows) {
                upperBound = avg;
            } else {
                lowerBound = avg + 1;
            }
        }
        pw.println(lowerBound);
        pw.close();

    }
}
