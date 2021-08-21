/*
LANG: JAVA
PROG: prefix
*/


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class prefix {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("prefix.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
        List<String> primitives = new ArrayList<>();
        //Read primitives
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals("."))
                break;
            StringTokenizer l = new StringTokenizer(line);
            while (l.hasMoreTokens()) {
                String temp = l.nextToken();
                if (solve(temp, primitives) < temp.length()) {
                    primitives.add(temp);
                } else {
                    System.out.println("false");
                }
            }
        }
        System.out.println(primitives);
        //Finish read primitives


        //Read sequence
        StringBuilder S = new StringBuilder();
        while ((line = br.readLine()) != null) {
            S.append(line);
        }
        System.out.println(S.toString());
        //Finish read sequence
        out.println(solve(S.toString(), primitives));
        br.close();
        out.close();
        System.out.println(System.currentTimeMillis() - startTime);

    }

    public static int solve(String S, List<String> primitives) {
        int len = S.toCharArray().length;
        int maxLength = 0;
        boolean[] available = new boolean[200004];
        available[0] = true;
        for (int i = 0; i < len; i++)
            if (available[i]) {
                for (String primitive : primitives) {
                    
                    if (i + primitive.length() <= len && S.substring(i, i + primitive.length()).equals(primitive)) {
                        maxLength = Math.max(maxLength, i + primitive.length());
                        available[i + primitive.length()] = true;
                    }
                }
            }
        return maxLength;
    }
}
