import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("blocks.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("blocks.out")));
        int lines = Integer.parseInt(br.readLine());
        int[] charFreq = new int[26];
        for (int i = 0; i < lines; i++) {
            String[] lineArray = br.readLine().split(" ");
            int[][] curCharFreq = new int[2][26];
            for (int j = 0; j < lineArray[0].length(); j++) {
                int curIndex = lineArray[0].charAt(j) - 97;
                //System.out.println(lineArray[0].charAt(j));
                curCharFreq[0][curIndex]++;
            }
            for (int j = 0; j < lineArray[1].length(); j++) {
                int curIndex = lineArray[1].charAt(j) - 97;
                curCharFreq[1][curIndex]++;
            }
            for (int j = 0; j < 26; j++) {
                charFreq[j] += Integer.max(curCharFreq[0][j], curCharFreq[1][j]);
            }
        }
        System.out.println(Arrays.toString(charFreq));
        for (int i = 0; i < charFreq.length - 1; i++) {
            pw.println(charFreq[i]);
        }
        pw.println(charFreq[charFreq.length - 1]);
        pw.close();

    }
}
