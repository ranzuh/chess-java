import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.Random;

public class Shakki {
    public static void main(String[] args) {
        Position pos = new Position();

        Player human = new HumanPlayer("human");

        Position poscopy = new Position(pos.getBoard());

        for (int i = 0; i < 10; i++) {
            System.out.println(pos);
            pos.printLegalMoves(Color.WHITE);
            Move m = human.chooseMove(pos.getLegalMoves(Color.WHITE));
            pos.result(m);
        }


        System.out.println(poscopy);


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