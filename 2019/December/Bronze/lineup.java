import java.io.*;
import java.util.Arrays;

public class lineup {
    public static int[][] connections;
    public static int lines;
    public static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lineup.in"));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("lineup.out")));
        lines = Integer.parseInt(br.readLine());
        connections = new int[lines][2];
        for (int line = 0; line < lines; line++) {
            String inputString = br.readLine();
            String firstWord = inputString.split(" ")[0];
            String lastWord = inputString.substring(inputString.lastIndexOf(" ") + 1);
            connections[line][0] = nameToNumber(firstWord);
            connections[line][1] = nameToNumber(lastWord);
        }
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7};
        arrangementSelect(a, 8);

    }

    public static int nameToNumber(String str) {
        if (str.equals("Beatrice")) {
            return 0;
        }
        if (str.equals("Belinda")) {
            return 1;
        }
        if (str.equals("Bella")) {
            return 2;
        }
        if (str.equals("Bessie")) {
            return 3;
        }
        if (str.equals("Betsy")) {
            return 4;
        }
        if (str.equals("Blue")) {
            return 5;
        }
        if (str.equals("Buttercup")) {
            return 6;
        }
        if (str.equals("Sue")) {
            return 7;
        }
        return -1;

    }

    public static boolean fulFill(int[] permutation) {
        for (int i = 0; i < lines; i++) {
            int zeroIndex = indexOf(connections[i][0], permutation);
            int oneIndex = indexOf(connections[i][1], permutation);
            if (Math.abs(zeroIndex - oneIndex) > 1) {
                return false;
            }
        }
        System.out.println("ANSWER: " + Arrays.toString(permutation));
        printPermutation(permutation);
        return true;
    }

    public static int indexOf(int number, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                return i;
            }
        }
        return -1;

    }

    public static void arrangementSelect(int[] a, int n) {
        System.out.println(String.format("A(%d, %d) = %d", a.length, n, arrangement(a.length, n)));
        arrangementSort(a, new int[n], 0);
    }

    public static long arrangement(int n, int m) {
        return (n >= m) ? factorial(n) / factorial(n - m) : 0;
    }

    public static void arrangementSort(int[] a, int[] result, int resultIndex) {
        int result_length = result.length;
        if (resultIndex >= result_length) {
            fulFill(result);
            System.out.println(Arrays.toString(result));
            return;
        }
        //
        for (int i = 0; i < a.length; i++) {

            boolean exist = false;
            for (int j = 0; j < resultIndex; j++) {
                if (a[i] == result[j]) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                result[resultIndex] = a[i];
                arrangementSort(a, result, resultIndex + 1);
            }
        }
    }

    public static long factorial(int n) {
        return (n > 1) ? n * factorial(n - 1) : 1;
    }

    public static void printPermutation(int[] permutation) {
        for (int i = 0; i < permutation.length; i++) {
            pw.println(numberToName(permutation[i]));

        }
        pw.close();
        System.exit(0);
    }

    public static String numberToName(int number) {
        if (number == 0) {
            return "Beatrice";
        }
        if (number == 1) {
            return "Belinda";
        }
        if (number == 2) {
            return "Bella";
        }
        if (number == 3) {
            return "Bessie";
        }
        if (number == 4) {
            return "Betsy";
        }
        if (number == 5) {
            return "Blue";
        }
        if (number == 6) {
            return "Buttercup";
        }
        if (number == 7) {
            return "Sue";
        }
        return "";
    }
}

