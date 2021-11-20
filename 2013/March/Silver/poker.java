import java.io.*;
//Poker Hands
public class poker {
    public static int MAX = 100100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("poker.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("poker.out")));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[MAX];
        array[0] = array[N + 1] = 0;
        for (int i = 1; i <= N; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }
        //Arrays.sort(array);
        long res = 0;
        for (int i = 0; i <= N; ++i) {
            res += (Math.abs(array[i + 1] - array[i]));
        }
        res /= 2;
        System.out.println("ANSWER: " + res);
        pw.println(res);
        pw.close();


    }
}
