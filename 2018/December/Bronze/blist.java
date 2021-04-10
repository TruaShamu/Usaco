import java.io.*;
import java.util.*;

public class blist {
    public static int bucketsInUse;
    public static int totalBuckets;
    public static int bucketsLeft;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("blist.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));
        int numLines = Integer.parseInt(br.readLine());
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
        for (int i = 0; i < numLines; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            treeMap.put(start, -value); //- means deduct from current bucket supply
            treeMap.put(end, value);
        }
        Collection<Integer> values = treeMap.values();

        List<Integer> listValues = new ArrayList<Integer>(values);

        totalBuckets = -listValues.get(0);
        bucketsInUse = totalBuckets;
        bucketsLeft = 0;
        for (int i = 1; i < listValues.size(); i++) {
            if (listValues.get(i) > 0) {
                //System.out.println(listValues.get(i) + " buckets have been returned.");
                bucketsLeft += listValues.get(i);
                bucketsInUse -= listValues.get(i);
            }
            if (listValues.get(i) < 0) {
                //System.out.println(-listValues.get(i) + " buckets are needed.");
                if (bucketsLeft < -listValues.get(i)) {
                    //System.out.println("Not enough buckets left");
                    totalBuckets += -listValues.get(i) - bucketsLeft;
                    //System.out.println("Totalbuckets: " + totalBuckets);
                    bucketsInUse = totalBuckets;
                    bucketsLeft = 0;
                } else {
                    bucketsInUse += -listValues.get(i);
                    bucketsLeft -= -listValues.get(i);
                }
            }
        }
        pw.println(totalBuckets);
        pw.close();

    }
}
