import java.io.*;
import java.util.*;

public class balancing {
    public static ArrayList<point> pList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
        int cows = Integer.parseInt(br.readLine());
        point[] list = new point[cows];
        for(int i = 0; i < cows; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list[i] = new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())/2);
        }
        Arrays.sort(list);
        int ret = list.length;
        for(int i = 0; i < cows; i++) {
            ArrayList<point> below = new ArrayList<point>();
            ArrayList<point> above = new ArrayList<point>();
            for(int j = 0; j < cows; j++) {
                if(list[j].y <= list[i].y) {
                    below.add(list[j]);
                }
                else {
                    above.add(list[j]);
                }
            }
            int belowIndex = 0;
            int aboveIndex = 0;
            while(belowIndex < below.size() || aboveIndex < above.size()) {
                int xBorder = Integer.MAX_VALUE;
                if(belowIndex < below.size()) {
                    xBorder = Math.min(xBorder, below.get(belowIndex).x);
                }
                if(aboveIndex < above.size()) {
                    xBorder = Math.min(xBorder, above.get(aboveIndex).x);
                }
                while(belowIndex < below.size() && below.get(belowIndex).x == xBorder) {
                    belowIndex++;
                }
                while(aboveIndex < above.size() && above.get(aboveIndex).x == xBorder) {
                    aboveIndex++;
                }
                ret = Math.min(ret, Math.max(Math.max(belowIndex, below.size() - belowIndex), Math.max(aboveIndex, above.size() - aboveIndex)));
            }
        }
        pw.println(ret);
        pw.close();
    }




}

class point implements Comparable<point>{
    public int x, y;

    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(point o) {
        return this.x - o.x;
    }


}

