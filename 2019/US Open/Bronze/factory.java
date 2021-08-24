import java.io.*;
import java.util.StringTokenizer;

public class factory {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("factory.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("factory.out")));
        int stations = Integer.parseInt(br.readLine());
        int[] inFlow = new int[stations]; //Flow to
        int[] outFlow = new int[stations]; //Flow from
        for (int walkway = 0; walkway < stations - 1; walkway++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int flowFrom = Integer.parseInt(st.nextToken()) - 1;
            int flowTo = Integer.parseInt(st.nextToken()) - 1;
            inFlow[flowTo]++;
            outFlow[flowFrom]++;
        }

        int station = -1;
        for (int i = 0; i < stations; i++) {
            if (inFlow[i] > 0 && outFlow[i] == 0) {
                if (station != -1) {
                    pw.println(-1);
                    pw.close();
                    System.exit(0);
                } else {
                    station = i + 1;
                }
            }
        }
        pw.println(station);
        pw.close();

    }
}
