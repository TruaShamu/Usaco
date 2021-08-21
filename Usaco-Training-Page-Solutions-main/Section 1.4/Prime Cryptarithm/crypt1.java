/*
TASK: crypt1
LANG: JAVA
 */

import java.io.*;
import java.util.StringTokenizer;

public class crypt1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
        int N = Integer.parseInt(br.readLine());

        int[] array = new int[N];
        int count = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        for (int a = 100; a < 999; a++) {
            for (int b = 10; b < 99; b++) {
                int c = a * b;
                String strA = Integer.toString(a);
                String strB = Integer.toString(b);
                int d = Character.getNumericValue(strB.charAt(0)) * a;
                int e = Character.getNumericValue(strB.charAt(1)) * a;
                if (Integer.toString(d).length() == 3 && Integer.toString(e).length() == 3 && isComb(d, array) && isComb(e, array)) {
                    if (isComb(a, array) && isComb(b, array)) {
                        if (isComb(c, array)) {
                            if (Integer.toString(c).length() == 4) {
                                count++;
                            }
                        }
                    }
                }
            }
        }

        pw.println(count);
        pw.close();
    }

    public static boolean isComb(int a, int[] array) {
        boolean isComb = false;
        String str = Integer.toString(a);
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < array.length; j++) {
                if (Character.getNumericValue(str.charAt(i)) == array[j]) {
                    isComb = true;
                    break;
                } else {
                    isComb = false;
                }
            }
            if (isComb == false) {
                break;
            }

        }
        return isComb;
    }
}
