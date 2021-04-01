import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class guess {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("guess.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("guess.out")));
        int animals = Integer.parseInt(br.readLine());
        ArrayList[] animalArray = new ArrayList[animals];
        for (int i = 0; i < animals; i++) {
            animalArray[i] = new ArrayList<String>();
        }
        for (int i = 0; i < animals; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int traits = Integer.parseInt(st.nextToken());
            for (int j = 0; j < traits; j++) {
                animalArray[i].add(st.nextToken());
            }
        }
        int maxDuplicates = 0;
        for (int animal1 = 0; animal1 <= animalArray.length - 2; animal1++) {
            for (int animal2 = animal1 + 1; animal2 < animalArray.length; animal2++) {
                System.out.println(animal1 + " " + animal2);
                int addedElements = animalArray[animal1].size() + animalArray[animal2].size();
                HashSet<String> hashset = new HashSet<>();
                hashset = new HashSet<>(animalArray[animal1]);
                hashset.addAll(animalArray[animal2]);
                int duplicates = addedElements - hashset.size();
                maxDuplicates = Integer.max(duplicates, maxDuplicates);

            }
        }
        System.out.println(maxDuplicates);
        System.out.println("ANSWER: " + (maxDuplicates + 1));
        pw.println((maxDuplicates + 1));
        pw.close();

    }
}
