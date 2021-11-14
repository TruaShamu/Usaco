import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lemonade.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
        ArrayList<Integer> input = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(input);
        Collections.reverse(input);
        ArrayList<Integer> newList = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < N; i++) {
            // System.out.println("VALUE: " + input.get(i));
            if (newList.size() > input.get(i)) {
                pw.println(count);
                pw.close();
                System.out.println(count);
                System.exit(0);
            } else {
                count++;
                newList.add(input.get(i));
            }
        }
        pw.println(count);
        pw.close();
    }
}
