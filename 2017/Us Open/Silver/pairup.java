import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static groupList gList;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
        int lines = Integer.parseInt(br.readLine());
        gList = new groupList();
        long totalTime = 0;
        int matches = 0;
        for (int i = 0; i < lines; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long freq = Integer.parseInt(st.nextToken());
            long time = Integer.parseInt(st.nextToken());
            gList.groups.add(new group(freq, time));
        }
        gList.sortbylocation();


        while (gList.groups.size() > 1) {

            long min = gList.groups.get(0).freq;
            long timeMin = gList.groups.get(0).time;
            long max = gList.groups.get(gList.groups.size() - 1).freq;
            long timeMax = gList.groups.get(gList.groups.size() - 1).time;

            long subtract = Long.min(min, max);
            matches += subtract;
            long curTime = ((timeMax + timeMin));

            if (subtract == min && min == max) {
                gList.groups.remove(0);

                gList.groups.remove(gList.groups.size() - 1);
            } else {
                if (subtract == min) {
                    gList.groups.remove(0);
                    gList.groups.get(gList.groups.size() - 1).freq -= subtract;
                }
                if (subtract == max) {
                    gList.groups.remove(gList.groups.size() - 1);
                    gList.groups.get(0).freq -= subtract;
                }
            }
            totalTime = Long.max(totalTime, curTime);
        }
        if (gList.groups.size() == 1) {
            long curTime = gList.groups.get(0).time * 2;
            totalTime = Long.max(curTime, totalTime);


        }
        System.out.println("ANSWER: " + totalTime);
        pw.println(totalTime);
        pw.close();
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println(elapsedTime + " milliseconds");

    }


}

class group {
    public long freq;
    public long time;


    public group(long freq, long time) {
        this.freq = freq;
        this.time = time;
    }


}

class groupList {

    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<group> ascLocation;

    // We initialize static variables inside a static block.
    //1. Interface
    static {
        ascLocation = new Comparator<group>() {
            @Override
            public int compare(group p1, group p2) {
                return Long.compare(p1.time, p2.time);
            }
        };

    }

    //2.Property

    ArrayList<group> groups = new ArrayList<group>();

    //3. Method
    //a. Set /Get
    //b. General Function(Method>


    public ArrayList<group> Getgroupss() {
        return this.groups;
    }

    groupList() {

    }



    /*public Book[] getBooks() {
        return books;
    } */

    public void sortbylocation() {
        Collections.sort(groups, ascLocation);
    }


    public groupList(ArrayList<group> ogroups) {
        this.groups = ogroups;
    }
}





/*
    @Test
    public void sortBooks() {
        group[] groups = {
                new group(0, 100, "S"),
                new group(1, 100, "E")
        };

        // 1. sort using Comparable
        Arrays.sort(groups);
        System.out.println(Arrays.asList(groups));

        // 2. sort using comparator: sort by id
        Arrays.sort(groups, new Comparator<groups>() {
            @Override
            public int compare(group o1, group o2) {
                return (o1.Location - o2.Location);
            }
        });
        System.out.println(Arrays.asList(groups));
    }
*/
