import java.io.*;
import java.util.*;

public class multimoo {
    public static int[] dR = {1, 0, -1, 0};
    public static int[] dC = {0, 1, 0, -1};

    public static int curr = 0;
    public static int N;
    public static int[][] grid;
    public static boolean[][] visited;
    public static HashMap<String, Region> nodeMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("multimoo.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("multimoo.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        //Color, size
        HashMap<Integer, Integer> cells = new HashMap<>();
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int color = Integer.parseInt(st.nextToken());
                if (cells.containsKey(color)) {
                    cells.put(color, cells.get(color) + 1);
                } else {
                    cells.put(color, 1);
                }
                grid[i][j] = color;
            }
        }

        //Find all regions
        visited = new boolean[N][N];
        int biggestSingle = 0; //biggest single region
        ArrayList<Region> regions = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!visited[r][c]) {
                    visited[r][c] = true;
                    //Floodfill this location
                    Region rg = new Region(grid[r][c], curr);
                    floodFill(r, c, grid[r][c], rg);
                    biggestSingle = Math.max(biggestSingle, curr);
                    rg.area = curr;
                    regions.add(rg);
                    curr = 0;
                }
            }
        }

        //These 2 groups can connect
        for (Region rg : regions) {
            for (String s : rg.cells) {
                String[] ind = s.split(" ");
                int r = Integer.valueOf(ind[0]);
                int c = Integer.valueOf(ind[1]);
                for (int i = 0; i < 4; i++) {
                    int newR = r + dR[i];
                    int newC = c + dC[i];
                    if (inBounds(newR, newC) && grid[r][c] != grid[newR][newC]) {
                        connect(rg, nodeMap.get((newR) + " " + (newC)));
                    }
                }
            }
        }


        int biggestTeam = 0;
        Collections.sort(regions); //sort regions descending.
        for (int i = 0; i < regions.size(); i++) {
            Region x = regions.get(i);
            for (Region y : x.connected) {
                //Optimal
                if (biggestTeam >= cells.get(x.num) + cells.get(y.num)) {
                    continue;
                }


                int currTeam = 0;
                ArrayList<Region> q = new ArrayList<>(); //queue
                HashSet<Region> visitedTeams = new HashSet<>();
                q.add(x);
                visitedTeams.add(x);
                while (!q.isEmpty()) {
                    Region rg = q.remove(0);
                    currTeam += rg.area;
                    for (Region o : rg.connected) {
                        if (!visitedTeams.contains(o) && (o.num == x.num || o.num == y.num)) {
                            visitedTeams.add(o);
                            q.add(o);
                        }
                    }
                }
                biggestTeam = Math.max(biggestTeam, currTeam);
            }
        }
        pw.print(biggestSingle + "\n" + biggestTeam + "\n");
        pw.close();

    }

    public static boolean inBounds(int r, int c) {
        if (r < N && r >= 0 && c < N && c >= 0) {
            return true;
        }
        return false;
    }

    public static void floodFill(int r, int c, int regID, Region rg) {
        if (grid[r][c] == regID) {
            nodeMap.put(r + " " + c, rg);
            rg.cells.add(r + " " + c);
            visited[r][c] = true;
            curr++;
            for (int i = 0; i < 4; i++) {
                int newR = r + dR[i];
                int newC = c + dC[i];
                if (inBounds(newR, newC) && !visited[newR][newC]) {
                    floodFill(newR, newC, regID, rg);
                }
            }
        }
    }

    public static void connect(Region a, Region b) {
        a.connected.add(b);
        b.connected.add(a);
    }

    static class Region implements Comparable<Region> {
        int num;
        int area;
        // Cells in this region
        HashSet<String> cells = new HashSet<>();
        // Adjacent regions
        HashSet<Region> connected = new HashSet<>();

        public Region(int a, int b) {
            num = a;
            area = b;
        }

        @Override
        public int compareTo(Region other) {
            return Integer.compare(other.area, this.area);
        }
    }
}
