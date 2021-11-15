import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem3 {
    public static ArrayList<Integer>[] killed, killed2;
    public static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cows = Integer.parseInt(br.readLine());
        ArrayList<point> east = new ArrayList<>();
        ArrayList<point> north = new ArrayList<>();
        for (int i = 0; i < cows; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char dir = st.nextToken().charAt(0);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (dir == 'E') {
                east.add(new point(x, y, i));
            }
            if (dir == 'N') {
                north.add(new point(x, y, i));
            }
        }

        //Sort east desc
        Collections.sort(east, new xDesc());

        //Sort north
        Collections.sort(north);
        ArrayList<killing> killings = new ArrayList<>();
        for (int i = 0; i < east.size(); i++) {
            for (int j = 0; j < north.size(); j++) {
                point East = east.get(i);
                point North = north.get(j);

                //No need to process
                if (East.x > North.x) {
                    continue;
                }


                int meetCoordX = North.x;
                int meetCoordY = East.y;
                int distanceX = meetCoordX - East.x;
                int distanceY = meetCoordY - North.y;
                if (distanceX != distanceY && distanceX >= 0 && distanceY >= 0) {
                    if (distanceX < distanceY) {

                        killings.add(new killing(meetCoordX, meetCoordY, distanceY, distanceX, North.idx, East.idx));
                    } else {
                        killings.add(new killing(meetCoordX, meetCoordY, distanceX, distanceY, East.idx, North.idx));
                    }
                }

            }
        }
        Collections.sort(killings);
        int[] answer = new int[cows];
        visited = new boolean[cows];
        Arrays.fill(visited, false);

        killed = new ArrayList[cows];
        killed2 = new ArrayList[cows];
        for (int i = 0; i < cows; i++) {
            killed[i] = new ArrayList<>();
        }

        Arrays.fill(answer, -1);
        for (killing oEvent : killings) {
            if ((answer[oEvent.kill] == -1 || (answer[oEvent.kill] > oEvent.killTime)) && answer[oEvent.dead] == -1) {
                answer[oEvent.dead] = oEvent.deadTime;
                killed[oEvent.kill].add(oEvent.dead);
            }
        }


        //Copy of
        for (int i = 0; i < cows; i++) {
            killed2[i] = new ArrayList<>(killed[i]);
        }


        for (int i = 0; i < cows; i++) {
            dfs(i);
        }


        for (int i = 0; i < cows; i++) {
            System.out.println(killed2[i].size());
        }


    }


    public static void dfs(int node) {
        if (!visited[node]) {
            //Visit all it's killed
            for (int child : killed[node]) {
                dfs(child);
            }

            for (int child : killed[node]) {
                for (int childOfChild : killed2[child]) {
                    killed2[node].add(childOfChild);
                }
            }
            visited[node] = true;
        }


    }


    static class point implements Comparable<point> {
        public int x;
        public int y;
        public int idx;

        public point(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }

        //Sort x ascending
        public int compareTo(point other) {
            return Integer.compare(this.x, other.x);
        }

    }

    static class xDesc implements Comparator<point> {
        public int compare(point a, point b) {
            return Integer.compare(b.x, a.x);
        }
    }

    static class killing implements Comparable<killing> {
        public int x, y;
        public int deadTime, killTime;
        public int dead, kill;


        public killing(int x, int y, int deadTime, int killTime, int dead, int kill) {
            this.x = x;
            this.y = y;
            this.dead = dead;
            this.deadTime = deadTime;
            this.killTime = killTime;
            this.kill = kill;
        }

        //Sort x ascending
        public int compareTo(killing other) {
            return Integer.compare(this.deadTime, other.deadTime);
        }


    }


}

