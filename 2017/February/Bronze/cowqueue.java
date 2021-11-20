import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowqueue.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowqueue.out")));
        int numberOfCows = Integer.parseInt(br.readLine());
        CowList cowList = new CowList();
        for (int i = 0; i < numberOfCows; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int processingTime = Integer.parseInt(st.nextToken());
            cowList.Cows.add(new Cow(startTime, processingTime));
        }
        cowList.sortByStartTime();
        //All cows now added to list and sorted
        for (int i = 0; i < cowList.Cows.size(); i++) {
            //System.out.println(cowList.Cows.get(i).startTime + " " + cowList.Cows.get(i).processingTime);
        }
        int time = 0;
        for (int i = 0; i < cowList.Cows.size(); i++) {
            if (cowList.Cows.get(i).startTime > time) {
                time = cowList.Cows.get(i).startTime;
            }
            time += cowList.Cows.get(i).processingTime;
        }
        //System.out.println("Time = " + time);
        pw.println(time);
        pw.close();
    }
}

class Cow {
    public int startTime;
    public int processingTime;

    public Cow(int startTime, int processingTime) {
        this.startTime = startTime;
        this.processingTime = processingTime;
    }


}

class CowList {

    static private Comparator<Cow> ascStartTime;

    // We initialize static variables inside a static block.
    //1. Interface
    static {
        ascStartTime = new Comparator<Cow>() {
            @Override
            public int compare(Cow p1, Cow p2) {
                return Integer.compare(p1.startTime, p2.startTime);
            }
        };

    }

    //2.Property

    ArrayList<Cow> Cows = new ArrayList<Cow>();

    //3. Method
    //a. Set /Get
    //b. General Function(Method>


    public ArrayList<Cow> GetCows() {
        return this.Cows;
    }

    public CowList() {

    }



    /*public Book[] getBooks() {
        return books;
    } */

    public void sortByStartTime() {
        Collections.sort(Cows, ascStartTime);
    }


    public CowList(ArrayList<Cow> oCows) {
        this.Cows = oCows;
    }
}
