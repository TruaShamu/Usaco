import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("maxcross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalSignals = Integer.parseInt(st.nextToken());
        int continuousSignals = Integer.parseInt(st.nextToken());
        int badSignals = Integer.parseInt(st.nextToken());
        int[] signalArray = new int[totalSignals];
        for (int i = 0; i < badSignals; i++) {
            signalArray[Integer.parseInt(br.readLine()) - 1] = 1;
        }
        for (int i = 1; i < signalArray.length; i++) {
            signalArray[i] += signalArray[i - 1];
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < signalArray.length - continuousSignals; i++) {
            answer = Integer.min(answer, signalArray[i + continuousSignals] - signalArray[i]);
        }
        System.out.println("ANSWER: " + answer);
        pw.println(answer);
        pw.close();


    }
}
