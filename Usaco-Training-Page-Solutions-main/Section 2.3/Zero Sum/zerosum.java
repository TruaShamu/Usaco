
/*
LANG: JAVA
PROG: zerosum
*/


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class zerosum {
    public static ArrayList<String> resultList;
    public static int N;

    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("zerosum.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
        N = Integer.parseInt(br.readLine());
        resultList = new ArrayList<>();
        dfs("1", 2);
        Collections.sort(resultList);
        for (String oString : resultList) {
            pw.println(oString);
        }
        pw.close();


    }

    public static void dfs(String s, int num) {
        if (num == N) {
            int eval = eval(s);
            if (eval == N) resultList.add(s + "-" + N);
            else if (eval == -N) resultList.add(s + "+" + N);
            else if (eval(s + " " + N) == 0) resultList.add(s + " " + N);
        } else {
            dfs(s + "+" + num, num + 1);
            dfs(s + "-" + num, num + 1);
            dfs(s + " " + num, num + 1);
        }
    }

    public static int eval(String s) {
        s = s.replaceAll("\\s", "");
        String[] arr = s.split("[+-]");
        Queue<Character> operators = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            if (symbol == '+' || symbol == '-') operators.add(symbol);
        }

        int eval = Integer.parseInt(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (operators.peek() == '+') {
                eval += Integer.parseInt(arr[i]);
            } else {
                eval -= Integer.parseInt(arr[i]);
            }
            operators.remove();
        }
        return eval;
    }
}
