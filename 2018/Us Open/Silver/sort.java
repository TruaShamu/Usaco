import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("sort.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
        int n = Integer.parseInt(br.readLine());
        entry[] array = new entry[n];
        for (int i = 0; i < n; i++) {
            array[i] = new entry(Integer.parseInt(br.readLine()), i);
        }
        Arrays.sort(array);
        int res = 1;
        for (int i = 0; i < array.length; i++) {
            res = Math.max(res, 1 + array[i].index - i);
        }
        System.out.println(res);
        pw.println(res);
        pw.close();
    }

}

class entry implements Comparable<entry> {
    public int value, index;

    public entry(int value, int index) {
        this.value = value;
        this.index = index;
    }

    public int compareTo(entry other) {
        if (this.value != other.value) return this.value - other.value;
        return this.index - other.index;
    }


}
