import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


 /*
   LANG: JAVA
  TASK:prime3
  */


public class prime3 {
    static boolean[] prime = new boolean[100001];
    static int[] digit = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader fin = new BufferedReader(new FileReader("prime3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prime3.out")));
        StringTokenizer st = new StringTokenizer(fin.readLine());
        int sum = Integer.parseInt(st.nextToken());
        int upperleft = Integer.parseInt(st.nextToken());
        int totalcount = 0;
        ArrayList<String> result = new ArrayList<String>();

        if (sum % 3 == 0) {
            out.println("NONE");
        } else {


            //Find the primes first
            Arrays.fill(prime, true);
            for (int i = 0; i < 100000; i++)
                if (i % 2 == 0)
                    prime[i] = false;
            prime[1] = false;
            prime[2] = true;

            for (int i = 3; i <= 1000; i += 2)
                if (prime[i])
                    for (int j = i + i; j <= 100000; j += i) prime[j] = false;

            //count the digit
            for (int i = 10001; i <= 99997; i++)
                if (prime[i]) {
                    int temp = i;
                    int tempsum = 0;
                    while (temp != 0) {
                        tempsum += temp % 10;
                        temp /= 10;

                    }
                    digit[i] = tempsum;

                }

            int[] numbers = new int[100000];
            int count = 0;
            for (int i = 10001; i <= 99997; i++) {
                if (prime[i] && digit[i] == sum)
                    numbers[count++] = i;

            }

            int[] line5 = new int[100000];
            int countline5 = 0;
            for (int i = 0; i < count; i++) {
                int temp = numbers[i];
                boolean flag = true;
                while (temp != 0) {
                    int dit = temp % 10;
                    if (dit == 0 || dit == 2 || dit == 4 || dit == 5 || dit == 6 || dit == 8) {
                        flag = false;
                        break;

                    }
                    temp /= 10;

                }
                if (flag) line5[countline5++] = numbers[i];

            }
            // for (int i = 0; i < countline5; i++) System.out.println("* " + line5[i]);

            int[] line1 = new int[100000];
            int countline1 = 0;
            for (int i = 0; i < count; i++) {
                int firstdit = numbers[i] / 10000;
                int seconddit = numbers[i] / 1000 % 10;
                int thirddit = numbers[i] / 100 % 10;
                int fourthdit = numbers[i] / 10 % 10;
                if (firstdit == upperleft && seconddit != 0 && thirddit != 0 && fourthdit != 0)
                    line1[countline1++] = numbers[i];

            }

            boolean[][] firstfifth = new boolean[10][10];
            boolean[][] secondfifth = new boolean[10][10];
            boolean[][] thirdfifth = new boolean[10][10];
            boolean[][] fourthfifth = new boolean[10][10];
            boolean[][][] secondfourthfifth = new boolean[10][10][10];
            boolean[][][][] firstsecondfourthfifth = new boolean[10][10][10][10];

            for (int i = 0; i < count; i++) {
                int firstdit = numbers[i] / 10000;
                int seconddit = numbers[i] / 1000 % 10;
                int thirddit = numbers[i] / 100 % 10;
                int fourthdit = numbers[i] / 10 % 10;
                int fifthdit = numbers[i] % 10;
                firstfifth[firstdit][fifthdit] = true;
                secondfifth[seconddit][fifthdit] = true;
                thirdfifth[thirddit][fifthdit] = true;
                fourthfifth[fourthdit][fifthdit] = true;
                secondfourthfifth[seconddit][fourthdit][fifthdit] = true;
                firstsecondfourthfifth[firstdit][seconddit][fourthdit][fifthdit] = true;
            }

            //Start
            int[][] map = new int[5][5];
            map[0][0] = upperleft;

            int temp = 0;
            //row 5
            for (int a = 0; a < countline5; a++) {
                temp = line5[a];
                for (int k = 4; k >= 0; k--) {
                    map[4][k] = temp % 10;
                    temp /= 10;
                }

                //column 5
                for (int b = 0; b < countline5; b++)
                    if (map[4][4] == line5[b] % 10) {
                        temp = line5[b];
                        for (int k = 4; k >= 0; k--) {
                            map[k][4] = temp % 10;
                            temp /= 10;

                        }


                        //main dig
                        for (int c = 0; c < count; c++)
                            if (map[4][0] == numbers[c] / 10000 && map[0][4] == numbers[c] % 10) {

                                int seconddit = numbers[c] / 1000 % 10;
                                int thirddit = numbers[c] / 100 % 10;
                                int fourthdit = numbers[c] / 10 % 10;
                                if (secondfifth[seconddit][map[3][4]] == true &&
                                        fourthfifth[seconddit][map[4][1]] == true &&
                                        thirdfifth[thirddit][map[2][4]] == true &&
                                        thirdfifth[thirddit][map[4][2]] == true &&
                                        fourthfifth[fourthdit][map[1][4]] == true &&
                                        secondfifth[fourthdit][map[4][3]] == true) {
                                    map[3][1] = seconddit;
                                    map[2][2] = thirddit;
                                    map[1][3] = fourthdit;


                                    //second dig
                                    for (int d = 0; d < count; d++)
                                        if (numbers[d] / 10000 == upperleft && numbers[d] % 10 == map[4][4] && numbers[d] / 100 % 10 == map[2][2]) {

                                            seconddit = numbers[d] / 1000 % 10;
                                            fourthdit = numbers[d] / 10 % 10;
                                            if (secondfourthfifth[seconddit][map[3][1]][map[4][1]] == true &&
                                                    secondfourthfifth[seconddit][map[1][3]][map[1][4]] == true &&
                                                    secondfourthfifth[map[3][1]][fourthdit][map[3][4]] == true &&
                                                    secondfourthfifth[map[1][3]][fourthdit][map[4][3]] == true) {
                                                map[1][1] = seconddit;
                                                map[3][3] = fourthdit;

                                                //first row
                                                for (int e = 0; e < countline1; e++)
                                                    if (line1[e] % 10 == map[0][4]) {
                                                        seconddit = line1[e] / 1000 % 10;
                                                        thirddit = line1[e] / 100 % 10;
                                                        fourthdit = line1[e] / 10 % 10;
                                                        if (firstsecondfourthfifth[seconddit][map[1][1]][map[3][1]][map[4][1]] == false ||
                                                                firstsecondfourthfifth[fourthdit][map[1][3]][map[3][3]][map[4][3]] == false)
                                                            continue;

                                                        map[0][1] = seconddit;
                                                        map[0][2] = thirddit;
                                                        map[0][3] = fourthdit;

                                                        //first column
                                                        for (int f = 0; f < countline1; f++)
                                                            if (line1[f] % 10 == map[4][0]) {
                                                                seconddit = line1[f] / 1000 % 10;
                                                                thirddit = line1[f] / 100 % 10;
                                                                fourthdit = line1[f] / 10 % 10;

                                                                if (firstsecondfourthfifth[seconddit][map[1][1]][map[1][3]][map[1][4]] == false ||

                                                                        firstsecondfourthfifth[fourthdit][map[3][1]][map[3][3]][map[3][4]] == false)
                                                                    continue;
                                                                map[1][0] = seconddit;
                                                                map[2][0] = thirddit;
                                                                map[3][0] = fourthdit;

                                                                //left four digit
                                                                map[1][2] = sum - map[1][0] - map[1][1] - map[1][3] - map[1][4];
                                                                map[3][2] = sum - map[3][0] - map[3][1] - map[3][3] - map[3][4];
                                                                map[2][1] = sum - map[0][1] - map[1][1] - map[3][1] - map[4][1];
                                                                map[2][3] = sum - map[0][3] - map[1][3] - map[3][3] - map[4][3];

                                                                //check
                                                                if (map[1][2] < 0 || map[3][2] < 0 || map[2][1] < 0 || map[2][3] < 0)
                                                                    continue;
                                                                if (map[1][2] > 9 || map[3][2] > 9 || map[2][1] > 9 || map[2][3] > 9)
                                                                    continue;

                                                                int tempsum = 0;

                                                                for (int k = 0; k < 5; k++) tempsum += map[2][k];
                                                                if (tempsum != sum) continue;

                                                                tempsum = 0;
                                                                for (int k = 0; k < 5; k++) tempsum += map[k][2];
                                                                if (tempsum != sum) continue;


                                                                tempsum = 0;
                                                                //row 2
                                                                for (int k = 0; k < 5; k++)
                                                                    tempsum = tempsum * 10 + map[1][k];
                                                                if (tempsum <= 99999 && !prime[tempsum]) continue;

                                                                //row 3
                                                                tempsum = 0;
                                                                for (int k = 0; k < 5; k++)
                                                                    tempsum = tempsum * 10 + map[2][k];
                                                                if (tempsum <= 99999 && !prime[tempsum]) continue;

                                                                //row 4
                                                                tempsum = 0;
                                                                for (int k = 0; k < 5; k++)
                                                                    tempsum = tempsum * 10 + map[3][k];
                                                                if (tempsum <= 99999 && !prime[tempsum]) continue;

                                                                //column 2
                                                                tempsum = 0;
                                                                for (int k = 0; k < 5; k++)
                                                                    tempsum = tempsum * 10 + map[k][1];
                                                                if (tempsum <= 99999 && !prime[tempsum]) continue;

                                                                //column 3
                                                                tempsum = 0;
                                                                for (int k = 0; k < 5; k++)
                                                                    tempsum = tempsum * 10 + map[k][2];
                                                                if (tempsum <= 99999 && !prime[tempsum]) continue;

                                                                //column 4
                                                                tempsum = 0;
                                                                for (int k = 0; k < 5; k++)
                                                                    tempsum = tempsum * 10 + map[k][3];
                                                                if (tempsum <= 99999 && !prime[tempsum]) continue;

                                                                totalcount++;
                                                                StringBuffer sb = new StringBuffer();
                                                                for (int x = 0; x < 5; x++)
                                                                    for (int y = 0; y < 5; y++)
                                                                        sb.append(map[x][y]);

                                                                result.add(sb.toString());
                                                            }
                                                    }
                                            }
                                        }

                                }
                            }
                    }
            }

        }

        if (totalcount > 0) {
            Collections.sort(result);
            int tc = 0;
            for (String x : result) {
                if (tc != 0)
                    out.println();
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++)
                        out.print(x.charAt(i * 5 + j));
                    out.println();
                }
                tc++;
            }

        } else
            out.println("NONE");
        out.close();
        System.exit(0);
    }
}

