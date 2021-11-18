import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("pails.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int amount = 0;
        int maxY = (m / y);
        int maxX = (m / x);
        for (int y1 = 0; y1 < maxY; y1++) {
            int smallBuckets = ((m - (y1 * y)) / x);
            System.out.println("Number of medium buckets: " + y1);
            System.out.println("Number of small buckets: " + smallBuckets);
            int capacityY = y1 * y;
            int capacityX = smallBuckets * x;
            int current = (capacityX + capacityY);
            System.out.println("Current: " + current);
            if (current > amount) {
                amount = current;
            }
        }
        for (int x1 = 0; x1 < maxX; x1++) {
            int largeBuckets = ((m - (x1 * x)) / y);
            System.out.println("Number of medium buckets: " + x1);
            System.out.println("Number of small buckets: " + largeBuckets);
            int capacityX = x1 * x;
            int capacityY = largeBuckets * y;
            int current = (capacityX + capacityY);
            System.out.println("Current: " + current);
            if (current > amount) {
                amount = current;
            }
        }

        pw.println(amount);
        pw.close();

    }
}
