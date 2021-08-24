import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowcode.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        long index = Long.parseLong(st.nextToken());
        pw.println(parse(s, index - 1));
        pw.close();
    }

    public static char parse(String s, long index) {
        if (index < s.length()) {
            return s.charAt((int) index);
        }
        long length = s.length();
        while (2 * length <= index) {
            length *= 2;
        }
        if (length == index) {
            return parse(s, length - 1);
        }
        return parse(s, index - length - 1);
    }
}
