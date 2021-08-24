import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ABCs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> arrli = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 7; i++) {
            arrli.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 7; k++) {
                    ArrayList<Integer> copy = new ArrayList<>(arrli);
                    boolean[] visited = new boolean[7];
                    if (i == j || i == k || j == k) {
                        continue;
                    }
                    int A = copy.get(i);
                    int B = copy.get(j);
                    int C = copy.get(k);
                    visited[i] = true;
                    visited[j] = true;
                    visited[k] = true;


                    if (!exists(arrli, visited, A + B)) continue;
                    if (!exists(arrli, visited, A + C)) continue;
                    if (!exists(arrli, visited, B + C)) continue;
                    if (!exists(arrli, visited, A + B + C)) continue;
                    ArrayList<Integer> answer = new ArrayList<>();
                    answer.add(A);
                    answer.add(B);
                    answer.add(C);
                    Collections.sort(answer);
                    System.out.println(answer.get(0) + " " + answer.get(1) + " " + answer.get(2));
                    System.exit(0);

                }
            }
        }
    }

    public static boolean exists(ArrayList<Integer> list, boolean[] visited, int i) {
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) == i && !visited[j]) {
                visited[j] = true;
                return true;
            }
        }
        return false;
    }
}
