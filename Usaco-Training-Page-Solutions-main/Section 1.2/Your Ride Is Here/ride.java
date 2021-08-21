/*
ID: your_id_here
LANG: JAVA
TASK: ride
*/

import java.io.*;


public class ride {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("ride.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
        String comet = f.readLine();
        String group = f.readLine();


        int cometTotal = 1; //total multiplied value of comet string, for instance COMETQ = 3+15+13+5+20+17 = 73
        int groupTotal = 1;
        String cometUpper = comet.toUpperCase(); // Turns all the letters to uppercase.

        for (int cnum = 0; cnum < comet.length(); cnum++) {
            char c = cometUpper.charAt(cnum); // Read in all the letters in the string.
            int cnewtoint = c - 64; //Convert char to int and because 'A' in ASCII table is  65, so subtract 64.
            cometTotal = cometTotal * cnewtoint; //Find the total multiplied val of the said comet string.
        }


        int crem = cometTotal % 47; // Remainder of the total val of comet string when divided by 47.
        String groupUpper = group.toUpperCase();
        for (int gnum = 0; gnum < groupUpper.length(); gnum++) {
            char c = groupUpper.charAt(gnum); // Read in all the letters in the string.
            int gnewtoint = c - 64; //Convert char to int and because 'A' in ASCII table is  65, so subtract 64.
            groupTotal = groupTotal * gnewtoint; //Find the total multiplied val of the said group string.
        }
        int grem = groupTotal % 47;
        if
        (grem == crem) {
            pw.println("GO");
        } else {
            pw.println("STAY");
        }
        pw.close();

    }

}
