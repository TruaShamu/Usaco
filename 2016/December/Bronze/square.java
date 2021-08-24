import java.io.*;
import java.util.StringTokenizer;

public class square {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("square.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("square.out")));
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        minX = Integer.min(x1, minX);
        minY = Integer.min(y1, minY);
        maxX = Integer.max(x2, maxX);
        maxY = Integer.max(y2, maxY);
        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());
        minX = Integer.min(x1, minX);
        minY = Integer.min(y1, minY);
        maxX = Integer.max(x2, maxX);
        maxY = Integer.max(y2, maxY);
        int sideX = maxX - minX;
        int sideY = maxY - minY;
        int maxSide = Integer.max(sideX, sideY);
        pw.println((int) Math.pow(maxSide, 2));
        pw.close();

    }
}
