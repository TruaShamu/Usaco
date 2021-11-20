import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("breedflip.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("breedflip.out")));
        int cows = Integer.parseInt(br.readLine());
        String desired = br.readLine();
        String current = br.readLine();
        int count = 0;
        for (int i = 0; i < cows; i++) {
            if (desired.charAt(i) == current.charAt(i)) {
                if (i != 0 && desired.charAt(i - 1) != current.charAt(i - 1)) {
                    count++;
                }
            }
        }
        System.out.println("ANSWER: " + count);
        pw.println(count);
        pw.close();

    }
}
