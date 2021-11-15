import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
TASK: hidden
LANG: JAVA
 */
public class hidden {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hidden.in"));
        PrintWriter pw = new PrintWriter("hidden.out");
        int n = Integer.parseInt(br.readLine());
        String str = "";
        String s;
        while ((s = br.readLine()) != null) {
            str += s;
        }
        //System.out.println(str);
        int i = 0, j = 1, k = 0;
        while (i < n && j < n && k < n) {
            int tmp = (int) (str.charAt((i + k) % n) - str.charAt((j + k) % n));
            if (tmp == 0)
                k++;
            else {
                if (tmp > 0) {
                    i += k + 1;
                    k = 0;
                } else {
                    j += k + 1;
                    k = 0;
                }
                if (i == j) j++;
            }
        }
        pw.println(Integer.min(i, j));
        pw.close();
        System.out.println("Answer: " + Integer.min(i, j));
    }

}
