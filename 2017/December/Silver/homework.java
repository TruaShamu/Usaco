import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("homework.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
        int pages = Integer.parseInt(br.readLine());
        int[] input = new int[pages];
        int[] suffixSum = new int[pages];
        int[] min = new int[pages];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < pages; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < input.length; i++) {
            min[i] = input[i];
            suffixSum[i] = input[i];
        }


        for (int i = pages - 2; i >= 0; i--) {
            suffixSum[i] += suffixSum[i + 1];
            min[i] = Integer.min(min[i + 1], input[i]);
        }
        System.out.println(Arrays.toString(suffixSum));
        System.out.println(Arrays.toString(min));
        int k = 0;

        int bestNum = 0;
        int bestDen = 1;
        ArrayList<Integer> answer = new ArrayList<>();
        double value = -1;

        for (int i = 1; i <= pages - 2; i++) {
            int raw = suffixSum[i] - min[i];
            int den = (pages - i - 1);
            double cur = (double) raw / (double) den;
            //System.out.println(cur);
            if (cur > value) {
                answer.clear();
                answer.add(i);
                value = cur;
            }
            if (cur == value) {
                answer.add(i);
            }
        }
        System.out.println("DELETED: " + k);
        HashSet<Integer> set = new HashSet<>(answer);
        for (int i : set) {
            pw.println(i);
        }
        //pw.println(k);
        pw.close();


    }
}
