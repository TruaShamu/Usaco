import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class perimeter {
    public static ArrayList<blob> bList;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("perimeter.out"));
        N = Integer.parseInt(br.readLine());
        bList = new ArrayList<>();
        int[][] filled = new int[N][N];
        int[][] group = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(group[i], -1);
            String inp = br.readLine();
            for (int j = 0; j < N; j++) {
                filled[i][j] = (inp.charAt(j) == '#' ? 1 : 0);
            }
        }

        //Union find
        int curGroup = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (filled[row][col] == 0) {
                    continue;
                }
                //[0,0]
                if (row == 0 && col == 0) {
                    group[row][col] = curGroup;
                    curGroup++;
                    bList.add(new blob(1, 1));
                    continue;
                }

                //[0, c]
                if (row == 0) {
                    if (group[row][col - 1] == -1) {
                        group[row][col] = curGroup;
                        curGroup++;
                        bList.add(new blob(1, 1));
                    } else {
                        group[row][col] = group[row][col - 1];
                        bList.get(group[row][col]).area++;
                    }
                }

                //[r,0]
                if (col == 0) {
                    if (group[row - 1][col] == -1) {
                        group[row][col] = curGroup;
                        curGroup++;
                        bList.add(new blob(1, 1));
                    } else {
                        group[row][col] = group[row - 1][col];
                        bList.get(group[row][col]).area++;
                    }
                }

                //[r, c]
                if (col != 0 && row != 0) {
                    int groupL = group[row][col - 1];
                    int groupU = group[row - 1][col];
                    if (groupL == -1 && groupU == -1) {
                        group[row][col] = curGroup;
                        curGroup++;
                        bList.add(new blob(1, 1));
                        continue;
                    }
                    if (groupL == groupU) {
                        group[row][col] = group[row - 1][col];
                        bList.get(group[row][col]).area++;
                        continue;
                    }
                    if (groupL != -1 && groupU == -1) {
                        group[row][col] = groupL;
                        bList.get(group[row][col]).area++;
                        continue;
                    }

                    if (groupL == -1 && groupU != -1) {
                        group[row][col] = groupU;
                        bList.get(group[row][col]).area++;
                        continue;
                    }

                    if (groupL != groupU) {
                        group[row][col] = groupL;
                        bList.get(groupL).area++;
                        bList.get(groupU).area = -1;
                        for (int i = 0; i < N; i++) {
                            for (int j = 0; j < N; j++) {
                                if (group[i][j] == groupU) {
                                    group[i][j] = groupL;
                                    bList.get(groupL).area++;
                                }
                            }
                        }
                    }


                }


            }
        }

        int[] dR = new int[]{-1, 0, 0, 1};
        int[] dC = new int[]{0, -1, 1, 0};
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (group[row][col] == -1) {
                    continue;
                }
                for (int i = 0; i < dR.length; i++) {
                    if (!onGrid(row + dR[i], col + dC[i]) || group[row][col] != group[row + dR[i]][col + dC[i]]) {
                        bList.get(group[row][col]).perimeter++;
                    }
                }
            }
        }

        Collections.sort(bList);
        System.out.println("ANSWER: " + bList.get(0).area + " " + (bList.get(0).perimeter - 1));
        pw.println(bList.get(0).area + " " + (bList.get(0).perimeter - 1));
        pw.close();


    }

    public static boolean onGrid(int r, int c) {
        return (r >= 0 && r < N && c >= 0 && c < N);
    }
}


class blob implements Comparable<blob> {
    public int area, perimeter;

    public blob(int area, int perimeter) {
        this.area = area;
        this.perimeter = perimeter;
        //this.id = id;
    }

    public int compareTo(blob other) {
        if (this.area == other.area) {
            return Integer.compare(this.perimeter, other.perimeter);
        } else {
            return Integer.compare(other.area, this.area);
        }
    }
}

