import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("crossroad.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("crossroad.out")));
        int cows = Integer.parseInt(br.readLine());
        int[] cowArray = new int[cows + 1];
        int crossings = 0;
        for (int i = 0; i < cowArray.length; i++) {
            cowArray[i] = -1;
        }
        System.out.println(Arrays.toString(cowArray));
        for (int i = 0; i < cows; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cow = Integer.parseInt(st.nextToken());
            int side = Integer.parseInt(st.nextToken());
            if (cowArray[cow] != -1) {
                if (cowArray[cow] != side) {
                    System.out.println("CURRENT: " + cowArray[cow] + " NEW: " + side);
                    crossings++;
                    System.out.println("cow: " + cow + " i: " + i);
                }
            }
            cowArray[cow] = side;
            //System.out.println(Arrays.toString(cowArray));
            System.out.println();


        }
        System.out.println("ANSWER: " + crossings);
        pw.println(crossings);
        pw.close();

    }
}
