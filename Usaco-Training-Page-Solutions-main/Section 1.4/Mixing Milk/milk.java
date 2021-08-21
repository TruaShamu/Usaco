/*
TASK: milk
LANG: JAVA
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class milk {

    public static void main(String[] args) throws IOException {
        //File
        BufferedReader br = new BufferedReader(new FileReader("milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
        //Read in the number of units needed and number of farmers
        String input1 = br.readLine();
        StringTokenizer line = new StringTokenizer(input1);
        int unitsNeeded = Integer.parseInt(line.nextToken());
        int farmernum = Integer.parseInt(line.nextToken());
        int totalcost = 0;
        Farm oFarm = new Farm();
        //For each line, make a news farmer and add to ofarm.farmmers
        for (int i = 0; i < farmernum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int units = Integer.parseInt(st.nextToken());
            oFarm.Farmers.add(new Farmer(price, units));
        }
        //Sort all farmers in oFarm with ascending
        oFarm.SortbyPrice();
        for (int i = 0; i < oFarm.Farmers.size(); i++) {
            if (unitsNeeded > 0) {
                int iUnits = oFarm.Farmers.get(i).units;
                int iPrice = oFarm.Farmers.get(i).price;
                int iAmount = oFarm.Farmers.get(i).getAmount();
                if (unitsNeeded >= iUnits) {
                    unitsNeeded = unitsNeeded - iUnits;
                    totalcost += iAmount;
                }
                if (unitsNeeded < iUnits) {
                    totalcost += unitsNeeded * iPrice;
                    unitsNeeded = 0;
                }
            }
        }
        out.println(totalcost);
        out.close();
    }

    static public class Farmer {
        int units, price;

        public Farmer(int price, int units) {
            this.units = units;
            this.price = price;
        }

        public int getAmount() {
            return price * units;
        }
    }

    static class Farm {
        public ArrayList<Farmer> Farmers = new ArrayList<Farmer>();
        static private Comparator<Farmer> ascPrice;


        // We initialize static variables inside a static block.
        //1. Interface
        static {
            ascPrice = new Comparator<Farmer>() {
                @Override
                public int compare(Farmer p1, Farmer p2) {
                    return (p1.price - p2.price);
                }
            };
        }

        public Farm() {

        }


        public void SortbyPrice() {
            Collections.sort(Farmers, ascPrice);
        }


    }
}
