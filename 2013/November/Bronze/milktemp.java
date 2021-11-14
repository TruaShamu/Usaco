import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int[] coldTemp;
    public static int[] hotTemp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milktemp.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milktemp.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cows = Integer.parseInt(st.nextToken()); //N
        int coldMilk = Integer.parseInt(st.nextToken()); //X
        int hotMilk = Integer.parseInt(st.nextToken()); //Y
        int medMilk = Integer.parseInt(st.nextToken()); //Z
        hotTemp = new int[cows + 1];
        coldTemp = new int[cows + 1];
        for (int i = 0; i < cows; i++) {
            st = new StringTokenizer(br.readLine());
            coldTemp[i] = Integer.parseInt(st.nextToken());
            hotTemp[i] = Integer.parseInt(st.nextToken());
        }
        coldTemp[cows] = 1000000001;
        hotTemp[cows] = 1000000001;
        Arrays.sort(coldTemp);
        Arrays.sort(hotTemp);
        int i = 0, j = 0;
        int currentMilk = coldMilk * cows;
        int answer = coldMilk * cows;
        while (i < cows || j < cows) {
            if (coldTemp[i] <= hotTemp[j]) {
                currentMilk += hotMilk - coldMilk;
                i++;
            } else {
                currentMilk += medMilk - hotMilk;
                j++;
            }
            if (currentMilk > answer)
                answer = currentMilk;
        }
        pw.println(answer);
        pw.close();


    }
}
