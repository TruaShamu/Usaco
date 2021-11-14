import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("badmilk.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("badmilk.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int guests = Integer.parseInt(st.nextToken());
        int milk = Integer.parseInt(st.nextToken());
        int events = Integer.parseInt(st.nextToken());
        int sickPeople = Integer.parseInt(st.nextToken());
        int maxSickPeople = 0;
        int[][] inputArray = new int[guests][milk];
        int[] timeSick = new int[guests];
        for (int i = 0; i < timeSick.length; i++) {
            timeSick[i] = -1;
        }
        //Read in each milk drinking event
        for (int i = 0; i < events; i++) {
            st = new StringTokenizer(br.readLine());
            int person = Integer.parseInt(st.nextToken()) - 1;
            int milkDrank = Integer.parseInt(st.nextToken()) - 1;
            int time = Integer.parseInt(st.nextToken());
            inputArray[person][milkDrank] = time;
        }
        //Read in sick people
        for (int i = 0; i < sickPeople; i++) {
            st = new StringTokenizer(br.readLine());
            int person = Integer.parseInt(st.nextToken()) - 1;
            int time = Integer.parseInt(st.nextToken());
            timeSick[person] = time;
        }
        for (int i = 0; i < guests; i++) {
            System.out.println(Arrays.toString(inputArray[i]));
        }

        //New Refined Array for finding poison milk candidates
        int[][] refinedArray = new int[guests][milk];
        for (int i = 0; i < guests; i++) {
            int sickTime = timeSick[i];
            if (sickTime != -1) {
                for (int column = 0; column < milk; column++) {
                    if (inputArray[i][column] < sickTime && inputArray[i][column] != 0) {
                        refinedArray[i][column] = 1;
                    }
                }

            }
        }
        //Refined array is now complete.
        int validMilk = findValidMilk(milk, refinedArray, timeSick);
        for (int row = 0; row < guests; row++) {
            if (inputArray[row][validMilk] != 0) {
                if (timeSick[row] != -1) {
                    if (inputArray[row][validMilk] < timeSick[row]) {
                        maxSickPeople++;
                    }

                } else {
                    maxSickPeople++;
                }
            }
        }
        pw.println(maxSickPeople);
        pw.close();

    }


    public static boolean validColumn(int[][] array, int column, int rows) {
        for (int row = 0; row < rows; row++) {
            if (array[row][column] != 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean MilkValid(int milk, int[][] refArray, int[] sickPeople) {
        for (int i = 0; i < sickPeople.length; i++) {
            if (sickPeople[i] != -1) {
                if (refArray[i][milk] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int findValidMilk(int totalMilks, int[][] refArray, int[] sickPeople) {
        for (int milk = 0; milk < totalMilks; milk++) {
            System.out.println("Cur Milk: " + milk);
            if (MilkValid(milk, refArray, sickPeople)) {
                return milk;
            }
        }
        return -1;
    }
}
