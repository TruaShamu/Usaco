import java.io.*;

/*
   TASK: subset
   LANG: JAVA
    */
public class subset {

    public static int N, setSum, targetNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("subset.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
        N = Integer.parseInt(br.readLine());

        setSum = (N * (N + 1)) / 2; //1+2+ ...N
        if (setSum % 2 != 0) {
            pw.println(0);
            pw.close();
            System.exit(0);
        }

        targetNum = setSum / 2; //The number to be reached
        //Now find how many numbers sum to targetNum
        long[] possiblePartitions = new long[targetNum + 1]; //Possible number of partitions for each sum
        possiblePartitions[0] = 1;
        int currentSum = 0;
        for (int i = 0; i < N; i++) {
            currentSum += i;

            for (int j = Math.min(targetNum, currentSum); j >= i; j--) {


                possiblePartitions[j] += possiblePartitions[j - i]; //Use smaller subset to add up to larger subset.

            }
        }
        pw.println(possiblePartitions[targetNum] / 2);
        pw.close();


    }


}
