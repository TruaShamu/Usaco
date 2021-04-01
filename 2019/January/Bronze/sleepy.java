import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
        int cows = Integer.parseInt(br.readLine());
        int[] cowArray = new int[cows];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cowArray.length; i++) {
            cowArray[i] = Integer.parseInt(st.nextToken());
        }
        int count = 1;
        for (int i = cowArray.length - 1; i > 0; i--) {
            System.out.println("i: " + i);
            if (cowArray[i - 1] < cowArray[i]) {
                count++;
            } else {
                break;
            }
        }
        System.out.println(count);
        pw.println(cowArray.length - count);
        pw.close();

    }
}
