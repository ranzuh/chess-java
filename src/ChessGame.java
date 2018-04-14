import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.Random;

public class ChessGame {
    Player white;
    Player black;
    Position currentPos;
    // moves list?


    public ChessGame(Player white, Player black, Position currentPos) {
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




//
//        ArrayList<Move> legalMoves = pos.getLegalMoves(Color.WHITE);
//
//        for (int i = 0; i < le1galMoves.size(); i++) {
//            Move m = legalMoves.get(i);
//            pos.result(m);
//            System.out.println(pos);
//            pos = new Position(savedpos.getBoard());
//       }

//        legalMoves = pos.getLegalMoves(Color.BLACK);
//
//        for (int i = 0; i < legalMoves.size(); i++) {
//            Move m = legalMoves.get(i);
//            pos.result(m);
//            System.out.println(pos);
//            pos = new Position();
//        }






    }

}