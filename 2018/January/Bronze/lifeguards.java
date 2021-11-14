import java.io.*;
import java.util.*;

public class Main {
    public static int[] shiftArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
        int numShifts = Integer.parseInt(br.readLine());
        shiftList sList = new shiftList();
        shiftArray = new int[1001];
        for (int i = 0; i < numShifts; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            shift oShift = new shift(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sList.shifts.add(oShift);


        }
        for (int i = 0; i < sList.shifts.size(); i++) {
            fillShift(sList.shifts.get(i), shiftArray);
        }
        System.out.println(Arrays.toString(shiftArray));
        //Shift array should be filled. Now simulate removing each shift
        int maxFilled = 0;
        for (shift oShift : sList.shifts) {
            int[] tempArray = shiftArray;
            removeShift(oShift, tempArray);
            //System.out.println(Arrays.toString(tempArray));
            maxFilled = Integer.max(maxFilled, howManyFilled(tempArray));
            System.out.println(Arrays.toString(tempArray));
            fillShift(oShift, tempArray);
        }
        System.out.println("ANSWER: " + maxFilled);
        pw.println(maxFilled);
        pw.close();

    }

    public static void fillShift(shift oShift, int[] array) {
        for (int i = oShift.start; i < oShift.end; i++) {
            array[i]++;
        }
    }

    public static int howManyFilled(int[] tempArray) {
        //How many hours have >=1 lifeguard?
        int answer = 0;
        for (int i : tempArray) {
            if (i > 0) {
                answer++;
            }
        }
        return answer;
    }

    public static void removeShift(shift oShift, int[] tempArray) {
        for (int i = oShift.start; i < oShift.end; i++) {
            tempArray[i]--;
        }
    }
}

class shift {
    public int start, end;


    public shift(int start, int end) {
        this.start = start;
        this.end = end;
    }


}

class shiftList {

    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<shift> ascLocation;

    // We initialize static variables inside a static block.
    //1. Interface
    static {
        ascLocation = new Comparator<shift>() {
            @Override
            public int compare(shift p1, shift p2) {
                return Integer.compare(p1.start, p2.start);
            }
        };

    }

    //2.Property

    ArrayList<shift> shifts = new ArrayList<shift>();

    //3. Method
    //a. Set /Get
    //b. General Function(Method>


    public ArrayList<shift> Getshiftss() {
        return this.shifts;
    }

    shiftList() {

    }



    /*public Book[] getBooks() {
        return books;
    } */

    public void sortbylocation() {
        Collections.sort(shifts, ascLocation);
    }


    public shiftList(ArrayList<shift> oshifts) {
        this.shifts = oshifts;
    }
}





/*
    @Test
    public void sortBooks() {
        shift[] shifts = {
                new shift(0, 100, "S"),
                new shift(1, 100, "E")
        };

        // 1. sort using Comparable
        Arrays.sort(shifts);
        System.out.println(Arrays.asList(shifts));

        // 2. sort using comparator: sort by id
        Arrays.sort(shifts, new Comparator<shifts>() {
            @Override
            public int compare(shift o1, shift o2) {
                return (o1.Location - o2.Location);
            }
        });
        System.out.println(Arrays.asList(shifts));
    }
*/
