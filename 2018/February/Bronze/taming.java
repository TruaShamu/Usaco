import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("taming.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
        int numberOfRecords = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] inputArray = new int[numberOfRecords];
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = Integer.parseInt(st.nextToken());
        }
        int[] statusArray = new int[numberOfRecords];
        //Read input finish
        if (statusArray[0] > 0) {
            //If first element is not missing or breakout.
            pw.println(-1);
            pw.close();
            System.exit(0);
        }
        statusArray[0] = 1;

        for (int i = inputArray.length - 1; i > 0; i--) {
            //System.out.println("i=" + i);
            //Loop from back
            if (inputArray[i] == -1) {
                //If i is normal missing element, mark as uncertain
                //System.out.println("inputarray[" + i + "] is missing");
                //System.out.println("statusarray[" + i + "] = 2");
                statusArray[i] = 2;
            }
            if (inputArray[i] == 0) {
                //If normal element, with breakout, mark as certain breakout
                //System.out.println("inputarray[" + i + "] is breakout");
                //System.out.println("statusarray[" + i + "] = 1");
                statusArray[i] = 1;
            }
            if (inputArray[i] > 0) {
                int start = i; //Number at which input[i] >0
                int number = inputArray[i]; //How many days since last breakout
                if (start - number < 0) {
                    pw.println(0);
                    pw.close();
                    System.exit(0);
                }
                for (int loc = i; loc > (i - number + 1); loc--) {
                    //System.out.println("statusarray[" + loc + "] ==0");
                    if (inputArray[loc] != -1 && (inputArray[loc] != number - (start - loc))) {
                        //System.out.println("problem");
                        pw.println(-1);
                        pw.close();
                        System.exit(0);
                    } else {
                        statusArray[loc] = 0;
                    }

                }
                i = i - number;
                System.out.println("i= " + i);
                statusArray[i] = 1;
                continue;
            }

        }
        int uncertain = 0;
        int certainYes = 0;
        //int certainNo = 0;
        for (int i = 0; i < statusArray.length; i++) {

            if (statusArray[i] == 1) {
                certainYes++;
            }
            if (statusArray[i] == 2) {
                uncertain++;
            }
        }
        pw.println(certainYes + " " + (certainYes + uncertain));
        pw.close();

    }
}
