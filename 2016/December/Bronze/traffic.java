import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class traffic {
    public static int curUpperbound;
    public static int curLowerbound;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("traffic.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("traffic.out")));
        int sections = Integer.parseInt(br.readLine());
        sectionList sList = new sectionList();
        for (int i = 0; i < sections; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sList.sections.add(new section(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        curLowerbound = -100000000;
        curUpperbound = 100000000;
        for (int i = sList.sections.size() - 1; i >= 0; i--) {
            //section oSection = sList.sections.get(sList.sections.size() - 1);
            //System.out.println(oSection);
            section oSection = sList.sections.get(i);
            if (oSection.Type.equals("off")) {
                System.out.println("off");
                curUpperbound += oSection.b;
                curLowerbound += oSection.a;
            }
            if (oSection.Type.equals("on")) {
                curUpperbound = (curUpperbound - oSection.a);
                curLowerbound = Math.max(0, curLowerbound - oSection.b); //works
            }
            if (oSection.Type.equals("none")) {
                System.out.println("none");
                curLowerbound = Math.max(curLowerbound, oSection.a);
                curUpperbound = Math.min(curUpperbound, oSection.b);

            }
            System.out.println(curLowerbound + " " + curUpperbound);
        }
        pw.println(curLowerbound + " " + curUpperbound);

        curLowerbound = -1000000;

        curUpperbound = 10000000;
        for (int i = 0; i < sections; i++) {
            section oSection = sList.sections.get(i);
            if (oSection.Type.equals("off")) {
                Off(oSection.a, oSection.b);
            }
            if (oSection.Type.equals("on")) {
                On(oSection.a, oSection.b);
            }
            if (oSection.Type.equals("none")) {
                None(oSection.a, oSection.b);
            }
            //System.out.println(curLowerbound + " " + curUpperbound);
        }
        pw.println(curLowerbound + " " + curUpperbound);
        pw.close();

    }

    public static void On(int x2, int y2) {
        curLowerbound += x2;
        curUpperbound += y2;
    }

    public static void Off(int x2, int y2) {
        curLowerbound = Integer.max(0, curLowerbound - y2);
        curUpperbound = Integer.max(0, curUpperbound - x2);
    }

    public static void None(int x2, int y2) {
        curLowerbound = Math.max(curLowerbound, x2);
        curUpperbound = Math.min(curUpperbound, y2);

    }
}


class section {
    public int a, b;
    public String Type;

    public section(String Type, int a, int b) {
        this.a = a;
        this.b = b;
        this.Type = Type;
    }


}

class sectionList {

    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<section> ascLocation;

    // We initialize static variables inside a static block.
    //1. Interface
    static {
        ascLocation = new Comparator<section>() {
            @Override
            public int compare(section p1, section p2) {
                return Integer.compare(p1.a, p2.a);
            }
        };

    }

    public sectionList() {

    }

    ArrayList<section> sections = new ArrayList<section>();

    //3. Method
    //a. Set /Get
    //b. General Function(Method>


    public ArrayList<section> GetSections() {
        return this.sections;
    }



    /*public Book[] getBooks() {
        return books;
    } */

    public void sortbylocation() {
        Collections.sort(sections, ascLocation);
    }


    public sectionList(ArrayList<section> oSections) {
        this.sections = oSections;
    }
}


class SortByLocation implements Comparator<section> {
    // Used for sorting in ascending order of
    // roll number
    public int compare(section a, section b) {
        return a.a - b.a;
    }
}


/*
    @Test
    public void sortBooks() {
        CowPoint[] CowPoints = {
                new CowPoint(0, 100, "S"),
                new CowPoint(1, 100, "E")
        };

        // 1. sort using Comparable
        Arrays.sort(CowPoints);
        System.out.println(Arrays.asList(CowPoints));

        // 2. sort using comparator: sort by id
        Arrays.sort(CowPoints, new Comparator<CowPoints>() {
            @Override
            public int compare(CowPoint o1, CowPoint o2) {
                return (o1.Location - o2.Location);
            }
        });
        System.out.println(Arrays.asList(CowPoints));
    }
*/
