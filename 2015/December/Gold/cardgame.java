import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter pw = new PrintWriter("cardgame.out");
        int n = Integer.parseInt(br.readLine());
        int[] elsieR1 = new int[n / 2];
        int[] elsieR2 = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            elsieR1[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < n / 2; i++) {
            elsieR2[i] = Integer.parseInt(br.readLine());
        }
        int[] elsie = new int[n];
        for (int i = 0; i < n / 2; i++) {
            elsie[i] = elsieR1[i];
        }
        for (int i = n / 2; i < n; i++) {
            elsie[i] = elsieR2[i - n / 2];
        }
        Arrays.sort(elsieR1);
        Arrays.sort(elsieR2);
        Arrays.sort(elsie);

        //Print Arrays
        System.out.println(Arrays.toString(elsieR1));
        System.out.println(Arrays.toString(elsieR2));
        System.out.println(Arrays.toString(elsie));
        //Make bessie array
        int[] bessie = new int[n];
        int iElsie = 0, iBessie = 0; //Index
        for (int cur = 1; cur <= 2 * n; cur++) {
            if (iBessie == n) break; //Reach end of array
            if (iElsie < n && elsie[iElsie] == cur) iElsie++;
            else bessie[iBessie++] = cur;
        }
        Arrays.sort(bessie);
        System.out.println(Arrays.toString(bessie));
        int res = 0;
        iBessie = n / 2;

        for (int i = 0; i < n / 2; i++) {
            //find the one that can win against elsie
            while (iBessie < n && bessie[iBessie] < elsieR1[i]) {
                iBessie++;
            }
            if (iBessie < n) {
                //WIN
                res++;
                iBessie++;
            }
        }
        iBessie = n / 2 - 1;
        for (int i = n / 2 - 1; i >= 0; i--) {
            while (iBessie >= 0 && bessie[iBessie] > elsieR2[i]) iBessie--;
            if (iBessie >= 0) System.out.println(i + " " + iBessie + " " + bessie[iBessie] + " " + elsieR2[i]);
            if (iBessie >= 0) {
                res++;
                iBessie--;
            }
        }
        pw.println(res);
        pw.close();

    }

}
