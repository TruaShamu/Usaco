import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
LANG: JAVA
TASK: contact
*/
public class contact {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("contact.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken()); //Min Length
        int B = Integer.parseInt(st.nextToken()); //Max length
        int N = Integer.parseInt(st.nextToken()); //Number of answers we're looking for

        StringBuilder build = new StringBuilder(); //The binary sequence
        String line;
        while ((line = br.readLine()) != null) {
            build.append(line);
        }
        int len = build.length();
        int[] totals = new int[(int) Math.pow(2, B + 1)];

        System.out.println("len: " + len);
        System.out.println("totals.length: " + totals.length);

        for (int i = A; i <= B; i++) { // length of sequence
            for (int j = 0; j < i; j++) { // offset of j
                for (int k = j; k <= len - i; k += i) { // Start point
                    int range = Integer.parseInt("1" + build.substring(k, k + i), 2);
                    //System.out.println(range);
                    totals[range]++; //Frequencies
                }
                //System.exit(0);
            }
        }

        int max = 0;
        for (int i = 2; i < totals.length; i++) {
            max = Integer.max(max, totals[i]);
        }

        List<List<Integer>> results = new ArrayList<>(); //Make a list of all strings with that frequency
        for (int i = 0; i <= max; i++) {
            results.add(new ArrayList<>());
        }

        for (int i = (int) Math.max(2, Math.pow(2, A)); i < totals.length; i++) {
            results.get(totals[i]).add(i);
        }

        int totalPrinted = 0;
        for (int i = max; i > 0; i--) {
            if (totalPrinted >= N) {
                //Printed all the answers
                break;
            }
            if (results.get(i).size() == 0) {
                //No numbers match this frequencies
                continue;
            }

            totalPrinted++;
            pw.println(i); //Print the frequency

            for (int j = 0; j < results.get(i).size(); j++) {
                pw.print(Integer.toString(results.get(i).get(j), 2).substring(1)); //Print the binary string
                if (j % 6 == 5 && j + 1 != results.get(i).size()) {
                    pw.println(); //Not enough space
                    continue;
                }
                if (j < results.get(i).size() - 1) {
                    pw.print(" ");
                }
            }
            pw.println();
        }
        pw.close();


    }
}
