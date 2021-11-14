import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int[] cowLocs;
    public static int cows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
        cows = Integer.parseInt(br.readLine());
        int[] shuffle = new int[cows];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cows; i++) {
            shuffle[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        cowLocs = new int[cows];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cows; i++) {
            cowLocs[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < 3; i++) {
            unShuffle(shuffle);
        }
        for (int i = 0; i < cowLocs.length; i++) {
            pw.println(cowLocs[i]);
        }

        pw.close();
    }

    public static void unShuffle(int[] shuffle) {
        int[] newArray = new int[cows];
        for (int i = 0; i < shuffle.length; i++) {
            newArray[i] = cowLocs[shuffle[i]];
        }
        cowLocs = newArray;
    }

}
