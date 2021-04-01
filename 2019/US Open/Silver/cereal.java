import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class cereal {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cereal.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("cereal.out"));
        StringTokenizer tok = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tok.nextToken());
        int M = Integer.parseInt(tok.nextToken());
        //Favorites.
        int[] f = new int[N];
        int[] s = new int[N];

        // Store in reverse order.
        for (int i = N - 1; i >= 0; i--) {
            tok = new StringTokenizer(br.readLine());
            f[i] = Integer.parseInt(tok.nextToken()) - 1;
            s[i] = Integer.parseInt(tok.nextToken()) - 1;
        }

        int[] occFeed = new int[M]; //Current cow occupying the cereal.
        int[] curChoice = new int[N]; //O if F, 1 if S.
        Arrays.fill(occFeed, -1);
        Arrays.fill(curChoice, -1);
        int numHave = 0;
        int[] res = new int[N]; //Result


        for (int i = 0; i < N; i++) {
            int favFeed = f[i]; //Critical cow and feed.
            int newCow = i;

            while (true) {

                //Fav-feed is unoccupied, so we can stop here.
                if (occFeed[favFeed] == -1) {
                    occFeed[favFeed] = newCow;
                    curChoice[newCow] = (f[newCow] == favFeed) ? 0 : 1;
                    numHave++;
                    break;
                } else {
                    //More difficult to handle.
                    int oldCow = occFeed[favFeed]; //Old guy who occupied this feed.
                    occFeed[favFeed] = newCow; //Update.
                    curChoice[newCow] = (f[newCow] == favFeed) ? 0 : 1;

                    // Getting bumped from s choice.
                    if (curChoice[oldCow] == 1) {
                        break;
                    }

                    // This will be the new cow we try with the s choice.
                    else {
                        newCow = oldCow;
                        favFeed = s[newCow];

                        // A better cow has this one, so we get bumped.
                        if (occFeed[favFeed] > newCow) break;
                    }
                }
            } // end while

            // Record answer.
            res[N - 1 - i] = numHave;
        }

        //Print the answer.
        for (int i = 0; i < N; i++) {
            pw.println(res[i]);
        }
        System.out.println("RES: " + Arrays.toString(res));
        pw.close();

    }
}
