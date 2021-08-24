import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class bphoto {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bphoto.in"));
        PrintWriter pw = new PrintWriter("bphoto.out");
        int cows = Integer.parseInt(br.readLine());
        Entry[] entries = new Entry[cows];
        for (int i = 0; i < cows; i++) {
            int height = Integer.parseInt(br.readLine());
            entries[i] = new Entry(height, i);
        }
        Arrays.sort(entries);
        int ans = 0;
        int seen = 0;
        BIT bit = new BIT(cows);
        for (Entry curr : entries) {
            int lhs = bit.query(curr.index); //left hand, side
            int rhs = seen - lhs; //right hand, side
            if (Math.max(lhs, rhs) > 2 * Math.min(lhs, rhs)) {
                ans++;
            }
            bit.update(curr.index, 1); //+1
            seen++;
        }
        pw.println(ans);
        pw.close();

    }
}

class Entry implements Comparable<Entry> {
    public int height, index;

    public Entry(int height, int index) {
        this.height = height;
        this.index = index;
    }

    public int compareTo(Entry s) {
        return s.height - height;
    }
}

class BIT {

    public int[] tree;

    public BIT(int n) {
        tree = new int[n + 5];
    }

    public void update(int index, int val) {
        index++;
        while (index < tree.length) {
            tree[index] += val;
            index += index & -index;
        }
    }

    public int query(int index) {
        int ret = 0;
        index++;
        while (index > 0) {
            ret += tree[index];
            index -= index & -index;
        }
        return ret;
    }
}






