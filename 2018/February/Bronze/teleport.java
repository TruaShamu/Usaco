import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("teleport.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int teleport1 = Integer.parseInt(st.nextToken());
        int teleport2 = Integer.parseInt(st.nextToken());
        int noTeleport = Math.abs(x - y);
        int distance = Integer.min(Math.abs(teleport1 - x), Math.abs(teleport2 - x));
        if (distance == Math.abs(teleport1 - x)) {
            distance += Math.abs(y - teleport2);
        } else {
            distance += Math.abs(y - teleport1);
        }
        if (distance > noTeleport) {
            pw.println(noTeleport);
        } else {
            pw.println(distance);
        }
        pw.close();

    }
}
