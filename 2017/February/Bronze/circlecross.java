import java.io.*;
import java.util.Arrays;

public class Main {
    public static int[][] letterArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("circlecross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));
        int rows = 26;
        String inputString = br.readLine();
        letterArray = new int[26][2];
        for (int row = 0; row < 26; row++) {
            for (int column = 0; column < 1; column++) {
                letterArray[row][column] = -1;
            }
        }
        for (int i = 0; i < 52; i++) {
            int curChar = inputString.charAt(i);
            if (letterArray[curChar - 65][0] == -1) {
                letterArray[curChar - 65][0] = i;
            }
            letterArray[curChar - 65][1] = i;


        }
        printMatrix(letterArray);
        int answerCount = 0;
        for (int number1 = 0; number1 < 26; number1++) {
            for (int number2 = number1 + 1; number2 < 26; number2++) {
                if (intersect(number1, number2)) {
                    answerCount++;
                }
            }
        }
        System.out.println("ANSWER: " + answerCount);
        pw.println(answerCount);
        pw.close();

    }

    public static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(
                        (row) -> {
                            System.out.print("[");
                            Arrays.stream(row)
                                    .forEach((el) -> System.out.print(" " + el + " "));
                            System.out.println("]");
                        }
                );
    }

    public static boolean intersect(int index1, int index2) {
        boolean intersects = false;
        if (letterArray[index1][0] < letterArray[index2][0] && letterArray[index2][0] < letterArray[index1][1]) {
            if (letterArray[index2][1] > letterArray[index1][1]) {
                return true;
            }
        }
        if (letterArray[index2][0] < letterArray[index1][0] && letterArray[index1][0] < letterArray[index2][1]) {
            if (letterArray[index1][1] > letterArray[index2][1]) {
                return true;
            }
        }

        return false;

    }

}
