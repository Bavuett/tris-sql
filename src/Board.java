import java.util.*;

public class Board {
    char[] cells = new char[9];
    char player;
    String movesString;

    Scanner input = new Scanner(System.in);
    Database db = new Database();

    public void makeMove(char playerID) {
        player = playerID;
        int index = 1;
        Boolean errorMade = false;

        System.out.print("Giocatore " + player + ", tocca a te! \nDove vuoi fare la tua mossa? [1 - 9] ");

        try {
            index = input.nextInt(); 
            errorMade = false;
        } catch (Exception e) {
            System.out.println("Hai inserito un carattere non corretto. Riprova!");
            errorMade = true;
            input.nextLine();
        }

        System.out.print("\n");

        if (index > 0 && index < 10 && cells[index - 1] != 'X' && cells[index - 1] != 'O' && errorMade != true) {
            cells[index - 1] = playerID;

            movesString = movesString + index;
            this.printBoard();
        } else {
            if (errorMade == false) {
                System.out.println("Questo spazio è già occupato. Riprova!\n");
            }

            this.makeMove(player);
        }
    }

    public void printBoard() {
        for (int i = 0; i < cells.length; i++) {

            if (cells[i] == 0) {
                System.out.print("[" + " " + "]" + " ");
            } else {
                System.out.print("[" + cells[i] + "]" + " ");
            }

            if (i == 2 || i == 5) {
                System.out.print("\n");
            }
        }

        System.out.println("\n");
    }

    // Check the if there's a winning combination.
    // Not exactly the best solution to check for them, but it will work.
    public Boolean checkIfWon() {
        Boolean hasWon = false;
        char outcome;

        // Horizontal combinations.
        if ((cells[0] == cells[1] && cells[0] == cells[2]) && cells[0] != 0) {
            hasWon = true;
        }

        if ((cells[3] == cells[4] && cells[3] == cells[5]) && cells[3] != 0) {
            hasWon = true;
        }
        
        if ((cells[6] == cells[7] && cells[6] == cells[8]) && cells[6] != 0) {
            hasWon = true;
        }

        // Vertical combinations.
        if ((cells[0] == cells[3] && cells[0] == cells[6]) && cells[0] != 0) {
            hasWon = true;
        }

        if ((cells[1] == cells[4] && cells[1] == cells[7]) && cells[1] != 0) {
            hasWon = true;
        }

        if ((cells[2] == cells[5] && cells[2] == cells[8]) && cells[2] != 0) {
            hasWon = true;
        }

        // Diagonal combinations.
        if ((cells[0] == cells[4] && cells[0] == cells[8]) && cells[0] != 0) {
            hasWon = true;
        }

        if ((cells[2] == cells[4] && cells[2] == cells[6]) && cells[2] != 0) {
            hasWon = true;
        }

        if (hasWon == true) {
            db.addMatch(movesString, 'W');
            return true;
        }

        return false;
    }
}
