import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static StripList sList;
    public static int cows, strips;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
        if (Math.pow(10, 18) > Long.MAX_VALUE) {
            System.out.println("WONT WORK");
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        cows = Integer.parseInt(st.nextToken());
        strips = Integer.parseInt(st.nextToken());
        sList = new StripList();
        //Read in each strip
        for (int i = 0; i < strips; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sList.Strips.add(new Strip(start, end));
        }
        //Sort
        sList.sortbylocation();
       /* for (Strip oStrip : sList.Strips) {
            System.out.println(oStrip.start + " " + oStrip.end);
        }

        */
        //System.out.println("2 WORKS: " + works(2));
        //System.exit(0);
        //BS
        long lowerbound = 0;
        long upperbound = (long) (Math.pow(10, 18) / cows) + 1;
        long res = -1;
        while (lowerbound <= upperbound) {
            long mid = (lowerbound + upperbound) / 2;
            //System.out.println(mid);

            if (works(mid)) {
                res = mid;
                lowerbound = mid + 1;
            } else {
                upperbound = mid - 1;
            }
        }
        System.out.println("ANSWER:" + res);
        pw.println(res);
        pw.close();
    }

    public static boolean works(long dist) {
        long prev = Long.MIN_VALUE; //the loc of the previous cow

        int cnt = 0;
        for (Strip oStrip : sList.Strips) {
            while (Long.max(prev + dist, oStrip.start) <= oStrip.end) { //The new location
                //Place as many on this strip as possible
                prev = Long.max(prev + dist, oStrip.start);
                cnt++;

                if (cnt >= cows) return true;
            }
            if (cnt >= cows) return true;
        }

        return (cnt >= cows);
    }
}

class Strip {
    public int start, end;

    public Strip(int start, int end) {
        this.start = start;
        this.end = end;
    }


}

class StripList {

    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<Strip> ascLocation;

    // We initialize static variables inside a static block.
    //1. Interface
    static {
        ascLocation = new Comparator<Strip>() {
            @Override
            public int compare(Strip p1, Strip p2) {
                if (p1.start != p2.start) {
                    return Integer.compare(p1.start, p2.start);
                }
                return Integer.compare(p2.start, p2.end);
            }
        };

    }

    //2.Property

    ArrayList<Strip> Strips = new ArrayList<Strip>();

    //3. Method
    //a. Set /Get
    //b. General Function(Method>


    public ArrayList<Strip> GetStripss() {
        return this.Strips;
    }

    StripList() {

    }



    /*public Book[] getBooks() {
        return books;
    } */

    public void sortbylocation() {
        Collections.sort(Strips, ascLocation);
    }


    public StripList(ArrayList<Strip> oStrips) {
        this.Strips = oStrips;
    }
}
