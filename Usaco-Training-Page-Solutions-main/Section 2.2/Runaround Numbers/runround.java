/*
TASK: runround
LANG: JAVA
 */

import java.io.*;

public class runround {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new FileReader("runround.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
        int number = Integer.parseInt(br.readLine());
        for (long i = number + 1; ; i++) {
            if (isRunaroundNumber(i)) {
                pw.println(i);
                pw.close();
                break;
            }

        }
        System.out.println(System.nanoTime() - startTime);


    }

    public static boolean isRunaroundNumber(long num) {
        char[] number = String.valueOf(num).toCharArray(); //Convert number to array of chars for mod
        //System.out.println(Arrays.toString(number));
        boolean[] exists = new boolean[128];
        exists['0'] = true;
        for (int i = 0; i < number.length; i++) {
            if (!exists[number[i]]) {
                exists[number[i]] = true;
                //Document all existing locs
            } else {
                //Repeating numbers
                return false;
            }
        }

        boolean[] visited = new boolean[number.length];
        int curLoc = 0;
        int count = 0;
        while (count <= number.length) {
            if (visited[curLoc]) {
                if (count < number.length) {
                    return false;
                } else {
                    return curLoc == 0;
                }
            } else {
                visited[curLoc] = true;
                //System.out.println("VISITED: " + Arrays.toString(visited));
                curLoc = (curLoc + (number[curLoc] - 48)) % number.length; //The location of the  digit of the runaround number
                //System.out.println("CURLOC: " + curLoc);
                count++;
            }
        }
        return true;
    }

}
