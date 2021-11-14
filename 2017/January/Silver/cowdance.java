import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Cow Dance Show SILVER
public class Main {
    public static int cows;
    public static int tMax;
    public static int[] showDuration;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        cows = Integer.parseInt(st.nextToken());
        tMax = Integer.parseInt(st.nextToken());
        showDuration = new int[cows];
        for (int i = 0; i < cows; i++) {
            showDuration[i] = Integer.parseInt(br.readLine());
        }
        for (int i : showDuration) {
            System.out.println(i);
        }

        //Now we binary search for K (stage capacity) , and check if time used <= tMax.
        int lowerbound = 1;
        int upperbound = cows;
        while (lowerbound != upperbound) {
            int average = (lowerbound + upperbound) / 2;
            if (fulfills(average)) {
                upperbound = average;
            } else {
                lowerbound = average + 1;
            }
        }
        //Print Answer
        pw.println(lowerbound);
        pw.close();
    }

    public static boolean fulfills(int stageCapacity) {
        int lastLeft = 0; //The time count of the previous cow who left the stage.
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < showDuration.length; i++) {
            if (pq.size() == stageCapacity) {
                lastLeft = pq.poll();
            }
            int newCow = showDuration[i]; //The time duration that the new cow is on the stage
            int newCowLeave = newCow + lastLeft; //Time when the current cow leaves the stage
            if (newCowLeave > tMax) {
                //Over time
                return false;
            }
            pq.add(newCowLeave);
        }
        return true;
    }
}
