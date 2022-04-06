public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("[ - - - The Tris Game - - - ]\n");

        int move = 0;
        char playerID  = 'X';
        Boolean hasWon = false;
        Board board = new Board();

        while (hasWon != true && move < 9) {
            if (playerID != 'X') {
                playerID = 'X';
            } else {
                playerID = 'O';
            }

            board.makeMove(playerID);
            hasWon = board.checkIfWon();
            
            move++;
        }

        if (hasWon == true) {
            System.out.println("Congratulazioni Giocatore " + playerID + ", hai vinto!");
        } else {
            System.out.println("Pareggio! Riavviate il gioco per ricominciare la sfida.");
        }
    }
}
