import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
TASK: holstein
LANG: JAVA
 */
public class holstein {

    public static combination[] BaseTable;
    public static int NumberOfFeeds;
    public static int NumberOfVitamins; //Number of vitamins
    public static int[] vitaminRequirements; //Vitamin requirement
    public static BufferedReader br;
    public static CombinationList[] WorkingTable;
    public static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        br = new BufferedReader(new FileReader("holstein.in"));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

        readInput();
        CheckListFulfill(0);
        if (CheckListFulfill(0)) {
            System.exit(0);
        }
        FillWorkingTable(1);

        for (int iStep = 1; iStep < NumberOfFeeds; iStep++) {

            if (CheckListFulfill(iStep)) {
                long end = System.nanoTime();
                System.out.println(end - startTime);
                System.exit(0);

            } else {
                FillWorkingTable(iStep + 1);
            }

        }


    }


    public static void readInput() throws IOException {
        NumberOfVitamins = Integer.parseInt(br.readLine());
        vitaminRequirements = new int[NumberOfVitamins];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < NumberOfVitamins; i++) {
            vitaminRequirements[i] = Integer.parseInt(st.nextToken());
        }


        NumberOfFeeds = Integer.parseInt(br.readLine());
        BaseTable = new combination[NumberOfFeeds];
        WorkingTable = new CombinationList[NumberOfVitamins];
        for (int i = 0; i < NumberOfFeeds; i++) {
            int[] CombinationVitaminQuantity = new int[NumberOfVitamins];

            StringTokenizer str = new StringTokenizer(br.readLine());

            for (int j = 0; j < NumberOfVitamins; j++) {
                int CurrentVitaminQuantity = Integer.parseInt(str.nextToken());
                CombinationVitaminQuantity[j] = CurrentVitaminQuantity;
            }

            BaseTable[i] = new combination(CombinationVitaminQuantity, generateCode1(i + 1, NumberOfFeeds));
        }

        FillWorkingTable(0);

    }


    public static void FillWorkingTable(int iStep) {

        if (iStep == 0) {
            CombinationList InputList = new CombinationList();
            for (int i = 0; i < BaseTable.length; i++) {
                InputList.combinations.add(BaseTable[i]);

            }
            WorkingTable[iStep] = InputList;


        } else {
            WorkingTable[iStep] = new CombinationList();

            for (int i = 0; i < WorkingTable[iStep - 1].combinations.size() - 1; i++) {
                combination oComb = WorkingTable[iStep - 1].combinations.get(i);
                for (int j = oComb.LastCodeLocation + 1; j < BaseTable.length; j++) {

                    combination oCombination = new combination(oComb, BaseTable[j]);
                    WorkingTable[iStep].combinations.add(oCombination);

                }
            }
            WorkingTable[iStep - 1].combinations.clear();
        }

    }

    public static boolean CheckCombinationFulfill(combination oComb) {


        for (int j = 0; j < oComb.vitamins.length; j++) {
            if (oComb.vitamins[j] < vitaminRequirements[j]) {
                return false;
            }
        }
        PrintOutput(oComb);
        return true;
    }

    public static boolean CheckListFulfill(int iStep) {
        for (int i = 0; i < WorkingTable[iStep].combinations.size(); i++) {
            if (CheckCombinationFulfill(WorkingTable[iStep].combinations.get(i))) {
                return true;
            }
        }
        return false;

    }


    public static void PrintOutput(combination oComb) {
        int numFeeds = 0;
        ArrayList<Integer> outputList = new ArrayList<>();
        for (int i = 0; i < oComb.Code.length(); i++) {
            if (oComb.Code.charAt(i) == '1') {
                numFeeds++;
                outputList.add(i);
            }
        }
        pw.print(numFeeds + " ");
        for (int i = 0; i < outputList.size() - 1; i++) {
            pw.print((outputList.get(i) + 1) + " ");
        }
        pw.println(outputList.get(outputList.size() - 1) + 1);
        pw.close();


    }

    public static String generateCode1(int index, int length) {
        String str = "";
        StringBuilder strBuff = new StringBuilder(str);
        for (int i = 1; i < length + 1; i++) {

            if (i != index) {

                strBuff.append("0");
            } else {
                strBuff.append("1");
            }
        }
        return strBuff.toString();

    }


}

class combination {


    String Code; //101, 110, etc
    int LastCodeLocation;

    int[] vitamins;

    public combination(int[] oVitamins, String oCode) {
        this.vitamins = Arrays.copyOf(oVitamins, oVitamins.length);

        Code = oCode;
        SetLastCodeLocation(oCode);

    }

    public combination(combination oOldWT, combination oBase) {
        int viatminsLength = oOldWT.vitamins.length;
        vitamins = new int[viatminsLength];
        if (oBase.vitamins.length == viatminsLength) {
            for (int i = 0; i < oOldWT.vitamins.length; i++) {
                vitamins[i] = oOldWT.vitamins[i] + oBase.vitamins[i];
            }


            Code = CombineCodes(oBase.Code, oOldWT.Code);
        }
        SetLastCodeLocation(Code);

    }

    public void SetLastCodeLocation(String oCode) {
        for (int i = 0; i < oCode.length(); i++) {
            if (oCode.charAt(i) == '1') {
                LastCodeLocation = i;

            }
        }
    }

    public static String CombineCodes(String code1, String code2) {
        String str = "";
        StringBuilder strBuild = new StringBuilder(str);
        for (int i = 0; i < code1.length(); i++) {
            if (code1.charAt(i) == '0' && code2.charAt(i) == '1' || code1.charAt(i) == '1' && code2.charAt(i) == '0') {
                strBuild.append("1");
            } else {
                strBuild.append("0");
            }
        }
        return strBuild.toString();

    }


}

class CombinationList {

    public CombinationList() {

    }

    ArrayList<combination> combinations = new ArrayList<combination>();

}



