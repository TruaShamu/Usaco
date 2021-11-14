import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] list = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(list);
        System.out.println("Sorted: " + Arrays.toString(list));

        int[] leftmostIndex = getLeftmost(list, k);
        System.out.println(Arrays.toString(leftmostIndex));

        int[] leftSize = new int[n];
        for (int i = 0; i < n; i++) {
            leftSize[i] = i - leftmostIndex[i] + 1;
            if (i > 0) {
                leftSize[i] = Math.max(leftSize[i], leftSize[i - 1]);
            }
        }
        int[] rightmostIndex = getRightmost(list, k);
        int[] rightSize = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            rightSize[i] = rightmostIndex[i] - i + 1;
            if (i < n - 1) {
                rightSize[i] = Math.max(rightSize[i], rightSize[i + 1]);
            }
        }
        int ret = 0;
        for (int i = 0; i < n - 1; i++) {
            ret = Math.max(ret, leftSize[i] + rightSize[i + 1]);
        }
        pw.println(ret);
        pw.close();
    }

    public static int[] getRightmost(int[] list, int k) {
        int[] ret = new int[list.length];
        int j = list.length - 1;
        for (int i = list.length - 1; i >= 0; i--) {
            while (j >= 0 && list[j] - list[i] > k) {
                j--;
            }
            ret[i] = j;
        }
        return ret;
    }

    public static int[] getLeftmost(int[] list, int k) {
        int[] ret = new int[list.length];
        int j = 0;
        for (int i = 0; i < list.length; i++) {
            while (j < list.length && list[i] - list[j] > k) {
                j++;
            }
            ret[i] = j;
        }
        return ret;
    }

}
