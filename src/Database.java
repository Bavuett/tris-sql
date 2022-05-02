import java.sql.*;

public class Database {
    static String url = "jdbc:mysql://localhost:3306/tris";
    static String username = "root";
    static String password = "";

    static Connection connection = null;
    static Statement dbst;
    static ResultSet result;

    static Board board;

    public void init() {
        board = new Board();
        System.out.println("Provo a connettermi al Database...\n");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Non riesco ad utilizzare il Driver. \nMotivo: " + e + "\n");
        }
    
        try {
            connection = DriverManager.getConnection(url, username, password) ;
                             
            if (connection != null) {
                System.out.println("Database connesso!\n");
            }
        } catch (Exception e) {
            System.out.println("Non riesco a connettermi al Database. \nMotivo: " + e + "\n");
        }
    
        try {
            dbst = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception e) {
            System.out.println("Non riesco ad eseguire uno statement. \nMotivo: " + e + "\n");
        }
    }

    // Print whole Database.
    // Only for debugging purposes.
    public void printDB() {
        try {
            result = dbst.executeQuery("SELECT * FROM ESPERIENZA");

            while (result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString(2) + " " + result.getString(3));
            }
        } catch (Exception e) {
            System.out.println("Non riesco ad eseguire una query. \nMotivo: " + e + "\n");
        }
    }

    public void addMatch(String movesString, char finalOutcome) {
        String playerMoves = movesString;
        char outcome = finalOutcome;

        try {
            dbst.executeUpdate("INSERT INTO esperienza (mosse, esito) VALUES ('" + playerMoves + "', '" + outcome + "')");
        } catch (Exception e) {
            System.out.println("Non riesco ad aggiungere la partita. \nMotivo: " + e + "\n");
        }
    }

    public int getNextMove(String movesString) {
        String currentMoves = movesString;
        String dbMoves = "";
        
        int moveIndex = currentMoves.length();
        int correctMove = 0;

        try {
            result = dbst.executeQuery("SELECT * FROM esperienza WHERE mosse LIKE '" + currentMoves + "%' AND esito = 'W' OR esito = 'D' ORDER BY esito = 'W' DESC");
            
            if (result.first()) {
                dbMoves = result.getString(2);
            }
        } catch (Exception e) {
            System.out.println("Non riesco ad eseguire una query. \nMotivo: " + e + "\n");
        }

        // Debug output.
        // System.out.println("Mosse inserite al momento: " + currentMoves + ". Indice per trovare la mossa corretta: " + moveIndex + ".");
        // System.out.println("Mosse trovate dal database: " + dbMoves + ".\n");

        if (dbMoves != "") {
            try {
                correctMove = Character.getNumericValue(dbMoves.charAt(moveIndex + 1));

                return correctMove;
            } catch (Exception e) {
                System.out.println("Non riesco a trovare una mossa adatta. Motivo: " + e + "\n");
            }
        } else {

            correctMove = board.randomMove();
            return correctMove;
        }

        return correctMove;
    }
}