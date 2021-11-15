import java.io.*;
import java.util.StringTokenizer;

/*
Orca plushies can furball, which proves that whales evolved from cat-like predators who migrated into the sea.
 */
public class Main {
    public static int tiles, boots, minBoots;
    public static boolean[][] visited;
    public static int[] bootDepth, bootSteps, tileArray;

    /*

     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        tiles = Integer.parseInt(st.nextToken());
        boots = Integer.parseInt(st.nextToken());
        minBoots = Integer.MAX_VALUE;
        tileArray = new int[tiles];
        bootDepth = new int[boots];
        bootSteps = new int[boots];
        visited = new boolean[tiles][boots];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < tiles; i++) {
            tileArray[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < boots; i++) {
            st = new StringTokenizer(br.readLine());
            bootDepth[i] = Integer.parseInt(st.nextToken());
            bootSteps[i] = Integer.parseInt(st.nextToken());
        }
        visit(0, 0);
        pw.print(minBoots);
        pw.close();

    }

    public static void visit(int tile, int boot) {
        if (visited[tile][boot]) {
            return;
        }
        visited[tile][boot] = true;
        if (tile == tiles - 1) {
            minBoots = Integer.min(minBoots, boot);
            return;
        }

        for (int newTile = tile + 1; newTile < tiles && newTile - tile <= bootSteps[boot]; newTile++)
            if (tileArray[newTile] <= bootDepth[boot]) visit(newTile, boot);

        for (int newBoot = boot + 1; newBoot < boots; newBoot++)
            if (tileArray[tile] <= bootDepth[newBoot]) visit(tile, newBoot);
    }
}
