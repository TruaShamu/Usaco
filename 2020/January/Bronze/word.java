import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("word.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("word.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int words = Integer.parseInt(st.nextToken());
        int charsPerLine = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        ArrayList<String> wordList = new ArrayList<>();
        for (int word = 0; word < words; word++) {
            wordList.add(st.nextToken());
        }
        String printString = wordList.get(0);
        int stringLength = wordList.get(0).length();
        for (int curWord = 1; curWord < wordList.size(); curWord++) {
            if (wordList.get(curWord).length() + stringLength <= charsPerLine) {
                printString += ' ';
                printString += wordList.get(curWord);
                stringLength += wordList.get(curWord).length();
            } else {
                pw.println(printString);
                printString = wordList.get(curWord);
                stringLength = printString.length();
            }
        }
        if (printString.length() != 0) {
            pw.println(printString);
        }
        pw.close();

    }
}
