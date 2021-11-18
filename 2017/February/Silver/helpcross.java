import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int chickens = Integer.parseInt(st.nextToken());
        int cows = Integer.parseInt(st.nextToken());
        ArrayList<Integer> chickenList = new ArrayList<>();
        for (int i = 0; i < chickens; i++) {
            chickenList.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(chickenList);
        cowList cowList = new cowList();
        for (int i = 0; i < cows; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            cowList.cows.add(new cow(start, end));
        }
        cowList.sortbylocation();

        int crossCows = 0;
        for (int i = 0; i < chickenList.size(); i++) {
            int curChicken = chickenList.get(i);
            for (int j = 0; j < cowList.cows.size(); j++) {
                cow oCow = cowList.cows.get(j);

                if (oCow.start > curChicken) {
                    continue;
                }
                if (curChicken >= oCow.start && curChicken <= oCow.end) {
                    crossCows++;
                    oCow.start = Integer.MAX_VALUE;
                    oCow.end = Integer.MAX_VALUE;
                    //cowList.Cows.remove(j);
                    break;
                }
            }
        }
        pw.println(crossCows);
        pw.close();
    }
}

class cow {
    public int start, end;

    public cow(int start, int end) {
        this.start = start;
        this.end = end;
    }


}

class cowList {

    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<cow> ascLocation;

    // We initialize static variables inside a static block.
    //1. Interface
    static {
        ascLocation = new Comparator<cow>() {
            @Override
            public int compare(cow p1, cow p2) {
                return Integer.compare(p1.end, p2.end);
            }
        };

    }

    //2.Property

    ArrayList<cow> cows = new ArrayList<cow>();

    //3. Method
    //a. Set /Get
    //b. General Function(Method>


    public ArrayList<cow> Getcowss() {
        return this.cows;
    }

    cowList() {

    }



    /*public Book[] getBooks() {
        return books;
    } */

    public void sortbylocation() {
        Collections.sort(cows, ascLocation);
    }


    public cowList(ArrayList<cow> ocows) {
        this.cows = ocows;
    }
}




