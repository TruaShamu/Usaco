
/*
LANG: JAVA
TASK: friday
*/

import java.io.*;

public class friday {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("friday.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
        int N = Integer.parseInt(br.readLine());

        int curDate = 0;
        int start = 1900;
        int end = start + N;


        int[] normalDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] leapYearDays = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] ans = new int[7];


        for (int y = start; y < end; y++) {
            for (int m = 0; m < 12; m++) {
                int day = (curDate + 12) % 7;
                ans[day]++;
                if (leap(y)) {
                    curDate += leapYearDays[m];
                } else {
                    curDate += normalDays[m];
                }

            }
        }
        pw.println(ans[5] + " " + ans[6] + " " + ans[0] + " " + ans[1] + " " + ans[2] + " " + ans[3] + " " + ans[4]);
        pw.close();


    }


    public static boolean leap(int year) {
        if (year % 100 == 0) {
            return (year % 400 == 0);
        } else {
            return year % 4 == 0;
        }
    }
}
