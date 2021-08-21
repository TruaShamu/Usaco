/*
TASK: namenum
LANG: JAVA
 */

import java.io.*;


public class namenum {
    public static void main(String[] args) throws IOException {

        BufferedReader br1 = new BufferedReader(new FileReader("namenum.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
        String cowString = br1.readLine();
        long cowcode = Long.parseLong(cowString);
        FileInputStream fstream = new FileInputStream("dict.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        int flgCount = 0;
        while ((strLine = br.readLine()) != null) {
            if (strLine.length() == cowString.length()) {
                //System.out.println(strLine);
                NameX oName = new NameX(strLine);
                if (oName.getValue() == cowcode) {
                    pw.println(oName.phrase);

                    flgCount++;
                }

            }
        }

        fstream.close();

        if (flgCount == 0) {
            pw.println("NONE");

        }
        pw.close();
    }

    public static class NameX {
        String phrase;
        double value;


        public NameX(String phrase) {
            this.phrase = phrase;
            this.value = ValueSetup();
        }


        public double getValue() {
            return value;
        }

        public double ValueSetup() {

            double mvalue = 0;
            for (int i = 0; i < phrase.length(); i++) {
                char currentChar = phrase.charAt(i);
                int curCodeInt;

                if ((currentChar == 'Q') || (currentChar == 'Z')) {
                    return -1;
                }


                if (currentChar < 'Q')
                    curCodeInt = ((currentChar - 'A') / 3) + 2;

                else
                    curCodeInt = ((currentChar - 'A' - 1) / 3) + 2;

                mvalue = mvalue * 10 + curCodeInt;
            }


            return mvalue;
        }

    }
}
