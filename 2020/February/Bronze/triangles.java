import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
        int points = Integer.parseInt(br.readLine());
        int[] xCoord = new int[points];
        int[] yCoord = new int[points];
        for (int i = 0; i < points; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            xCoord[i] = x;
            yCoord[i] = y;
        }

        int maxArea = -1;
        for (int i = 0; i < points; i++) {
            int pivotX = xCoord[i];
            int pivotY = yCoord[i];
            int maxXDistance = 0;
            int maxYDistance = 0;
            for (int point = 0; point < points; point++) {
                if (xCoord[point] == pivotX) {
                    maxYDistance = Integer.max(maxYDistance, Math.abs(yCoord[point] - pivotY));
                }
                if (yCoord[point] == pivotY) {
                    maxXDistance = Integer.max(maxXDistance, Math.abs(xCoord[point] - pivotX));
                }

            }
            int area = maxXDistance * maxYDistance;
            maxArea = Integer.max(area, maxArea);

        }

        System.out.println("ANSWER: " + maxArea);
        pw.println(maxArea);
        pw.close();

    }


}
