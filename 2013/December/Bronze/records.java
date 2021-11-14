import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static GroupList gList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("records.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("records.out")));
        int hours = Integer.parseInt(br.readLine());
        gList = new GroupList();

        for (int i = 0; i < hours; i++) {
            String input = br.readLine();
            input = sortString(input);
            int index = index(input);
            if (index != -1) {
                gList.Groups.get(index).frequencies++;
            } else {
                gList.Groups.add(new Group(input, 1));
            }
        }
        gList.sortbylocation();
        pw.println(gList.Groups.get(gList.Groups.size() - 1).frequencies);
        pw.close();
    }

    public static String sortString(String inputString) {
        // convert input string to char array
        char tempArray[] = inputString.toCharArray();

        // sort tempArray
        Arrays.sort(tempArray);

        // return new sorted string
        return new String(tempArray);
    }

    public static int index(String input) {
        for (int i = 0; i < gList.Groups.size(); i++) {
            if (gList.Groups.get(i).sortedString.equals(input)) {
                return i;
            }
        }
        return -1;
    }

}

class Group {
    public int frequencies;
    public String sortedString;

    public Group(String sortedString, int frequencies) {
        this.sortedString = sortedString;
        this.frequencies = frequencies;
    }


}

class GroupList {

    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<Group> ascLocation;

    // We initialize static variables inside a static block.
    //1. Interface
    static {
        ascLocation = new Comparator<Group>() {
            @Override
            public int compare(Group p1, Group p2) {
                return Integer.compare(p1.frequencies, p2.frequencies);
            }
        };

    }

    //2.Property

    ArrayList<Group> Groups = new ArrayList<Group>();

    //3. Method
    //a. Set /Get
    //b. General Function(Method>


    public ArrayList<Group> GetGroupss() {
        return this.Groups;
    }

    GroupList() {

    }



    /*public Book[] getBooks() {
        return books;
    } */

    public void sortbylocation() {
        Collections.sort(Groups, ascLocation);
    }


    public GroupList(ArrayList<Group> oGroups) {
        this.Groups = oGroups;
    }
}

