import java.io.*;
import java.util.StringTokenizer;

public class revegetate {
    public static BufferedReader br;
    public static int[][] edgeArray;
    public static int[][] colorArray;
    public static int[] resultArray;
    //public static int size;
    public static int totalFields;

    public static void main(String[] args) throws IOException {

        Test();


    }

    public static void Test() throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));
        ReadIntoEdgeArray();
        colorArray = new int[totalFields][4];
        for (int i = 0; i < totalFields; i++) {
            color(i, FindRivals(i));
        }

        int[] array = PlugIntoResultArray();
        for (int i = 0; i < array.length; i++) {
            pw.print(array[i]);
        }
        pw.println();
        pw.close();
    }

    public static int color(int curField, int[] rivals) {
        //System.out.println("Color Start");
        int resultColor = 0;
        for (int color = 0; color < 4; color++) {
            if (colorArray[curField][color] == 0) { //Minus One, to prevent out of bounds
                colorArray[curField][color] = 1;
                resultColor = color;
                break;
            }
        }
        //Finish deciding color of field
        for (int i = 0; i < rivals.length; i++) {
            int rival = rivals[i]; //Minus One, to prevent out of bounds
            //Exclude rivals
            if (rival != -1) {
                colorArray[rival][resultColor] = -1;
            }
        }
        //System.out.println("Color End");
        return resultColor;

    }



    public static int FindColorArrayIndex(int field) {
        for (int i = 0; i < 4; i++) {
            if (colorArray[field][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static int[] PlugIntoResultArray() {
        int[] resultArray = new int[totalFields];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = FindColorArrayIndex(i) + 1;
        }
        //System.out.println("Length:" + resultArray.length);
        return resultArray;

    }

    public static void ReadIntoEdgeArray() throws IOException {
        br = new BufferedReader(new FileReader("revegetate.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        totalFields = Integer.parseInt(st.nextToken());
        edgeArray = new int[totalFields][totalFields];
        int numberOfLines = Integer.parseInt(st.nextToken());
        for (int i = 0; i < numberOfLines; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int field1 = Integer.parseInt(st1.nextToken()) - 1; //Minus one
            int field2 = Integer.parseInt(st1.nextToken()) - 1;
            edgeArray[field1][field2] = 1;
            edgeArray[field2][field1] = 1;
        }
    }


    public static int[] FindRivals(int curField) {
        int row = curField;
        int[] rivals = new int[totalFields];
        for (int i = 0; i < rivals.length; i++) {
            rivals[i] = -1;
        }
        int count = 0;
        for (int column = 0; column < rivals.length; column++) {
            if (edgeArray[row][column] == 1) {
                rivals[count] = column;
                count++;
            }
        }
        return rivals;
    }

}

