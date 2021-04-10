import java.io.*;
import java.util.StringTokenizer;

public class mixmilk {
    public static int[] milkCapacity = new int[3];
    public static int[] milkLevel = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mixmilk.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
        milkCapacity = new int[3];
        milkLevel = new int[3];
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            milkCapacity[i] = Integer.parseInt(st.nextToken());
            milkLevel[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < 33; i++) {
            pour0to1();
            pour1to2();
            pour2to0();
        }
        pour0to1();
        //System.out.println(Arrays.toString(milkLevel));
        pw.println(milkLevel[0]);
        pw.println(milkLevel[1]);
        pw.println(milkLevel[2]);
        pw.close();


    }

    public static void pour0to1() {
        int remainingCapacity1 = milkCapacity[1] - milkLevel[1]; //The remaining amount that 0 can be filled
        if (remainingCapacity1 > milkLevel[0]) {
            milkLevel[1] = milkLevel[1] + milkLevel[0];
            milkLevel[0] = 0;
        } else {
            milkLevel[0] -= milkCapacity[1] - milkLevel[1];
            milkLevel[1] = milkCapacity[1];
        }

    }

    public static void pour1to2() {
        int remainingCapacity1 = milkCapacity[2] - milkLevel[2]; //The remaining amount that 0 can be filled
        if (remainingCapacity1 > milkLevel[1]) {
            milkLevel[2] = milkLevel[2] + milkLevel[1];
            milkLevel[1] = 0;
        } else {
            milkLevel[1] -= milkCapacity[2] - milkLevel[2];
            milkLevel[2] = milkCapacity[2];
        }

    }

    public static void pour2to0() {
        int remainingCapacity1 = milkCapacity[0] - milkLevel[0]; //The remaining amount that 0 can be filled
        if (remainingCapacity1 > milkLevel[2]) {
            milkLevel[0] = milkLevel[0] + milkLevel[2];
            milkLevel[2] = 0;
        } else {
            milkLevel[2] -= milkCapacity[0] - milkLevel[0];
            milkLevel[0] = milkCapacity[0];
        }

    }
}
