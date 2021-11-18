import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("notlast.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("notlast.out")));
        int entries = Integer.parseInt(br.readLine());
        int[] milkArray = new int[7];
        for (int i = 0; i < entries; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();

            int amountIncrease = Integer.parseInt(st.nextToken());
            char start = name.charAt(0);

            if (start == 'B') {
                milkArray[0] += amountIncrease;
            }
            if (start == 'E') {
                milkArray[1] += amountIncrease;
            }
            if (start == 'D') {
                milkArray[2] += amountIncrease;
            }
            if (start == 'G') {
                milkArray[3] += amountIncrease;
            }
            if (start == 'A') {
                milkArray[4] += amountIncrease;
            }
            if (start == 'M') {
                milkArray[5] += amountIncrease;
            }
            if (start == 'H') {
                milkArray[6] += amountIncrease;
            }
        }
        int minMilk = Integer.MAX_VALUE;
        for (int i : milkArray) {
            minMilk = Integer.min(minMilk, i);
        }
        System.out.println("MIN MILK: " + minMilk);
        System.out.println(Arrays.toString(milkArray));
        if (allSame(milkArray, minMilk)) {
            System.out.println("dndasgajg");
            pw.println("Tie");
            pw.close();
            System.exit(0);
        }
        int answerMilk = Integer.MAX_VALUE;
        int answerMilkCount = 1;
        for (int i : milkArray) {
            if (i != minMilk) {
                if (i == answerMilk) {
                    answerMilkCount++;
                } else {
                    if (i < answerMilk) {
                        answerMilk = i;
                        answerMilkCount = 1;
                    }
                }
            }
        }
        if (answerMilkCount != 1) {

            pw.println("Tie");
            pw.close();
            System.exit(0);
        }
        int index = -1;
        for (int i = 0; i < milkArray.length; i++) {
            if (milkArray[i] == answerMilk) {
                index = i;
                break;
            }
        }
        if (index == 0) {
            pw.println("Bessie");
        }
        if (index == 1) {
            pw.println("Elsie");
        }
        if (index == 2) {
            pw.println("Daisy");
        }
        if (index == 3) {
            pw.println("Gertie");
        }
        if (index == 4) {
            pw.println("Annabelle");
        }
        if (index == 5) {
            pw.println("Maggie");
        }
        if (index == 6) {
            pw.println("Henrietta");
        }
        pw.close();


    }

    public static boolean allSame(int[] milkArray, int value) {
        for (int i : milkArray) {
            if (i != value) {
                return false;
            }
        }
        return true;
    }

}
