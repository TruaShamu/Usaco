import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lasers.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        Map<Line, Integer> dist = new HashMap<Line, Integer>();
        LinkedList<Line> queue = new LinkedList<Line>();
        queue.add(new Line(y1, true));
        dist.put(new Line(y1, true), 0);
        queue.add(new Line(x1, false));
        dist.put(new Line(x1, false), 0);
        Map<Integer, ArrayList<Integer>> xtoY = new HashMap<Integer, ArrayList<Integer>>();
        Map<Integer, ArrayList<Integer>> ytoX = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (!xtoY.containsKey(x)) {
                xtoY.put(x, new ArrayList<Integer>());
            }
            xtoY.get(x).add(y);
            if (!ytoX.containsKey(y)) {
                ytoX.put(y, new ArrayList<Integer>());
            }
            ytoX.get(y).add(x);
        }
        int ret = -1;
        while (!queue.isEmpty()) {
            Line curr = queue.removeFirst();
            if (curr.horizontal && curr.val == y2) {
                ret = dist.get(curr);
                break;
            }
            if (!curr.horizontal && curr.val == x2) {
                ret = dist.get(curr);
                break;
            }
            Map<Integer, ArrayList<Integer>> source = curr.horizontal ? ytoX : xtoY;
            if (source.containsKey(curr.val)) {
                for (int dest : source.get(curr.val)) {
                    Line nextLine = new Line(dest, !curr.horizontal);
                    if (!dist.containsKey(nextLine)) {
                        dist.put(nextLine, dist.get(curr) + 1);
                        queue.add(nextLine);
                    }
                }
            }
        }
        pw.println(ret);
        pw.close();


    }

    static class Line {
        public int val;
        public boolean horizontal;

        public Line(int val, boolean horizontal) {
            this.val = val;
            this.horizontal = horizontal;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (horizontal ? 1231 : 1237);
            result = prime * result + val;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Line other = (Line) obj;
            if (horizontal != other.horizontal)
                return false;
            if (val != other.val)
                return false;
            return true;
        }


    }

}
