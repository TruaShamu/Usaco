/*
TASK: comehome
LANG: JAVA
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class comehome {
    public static int INF = 100000;
    public static int[][] dist = new int[52][52];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("comehome.in"));
        PrintWriter pw = new PrintWriter("comehome.out");
        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                dist[i][j] = INF;
            }
        }

        for (int i = 0; i < 26; i++) {
            dist[i][i] = 0;
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char A = st.nextToken().charAt(0);
            char B = st.nextToken().charAt(0);
            int a = convert(A);
            int b = convert(B);
            int c = Integer.parseInt(st.nextToken());
            if (dist[a][b] > c) {
                dist[a][b] = dist[b][a] = c;
            }
        }

        for (int k = 0; k < 52; k++) {
            for (int i = 0; i < 52; i++) {
                for (int j = 0; j < 52; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        char letter = ' ';
        int res = INF;

        for (char i = 'A'; i < 'Z'; i++) {
            int len = dist[convert(i)][convert('Z')];
            if (len < res) {
                res = len;
                letter = i;
            }
        }

        pw.println(letter + " "+ res);
        pw.close();

    }

    public static int convert(char oChar) {
        if (Character.isUpperCase(oChar)) {
            return oChar - 'A';
        }
        return (oChar - 'a') + 26;
    }
}
