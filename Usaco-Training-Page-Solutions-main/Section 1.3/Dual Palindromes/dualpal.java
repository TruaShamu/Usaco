/*
TASK: dualpal
LANG: JAVA
 */

import java.io.*;
import java.util.StringTokenizer;

public class dualpal {

    public static boolean isPal(String number) {
        boolean pal;
        String b = "";
        int n = number.length();
        for (int i = n - 1; i >= 0; i--) {
            b = b + number.charAt(i);
        }
        if (number.equalsIgnoreCase(b)) {
            pal = true;
        } else {
            pal = false;

        }
        return pal;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        StringTokenizer line = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(line.nextToken());
        int start = Integer.parseInt(line.nextToken());
        int count = 0;
        for (int i = (start + 1); count < num; i++) {
            int bases = 0;
            for (int base = 2; base <= 10; base++) {
                String result = Integer.toString(i, base);
                if (isPal(result)) {
                    bases++;
                }
            }
            if (bases >= 2) {
                count++;
                pw.println(i);
            }
        }
        pw.close();
    }
}
