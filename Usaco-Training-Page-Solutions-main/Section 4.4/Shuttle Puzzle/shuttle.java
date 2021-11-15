import java.io.*;

public class shuttle {
    public static int count;
    public static PrintWriter pw;

    /*
    TASK: shuttle
    LANG: JAVA
    CASES: 10/10
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shuttle.in"));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.out")));
        int N = Integer.parseInt(br.readLine());
        count = 0;
        int p = N;
        int sig = 1;

        print(p);
        for (int i = 1; i < N; ++i) {
            for (int j = 0; j < i; ++j) {
                print(p += 2 * sig);
            }
            print(p += sig);
            sig = -sig;
        }
        for (int i = 0; i < N; ++i) {
            print(p += 2 * sig);
        }

        sig = -sig;
        for (int i = N - 1; i > 0; --i) {
            print(p += sig);
            for (int j = 0; j < i; ++j) {
                print(p += 2 * sig);
            }
            sig = -sig;
        }
        pw.println(--p);
        pw.close();


    }

    public static void print(int x) throws IOException {
        //c = (a < b) ? a : b;

        if ((++count) % 20 == 0) {
            pw.print(x);
            pw.println();
        } else {
            pw.print(x);
            pw.print(" ");
        }
    }
}

