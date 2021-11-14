import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class berries {
    public static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //tree
        int k = Integer.parseInt(st.nextToken()); //basket

        trees = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        int res = 0;

        // Try i as the number of berries Elsie gets.
        for (int i = 1; i <= 1000; i++) {
            res = Math.max(res, go(i, k / 2));
        }
        // Output result.
        pw.println(res);
        pw.close();

    }

    public static int go(int limit, int baskets) {

        // Set up our priority queue.
        PriorityQueue<Integer> pq = new PriorityQueue<>(10, Collections.reverseOrder());
        for (int x : trees) {
            pq.add(x);
        }

        // Have Elsie take exactly [limit] berries each time.
        for (int i = 0; i < baskets; i++) {
            if (pq.size() == 0) {
                return -1;
            }
            int next = pq.poll();
            if (next < limit) {
                return -1;
            }
            if (next > limit) {
                pq.add(next - limit);
            }
        }

        int bessie = 0, cnt = 0;

        // Now it's bessie's turn.
        while (pq.size() > 0 && cnt < baskets) {

            // Get next.
            int next = pq.poll();
            cnt++;

            // Just have Bessie take limit also.
            if (next > limit) {
                bessie += limit;
                pq.add(next - limit);
            }

            // Here she takes the whole thing.
            else {
                bessie += next;
            }
        }

        // answer
        return bessie;
    }
}
