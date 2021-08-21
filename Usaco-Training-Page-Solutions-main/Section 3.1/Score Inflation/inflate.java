import java.io.*;
import java.util.StringTokenizer;

/*
LANG: JAVA
TASK: inflate
*/
public class inflate {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inflate.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int time = Integer.parseInt(st.nextToken());
        int[] data = new int[time + 1];
        int classes = Integer.parseInt(st.nextToken());
        for (int i = 0; i < classes; i++) {
            st = new StringTokenizer(br.readLine());
            int points = Integer.parseInt(st.nextToken());
            int minutes = Integer.parseInt(st.nextToken());
            for (int j = minutes; j <= time; j++) {
                if (data[j] < data[j - minutes] + points) {
                    data[j] = data[j - minutes] + points;
                }
            }

        }
        pw.println(data[time]);
        pw.close();
    }
}
