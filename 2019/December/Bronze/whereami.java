import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class whereami {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("whereami.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("whereami.out")));
        int length = Integer.parseInt(br.readLine());
        String input = br.readLine();
        ArrayList<String> suffixes = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            suffixes.add(input.substring(input.length() - i));
        }
        Collections.sort(suffixes);
        System.out.println(suffixes);
        int answer = 0;
        for (int i = 1; i < suffixes.size(); i++) {
            answer = Integer.max(answer, charactersInCommon(suffixes.get(i), suffixes.get(i - 1)));
        }
        System.out.println("ANSWER: " + (answer + 1));
        pw.println(answer + 1);
        pw.close();

    }

    public static int charactersInCommon(String s1, String s2) {
        int inCommon = 0;
        int length = Integer.min(s1.length(), s2.length());
        for (int i = 0; i < length; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                inCommon++;
            } else {
                break;
            }
        }
        return inCommon;
    }

}

