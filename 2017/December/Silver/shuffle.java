import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
        int cows = Integer.parseInt(br.readLine()); //n
        int[] array = new int[cows];
        int[] recipient = new int[cows];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cows; i++) {
            array[i] = Integer.parseInt(st.nextToken()) - 1;
            recipient[array[i]]++;
        }
        int ret = cows;
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < cows; i++) {
            if (recipient[i] == 0) {
                q.add(i);
                ret--;
            }
        }
        while (!q.isEmpty()) {
            int curr = q.removeFirst();
            if (--recipient[array[curr]] == 0) {
                q.add(array[curr]);
                ret--;
            }
        }
        pw.println(ret);
        pw.close();
    }

}

