import java.io.*;
import java.util.ArrayList;

/*
TASK: frac1
LANG: JAVA
 */
public class frac1 {
    public static int maxDenominator;
    public static double[] table;
    public static double[][] decimalMatrix;
    public static fractionList fList;
    public static PrintWriter pw;

    /*
    NOTES:
    1 and 0 are special cases
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("frac1.in"));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
        long startTime = System.nanoTime();
        maxDenominator = Integer.parseInt(br.readLine());
        table = new double[maxDenominator + 1];
        decimalMatrix = new double[maxDenominator + 1][maxDenominator + 1];
        fList = new fractionList();
        if (maxDenominator == 1) {
            pw.println(0 + "/" + 1);
            pw.println("1/1");
            System.out.println(System.nanoTime() - startTime);
            pw.close();
            System.exit(0);
        }
        InitializeList();
        pw.println("0/1");
        PrintFractions();
        pw.println("1/1");
        System.out.println(System.nanoTime() - startTime);
        pw.close();
        //TestPush();


    }


    //Function for printing output. Only problem is for elements with repeated decimal.
    public static void PrintFractions() {
        while (!NotContinue()) {
            fraction oFraction = fList.Pop();
            pw.println(oFraction.numerator + "/" + oFraction.denominator);
            //PrintDecimals();
        }


    }
    public static void PrintDecimals() {
        for (int i = 0; i < fList.fractions.size(); i++) {
            fraction oFraction = fList.fractions.get(i);
            System.out.println("(" + oFraction.numerator + "/" + oFraction.denominator + ")'s decimal is " + oFraction.decimal);
        }
    }


    //Initialize the list, plugging in fractions
    public static void InitializeList() {
        for (int i = maxDenominator; i >= 2; i--) {
            fList.fractions.add(new fraction(1, i));
            //fList.fractions.get(i - 2).decimal = decimalMatrix[i][1];
        }
        //fList.sortByDecimal();

    }

    public static boolean NotContinue() {
        boolean isContinue = true;
        for (int i = 0; i < fList.fractions.size(); i++) {
            fraction fraction = fList.fractions.get(i);
            if (fraction.decimal < 1) {
                isContinue = false;
            }
        }
        return isContinue;
    }


}

class fraction {
    int denominator;

    int numerator;
    double decimal;

    public fraction(int oNumerator, int oDenominator) {
        this.numerator = oNumerator;
        this.denominator = oDenominator;

        //decimal = (double) (numerator / denominator);
        double den = denominator;
        double num = numerator;
        decimal = num / den;

    }

    public void IncreaseNumerator(int iStep) {
        setNumerator(this.numerator + iStep);
        //if (denominator == 7)
        //System.out.println(numerator + "/7 Here");
    }

    public void setNumerator(int iNumerator) {
        this.numerator = iNumerator;
        double den = this.denominator;
        double num = this.numerator;
        this.decimal = num / den;


    }

    public fraction(fraction ofranc) {
        //Testing
        this.numerator = ofranc.numerator; //+ 1;
        this.denominator = ofranc.denominator;
        double den = denominator;
        double num = numerator;
        decimal = num / den;
        //decimal = (double) (numerator / denominator);

    }

    public boolean MoveNext() {
        IncreaseNumerator(1);
        return (numerator < denominator);

    }


}

class fractionList {
   


    public fractionList() {

    }
    //2.Property

    ArrayList<fraction> fractions = new ArrayList<fraction>();

  public fraction Pop() {
        fraction ofrac = fractions.get(0);
        fraction nfrac = new fraction(ofrac.numerator, ofrac.denominator);
        fraction retfrac = new fraction(ofrac);
        //System.out.println(retfrac.numerator + "/" + retfrac.denominator);
        fractions.remove(0);

        if (nfrac.MoveNext())
            //System.out.println(nfrac.numerator + "/" + nfrac.denominator);
            Push(nfrac);

        return retfrac;
    }

    public void RemoveOriginal(int oIndex) {
        fraction ofrac = fractions.get(oIndex);
        fraction nfrac = new fraction(ofrac);

        fractions.remove(oIndex);
        if (nfrac.MoveNext()) Push(nfrac);

    }


    //Tested Push(), should be fine
    public void Push(fraction nfrac) {


        int i = fractions.size() - 1;
        fraction CompareFranc;
        int flag = 1; //Should be added in queue or not
        

        if (fractions.size() == 0) {
            fractions.add(nfrac);

        } else {

            CompareFranc = fractions.get(i);

            if (nfrac.decimal > CompareFranc.decimal) {
                int j = fractions.size();
                fractions.add(nfrac);
            } else {
                while (CompareFranc.decimal >= nfrac.decimal) {


                    if (CompareFranc.decimal == nfrac.decimal) {
                        if (CompareFranc.denominator > nfrac.denominator) {
                            RemoveOriginal(i);
                        } else {
                            nfrac.IncreaseNumerator(1);
                            if (nfrac.numerator < nfrac.denominator) {
                                Push(nfrac);

                            }
                            flag = 0; //Not Add in queue;


                        }
                    }
                    //Showsign(7, nfrac, CompareFranc);

                    if (i == 0) {
                        fractions.add(0, nfrac);
                        break;
                    } else {
                        i--;
                        CompareFranc = fractions.get(i);
                    }


                }
                if (flag == 1) {
                    fractions.add(i + 1, nfrac);
                    //ShowQueue();
                }
            }
        }


    }


}


