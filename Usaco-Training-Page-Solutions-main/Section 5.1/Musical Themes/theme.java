/*
TASK: theme
LANG: JAVA
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class theme {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("theme.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("theme.out")));
        int[] sequence = new int[5000];
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();
        int count = 0;
        //Read input
        while (input != null) {
            StringTokenizer st = new StringTokenizer(input);
            while (st.hasMoreTokens()) {
                sequence[count] = Integer.parseInt(st.nextToken());
                count++;
            }
            input = br.readLine();
        }
        System.out.println(Arrays.toString(sequence));
        int longest = 1;

        for (int i = 1; i < n; ++i) {
            int length = 1;
            for (int j = n - i - 1 - 1; j >= 0; --j) {
                if (sequence[j] - sequence[j + 1] == sequence[j + i] - sequence[j + i + 1]) {
                    ++length;
                    if (length > i)
                        length = i;
                    if (longest < length)
                        longest = length;
                } else {
                    length = 1;
                }
            }
        }
        System.out.println((longest >= 5) ? longest : 0);
        pw.println((longest >= 5) ? longest : 0);
        pw.close();

    }
}

