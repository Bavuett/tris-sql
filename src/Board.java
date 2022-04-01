import java.util.*;

public class Board {
    char[] cells = new char[9];

    Scanner input = new Scanner(System.in);

    public void makeMove(char playerID) {
        int index = 1;
        char player = playerID;

        System.out.print("Dove vuoi fare la tua mossa? [1 - 9] ");

        try {
            index = input.nextInt(); 
        } catch (Exception e) {
            System.out.println("Hai inserito un carattere non corretto. Riprova!\n");
            input.nextLine();
        }

        if (index > 0 && index < 10 && cells[index] != 'X' && cells[index] != 'O') {
            cells[index - 1] = playerID;

            for (int i = 0; i < cells.length; i++) {
                System.out.print(cells[i] + " ");
            }

            System.out.print("\n");
        } else {
            System.out.println("Questo spazio è già occupato, o hai inserito un numero non permesso. Riprova!\n");
            this.makeMove(player);
        }
    }
}
