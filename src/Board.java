import java.util.*;

public class Board {
    public static char[] cells = new char[9];

    char player;

    Boolean dbActive;

    String movesString = "";

    Scanner input = new Scanner(System.in);
    Database db = new Database();
    Random rnd = new Random();

    // This method is run everytime to make the move.
    public void makeMove(char playerID) {
        player = playerID;
        int index = 1;
        Boolean errorMade = false;

        System.out.print("Giocatore " + player + ", tocca a te! \nDove vuoi fare la tua mossa? [1 - 9] ");

        // If player is human, ask move from keyboard.
        // Else, ask the database.
        if (player == 'O' || dbActive == false) {
            try {
                index = input.nextInt(); 
                errorMade = false;
            } catch (Exception e) {
                System.out.println("Hai inserito un carattere non corretto. Riprova!");
                
                errorMade = true;
                input.nextLine();
            }
        } else {
            index = db.getNextMove(movesString);
            System.out.println(index);
        }

        System.out.print("\n");

        // Check if the move is valid, and make the move.
        // If it isn't, request input again.
        if (index > 0 && index < 10 && cells[index - 1] != 'X' && cells[index - 1] != 'O' && errorMade != true) {
            cells[index - 1] = playerID;
            movesString = movesString + index;
            
            this.printBoard();
        } else {
            if (errorMade == false) {
                System.out.println("Questo spazio è occupato o non valido. Riprova!\n");
            }

            this.makeMove(player);
        }
    }

    // Create new move if no move on the database is found.
    public int randomMove() {
        int chosenAvailableIndex = 0;
        int generatedMove = 0;

        ArrayList<Integer> availableIndexes = new ArrayList<Integer>(0);

        // Look for the indexes that are not full yet.
        for (int i = 0; i < 9; i++) {
            if (cells[i] == 0) {
                availableIndexes.add(i);
            }
        }

        // Choose a random index from the array and prepare
        // the return value.
        chosenAvailableIndex = rnd.nextInt(availableIndexes.size());
        generatedMove = (int)availableIndexes.get(chosenAvailableIndex) + 1;

        return generatedMove;
    }

    // Print the Board.
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
    public Boolean checkIfWon(int move) {
        // Horizontal combinations.
        if ((cells[0] == cells[1] && cells[0] == cells[2]) && cells[0] != 0) {
            return true;
        }

        if ((cells[3] == cells[4] && cells[3] == cells[5]) && cells[3] != 0) {
            return true;
        }
        
        if ((cells[6] == cells[7] && cells[6] == cells[8]) && cells[6] != 0) {
            return true;
        }

        // Vertical combinations.
        if ((cells[0] == cells[3] && cells[0] == cells[6]) && cells[0] != 0) {
            return true;
        }

        if ((cells[1] == cells[4] && cells[1] == cells[7]) && cells[1] != 0) {
            return true;
        }

        if ((cells[2] == cells[5] && cells[2] == cells[8]) && cells[2] != 0) {
            return true;
        }

        // Diagonal combinations.
        if ((cells[0] == cells[4] && cells[0] == cells[8]) && cells[0] != 0) {
            return true;
        }

        if ((cells[2] == cells[4] && cells[2] == cells[6]) && cells[2] != 0) {
            return true;
        }

        return false;
    }

    public String getMoves() {
        return movesString;
    }

    public char getOutcome() {
        if (player == 'X') {
            return 'W';
        } 
        
        return 'L';
    }

    public void askOpponent() {
        char dbChoice = 0;

        System.out.print("Vuoi giocare contro il database? [Y/n] ");

        try {
            dbChoice = input.next().charAt(0);
        } catch (Exception e) {
            // Set choice as positive by default.
            dbChoice = 'y';

            input.nextLine();
        }

        switch (dbChoice) {
            case 'y':
            case 'Y':
                System.out.println("Giocherai contro il Database.\n");
                dbActive = true;
                break;

            case 'n':
            case 'N':
                System.out.println("Giocherai contro un avversario umano. \nLa partità sarà salvata dal database, in modo che possa utilizzarla per allenarsi.\n");
                dbActive = false;
                break;

            default:
                System.out.println("Carattere non riconosciuto. \nPrenderò per scontato che tu voglia giocare con il database.\n");
                dbActive = true;
        }
    }
}