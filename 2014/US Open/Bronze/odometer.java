import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static long upperBoundLength;
    public static long lowerBoundLength;
    public static long upperBound;
    public static long lowerBound;
    public static int totalCount;
    //public static int TestLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("odometer.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("odometer.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        lowerBound = Long.parseLong(st.nextToken());
        upperBound = Long.parseLong(st.nextToken());

        lowerBoundLength = Long.toString(lowerBound).length();
        upperBoundLength = Long.toString(upperBound).length();

        //upperBoundLength = 4;
        //System.out.println(Numberize(1, 9, 2, 3));
        findComb();
        pw.println(totalCount);
        pw.close();

    }


    public static void checkIfInInterval(int[] int1) {
        String s = "";
        for (int i = 0; i < int1.length; i++) {
            s += Integer.toString(int1[i]);
        }

        long num = Long.parseLong(s);
        if (lowerBound <= num && upperBound >= num && s.charAt(0) != 0) {
            totalCount++;
        }

    }


    public static long Numberize(int seed, int secondnum, int slotnum, long strLength) {
        long Target = 0;
        if (slotnum > strLength - 1) return 0;
        for (int i = 0; i <= strLength - 1; i++) {
            if (i == slotnum)

                Target += secondnum * Math.pow(10, (strLength - i - 1));
            else
                Target += seed * Math.pow(10, (strLength - i - 1));

            //System.out.println(Target);
        }

        return Target;
    }

    public static long NumberizeV(int seed, int secondnum, int slotnum, long strLength) {
        String Target = "";
        for (int i = 0; i < strLength - 1; i++) {
            if (i == slotnum) {
                if (i == 0 && secondnum == 0) {
                    return -1;
                }
                Target += Integer.toString(secondnum);
            } else {
                if (i == 0 && seed == 0)
                    continue;
                else
                    Target += Integer.toString(seed);
            }
        }


        return Long.parseLong(Target);
    }


    public static void findComb() {
        totalCount = 0;
        for (long strlength = lowerBoundLength; strlength <= upperBoundLength; strlength++) {


            for (int seed = 0; seed <= 9; seed++) { //Choose seed number
                for (int slotnum = 0; slotnum <= strlength; slotnum++) {

                    for (int i = 0; i <= 9; i++) {
                        if (slotnum == 0 && i == 0) continue;

                        if (seed == i) continue;

                        long TargetNum = Numberize(seed, i, slotnum, strlength);
                        if (TargetNum < lowerBound) continue;
                        if (TargetNum > upperBound) break;


                        if (TargetNum < Math.pow(10, strlength - 1)) break;

                        totalCount++;
                    }

                }


            }

        }
    }
}
