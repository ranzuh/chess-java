import java.util.ArrayList;
import java.util.Random;

public class Shakki {
    public static void main(String[] args) {
        Position pos = new Position();
        System.out.println(pos);

        pos = pos.result(new Move(0, 0, Direction.RIGHT));
        Position posCopy = new Position(pos.getBoard());
        System.out.println(pos);
        System.out.println(posCopy);

        pos = pos.result(new Move(1, 0, Direction.RIGHT));
        posCopy = posCopy.result(new Move(1, 0, Direction.LEFT));

        System.out.println(pos);
        System.out.println(posCopy);

        ArrayList<Move> legalMoves = pos.getLegalMoves(Color.WHITE);

        System.out.println(legalMoves);


//        ArrayList<Move> legalMoves = pos.getLegalMoves(Color.WHITE);
//
//        for (int i = 0; i < legalMoves.size(); i++) {
//            Move m = legalMoves.get(i);
//            pos.result(m);
//            System.out.println(pos);
//            pos = new Position();
//        }
//
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