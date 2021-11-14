import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("photo.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("photo.out")));
        int cows = Integer.parseInt(br.readLine());
        int[] cowArray = new int[cows - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] minComb = new int[cows];
        for (int i = 0; i < cows; i++) {
            minComb[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < cows - 1; i++) {
            cowArray[i] = Integer.parseInt(st.nextToken());
        }
        //Input correct
        int aPlusB = cowArray[0];
        for (int a = 1; a < aPlusB; a++) {
            int[] currentCombination = new int[cows];
            int b = aPlusB - a;
            if (a == b) {
                continue;
            }
            System.out.println("a:" + a + " b: " + b);
            currentCombination[0] = a;
            currentCombination[1] = b;
            for (int i = 2; i < cows; i++) {
                currentCombination[i] = cowArray[i - 1] - currentCombination[i - 1];
            }
            if (!solutionWorks(currentCombination)) {
                continue;
            }

            for (int i = 0; i < currentCombination.length; i++) {
                if (currentCombination[i] > minComb[i]) {
                    break;
                }
                if (currentCombination[i] < minComb[i]) {
                    minComb = currentCombination;
                    break;
                }
            }

        }
        for (int i = 0; i < minComb.length - 1; i++) {
            pw.print(minComb[i] + " ");
        }
        pw.println(minComb[minComb.length - 1]);
        pw.close();

    }

    public static boolean solutionWorks(int[] answer) {
        HashSet<Integer> noDuplicates = new HashSet<>();
        ArrayList<Integer> empty = new ArrayList<>();
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] <= 0) {
                return false;
            }
            empty.add(answer[i]);
        }
        noDuplicates.addAll(empty);
        if (noDuplicates.size() != empty.size()) {
            return false;
        }
        return true;
    }
}
