public class ChessGame {
    private Player white;
    private Player black;
    private Position currentPos;
    // moves list?


    ChessGame(Player white, Player black, Position currentPos) {
        this.white = white;
        this.black = black;
        this.currentPos = currentPos;
    }

    public static void main(String[] args) {

        Player white = new HumanPlayer("human");
        Player black = new ComputerPlayer("computer");
        Position initialPos = new Position();

        ChessGame chess = new ChessGame(white, black, initialPos);

        while(true) {
            Move move;
            if(chess.currentPos.getMovesNext() == Color.WHITE) {
                System.out.println(white.name + " turn");
                move = white.chooseMove(chess.currentPos);
                System.out.println();
            }
            else {
                System.out.println(black.name + " turn");
                move = black.chooseMove(chess.currentPos);
                System.out.println(black.name + " chose " + move + "\n");
                //chess.currentPos.printMove(move);
            }
            chess.currentPos = chess.currentPos.result(move);
        }

    }

}