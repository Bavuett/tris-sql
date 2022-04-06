import java.util.*;

public class Board {
    char[] cells = new char[9];

    Scanner input = new Scanner(System.in);

    public void makeMove(char playerID) {
        int index = 1;
        char player = playerID;

        System.out.print("Giocatore " + player + ", tocca a te! \nDove vuoi fare la tua mossa? [1 - 9] ");

        try {
            index = input.nextInt(); 
        } catch (Exception e) {
            System.out.println("Hai inserito un carattere non corretto. Riprova!\n");
            input.nextLine();
        }

        System.out.print("\n");

        if (index > 0 && index < 10 && cells[index - 1] != 'X' && cells[index - 1] != 'O') {
            cells[index - 1] = playerID;

            this.printBoard();
        } else {
            System.out.println("Questo spazio è già occupato, o hai inserito un numero non permesso. Riprova!\n");
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

        System.out.print("\n\n");
    }

    public Boolean checkIfWon() {
        if (cells[0] == cells[1] && cells[0] == cells[2] && cells[0] != 0) {
            return true;
        }

        if (cells[3] == cells[4] && cells[3] == cells[5] && cells[3] != 0) {
            return true;
        }

        return false;
    }
}
