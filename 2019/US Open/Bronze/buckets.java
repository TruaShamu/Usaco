import java.io.*;

public class Main {
    public static int[] bucket;
    public static int[] lake;
    public static int[] rock;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("buckets.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("buckets.out")));
        bucket = new int[2]; //row, column
        lake = new int[2];
        rock = new int[2];
        for (int row = 0; row < 10; row++) {
            String inputStr = (br.readLine());
            for (int column = 0; column < 10; column++) {
                char c = inputStr.charAt(column);
                if (c != '.') {
                    if (c == 'B') {
                        bucket[0] = row;
                        bucket[1] = column;
                    }
                    if (c == 'L') {
                        lake[0] = row;
                        lake[1] = column;
                    }
                    if (c == 'R') {
                        rock[0] = row;
                        rock[1] = column;
                    }
                }
            }
        }
        int distance = findDistance();
        if (lake[0] == bucket[0] && bucket[0] == rock[0]) {
            if ((lake[1] < rock[1] && rock[1] < bucket[1]) || (bucket[1] < rock[1] && rock[1] < lake[1])) {
                pw.println(distance + 2);
                pw.close();
                System.exit(0);
            }
        }
        if (lake[1] == bucket[1] && bucket[1] == rock[1]) {
            if ((lake[0] < rock[0] && rock[0] < bucket[0]) || (bucket[0] < rock[0] && rock[0] < lake[0])) {
                pw.println(distance + 2);
                pw.close();
                System.exit(0);
            }
        }
        pw.println(distance);
        pw.close();

    }

    public static int findDistance() {
        return Math.abs(lake[0] - bucket[0]) + Math.abs(lake[1] - bucket[1]) - 1;

    }

}
