import java.io.*;
import java.util.Arrays;

public class Main {
    //public static int minDifference;
    public static int[] playerSkillLevel; //Skill level for each player
    public static int[] playerTeam; //Which team each player belongs to
    public static int[] playersPerGroup = {0, 0, 0, 0}; //Number of players in each group
    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bteams.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bteams.out")));
        playerSkillLevel = new int[12];
        playerTeam = new int[12];
        //minDifference = Integer.MAX_VALUE;


        for (int i = 0; i < 12; i++) {
            playerSkillLevel[i] = Integer.parseInt(br.readLine());
        }
        int[][] recursionArray = new int[4][3];
        for (int[] row : recursionArray) {
            Arrays.fill(row, -1);
        }
        recurse(0);
        pw.println(answer);
        pw.close();

    }

    public static void recurse(int step) {
        if (step == 12) {
            int[] SumPerTeam = {0, 0, 0, 0};
            for (int i = 0; i < 12; i++) {
                SumPerTeam[playerTeam[i]] += playerSkillLevel[i];
            }
            int S = Math.max(Math.max(SumPerTeam[0], SumPerTeam[1]),
                    Math.max(SumPerTeam[2], SumPerTeam[3]));
            int s = Math.min(Math.min(SumPerTeam[0], SumPerTeam[1]),
                    Math.min(SumPerTeam[2], SumPerTeam[3]));

            if (S - s < answer) {
                answer = S - s;
            }
            return;

        }
        //System.out.println("recurse");
        //Step is the number to be recursed.
        for (int team = 0; team < 4; team++) {

            if (playersPerGroup[team] < 3) {
                playerTeam[step] = team;
                playersPerGroup[team]++;


                recurse(step + 1);
                playersPerGroup[team]--;


            }
        }
    }


}
