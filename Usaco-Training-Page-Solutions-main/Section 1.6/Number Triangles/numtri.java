import java.io.*;
import java.util.StringTokenizer;

/*
TASK: numtri
LANG: JAVA
 */
public class numtri {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
        int rows = Integer.parseInt(br.readLine());
        //Part 1: Read in input
        int[][] triArray = new int[rows][rows]; //row followed by column
        int totalCost = 0;
        //Read in original input array
        //Array reading works!
        for (int i = 0; i < rows; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                int x = Integer.parseInt(st.nextToken());
                //System.out.println(x);
                triArray[i][j] = x;
            }
            //System.out.println("Switch row");
        }
        //Array rearranging works!
        int[][] newArray = rearrange(triArray, rows);
        //System.out.println("rearrange finished");
        recursion3(newArray, rows, rows - 1);
        pw.println(newArray[0][0]);
        pw.close();


    }


    public static int[][] rearrange(int[][] array, int rows) {
        int[][] newArray = new int[rows][rows];
        for (int column = 0; column < rows; column++) {
            for (int row = 0; row < rows - column; row++) {
                newArray[row][column] = array[row + column][column];
            }
        }

        return newArray;
    }

    public static void recursive2(int totalCost, int[][] triArray, int count) {
        int row = count - 1;
        while (count > 0) {
            for (int column = 0; column < count - 2; column++) {
                if (triArray[row][column] > triArray[row - 1][column + 1]) {
                    triArray[row - 1][column] += triArray[row][column];
                    totalCost += triArray[row][column];
                } else {
                    triArray[row - 1][column] += triArray[row - 1][column + 1];
                    totalCost += triArray[row - 1][column + 1];
                }
                row--;
            }
            count--;
            recursive2(totalCost, triArray, count);
        }
    }

    public static void recursion3(int[][] triArray, int rows, int times) {
        for (int column = 0; column < (rows - 1); column++) {
            if (triArray[rows - column - 1][column] > triArray[rows - column - 2][column + 1]) {
                triArray[rows - column - 2][column] += triArray[rows - column - 1][column];
            } else {
                triArray[rows - column - 2][column] += triArray[rows - column - 2][column + 1];
            }

        }
        times--;
        rows--;
        if (times > 0) {
            recursion3(triArray, rows, times);
        }
    }
}
