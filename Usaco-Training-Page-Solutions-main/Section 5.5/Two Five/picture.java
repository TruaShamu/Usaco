import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/*
TASK: picture
LANG: JAVA
 */
public class picture {
    public static nodeList eX, eY;
    public static int n;
    public static int balance = 10000 + 5;
    public static int[] level = new int[balance * 3];
    public static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("picture.in"));
        PrintWriter pw = new PrintWriter("picture.out");
        n = Integer.parseInt(br.readLine());
        eX = new nodeList();
        eY = new nodeList();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            node tmp1 = new node(0, 0, 0, false);
            node tmp2 = new node(0, 0, 0, false);
            node tmp3 = new node(0, 0, 0, false);
            node tmp4 = new node(0, 0, 0, false);
            //Take apart all the lines in a rectangle
            int ui, uj, di, dj;
            di = Integer.parseInt(st.nextToken());
            dj = Integer.parseInt(st.nextToken());
            ui = Integer.parseInt(st.nextToken());
            uj = Integer.parseInt(st.nextToken());
            //Starts the blocks
            tmp1.s = di;
            tmp1.e = ui;
            tmp1.base = uj;
            tmp1.start = false;
            eY.nodes.add(tmp1);
            tmp2.s = di;
            tmp2.e = ui;
            tmp2.base = dj;
            tmp2.start = true;
            eY.nodes.add(tmp2);
            tmp3.s = dj;
            tmp3.e = uj;
            tmp3.base = ui;
            tmp3.start = false;
            eX.nodes.add(tmp3);
            tmp4.s = dj;
            tmp4.e = uj;
            tmp4.base = di;
            tmp4.start = true;
            eX.nodes.add(tmp4);
        }
        //Sort top to bottom, left to right
        eX.sortbylocation();
        eY.sortbylocation();
        scan(eX);
        scan(eY);
        System.out.println("ANSWER: " + ans);
        pw.println(ans);
        pw.close();

    }

    public static void scan(nodeList nList) {
        Arrays.fill(level, 0);
        for (int i = 0; i < nList.nodes.size(); i++) {
            node tmp = nList.nodes.get(i);
            if (tmp.start) {
                //Higher end
                for (int j = tmp.s; j < tmp.e; j++) {
                    level[j + balance]++;
                    if (level[j + balance] == 1) ans++; //This cell only has 1
                }
            } else {
                //Lower
                for (int j = tmp.s; j < tmp.e; j++) {
                    level[j + balance]--;
                    if (level[j + balance] == 0) ans++;
                }
            }
        }
    }
}


class node {

    public int base, s, e;
    public boolean start;

    public node(int base, int s, int e, boolean start) {
        this.base = base;
        this.s = s;
        this.e = e;
        this.start = start;
    }


}

class nodeList {

    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<node> ascLocation;

    // We initialize static variables inside a static block.
    //1. Interface
    static {
        ascLocation = new Comparator<node>() {
            @Override
            public int compare(node p1, node p2) {
                // @todo Not sure if works or not../
                int p1Bool = (p1.start) ? (1) : (0);
                int p2Bool = (p2.start) ? (1) : (0);
                if ((p1.base < p2.base || p1.base == p2.base && p1Bool > p2Bool)) {
                    return -1;
                }

                if ((p1.base == p2.base && p1Bool == p2Bool)) {
                    return 0;
                }
                return 1;

            }
        };

    }

    //2.Property

    ArrayList<node> nodes = new ArrayList<node>();

    //3. Method
    //a. Set /Get
    //b. General Function(Method>


    public ArrayList<node> Getnodess() {
        return this.nodes;
    }

    nodeList() {

    }



    /*public Book[] getBooks() {
        return books;
    } */

    public void sortbylocation() {
        Collections.sort(nodes, ascLocation);
    }


    public nodeList(ArrayList<node> onodes) {
        this.nodes = onodes;
    }
}

