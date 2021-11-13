import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowsignal.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowsignal.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());
        int expansionFactor = Integer.parseInt(st.nextToken());
        for (int i = 0; i < rows; i++) {
            String inputString = br.readLine();
            String outputString = "";
            for (int j = 0; j < inputString.length(); j++) {
                for (int ex = 0; ex < expansionFactor; ex++) {
                    outputString += inputString.charAt(j);
                }

            }
            for (int ex = 0; ex < expansionFactor; ex++) {
                pw.println(outputString);
            }

            outputString = "";
        }
        pw.close();

    }
}
