import java.io.*;
import java.util.ArrayList;

public class moobuzz {
    public static ArrayList<Integer> store;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
        int N = Integer.parseInt(br.readLine());
        store = new ArrayList<>();

        for (int i = 1; i < 16; i++) {
            if (number(i)) {
                store.add(i);
            }
        }

        int answer = solve(N);
        pw.println(answer);
        pw.close();


    }


    public static boolean number(int x) {

        return (x % 3 != 0) && (x % 5 != 0);
    }


    public static int solve(int N) {
        int groups = (N - 1) / 8;
        return store.get(N - (8 * groups) - 1) + (15 * groups);
    }
}




