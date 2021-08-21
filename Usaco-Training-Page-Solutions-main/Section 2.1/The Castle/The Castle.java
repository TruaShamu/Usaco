import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
PROB: castle
LANG: JAVA
LINK: https://train.usaco.org/usacoprob2?a=DrTO6GX1LvD&S=castle
 */
public class castle {
    public static int curGroup;
    public static groupList gList;
    public static int[][] inputArray;
    public static int[][] groupArray;
    public static int rows;
    public static int columns;
    public static int mRow, mColumn;
    public static char mDir;
    public static int combArea;

    public static void main(String[] args) throws IOException {
        final long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("castle.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));

        curGroup = 0;
        gList = new groupList(new ArrayList<>());
        StringTokenizer st = new StringTokenizer(br.readLine());
        columns = Integer.parseInt(st.nextToken());
        rows = Integer.parseInt(st.nextToken());
        inputArray = new int[rows][columns];
        groupArray = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            String readLine = br.readLine();
            st = new StringTokenizer(readLine);
            for (int column = 0; column < columns; column++) {
                inputArray[row][column] = Integer.parseInt(st.nextToken());
            }
        }


        initializeGroupArray();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int curCode = inputArray[row][column];

                if (!connectW(curCode) && !connectN(curCode)) {
                    makeNewGroup(row, column);
                }
                if (!connectW(curCode) && connectN(curCode)) {

                    int northGroup = groupArray[row - 1][column];
                    groupArray[row][column] = northGroup;
                    gList.groups.get(northGroup).area++;
                }
                if (connectW(curCode) && !connectN(curCode)) {
                    int westGroup = groupArray[row][column - 1];

                    groupArray[row][column] = westGroup;
                    gList.groups.get(westGroup).area++;
                }
                if (connectW(curCode) && connectN(curCode)) {
                    //System.out.println("row: " + row + " column: " + column);
                    int westGroup = groupArray[row][column - 1];
                    int northGroup = groupArray[row - 1][column];
                    if (northGroup != westGroup) {
                        groupArray[row][column] = northGroup;
                        merge(northGroup, westGroup);
                    } else {
                        groupArray[row][column] = northGroup;
                    }
                    gList.groups.get(northGroup).area++;
                }
            }

        }

        groupList gListTemp = gList;
        int numRooms = 0;
        int maxSize = 0;
        for (group oGroup : gListTemp.groups) {
            if (oGroup.area != 0) {
                numRooms++;
            }
            if (oGroup.area > maxSize) {
                maxSize = oGroup.area;
            }
        }
        pw.println(numRooms);
        pw.println(maxSize);

        combArea = 0;
        mRow = 0;
        mColumn = 0;
        mDir = ' ';
        for (int row = rows - 1; row >= 0; row--) {
            for (int column = 0; column < columns; column++) {
                int curArea = gList.groups.get(groupArray[row][column]).area;

                if (row != 0 && (groupArray[row][column] != groupArray[row - 1][column])) {

                    int northArea = gList.groups.get(groupArray[row - 1][column]).area;
                    int updateArea = curArea + northArea;
                    if (updateArea > combArea) {
                        updateValues(row, column, curArea, northArea, 'N');
                    }
                    if (updateArea == combArea) {
                        if (column < mColumn) {
                            updateValues(row, column, curArea, northArea, 'N');
                        }
                        if (column == mColumn) {
                            if (row > mRow) {
                                updateValues(row, column, curArea, northArea, 'N');
                            }
                            if (row == mRow) {
                                if (mDir == 'E') {
                                    updateValues(row, column, curArea, northArea, 'N');
                                }
                            }
                        }
                    }

                }
                if (column != columns - 1 && (groupArray[row][column] != groupArray[row][column + 1])) {
                    //System.out.println("CHECK EAST");
                    int eastArea = gList.groups.get(groupArray[row][column + 1]).area;
                    //System.out.println("EAST AREA: " + eastArea);
                    int updateArea = curArea + eastArea;
                    if (updateArea > combArea) {
                        updateValues(row, column, curArea, eastArea, 'E');
                    }
                    if (updateArea == combArea) {
                        if (column < mColumn) {
                            updateValues(row, column, curArea, eastArea, 'E');
                        }
                        if (column == mColumn) {
                            if (row > mRow) {
                                updateValues(row, column, curArea, eastArea, 'E');
                            }

                        }
                    }
                }
            }
        }

        pw.println(combArea);
        pw.println((mRow + 1) + " " + (mColumn + 1) + " " + mDir);
        pw.close();
        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime));

    }


    public static boolean connectW(int code) {
        if (code % 2 == 1) {
            return false;
        }
        return true;
    }

    public static boolean connectN(int code) {
        if (2 <= code && code < 4 || (6 <= code && code < 8) || code < 12 && code >= 10 || code >= 14) {
            return false;
        }
        return true;

    }

    public static void initializeGroupArray() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                groupArray[row][column] = -1;
            }
        }
    }

    public static void makeNewGroup(int row, int column) {
        groupArray[row][column] = curGroup;
        gList.groups.add(new group(curGroup, 1));
        curGroup++;
    }

    public static void merge(int group1, int group2) {
        gList.groups.get(group1).area += gList.groups.get(group2).area;
        gList.groups.get(group2).area = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (groupArray[row][column] == group2) {
                    groupArray[row][column] = group1;
                }
            }
        }
    }

    public static void updateValues(int row, int column, int curArea, int addedArea, char dir) {
        mRow = row;
        mColumn = column;
        mDir = dir;
        combArea = curArea + addedArea;

    }
}

class group {
    public int id, area;

    public group(int id, int area) {
        this.id = id;
        this.area = area;
    }


}

class groupList {

    ArrayList<group> groups = new ArrayList<group>();

    public groupList(ArrayList<group> ogroups) {
        this.groups = ogroups;
    }
}
