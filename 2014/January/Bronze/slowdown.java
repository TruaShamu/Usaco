import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static double currentStep;
    public static double currentTime;
    public static double currentDistance;
    public static BufferedReader br;
    public static int eventNum;
    public static PriorityQueue<Integer> timeQueue;
    public static PriorityQueue<Integer> distanceQueue;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("slowdown.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("slowdown.out")));
        eventNum = Integer.parseInt(br.readLine()); //The number of slowdowns.
        currentStep = 1;

        ReadInput();
        //TestCase();
        Calculate();
        remainingDistance();
        pw.println(Math.round(currentTime));
        pw.close();


    }


    public static void TestCase() {
        currentStep = 1;
        currentDistance = 0;
        currentTime = 0;
        ProcessV(10, 30);
        //System.out.println("Time:" + currentTime);
        //System.out.println("Distance:" + currentDistance);
        //System.out.println("Step:" + currentStep);
    }

    public static int ProcessV(double D, double T) {


        double WorkingTD = currentDistance + ((T - currentTime) * 1 / currentStep);
        //System.out.println("WorkingTD:" + WorkingTD);
        if (D < WorkingTD) {
            currentTime = currentTime + (D - currentDistance) / (1 / currentStep);
            currentDistance = D;
            currentStep++;
            return -1;
        }
        if (D > WorkingTD) {
            currentDistance += (T - currentTime) * (1 / currentStep);

            currentTime = T;
            currentStep++;
            return 1;
        }
        if (D == WorkingTD) {


            currentDistance += (T - currentTime) * (1 / currentStep);
            currentTime = T;
            currentStep += 2;
            return 0;
        }
        return 999;
    }

    public static void ReadInput() throws IOException {
        timeQueue = new PriorityQueue<Integer>();
        distanceQueue = new PriorityQueue<Integer>();
        for (int i = 0; i < eventNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int numeric = Integer.parseInt(st.nextToken());
            if (type.equals("T")) {
                timeQueue.add(numeric);
            } else {
                distanceQueue.add(numeric);
            }
        }

    }

    public static double RetrieveTime() {
        if (timeQueue.size() > 0)
            return timeQueue.poll();
        else
            return Double.MAX_VALUE;

    }

    public static double RetrieveDis() {
        if (distanceQueue.size() > 0)
            return distanceQueue.poll();
        else
            return Double.MAX_VALUE;

    }

    public static void Calculate() {
        Double T, D;
        T = RetrieveTime();
        D = RetrieveDis();
        int status = 0;

        //System.out.println("Dis:" + D);
        //System.out.println("Time:" + T);
        while ((T != Double.MAX_VALUE) || (D != Double.MAX_VALUE)) {
            status = ProcessV(D, T);
            //System.out.println("Dis:" + D);
            //System.out.println("Time:" + T);
            if (status == -1) D = RetrieveDis();
            if (status == 1) T = RetrieveTime();
            if (status == 0) {
                T = RetrieveTime();
                D = RetrieveDis();
            }

            ///System.out.println("CurrentTime:" + currentTime);

        }

        //System.out.println("Time:" + currentTime);
        //System.out.println("Distance:" + currentDistance);
        //System.out.println("Step:" + currentStep);
    }

    public static void remainingDistance() {
        System.out.println("CurrentTime:" + currentTime);
        System.out.println("CurrentDis:" + currentDistance);
        System.out.println("currentStep:" + currentStep);
        currentTime += (1000 - currentDistance) / (1 / currentStep);
        System.out.println("CurrentTime:" + currentTime);
    }
}
