import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
TASK: hamming
LANG: JAVA
LINK: https://train.usaco.org/usacoprob2?a=DrTO6GX1LvD&S=hamming
 */
public class hamming {
    public static int NumberOfCodewords;
    public static int BitLength;
    public static int MinimumDistance;
    public static NumberList numberList;
    public static long startTime;
    public static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new FileReader("hamming.in"));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        NumberOfCodewords = Integer.parseInt(st.nextToken());
        BitLength = Integer.parseInt(st.nextToken());
        MinimumDistance = Integer.parseInt(st.nextToken());
        numberList = new NumberList();
        WrapUp();

    }

    public static String ConvertToBinary(int DecimalNumber) {
        //Converts a decimal number to a binary number
        String binary = Integer.toBinaryString(DecimalNumber);
        int difference = BitLength - binary.length();
        String empty = "";
        StringBuilder EmptyString = new StringBuilder(empty);
        for (int i = 0; i < difference; i++) {
            EmptyString.append("0");
        }
        empty = EmptyString.toString();
        empty += binary;
        return empty;
    }

    public static boolean CheckHamming(String Binary1, String Binary2) {
        //Checks if hamming distance between 2 binary numbers is greater than minimum distance
        int DifferenceCount = 0;
        for (int i = 0; i < Binary1.length(); i++) {
            if (Binary1.charAt(i) != Binary2.charAt(i)) {
                DifferenceCount++;
                if (DifferenceCount == MinimumDistance) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean CheckFulfillRequirements(int DecimalNumber) {
        String BinaryString = ConvertToBinary(DecimalNumber);
        for (Number oNumber : numberList.numbers) {
            if (!CheckHamming(BinaryString, oNumber.BinaryString)) {
                return false;
            }
        }
        return true;
    }

    public static void WrapUp() {
        double BitDouble = BitLength;
        numberList.numbers.add(new Number(ConvertToBinary(0), 0));
        double upperBound = Math.pow(2, BitDouble);

        for (double i = 0; i < upperBound; i++) {
            //System.out.println("hello");
            if (numberList.numbers.size() == NumberOfCodewords) {
                PrintOutput();
                break;
            } else {
                int decimalNumber = (int) i;
                //System.out.println(i);
                if (CheckFulfillRequirements(decimalNumber)) {
                    //System.out.println("fulfills");
                    numberList.numbers.add(new Number(ConvertToBinary(decimalNumber), decimalNumber));
                }
            }
            if (numberList.numbers.size() == NumberOfCodewords) {
                PrintOutput();
                long endTime = System.nanoTime();
                System.out.println("Took " + (endTime - startTime) + " ns");
                break;
            }

        }

    }

    public static void PrintOutput() {
        int count = 0;
        for (int i = 0; i < numberList.numbers.size(); i++) {
            Number oNumber = numberList.numbers.get(i);
            if (count == 9) {
                pw.print(oNumber.DecimalNumber);
                pw.println();
                count = 0;
                continue;


            }
            if (count < 9) {
                if (i == numberList.numbers.size() - 1) {
                    pw.println(oNumber.DecimalNumber);
                } else {
                    pw.print(oNumber.DecimalNumber + " ");
                }
                count++;

            }

        }
        //pw.println(numberList.numbers.get(numberList.numbers.size() - 1).DecimalNumber);
        pw.close();

    }


}

class Number {
    //Our Class
    String BinaryString;
    int DecimalNumber;

    public Number(String oBinaryString, int oDecimalNumber) {
        this.BinaryString = oBinaryString;
        this.DecimalNumber = oDecimalNumber;

    }
}

class NumberList {

    public NumberList() {

    }
    //2.Property

    ArrayList<Number> numbers = new ArrayList<Number>();

    //3. Method
    //a. Set /Get
    //b. General Function(Method>
}
