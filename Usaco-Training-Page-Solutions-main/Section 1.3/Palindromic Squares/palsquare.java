/*
LANG: JAVA
TASK: palsquare
 */

import java.io.*;

public class palsquare {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
        int base = Integer.parseInt(br.readLine());
        for (int i = 1; i < 300; i++) {
            int square = i * i;
            String SquareConverted = toBaseB(square, base);
            if (isPali(SquareConverted)) {
                pw.println(toBaseB(i, base) + " " + SquareConverted);
            }

        }
        pw.close();

    }


    public static char decimalToBaseB(int input) {
        if (input >= 0 && input <= 9) {
            String str = String.valueOf(input);
            return Character.toUpperCase(str.charAt(0));
        } else {
            return Character.toUpperCase((char) ('a' + (input - 10)));
        }
    }

    public static String toBaseB(int number, int base) {
        String result = "";
        while (number > 0) {
            int remainder = number % base;
            number = number / base;
            result = decimalToBaseB(remainder) + result;
        }
        return result;
    }

    public static boolean isPali(String number) {
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


}



