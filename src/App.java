public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("[ - - - The Tris Game - - - ]\n");

        int move, turn;
        Boolean hasWon = false;
        Board board = new Board();

        for (int j = 0; j < 9; j++) {
            board.makeMove('X');
        }
    }
}
