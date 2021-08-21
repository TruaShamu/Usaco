import java.io.*;
import java.util.*;

/*
TASK: milk3
LANG: JAVA
 */
public class milk3 {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //The capacities of milk jugs
        int[] milkCapacities = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        int[] milkLevels = {0, 0, milkCapacities[2]};
        //int[] milkLevels = {0, 2, 8};
        //int[] array = pour2(milkLevels, milkCapacities, 1, 2);
        //for (int i = 0; i < array.length; i++) {
        //  System.out.println(array[i]);
        //}
        //System.exit(12);
        boolean[][][] visited = new boolean[21][21][21];
        visited[milkLevels[0]][milkLevels[1]][milkLevels[2]] = true;
        //visited[milkCapacities[0]][milkCapacities[1]][milkCapacities[2]] = true;
        //visited[0][0][0] = true;
        Set<Integer> cvalues = new HashSet<>();
        cvalues.add(milkLevels[2]);
        //recursiveLoop(cvalues, milkLevels, milkCapacities, visited);
        recursion2(visited, milkLevels, milkCapacities, cvalues);
        List<Integer> outputList = new ArrayList<>(cvalues);
        Collections.sort(outputList);
        for (int i = 0; i < outputList.size() - 1; i++) {
            //THIS MAY BREAK STUFF
            pw.print(outputList.get(i) + " ");
        }
        pw.print(outputList.get(outputList.size() - 1));
        pw.println();
        pw.close();
        long endTime = System.nanoTime();
        System.out.println("Total Time = " + (endTime - startTime) + " ns");

    }

    public static void recursiveLoop(Set<Integer> cvalues, int[] startingmilkLevels, int[] startingmilkCapacities, boolean[][][] visited) {

        for (int i = 0; i < startingmilkLevels.length; i++) {
            for (int j = 0; j < startingmilkLevels.length; j++) {
                if (i != j) {
                    System.out.println("PourFrom jug is " + i + ".");
                    System.out.println("PourTo jug is " + j + ".");
                    //Find all 6 children
                    int[] array = pour(startingmilkLevels, startingmilkCapacities, i, j);
                    System.out.println("After pouring:");
                    for (int k = 0; k < array.length; k++) {
                        System.out.print(array[k] + "    ");

                    }
                    if (!visited[array[0]][array[1]][array[2]]) {
                        System.out.println("Not visited");
                        visited[array[0]][array[1]][array[2]] = true;
                        if (array[0] == 0) {
                            cvalues.add(array[2]);
                            System.out.println("!!WE FOUND A VALUE!! C = " + array[2] + ".");
                        }
                        System.out.println("Enter recursion");
                        recursiveLoop(cvalues, array, startingmilkCapacities, visited);
                    } else {
                        System.out.println("Visited");
                    }
                }
            }
        }

    }

    //Pour milk from one jug to another.
    public static int[] pour(int[] milklevels, int[] milkcapacity, int pourfrom, int pourto) {
        int[] newmilk = Arrays.copyOf(milklevels, 3);
        // space is the space in the receiving jug
        int space = milkcapacity[pourto] - milklevels[pourto];
        //System.out.println("Space left for filling in jug " + pourto + " is " + space + ".");
        //If not enough space in the receiving jug to store all the milk from the pouring jug:
        if (space < milklevels[pourfrom]) {
            //System.out.println("Not enough space in jug " + pourto + " to store everything from jug " + pourfrom + ". ");
            newmilk[pourto] = milkcapacity[pourto];

            newmilk[pourfrom] = milklevels[pourfrom] - space;
        } else {
            //System.out.println("Enough space in jug " + pourto + " to store everything from jug " + pourfrom + ". ");
            newmilk[pourto] = milklevels[pourfrom];
            newmilk[pourfrom] = 0;
        }

        return newmilk;
    }

    public static void recursion2(boolean[][][] visited, int[] milkLevels, int[] milkCapacities, Set<Integer> cValues) {
        for (int from = 0; from < milkLevels.length; from++) {
            for (int to = 0; to < milkLevels.length; to++) {
                if (from != to) {
                    System.out.println("PourFrom jug is " + from + ".");
                    System.out.println("PourTo jug is " + to + ".");
                    int[] newMilk = pour2(milkLevels, milkCapacities, from, to);
                    if (!visited[newMilk[0]][newMilk[1]][newMilk[2]]) {
                        for (int k = 0; k < newMilk.length; k++) {
                            System.out.println(newMilk[k] + "    ");

                        }
                        System.out.println(".......");
                        visited[newMilk[0]][newMilk[1]][newMilk[2]] = true;
                        if (newMilk[0] == 0) {
                            cValues.add(newMilk[2]);
                        }
                        recursion2(visited, newMilk, milkCapacities, cValues);
                    }
                }
            }
        }
    }

    public static int[] pour2(int[] milkLevels, int[] milkCapacities, int from, int to) {
        int[] newMilk = new int[3];
        for (int i = 0; i < milkLevels.length; i++) {
            newMilk[i] = milkLevels[i];
        }
        int pourSpace = milkCapacities[to] - milkLevels[to];
        if (milkLevels[from] == 0) {
            return newMilk;
        } else {
            if (pourSpace < milkLevels[from]) {
                newMilk[to] = milkCapacities[to];
                newMilk[from] = milkLevels[from] - pourSpace;
            } else {
                newMilk[from] = 0;
                newMilk[to] = milkLevels[from] + milkLevels[to];
            }
            return newMilk;
        }

    }
}
