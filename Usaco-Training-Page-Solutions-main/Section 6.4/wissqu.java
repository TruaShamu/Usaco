import java.io.*;
import java.util.ArrayList;
/*
TASK: wissqu
LANG: JAVA
 */
public class wissqu {
    public static int[] cow = {3, 3, 3, 4, 3};
    public static int[] di = {0, 0, 1, -1, 1, 1, -1, -1};
    public static int[] dj = {1, -1, 0, 0, 1, -1, 1, -1};
    public static char[][] a = new char[6][6];
    public static boolean[][] visited = new boolean[6][6];
    public static String path;
    public static ArrayList<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("wissqu.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wissqu.out")));
        for (int i = 1; i <= 4; i++) {
            String input = br.readLine();
            for (int j = 1; j <= 4; j++) {
                char token = input.charAt(j - 1);
                a[i][j] = token;
            }
        }
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                boolean flag = false;
                if (a[i][j] == 'D') continue;
                for (int t = 0; t < 8; t++) {
                    int tmpI = i + di[t];
                    int tmpJ = j + dj[t];
                    if (!(tmpI >= 1 && tmpI <= 4 && tmpJ >= 1 && tmpJ <= 4)) {
                        continue;
                    }
                    if (a[tmpI][tmpJ] == 'D') {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    cow[('D' - 'A')]--;
                    char tmp = a[i][j];
                    a[i][j] = 'D';
                    visited[i][j] = true;
                    search(path + 'D' + (char) (i + '0') + (char) (j + '0'));
                    cow[(int) ('D' - 'A')]++;
                    a[i][j] = tmp;
                    visited[i][j] = false;
                }
            }
        }
        String tmpMin = result.get(0);
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).compareTo(tmpMin) <= 0) {
                tmpMin = result.get(i);
            }
        }
        for (int i = 4; i < tmpMin.length(); i++) {
            if (i % 3 != 0) {
                pw.print(tmpMin.charAt(i) + " ");
            } else pw.println(tmpMin.charAt(i));
        }
        //pw.println();
        pw.println(result.size());
        pw.close();

    }

    public static void search(String path) {

        boolean flag = false;
        for (int i = 0; i < 5; i++) {
            if (cow[i] > 0) flag = true;
        }
        if (!flag) {
            result.add(path);
            return;
        }
        for (int c = 0; c < 5; c++) {
            if (cow[c] == 0) continue;
            char tmp = (char) (c + 'A');
            for (int i = 1; i <= 4; i++)
                for (int j = 1; j <= 4; j++) {
                    if (a[i][j] == tmp) continue;
                    if (visited[i][j]) continue;
                    flag = false;
                    for (int t = 0; t < 8; t++) {
                        int tmpI = i + di[t];
                        int tmpJ = j + dj[t];
                        if (!(tmpI >= 1 && tmpI <= 4 && tmpJ >= 1 && tmpJ <= 4)) continue;
                        if (a[tmpI][tmpJ] == tmp) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        cow[c]--;
                        char tmpChar = a[i][j];
                        a[i][j] = tmp;
                        visited[i][j] = true;
                        search(path + tmp + (char) (i + '0') + (char) (j + '0'));
                        cow[c]++;
                        a[i][j] = tmpChar;
                        visited[i][j] = false;
                    }
                }
        }

    }
}
