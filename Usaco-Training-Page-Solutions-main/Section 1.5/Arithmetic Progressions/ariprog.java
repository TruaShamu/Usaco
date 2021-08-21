/*
TASK: ariprog
LANG: JAVA
 */


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class ariprog {

    public static SequenceList ResultList;
    public static ArrayList<Integer> TargetList;
    public static Integer N, M;

    public static int Is[] = new int[125001];

    public static void main(String[] args) throws IOException {
        ResultList = new SequenceList();
        TargetList = new ArrayList<>();

        long startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        N = Integer.parseInt(br.readLine()); // the length of progressions for which to search
        M = Integer.parseInt(br.readLine()); //  an upper bound to limit the search to the bisquares with 0 <= p,q <= M.
        //ArrayList<Integer> BiSq = Bisquares(M);
        BisquaresW(M); //Improve 1: Self-defined Quicksort 20% faster than Collection.sort
        //TargetList = Bisquares(M);
        //PrintTargetList();//Willie
        RouteCheck(N);
        //System.out.println(ResultList.sequences.size());
        //PrintRoutes();


        ResultList.SpecialSort();
        if (ResultList.sequences.size() == 0) {
            pw.println("NONE");
            pw.close();
            System.exit(0);
        }
        for (int i = 0; i < ResultList.sequences.size(); i++) {
            pw.println(ResultList.sequences.get(i).a + " " + ResultList.sequences.get(i).b);
        }


        pw.close();
        long endTime = System.nanoTime();
        System.out.println("Total Time = " + (endTime - startTime) + " ns");


    }


    public static void BisquaresW(int M) {


        for (int row = 0; row <= M; row++) {
            for (int i = row; i <= M; i++) {
                TargetList.add((row * row) + (i * i));
                Is[(row * row) + (i * i)] = 1;
            }

        }

        TargetList = new ArrayList<>(new HashSet<>(TargetList));

        sort(TargetList);
        //Sorts.sort(TargetList);
        //Collections.sort(TargetList, Collections.reverseOrder());

    }

    public static void ReorgResultList() {


        ResultList.sequences = new ArrayList<>(new HashSet<>(ResultList.sequences));


    }


    public static void RouteCheck(int Remaining_Steps) {
        int Gapsize, Uppersize, LowerSize;
        int Original_Steps = Remaining_Steps;
        //Uppersize = (TargetList.get(0) - TargetList.get(TargetList.size() - 1)) / (Remaining_Steps - 1);


        for (int i = 0; i < TargetList.size() - N + 1; i++) {
            int n = 1;
            Item oItem = new Item(i, TargetList.get(i));

            //if (i + n > TargetList.size()) continue;
            //System.out.println("Start:" + TargetList.get(i));
            //System.out.println("Uppersize:" + Uppersize);
            Uppersize = (oItem.Value) / (Remaining_Steps - 1);
            LowerSize = oItem.Value - TargetList.get(i + n);
            while (Uppersize >= LowerSize) {

                //LowerSize = TargetList.get(i) - TargetList.get(i + n);
                //System.out.println(LowerSize);
                //int FirstLocation = ForwardMove(oItem, Remaining_Steps, LowerSize);
                int FirstLocation = ForwardMoveW(oItem.Value, Remaining_Steps, LowerSize);
                //System.out.println(FirstLocation);
                if (FirstLocation != -1) ResultList.sequences.add(new Sequence(FirstLocation, LowerSize));
                n++;

                if (i + n > TargetList.size() - 1) break;
                else LowerSize = oItem.Value - TargetList.get(i + n);
            }
        }

    }


    public static int ForwardMoveW(int Location, int Remaing_Steps, int Gapsize) {

        int NewLocation = Location - Gapsize;
        if (Is[NewLocation] == 0) return -1;
        else {
            Remaing_Steps--;


            if (Remaing_Steps == 1) return NewLocation;
            else {
                return ForwardMoveW(NewLocation, Remaing_Steps, Gapsize);
            }

        }
    }

    public static Item Checkcontain(int OldLocIndex, int NewLocation) {

        for (int i = OldLocIndex + 1; i <= TargetList.size() - 1; i++) {
            int Val = TargetList.get(i);

            if (Val == NewLocation) return new Item(i, Val);
            if (Val > NewLocation) continue;
            if (Val < NewLocation) return new Item(-1, -1);
        }
        return new Item(-1, -1);
    }

    public static void PrintTargetList() {
        for (int i = 0; i < TargetList.size(); i++) {
            System.out.println(TargetList.get(i));
        }

    }


    public static void sort(ArrayList<Integer> olist) {
        sort(olist, 0, olist.size() - 1);
    }

    public static void sort(ArrayList<Integer> olist, int from, int to) {
        if (from < to) {
            int pivot = from;
            int left = from + 1;
            int right = to;
            int pivotValue = olist.get(pivot);
            while (left <= right) {
                // left <= to -> limit protection
                while (left <= to && pivotValue <= olist.get(left)) {
                    left++;
                }
                // right > from -> limit protection
                while (right > from && pivotValue > olist.get(right)) {
                    right--;
                }
                if (left < right) {
                    Collections.swap(olist, left, right);
                }
            }
            Collections.swap(olist, pivot, left - 1);
            sort(olist, from, right - 1); // <-- pivot was wrong!
            sort(olist, right + 1, to);   // <-- pivot was wrong!
        }
    }


}


class Sequence implements Comparable<Sequence> {
    int a, b;

    public Sequence(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Sequence o) {
        int i = Integer.compare(this.b, o.b);

        if (i != 0)
            return i;

        return Integer.compare(this.a, o.a);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }
        Sequence Seq = (Sequence) o;
        if (a != Seq.a)
            return false;

        if (b != Seq.b)
            return false;


        return true;
    }

}

class SequenceList {


    public SequenceList() {

    }


    public ArrayList<Sequence> sequences = new ArrayList<Sequence>();


    //Sort by b, then sort by a within that range
    public void SpecialSort() {
        Collections.sort(sequences, Sequence::compareTo);

    }


}

class Item {
    public int Location;
    public int Value;

    public Item(int oLoc, int oVal) {
        this.Location = oLoc;
        this.Value = oVal;
    }


}


