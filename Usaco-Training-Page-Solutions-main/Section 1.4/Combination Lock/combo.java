import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
TASK: combo
LANG: JAVA
 */
public class combo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("combo.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
        SlotList SlotsArraylist = new SlotList();
        int N = Integer.parseInt(br.readLine());
        //Save Farmer Johns combination as an array
        StringTokenizer st = new StringTokenizer(br.readLine());
        int fLock[] = new int[3];
        for (int i = 0; i < 3; i++) {
            fLock[i] = Integer.parseInt(st.nextToken());
        }
        //Save the MasterLock as an array
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int mLock[] = new int[3];
        for (int i = 0; i < 3; i++) {
            mLock[i] = Integer.parseInt(st1.nextToken());
        }

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                for (int k = -2; k <= 2; k++) {
                    SlotsArraylist.slots.add(new slot(AdjustedLocation(N, fLock[0], i), AdjustedLocation(N, fLock[1], j),
                            AdjustedLocation(N, fLock[2], k)));
                }
            }
        }

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                for (int k = -2; k <= 2; k++)
                    SlotsArraylist.slots.add(new slot(AdjustedLocation(N, mLock[0], i), AdjustedLocation(N, mLock[1], j),
                            AdjustedLocation(N, mLock[2], k)));
            }
        }

        SlotsArraylist.sortbyValue();
        double PreviousValue = 0;
        int count = 0;
        System.out.println(SlotsArraylist.slots.size());
        for (int i = 0; i < SlotsArraylist.slots.size(); i++) {
            if (SlotsArraylist.slots.get(i).getValue() != PreviousValue) {
                count++;
            }
            PreviousValue = SlotsArraylist.slots.get(i).getValue();
        }
        pw.println(count);
        pw.close();

    }

    public static boolean Overlaps(int array[], int array1[], int N) {
        boolean isOverlap = true;
        int overlaps = 1;
        if (N <= 5) {
            isOverlap = true;
        } else {

            for (int i = 0; i < 3; i++) {
                int dif;
                dif = Math.abs((((array[i] + 2) % N) - (array1[i] + 2) % N)); // Distance between 2 numbers
                if (dif > 4) {
                    isOverlap = false;

                    break;
                }
            }
        }
        return isOverlap;

    }

    public static int[] range(int slotnum, int center, int distance) {
        int slots[] = new int[distance * 2 + 1];
        // Two exception:
        // (1) center-distance <=0 adapt center-distance+slotnum Example [1..7] ceneter is 1 left second should be 1-2+7=6 (V)
        // (2) center+distance >=slotnum adapt center+distance-slotnum Example [1..7] center is 6 right second should be 6+2-7=1 (V)
        // Modelling correct
        for (int i = -distance; i < distance + 1; i++) {
            int targetnum;
            if (center + i <= 0) {
                targetnum = center + i + slotnum; //Below lower bound
            } else if (center + i >= slotnum) {
                targetnum = center + i - slotnum; //Above lower bound
            } else
                targetnum = center + i; //Normal Case
            slots[i + distance] = targetnum;
        }

        return slots;
    }

    public static int AdjustedLocation(int slotnum, int center, int distance) {
        // Two exception:
        // (1) center-distance <=0 adapt center-distance+slotnum Example [1..7] ceneter is 1 left second should be 1-2+7=6 (V)
        // (2) center+distance >=slotnum adapt center+distance-slotnum Example [1..7] center is 6 right second should be 6+2-7=1 (V)
        // Modelling correct
        int targetnum = center + distance; //Normal Case
        while (targetnum <= 0 || targetnum > slotnum)
            if (targetnum <= 0) {
                targetnum += slotnum; //Below lower bound
            } else {
                targetnum -= slotnum; //Above lower bound
            }

        return targetnum;
    }

    static class slot {
        int[] vector;
        double value;

        public slot(int[] oVector) {
            this.vector = oVector;
            value = vector[0] * 10 ^ 4 + vector[1] * 10 ^ 2 + vector[2];
        }

        public slot(int i, int j, int k) {
            this.vector = new int[]{i, j, k};
            value = vector[0] * (10000) + vector[1] * (100) + vector[2];
        }

        public double getValue() {
            return value;
        }

        public int[] getVector() {
            return vector;
        }


    }

    public static class SlotList {

        static private Comparator<slot> ascValue;

        static {
            ascValue = new Comparator<slot>() {
                @Override
                public int compare(slot p1, slot p2) {
                    return Double.compare(p1.value, p2.value);
                }
            };

        }

        public SlotList() {

        }

        ArrayList<slot> slots = new ArrayList<slot>();

        public ArrayList<slot> GetSlots() {
            return this.slots;
        }

        public void sortbyValue() {
            Collections.sort(slots, ascValue);
        }

        public SlotList(ArrayList<slot> oslots) {
            this.slots = oslots;
        }
    }
}
