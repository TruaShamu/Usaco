import java.io.*;
import java.util.StringTokenizer;

public class gymnastics {
    public static int rows;
    public static int columns;
    public static int[][] inputArray;
    public static int[][] cowPositionArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("gymnastics.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gymnastics.out")));
        long startTime = System.nanoTime();
        StringTokenizer st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken());
        columns = Integer.parseInt(st.nextToken());
        inputArray = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            StringTokenizer str = new StringTokenizer(br.readLine());
            for (int column = 0; column < columns; column++) {
                inputArray[row][column] = Integer.parseInt(str.nextToken());
            }
        }
        PrintArray(inputArray);
        //Set up position array
        cowPositionArray = new int[rows][columns];

        for (int cowID = 0; cowID < columns; cowID++) {
            System.out.println("Current cow =" + cowID);
            for (int session = 0; session < rows; session++) {
                System.out.println("Curremt session = " + session);
                int location = 0;
                for (int column = 0; column < columns; column++) {

                    if (inputArray[session][column] == cowID + 1) {
                        System.out.println("Cow " + cowID + " WAS found at column " + column + " for session " + session);
                        location = column;
                        break;
                    }

                }
                cowPositionArray[session][cowID] = location;

            }
        }
        PrintArray(cowPositionArray);
        pw.println(ConsistentPairCount());
        pw.close();


    }

    public static void PrintArray(int[][] array) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                System.out.print(array[row][column] + " ");
            }
            System.out.println();
        }
    }

    public static int ConsistentPairCount() {
        int consistentCount = 0;
        for (int column = 0; column < columns; column++) {
            for (int column2 = column + 1; column2 < columns; column2++) {
                System.out.println("Comparing " + column + " and " + column2);
                if (CheckConsistentLarger(column, column2) || CheckConsistentSmaller(column, column2)) {
                    consistentCount++;
                }
            }
        }
        return consistentCount;
    }

    public static boolean CheckConsistentSmaller(int column1, int column2) {
        for (int row = 0; row < rows; row++) {
            if (cowPositionArray[row][column1] > cowPositionArray[row][column2]) {
                System.out.println(cowPositionArray[row][column1] + ">" + cowPositionArray[row][column2]);
                return false;
            }
        }
        return true;
    }

    public static boolean CheckConsistentLarger(int column1, int column2) {
        for (int row = 0; row < rows; row++) {
            if (cowPositionArray[row][column1] < cowPositionArray[row][column2]) {
                return false;
            }
        }
        return true;
    }


}
