
/*
LANG: JAVA
PROG: lamps
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class lamps {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("lamps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
        int N = Integer.parseInt(br.readLine());
        int presses = Integer.parseInt(br.readLine());
        StringTokenizer line1 = new StringTokenizer(br.readLine());
        StringTokenizer line2 = new StringTokenizer(br.readLine());
        List<Integer> finalOn = new ArrayList<>();
        List<Integer> finalOff = new ArrayList<>();
        while (line1.hasMoreTokens()) {
            int token = Integer.parseInt(line1.nextToken());
            if (token == -1)
                break;
            finalOn.add(token);
        }
        while (line2.hasMoreTokens()) {
            int token = Integer.parseInt(line2.nextToken());
            if (token == -1)
                break;
            finalOff.add(token);
        }
        TreeSet<String> works = new TreeSet<>();
        String[] solutions = new String[]{"111111", "000000", "010101", "101010", "011011", "100100", "110001", "001110"};
        if (presses % 6 == 0) {
            works.add(solutions[0]);
            if (presses > 0) {
                works.add(solutions[1]);
                works.add(solutions[2]);
                works.add(solutions[3]);
                works.add(solutions[4]);
                works.add(solutions[5]);
                works.add(solutions[6]);
                works.add(solutions[7]);
            }
        } else if (presses % 6 == 1) {
            works.add(solutions[1]);
            works.add(solutions[2]);
            works.add(solutions[3]);
            works.add(solutions[4]);
        } else if (presses % 6 == 2) {
            works.add(solutions[0]);
            works.add(solutions[1]);
            works.add(solutions[2]);
            works.add(solutions[3]);
            works.add(solutions[5]);
            works.add(solutions[6]);
            works.add(solutions[7]);
        } else if (presses % 6 == 5) {
            works.add(solutions[0]);
            works.add(solutions[4]);
            works.add(solutions[5]);
            works.add(solutions[6]);
            works.add(solutions[7]);
        } else {
            works.add(solutions[0]);
            works.add(solutions[1]);
            works.add(solutions[2]);
            works.add(solutions[3]);
            works.add(solutions[4]);
            works.add(solutions[5]);
            works.add(solutions[6]);
            works.add(solutions[7]);
        }
        List<String> results = new ArrayList<>();
        results.addAll(works);
        boolean isResult = false;
        for (int i = 0; i < results.size(); i++) {
            char[] temp = results.get(i).toCharArray();
            boolean good = true;
            System.out.println(temp);
            for (int test : finalOn) {
                if (temp[(test - 1) % 6] != '1') {
                    good = false;
                    break;
                }
            }
            for (int test : finalOff) {
                if (temp[(test - 1) % 6] != '0') {
                    good = false;
                    break;
                }
            }
            if (good) {
                isResult = true;
                for (int j = 0; j < N; j++) {
                    out.print(temp[j % 6]);
                }
                out.println();
            }
        }
        if (!isResult) {
            out.println("IMPOSSIBLE");
        }
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
