/*
LANG: JAVA
PROG: ttwo
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ttwo {
    public static char[][] grid;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("ttwo.in"));
        PrintWriter pw = new PrintWriter("ttwo.out");
        grid = new char[10][10];
        for (int row = 0; row < 10; row++) {
            String line = br.readLine();
            grid[row] = line.toCharArray();
        }
        int[] dirX = new int[]{1, 0, -1, 0};
        int[] dirY = new int[]{0, 1, 0, -1};
        int fjX = -1; //FJ location
        int fjY = -1;
        int fjDir = 3; //Starts facimg n
        List<Integer> cowXs = new ArrayList<>(); //Cow locstiond
        List<Integer> cowYs = new ArrayList<>();
        List<Integer> cowDirs = new ArrayList<>();
        //Find loc if cows and fJ
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == 'F') {

                    fjY = i;
                    fjX = j;
                    grid[i][j] = '.';
                } else if (grid[i][j] == 'C') {
                    cowXs.add(j);
                    cowYs.add(i);
                    cowDirs.add(3);
                    grid[i][j] = '.';
                }
            }
        }
        boolean works = false;
        int minutes = 0;
        while (!works) {
            //For FJ
            int newX = fjX + dirX[fjDir];
            int newY = fjY + dirY[fjDir];
            if (needTurn(newX, newY)) {
                fjDir = (fjDir + 1) % 4;
            } else {
                //Go to new loc
                fjX = newX;
                fjY = newY;
            }
            //For cow pair
            for (int i = 0; i < cowXs.size(); i++) {
                newX = cowXs.get(i) + dirX[cowDirs.get(i)];
                newY = cowYs.get(i) + dirY[cowDirs.get(i)];
                if (needTurn(newX, newY)) {
                    int newCowDir = (cowDirs.get(i) + 1) % 4;
                    cowDirs.set(i, newCowDir);
                } else {
                    //Set new locs
                    cowXs.set(i, newX);
                    cowYs.set(i, newY);
                }
                if (cowXs.get(i).equals(fjX) && cowYs.get(i).equals(fjY)) {
                    //If they meet
                    System.out.println("meet");
                    works = true;
                }
            }
            if (minutes > 160000) {
                System.out.println("break");
                break;
            }
            minutes++;
        }
        if (works) {
            pw.println(minutes);

        } else {
            pw.println(0);
        }
        pw.close();
    }

    public static boolean needTurn(int newX, int newY) {
        if (newX >= 10 || newX < 0 || newY >= 10 || newY < 0 || grid[newY][newX] == '*') {
            return true;
        }
        return false;

    }
}
