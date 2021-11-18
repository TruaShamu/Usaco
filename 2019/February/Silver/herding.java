import java.io.*;
import java.util.Arrays;

public class Main {
    public static int cows;
    public static int[] cowArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("herding.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));
        cows = Integer.parseInt(br.readLine());
        cowArray = new int[cows];
        for (int i = 0; i < cows; i++) {
            cowArray[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(cowArray);
        int min = findMin();
        int max = Integer.max(cowArray[cows - 2] - cowArray[0], cowArray[cows - 1] - cowArray[1]) - (cows - 2);
        pw.println(min);
        pw.println(max);
        pw.close();

    }

    public static int findMin() {
        if (cowArray[cows - 2] - cowArray[0] == cows - 2 && cowArray[cows - 1] - cowArray[cows - 2] > 2) return 2;
        if (cowArray[cows - 1] - cowArray[1] == cows - 2 && cowArray[1] - cowArray[0] > 2) return 2;
        int i, j = 0, best = 0;
        for (i = 0; i < cows; i++) {
            while (j < cows - 1 && cowArray[j + 1] - cowArray[i] <= cows - 1) j++;
            best = Integer.max(best, j - i + 1);
        }
        return cows - best;
    }
}
