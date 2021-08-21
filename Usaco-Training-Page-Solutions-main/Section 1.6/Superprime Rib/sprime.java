import java.io.*;
import java.util.ArrayList;

/*
TASK: sprime
LANG: JAVA
 */
public class sprime {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sprime.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
        long startTime = System.nanoTime();
        ArrayList<Long> ribList = new ArrayList<>();
        int ribs = Integer.parseInt(br.readLine());
        //System.out.println("Number of ribs = " + ribs);
        int[] singlePrimes = {2, 3, 5, 7};
        int[] otherPrimes = {1, 3, 7, 9};
        if (ribs == 1) {
            for (int i : singlePrimes) {
                pw.println(i);
            }
        } else {
            for (int i : singlePrimes) {
                FindRib(ribList, ribs, i, otherPrimes);
            }
            for (int i = 0; i < ribList.size(); i++) {
                pw.println(ribList.get(i));
            }

        }
        pw.close();

        long runningTime = System.nanoTime() - startTime;
        // System.out.println(" Total time = " + runningTime + "ns");

    }

    static boolean isPrime(long n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }

    public static void FindRib(ArrayList<Long> ribList, int digits, long number, int[] otherPrimes) {
        // ArrayList<Long> ribList = new ArrayList<>();
        int currentDigit = Long.toString(number).length();
        //System.out.println("Number of digits in " + number + " is " + currentDigit);
        if (currentDigit < digits) {
            for (int i : otherPrimes) {
                long newrib = (number * 10) + i;
                if (isPrime(newrib)) {
                    FindRib(ribList, digits, newrib, otherPrimes);
                }
            }
        } else {
            ribList.add(number);
        }
    }

}
