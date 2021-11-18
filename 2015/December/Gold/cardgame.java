import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter pw = new PrintWriter("cardgame.out");
        int n = Integer.parseInt(br.readLine());
        int[] elsieLeft = new int[n / 2];
        int[] elsieRight = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            elsieLeft[i] = Integer.parseInt(br.readLine().trim());
        }
        for (int i = 0; i < n / 2; i++) {
            elsieRight[i] = Integer.parseInt(br.readLine().trim());
        }
        int[] elsie = new int[n];
        for (int i = 0; i < n / 2; i++) {
            elsie[i] = elsieLeft[i];
        }
        for (int i = n / 2; i < n; i++) {
            elsie[i] = elsieRight[i - n / 2];
        }

        Arrays.sort(elsieLeft);
        Arrays.sort(elsieRight);
        Arrays.sort(elsie);
        System.out.println(Arrays.toString(elsieLeft));
        System.out.println(Arrays.toString(elsieRight));
        System.out.println(Arrays.toString(elsie));

        int[] bessie = new int[n];
        int iElsie = 0, iBessie = 0;
        for (int cur = 1; cur <= 2 * n; cur++) {
            if (iBessie == n) break;
            if (iElsie < n && elsie[iElsie] == cur) iElsie++;
            else bessie[iBessie++] = cur;
        }
        Arrays.sort(bessie);
        System.out.println(Arrays.toString(bessie));
        int res = 0;
        iBessie = n / 2;


        for (int i = 0; i < n / 2; i++) {
            while (iBessie < n && bessie[iBessie] < elsieLeft[i]) iBessie++;
            if (iBessie < n) {
                res++;
                iBessie++;
            }
        }
        iBessie = n / 2 - 1;
        for (int i = n / 2 - 1; i >= 0; i--) {
            while (iBessie >= 0 && bessie[iBessie] > elsieRight[i]) iBessie--;
            if (iBessie >= 0) System.out.println(i + " " + iBessie + " " + bessie[iBessie] + " " + elsieRight[i]);
            if (iBessie >= 0) {
                res++;
                iBessie--;
            }
        }
        pw.println(res);
        pw.close();

    }
}
