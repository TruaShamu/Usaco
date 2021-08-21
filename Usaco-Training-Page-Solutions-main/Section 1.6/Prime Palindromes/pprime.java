/*
TASK: pprime
LANG: JAVA
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class pprime {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("pprime.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long startTime = System.nanoTime();
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        long lower = Long.toString(a).length();
        long upper = Long.toString(b).length();
        ArrayList<Long> arr = generatePalindromes(b + 1);
        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) >= a) {
                pw.println(arr.get(i));

            }

        }

        long endTime = System.nanoTime();
        System.out.println();
        System.out.println("Took " + (endTime - startTime) + " ns");
        pw.close();

    }

    public static void FindPalindrome(ArrayList<Long> arr, long digits) {
        if (digits == 1) {
            Digit1Palindromes(arr);
        } else {
            if (digits == 2) {
                Digit2Palindrome(arr);
            } else {
                if (digits == 3) {
                    Digit3Palindrome(arr);
                } else {
                    if (digits == 4) {
                        Digit4Palindrome(arr);
                    } else {
                        if (digits == 5) {
                            Digit5Palindrome(arr);
                        } else {
                            if (digits == 6) {
                                Digit6Palindrome(arr);
                            } else {
                                if (digits == 7) {
                                    Digit7Palindrome(arr);
                                } else {
                                    Digit8Palindrome(arr);
                                }
                            }
                        }
                    }
                }
            }
        }


    }

    public static void Digit1Palindromes(ArrayList<Long> arr) {
        for (long i = 1; i < 9; i++) {
            if (isPrime(i)) {
                arr.add(i);
            }

        }
    }

    public static void Digit2Palindrome(ArrayList<Long> arr) {
        for (long i = 1; i < 9; i++) {
            long ans = (i * 10) + i;
            if (isPrime(ans)) {
                arr.add(ans);
            }
        }
    }

    public static void Digit3Palindrome(ArrayList<Long> arr) {
        for (long i = 1; i < 9; i++) {
            for (long j = 1; j < 9; j++) {
                long ans = (i * 101) + (j * 10);
                if (isPrime(ans)) {
                    arr.add(ans);
                }

            }
        }
    }

    public static void Digit4Palindrome(ArrayList<Long> arr) {
        for (long i = 1; i < 9; i++) {
            for (long j = 1; j < 9; j++) {
                long ans = i * 1010 + j * 101;
                if (isPrime(ans)) {
                    arr.add(ans);
                }
            }
        }
    }

    public static void Digit5Palindrome(ArrayList<Long> arr) {
        for (long i = 1; i < 9; i++) {
            for (long j = 1; j < 9; j++) {
                for (int k = 1; k < 9; k++) {
                    long ans = i + 10001 + j * 1010 + k * 100;
                    if (isPrime(ans)) {
                        arr.add(ans);
                    }
                }
            }
        }
    }

    public static void Digit6Palindrome(ArrayList<Long> arr) {
        for (long i = 1; i < 9; i++) {
            for (long j = 1; j < 9; j++) {
                for (int k = 1; k < 9; k++) {
                    long ans = i + 100001 + j * 10010 + k * 1100;
                    if (isPrime(ans)) {
                        arr.add(ans);
                    }
                }
            }
        }

    }

    public static void Digit7Palindrome(ArrayList<Long> arr) {
        for (long i = 1; i < 9; i++) {
            for (long j = 1; j < 9; j++) {
                for (long k = 1; k < 9; k++) {
                    for (long l = 0; l < 9; l++) {
                        long ans = i * 1000001 + j * 100010 + k * 10100 + l * 1000;
                        if (isPrime(ans)) {
                            arr.add(ans);
                        }

                    }
                }
            }
        }
    }

    public static void Digit8Palindrome(ArrayList<Long> arr) {
        for (long i = 1; i < 9; i++) {
            for (long j = 1; j < 9; j++) {
                for (long k = 1; k < 9; k++) {
                    for (long l = 0; l < 9; l++) {
                        long ans = i * 10000001 + j * 1000010 + k * 100100 + l * 11000;
                        if (isPrime(ans))
                            arr.add(ans);
                    }
                }
            }
        }

    }


    public static void FindEvenPalindromes(int upper, boolean evendigits) {
        String str = "";
        String str1 = Integer.toString(upper);
        double limit = 999;
        double x;
        if (evendigits) {
            for (int i = 0; i < str1.length() / 2; i++) {
                str += str1.charAt(i);
                limit = Integer.parseInt(str);
            }
        } else {
            x = (str1.length() - 1) / 2;
            limit = Math.pow(10, x);
        }

        for (int i = 1; i < limit; i++) {
            String pali = Integer.toString(i);
            StringBuilder sb = new StringBuilder(pali);
            pali += (sb.reverse().toString());
            System.out.println(pali);

        }
    }

    public static void FindOddPalindromes(int upper, boolean evendigits, int numOfDigits) {
        if (upper > 10) {

        } else {
            for (int i = 1; i < 9; i++) {
                System.out.println(i);
            }
        }
    }

    static long createPalindrome(int input, int b, int isOdd) {
        int n = input;
        int palin = input;

        if (isOdd == 1)
            n /= b;

        // Creates palindrome by just appending reverse
        // of number to itself
        while (n > 0) {
            palin = palin * b + (n % b);
            n /= b;
        }
        return palin;
    }

    static ArrayList<Long> generatePalindromes(long n) {
        long number;
        ArrayList<Long> arr = new ArrayList<>();
        for (int j = 0; j < 2; j++) {

            int i = 1;
            while ((number = createPalindrome(i, 10, j % 2)) < n) {
                if (isPrime(number)) {
                    arr.add(number);

                }
                i++;
            }
        }
        return arr;
    }

    static boolean isPrime(long n) {
        // Corner cases
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }
}


