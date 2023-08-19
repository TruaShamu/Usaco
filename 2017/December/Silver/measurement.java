import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class measurement {

    public static int[] cowID;
    public static int[] delta;
    public static HashMap<Integer, Integer> cows;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter out = new PrintWriter(new FileWriter("measurement.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        cowID = new int[1000001];
        delta = new int[1000001];
        cows = new HashMap<>();
        int ID = 1; //Unique cows


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken()); //day
            cowID[day] = Integer.parseInt(st.nextToken()); //cow

            //Add to map if we haven't seen it.
            if (!cows.containsKey(cowID[day])) {
                cows.put(cowID[day], ID++);
            }

            delta[day] = Integer.parseInt(st.nextToken());
        }

        // Remap cows.
        for (int i = 0; i < cowID.length; i++) {
            if (cowID[i] != 0) {
                cowID[i] = cows.get(cowID[i]);
            }
        }


        int[] milk = new int[ID];
        TreeMap<Integer, Integer> map = new TreeMap<>(); //Milk Level, Freq
        map.put(0, ID);
        int res = 0, max = 0;

        // Go through each day.
        for (int i = 0; i < cowID.length; i++) {

            if (cowID[i] == 0) {
                continue;
            }

            // prev and cur
            int prev = milk[cowID[i]];
            int cur = prev + delta[i];
            milk[cowID[i]] = cur;

            // Change Tree Map - remove old value.
            int numOld = map.get(prev);
            boolean flag = false;
            if (numOld == 1) {
                flag = true;
                map.remove(prev);
            } else {
                map.put(prev, numOld - 1);
            }

            // Place new value.
            if (map.containsKey(cur)) {
                map.put(cur, map.get(cur) + 1);
            } else {
                map.put(cur, 1);
            }


            // Wasn't best now I am.
            if (prev < max && cur >= max) {
                res++;
            }

            // Become unique best, after being in a tie.
            if (prev == max && numOld > 1 && cur > max) {
                res++;
            }

            int newtop = map.lastKey();

            // Was best now I am not.
            if (prev == max && cur < newtop) {
                res++;
            }

            // Was best, still am best, but now I share.
            if (prev == max && cur == newtop && map.get(newtop) > 1) {
                res++;
            }

            // Update max.
            max = newtop;
        }

        out.println(res);
        out.close();

    }
}
