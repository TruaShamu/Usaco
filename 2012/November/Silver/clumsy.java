import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("clumsy.in"));
        PrintWriter pw = new PrintWriter(new File("clumsy.out"));
        String s = br.readLine();
        int depth = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                depth++;
            else
                depth--;

            if (depth < 0) {
                depth = 1;
                ans++;

            }
        }

        ans += depth / 2;
        pw.println(ans);
        pw.close();
    }
}
