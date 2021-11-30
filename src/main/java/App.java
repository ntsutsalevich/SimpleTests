import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) {
        try {
            createListGames();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createListGames() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("D:/gamesTest/gamesAppJSONPCallback.txt")));

        BufferedWriter bwSet = new BufferedWriter(new FileWriter(new File("D:/gamesTest/gameNamesSet.txt")));

        List<String> list = new ArrayList<>();
        Set<String> set=new TreeSet<>();
        String s = null;
        while ((s = br.readLine()) != null) {
            if (s.contains("\"name\"")) {
                String[] s1 = s.split("\": \"");

                s1[1] = s1[1].replaceAll("[^-\\da-zA-Z &]", "");
                set.add(s1[1]);

            }
        }
        br.close();

        for (String str:set
             ) {
            bwSet.write(str+"\n");
        }
        bwSet.close();
        System.out.println(set.size());
    }

}
