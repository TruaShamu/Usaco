import java.io.*;

//Hoof Paper Scissors SILVER
public class Main {
    public static int[] pWin;
    public static int[] hWin;
    public static int[] sWin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        int gestures = Integer.parseInt(br.readLine());
        pWin = new int[gestures];
        hWin = new int[gestures];
        sWin = new int[gestures];

        for (int i = 0; i < gestures; i++) {
            char symbol = br.readLine().charAt(0);
            if (symbol == 'H') {
                //paper > hoof
                pWin[i]++;
            }
            if (symbol == 'P') {
                // scissors > paper
                sWin[i]++;
            }
            if (symbol == 'S') {
                // hoof > scissors
                hWin[i]++;
            }
        }
        for (int i = 1; i < gestures; i++) {
            pWin[i] = pWin[i] + pWin[i - 1];
            hWin[i] = hWin[i] + hWin[i - 1];
            sWin[i] = sWin[i] + sWin[i - 1];
        }
        // System.out.println("H:" + Arrays.toString(hWin));
        //System.out.println("P:" + Arrays.toString(pWin));
        //System.out.println("S:" + Arrays.toString(sWin));
        //Works

        // 6 combinations
        // H to P / P to H
        // H to S / S to H
        // P to S / S to P
        int maxWon = 0;
        for (int i = 0; i < gestures; i++) {
            int hToP = hWin[i] + (pWin[gestures - 1] - pWin[i]);
            maxWon = Integer.max(hToP, maxWon);
        }
        for (int i = 0; i < gestures; i++) {
            int pToH = pWin[i] + (hWin[gestures - 1] - hWin[i]);
            maxWon = Integer.max(pToH, maxWon);
        }
        for (int i = 0; i < gestures; i++) {
            int hToS = hWin[i] + (sWin[gestures - 1] - sWin[i]);
            maxWon = Integer.max(hToS, maxWon);
        }
        for (int i = 0; i < gestures; i++) {
            int sToH = sWin[i] + (hWin[gestures - 1] - hWin[i]);
            maxWon = Integer.max(sToH, maxWon);
        }
        for (int i = 0; i < gestures; i++) {
            int pToS = pWin[i] + (sWin[gestures - 1] - sWin[i]);
            maxWon = Integer.max(pToS, maxWon);
        }
        for (int i = 0; i < gestures; i++) {
            int sToP = sWin[i] + (pWin[gestures - 1] - pWin[i]);
            maxWon = Integer.max(sToP, maxWon);
        }

        System.out.println("ANSWER: " + maxWon);
        pw.println(maxWon);
        pw.close();

    }
}
