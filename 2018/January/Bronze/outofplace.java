import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("outofplace.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("outofplace.out")));
        int cows = Integer.parseInt(br.readLine());
        int[] cowArray = new int[cows];
        int[] sortedArray = new int[cows];
        for (int i = 0; i < cows; i++) {
            //Read in cows
            cowArray[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < cows; i++) {
            //Copy from array 1 to array 2
            sortedArray[i] = cowArray[i];
        }
        Arrays.sort(sortedArray);
        int answerCount = 0;
        for (int i = 0; i < cows; i++) {
            if (sortedArray[i] != cowArray[i]) {
                answerCount++;
            }
        }
        pw.println(answerCount - 1);
        pw.close();

    }
}
