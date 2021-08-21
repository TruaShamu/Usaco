import java.io.*;
import java.util.*;
/*
TASK: gift1
LANG: JAVA
 */
class gift1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("gift1.in"));
        PrintWriter pw = new PrintWriter("gift1.out");
        HashMap<String, Integer> map = new HashMap<>();
        int NP = Integer.parseInt(br.readLine());
        String[] s = new String[NP];
        for (int i = 0; i < NP; i++) {
            s[i] = br.readLine();
            map.put(s[i], 0);
        }
        for (int i = 0; i < NP; i++) {
            String giver = br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int amt = Integer.parseInt(st.nextToken());
            int NG = Integer.parseInt(st.nextToken());
            if (NG > 0) {
                amt = (amt / NG) * NG;
                map.put(giver, map.get(giver) - amt);
                amt /= NG;
                for (int j = 0; j < NG; j++) {
                    String getter = br.readLine();
                    map.put(getter, map.get(getter) + amt);
                }
            }
        }
        for (int i = 0; i < NP; i++) {
            pw.println(s[i] + " " + map.get(s[i]));
        }
        pw.close();
    }
}
