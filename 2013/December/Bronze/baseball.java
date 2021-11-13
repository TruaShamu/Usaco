import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("baseball.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("baseball.out")));
        int cows = Integer.parseInt(br.readLine());
        int count = 0;
        ArrayList<Integer> cowList = new ArrayList<>();
        for (int i = 0; i < cows; i++) {
            cowList.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(cowList);
        System.out.println(cowList);

        for (int cow1 = 0; cow1 < cowList.size() - 2; cow1++) {
            for (int cow2 = cow1 + 1; cow2 < cowList.size() - 1; cow2++) {
                if (cow2 == cow1) {
                    continue;
                }

                for (int cow3 = cow2 + 1; cow3 < cowList.size(); cow3++) {
                    if (cow3 == cow1 || cow3 == cow2) {
                        continue;
                    }

                    int firstGap = cowList.get(cow2) - cowList.get(cow1);
                    int secondGap = cowList.get(cow3) - cowList.get(cow2);
                    if (secondGap >= firstGap && secondGap <= (2 * firstGap)) {

                        count++;

                    }


                }
            }
        }
        pw.println(count);
        pw.close();

    }
}
