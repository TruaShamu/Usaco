import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class paint {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("paint.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // Walking Segments
        int K = Integer.parseInt(st.nextToken()); //Min Coats Of Paint
        //System.out.println("N: " + N + " K: " + K);

        //Initialize event array
        Event[] events = new Event[2 * N];
        for (int i = 0; i < events.length; i++) {
            events[i] = new Event(0, 0);
        }


        int loc = 0; //The current location
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int units = Integer.parseInt(st.nextToken());
            //System.out.println("UNITS: " + units);
            int newLoc = loc; // Loc after walking this segment
            char dir = st.nextToken().charAt(0); //Dir L or R

            if (dir == 'L')
                newLoc -= units;

            if (dir == 'R')
                newLoc += units;


            events[2 * i].loc = Integer.min(loc, newLoc); //Start
            events[2 * i].delta = 1;
            events[2 * i + 1].loc = Integer.max(loc, newLoc); //End
            events[2 * i + 1].delta = -1;
            loc = newLoc;


        }
        Arrays.sort(events);

        int nCoats = 0;
        int answer = 0;
        for (int i = 0; i < 2 * N; i++) {
            if (i > 0 && nCoats >= K) {
                answer += events[i].loc - events[i - 1].loc;
            }
            nCoats += events[i].delta;
        }

        System.out.println("ANSWER: " + answer);
        pw.println(answer);
        pw.close();
    }
}

class Event implements Comparable<Event> {
    public int delta;
    public int loc;

    public Event(int loc, int delta) {
        this.loc = loc;
        this.delta = delta; //+1 for start, -1 for end
    }

    public int compareTo(Event oEvent) {
        return Integer.compare(this.loc, oEvent.loc);

    }
}

