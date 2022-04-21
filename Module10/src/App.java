
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        Connection con = null;
        try{
            /**
             * Load jdbc driver and establish connection to the database
             */
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordoccurences", "admin", "password");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }

        String word;
        int choice;
        /**
         * sorting words, do while loop to fetch multiple words from the user
         */
        do {
            Scanner sc = new Scanner(System.in);
            try {
                // get user input
                System.out.println("Enter a word");
                word = sc.next();

                // prepared statement
                PreparedStatement pst = con.prepareStatement("INSERT INTO wordoccurences.word (word) values(?)");
                // setting the word and executing user queries
                pst.setString(1, word);
                pst.executeUpdate();

                // displaying database data after user input
                System.out.println("Database after addition of the word");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * from wordoccurences.word");

                while (rs.next()) {
                    System.out.println(rs.getString("word"));
                }

            } catch (SQLException e) {
                System.out.println("Error:");
                System.out.println(e.getMessage());
            }
            System.out.println("Do you want to enter more words (press 1 )");
            choice = sc.nextInt();
        } while (choice == 1);
        // ** calculating word frequecy
        System.out.println("The frequency of each word in the database is");

        // create a map to store the word and corrresponding frequencies
        Map<String, Integer> occurrence = new LinkedHashMap<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from wordoccurences.word");

            while (rs.next()) {
                // get the word from the database
                String word_input = rs.getString("word");

                // check if the word is present
                if (occurrence.get(word_input) == null) {
                    // add 1 to the occurrence if the word is not present
                    occurrence.put(word_input, 1);
                } else {
                    // increase the occurrence if its already pesen in the map
                    occurrence.put(word_input, occurrence.get(word_input) + 1);
                }
            }

            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // dislay the words alongside heir occurrence
        Set<String> key = occurrence.keySet();
        for (String k : key) {
            System.out.println("Word: " + k + "\t Occurence: " + occurrence.get(k));
            ;
        }
    }
}
