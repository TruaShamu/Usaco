import java.io.*;
import java.util.StringTokenizer;

/*
TASK: hamming
LANG: JAVA
LINK: https://train.usaco.org/usacoprob2?a=DrTO6GX1LvD&S=hamming

I reviewed the analysis of the problem and redid my solution.
This version is more efficient than the original one I wrote because it uses bitwise;
It  is also slightly more clean and readable.

Quick Explanation
Step 1: We want to loop through all values i  from (0 -- 2^B) and j from (0 -- 2^B).
We want to record whether hammingDistance(i, j) >= D or not. This is stored in the 'diff' matrix.
Step 2: Then, we recursively add the first value 'next' where 'next' has hammingDist >= D. We add this to our output array 'ans'.
When the number of elements in 'ans' is equal to N, we can stop and print the answer.
 */
public class hamming {
    public static long startTime;
    public static int maxValue;
    public static int N, B, D;
    public static int[][] diff;
    public static int[] ans;

    public static PrintWriter pw;


    public static void main(String[] args) throws IOException {
        startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new FileReader("hamming.in"));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //N codewords
        B = Integer.parseInt(st.nextToken()); //B bits long
        D = Integer.parseInt(st.nextToken()); //D distance
        maxValue = 1 << B; //@todo The max number we can have for an answer, in decimal

        ans = new int[N];
        diff = new int[maxValue + 3][maxValue + 3];
        for (int num1 = 0; num1 < maxValue; num1++) {
            for (int num2 = 0; num2 < maxValue; num2++) {
                diff[num1][num2] = 0;
                //Compare the bit at each position.
                for (int idx = 0; idx < B; idx++) {
                    int num1Bit = (1 << idx) & num1; //@todo num1's bit at idx.
                    int num2Bit = (1 << idx) & num2; //@todo num2's bit at idx.
                    if (num1Bit != num2Bit) {
                        diff[num1][num2]++;
                    }
                }
            }
        }

        ans[0] = 0;
        recurse(1, 1);


    }

    public static void recurse(int curIdx, int prev) {

        if (curIdx == N) {
            //@todo We are finished.
            for (int i = 0; i < N; i++) {
                pw.print(ans[i]);
                if (i != N - 1 && (i % 10 != 9)) {
                    pw.print(" ");
                }
                if (i % 10 == 9 || i == N - 1) {
                    pw.println();
                }
            }

            pw.close();
            System.exit(0);
        }

        for (int next = prev; next < maxValue; next++) {
            boolean addIt = true;
            for (int old = 0; old < curIdx; old++) {
                //@todo The old number and the next number differs by less than D bits.
                if (diff[ans[old]][next] < D) {
                    addIt = false;
                    break;
                }
            } //@todo Add it to the answer and recurse.
            if (addIt) {
                ans[curIdx] = next;
                recurse(curIdx + 1, next + 1);
            }
        }
    }

}
