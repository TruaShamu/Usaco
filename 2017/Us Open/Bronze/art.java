import java.io.*;
import java.util.*;

public class Main {
    public static blockList bList;
    public static int[][] gridArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("art.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("art.out")));
        int rows = Integer.parseInt(br.readLine());
        gridArray = new int[rows][rows];
        int[] array = new int[10 + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = -1; //-1 means not in use.
        }
        for (int row = 0; row < rows; row++) {
            String x = br.readLine();
            System.out.println(x);
            StringTokenizer st = new StringTokenizer(x);
            for (int column = 0; column < rows; column++) {
                gridArray[row][column] = x.charAt(column) - 48;
            }
        }

        //Read in inputarray.
        bList = new blockList();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < rows; column++) {
                int blockId = gridArray[row][column];
                if (blockId != 0) {
                    int loc = listLoc(blockId);
                    if (loc != -1) {
                        bList.blocks.get(loc).lowerRightY = Integer.max(row, bList.blocks.get(loc).lowerRightY);
                        bList.blocks.get(loc).lowerRightX = Integer.max(column, bList.blocks.get(loc).lowerRightX);
                        bList.blocks.get(loc).upperLeftY = Integer.min(row, bList.blocks.get(loc).upperLeftY);
                        bList.blocks.get(loc).upperLeftX = Integer.min(column, bList.blocks.get(loc).upperLeftX);
                    } else {
                        //Make new block (WIP)
                        bList.blocks.add(new block(blockId, column, row, column, row));
                        array[blockId] = 0; //This means it is possible candidate
                    }


                }
            }
        }
        System.out.println(Arrays.toString(array));
        for (block oBlock : bList.blocks) {
            System.out.println("upper left: x=" + oBlock.upperLeftX + " y=" + oBlock.upperLeftY);
            System.out.println("lower right: x=" + oBlock.lowerRightX + " y=" + oBlock.lowerRightY);
        }
        //Everything has been added to our block list.
        for (int i = 0; i < bList.blocks.size() - 1; i++) {
            for (int j = i + 1; j < bList.blocks.size(); j++) {
                System.out.println("Comparing " + bList.blocks.get(i).id + " and " + bList.blocks.get(j).id + ".");
                int olp = overlap(i, j);

                if (olp != -1) {
                    array[bList.blocks.get(olp).id] = 1; //1 means it is not a possible candidate

                }

            }
        }
        int answer = 0;
        for (int i : array) {
            if (i == 0) {
                answer++;
            }
        }
        pw.println(answer);
        pw.close();

    }

    public static int listLoc(int id) {
        for (int i = 0; i < bList.blocks.size(); i++) {
            if (bList.blocks.get(i).id == id) {
                return i;
            }
        }
        return -1;
    }

    public static int overlap(int index1, int index2) {
        //Returns index1 if square on index1 is on top of the square in index2.
        for (int column = bList.blocks.get(index2).upperLeftX; column <= bList.blocks.get(index2).lowerRightX; column++) {
            for (int row = bList.blocks.get(index2).upperLeftY; row <= bList.blocks.get(index2).lowerRightY; row++) {
                if (gridArray[row][column] == bList.blocks.get(index1).id) {
                    System.out.println(bList.blocks.get(index1).id + " is on " + bList.blocks.get(index2).id);
                    return index1;
                }

            }
        }

        for (int column = bList.blocks.get(index1).upperLeftX; column <= bList.blocks.get(index1).lowerRightX; column++) {
            for (int row = bList.blocks.get(index1).upperLeftY; row <= bList.blocks.get(index1).lowerRightY; row++) {
                if (gridArray[row][column] == bList.blocks.get(index2).id) {
                    System.out.println(bList.blocks.get(index2).id + " is on " + bList.blocks.get(index1).id);
                    return index2;
                }
            }
        }
        return -1;
    }
}

class block {
    public int upperLeftX, upperLeftY, lowerRightX, lowerRightY, id;

    public block(int id, int upperLeftX, int upperLeftY, int lowerRightX, int lowerRightY) {
        this.id = id;
        this.lowerRightX = lowerRightX;
        this.lowerRightY = lowerRightY;
        this.upperLeftX = upperLeftX;
        this.upperLeftY = upperLeftY;
    }


}

class blockList {

    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<block> ascLocation;

    // We initialize static variables inside a static block.
    //1. Interface
    static {
        ascLocation = new Comparator<block>() {
            @Override
            public int compare(block p1, block p2) {
                return Integer.compare(p1.id, p2.id);
            }
        };

    }

    //2.Property

    ArrayList<block> blocks = new ArrayList<block>();

    //3. Method
    //a. Set /Get
    //b. General Function(Method>


    public ArrayList<block> GetBlockss() {
        return this.blocks;
    }

    blockList() {

    }



    /*public Book[] getBooks() {
        return books;
    } */

    public void sortbylocation() {
        Collections.sort(blocks, ascLocation);
    }


    public blockList(ArrayList<block> oBlocks) {
        this.blocks = oBlocks;
    }
}





/*
    @Test
    public void sortBooks() {
        block[] blocks = {
                new block(0, 100, "S"),
                new block(1, 100, "E")
        };

        // 1. sort using Comparable
        Arrays.sort(blocks);
        System.out.println(Arrays.asList(blocks));

        // 2. sort using comparator: sort by id
        Arrays.sort(blocks, new Comparator<blocks>() {
            @Override
            public int compare(block o1, block o2) {
                return (o1.Location - o2.Location);
            }
        });
        System.out.println(Arrays.asList(blocks));
    }
*/
