
import java.io.*;

/*
LANG: JAVA
PROG: calfflac
*/

public class calfflac {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("calfflac.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));

        // read input
        char[] text = new char[20000];
        int textLength = 0;
        while (br.ready()) {
            text[textLength++] = (char) br.read();
        }
        String s = new String(text, 0, textLength);

        int resLen = 0;
        String res = null;
        String tmp = null;

        /* AABAA*/
        for (int i = 0; i < s.length(); i++) {
            if (isChar(s.charAt(i))) {
                tmp = res(s, i, i);
                if (tmp != null && alphabetCharCount(tmp) > resLen) {
                    resLen = alphabetCharCount(tmp);
                    res = tmp;
                }
            }
        }

        /* AABBAA*/
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isChar(c)) {
                int ncpos = nextChar(s, c, i + 1);
                if (ncpos != -1) {
                    tmp = res(s, i, ncpos);
                    if (tmp != null && alphabetCharCount(tmp) > resLen) {
                        resLen = alphabetCharCount(tmp);
                        res = tmp;
                    }
                }
            }
        }

        pw.println(resLen);
        pw.println(res);
        pw.close();
        System.exit(0);
    }

    private static int nextChar(String s, char c, int beg) {
        for (int i = beg; i < s.length(); i++) {
            if (s.charAt(i) == c)
                return i;
            else if (isChar(s.charAt(i)))
                return -1;
        }
        return -1;
    }

    private static int alphabetCharCount(String s) {
        //How many letters in the string
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isChar(s.charAt(i))) {
                cnt++;
            }
        }
        return cnt;
    }

    private static String res(String s, int low, int high) {
        int first = low, last = high;
        while (low >= 0 && high <= s.length() - 1) {
            if (!isChar(s.charAt(low))) {
                low--;
                continue;
            }
            if (!isChar(s.charAt(high))) {
                high++;
                continue;
            }
            if (!isCharEqual(s.charAt(low), s.charAt(high)))
                return s.substring(first, last + 1);
            else {
                first = low;
                last = high;
                low--;
                high++;
            }
        }
        return s.substring(first, last + 1);
    }

    private static boolean isChar(char c) {
        //Is this character a letter
        if (c >= 'a' && c <= 'z') return true;
        if (c >= 'A' && c <= 'Z') return true;
        return false;
    }

    private static boolean isCharEqual(char c1, char c2) {
        //Are two letters equal
        return Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }
}
