import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class cowpatibility {
    public static HashMap<ArrayList<Integer>, Integer> subsets;

    public static void main(String[] args) throws IOException {
        subsets = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("cowpatibility.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));

        int N = Integer.parseInt(br.readLine());
        int[] flavors = new int[5];

        //Read input
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                flavors[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(flavors);
            updateSubsets(flavors);
        }


        // use the HashMap to compute answer - we use arr to determine whether the number should be added or substracted
        // from the total based on the equation explained above
        int[] arr = {0, 1, -1, 1, -1, 1}; //Add
        long ans = 0;

        for (ArrayList<Integer> subset : subsets.keySet()) {
            int numCows = subsets.get(subset);
            ans += arr[subset.size()] * (numCows * (numCows - 1) / 2);
        }

        // we have calculated the number of compatible cows, so subtract from the total number of pairings to get the number of incompatible cows
        ans = N * (N - 1) / 2 - ans;

        // write output
        pw.println(ans);
        pw.close();
    }

    static void updateSubsets(int[] flavors) {
        // add all the possible subsets of these 5 flavors to our HashMap
        for (int i = 1; i < 32; i++) {
            ArrayList<Integer> subset = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(flavors[j]);
                }
            }

            // add this subset to the HashMap
            subsets.put(subset, subsets.getOrDefault(subset, 0) + 1);

        }
    }
}
