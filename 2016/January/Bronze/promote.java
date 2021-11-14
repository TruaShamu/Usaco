import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("promote.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("promote.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int beforeBronze = Integer.parseInt(st.nextToken());
        int afterBronze = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int beforeSilver = Integer.parseInt(st.nextToken());
        int afterSilver = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int beforeGold = Integer.parseInt(st.nextToken());
        int afterGold = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int beforePlatinum = Integer.parseInt(st.nextToken());
        int afterPlatinum = Integer.parseInt(st.nextToken());
        int promoteFromGold = afterPlatinum - beforePlatinum;
        int promoteFromSilver = afterGold - beforeGold + (promoteFromGold);
        int promoteFromBronze = promoteFromGold + afterGold - beforeGold + afterSilver - beforeSilver;
        pw.println(promoteFromBronze);
        pw.println(promoteFromSilver);
        pw.println(promoteFromGold);
        pw.close();

    }
}
