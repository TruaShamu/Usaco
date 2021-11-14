import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("highcard.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
        int cards = Integer.parseInt(br.readLine());
        boolean[] elsieOwns = new boolean[2 * cards + 1];
        for (int i = 0; i < cards; i++) {
            elsieOwns[Integer.parseInt(br.readLine())] = true;
        }
        ArrayList<Integer> bessie = new ArrayList<Integer>();
        ArrayList<Integer> elsie = new ArrayList<Integer>();
        int points = 0;
        for (int i = 1; i <= 2 * cards; i++) {
            if (elsieOwns[i]) {
                elsie.add(i);
            } else {
                bessie.add(i);
            }
        }
        int elsieIndex = 0;
        int bessieIndex = 0;
        while (bessieIndex < cards && elsieIndex < cards) {
            if (bessie.get(bessieIndex) > elsie.get(elsieIndex)) {
                points++;
                bessieIndex++;
                elsieIndex++;
            } else {
                bessieIndex++;
            }
        }
       pw.println(points);
        pw.close();
    }
}
