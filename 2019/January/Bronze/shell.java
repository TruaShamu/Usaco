import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int[] shellPos;
    public static int[] freq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shell.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shell.out")));
        int swaps = Integer.parseInt(br.readLine());
        shellPos = new int[]{1, 2, 3};
        freq = new int[3];
        for (int i = 0; i < swaps; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int shell1 = Integer.parseInt(st.nextToken()) - 1;
            int shell2 = Integer.parseInt(st.nextToken()) - 1;
            int guess = Integer.parseInt(st.nextToken()) - 1;
            swap(shell1, shell2);
            freq[shellPos[guess] - 1]++;
        }
        int max = -1;
        for (int i = 0; i < 3; i++) {
            max = Integer.max(max, freq[i]);
        }
        pw.println(max);
        pw.close();


    }

    public static void swap(int index1, int index2) {
        int shellInIndex1 = shellPos[index1];
        int shellInIndex2 = shellPos[index2];
        shellPos[index2] = shellInIndex1;
        shellPos[index1] = shellInIndex2;
    }
}
