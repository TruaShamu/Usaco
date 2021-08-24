import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("stampede.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stampede.out")));
        int N = Integer.parseInt(br.readLine());
        Event[] events = new Event[2 * N];
        item[] items = new item[N];
        //Read in input
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            items[i] = new item(i, y);
            events[2 * i] = new Event(y, -(x + 1) * r, i, true);
            events[2 * i + 1] = new Event(y, -x * r, i, false);
        }
        Arrays.sort(events);

        boolean[] seen = new boolean[N];
        TreeSet<item> ts = new TreeSet<>();

        int i = 0;
        while (i < 2 * N) {
            // Find range of events with the same time.
            int j = i;
            while (j < 2 * N && events[j].time == events[i].time) {
                j++;
            }
            j--; //For when j==i
            // Process these events.
            for (int k = i; k <= j; k++) {
                if (events[k].start) {
                    ts.add(items[events[k].id]);
                } else {
                    ts.remove(items[events[k].id]);
                }
            }
            // Mark this as being seen.
            if (ts.size() > 0) {
                seen[ts.first().id] = true;
            }
            // Go onto the next group.
            i = j + 1;
        }
        int res = 0;
        for (i = 0; i < N; i++) {
            if (seen[i]) {
                res++;
            }
        }
        System.out.println("ANSWER: " + res);
        pw.println(res);
        pw.close();
    }
}

class Event implements Comparable<Event> {

    public int y;
    public int time;
    public int id;
    public boolean start;

    public Event(int y, int time, int id, boolean start) {
        this.y = y;
        this.time = time;
        this.id = id;
        this.start = start;
    }

    public int compareTo(Event other) {
        if (time != other.time) {
            return time - other.time;
        }
        if (!start && other.start) return -1;
        if (start && !other.start) return 1;
        return 0;
    }
}

class item implements Comparable<item> {
    public int id, y;

    public item(int id, int y) {
        this.id = id;
        this.y = y;
    }

    public int compareTo(item other) {
        return this.y - other.y;
    }
}



class MyComparator implements Comparator<Event> {
    public int compare(Event a, Event b) {
        return a.y - b.y;
    }
}
