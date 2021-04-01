import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class traffic {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("traffic.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("traffic.out")));
        int events = Integer.parseInt(br.readLine());
        EventList eList = new EventList();
        for (int i = 0; i < events; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            eList.Events.add(new Event(type, a, b));
        }
        int lowerBound = -200000;
        int upperBound = 200000;
        for (int i = events - 1; i >= 0; i--) {
            Event curEvent = eList.Events.get(i);
            if (curEvent.type.equals("on")) {
                lowerBound = Math.max(0, lowerBound - curEvent.b);
                upperBound -= curEvent.a;

            }
            if (curEvent.type.equals("off")) {
                lowerBound += curEvent.a;
                upperBound += curEvent.b;

            }
            if (curEvent.type.equals("none")) {
                lowerBound = Math.max(lowerBound, curEvent.a);
                upperBound = Math.min(upperBound, curEvent.b);
            }
        }
        pw.println(lowerBound + " " + upperBound);
        lowerBound = -200000;
        upperBound = 200000;
        for (int i = 0; i < events; i++) {
            Event curEvent = eList.Events.get(i);
            if (curEvent.type.equals("on")) {
                lowerBound += curEvent.a;
                upperBound += curEvent.b;
            }
            if (curEvent.type.equals("off")) {
                lowerBound = Integer.max(0, lowerBound - curEvent.b);
                upperBound = Integer.max(0, upperBound - curEvent.a);
            }
            if (curEvent.type.equals("none")) {
                lowerBound = Integer.max(curEvent.a, lowerBound);
                upperBound = Integer.min(curEvent.b, upperBound);
            }

        }
        pw.println(lowerBound + " " + upperBound);
        pw.close();


    }
}

class Event {
    public int a, b;
    public String type;

    public Event(String type, int a, int b) {
        this.a = a;
        this.b = b;
        this.type = type;
    }


}

class EventList {

    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<Event> ascLocation;

    // We initialize static variables inside a static block.
    //1. Interface
    static {
        ascLocation = new Comparator<Event>() {
            @Override
            public int compare(Event p1, Event p2) {
                return Integer.compare(p1.a, p2.a);
            }
        };

    }

    //2.Property

    ArrayList<Event> Events = new ArrayList<>();

    EventList() {

    }

}


