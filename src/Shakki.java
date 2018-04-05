import java.util.ArrayList;
import java.util.Random;

public class Shakki {
    public static void main(String[] args) {
        Position pos = new Position();
        System.out.println(pos);

        ArrayList<Move> legalMoves = pos.getLegalMoves(Color.WHITE);

        for (int i = 0; i < legalMoves.size(); i++) {
            Move m = legalMoves.get(i);
            pos.result(m);
            System.out.println(pos);
            pos = new Position();
        }

        legalMoves = pos.getLegalMoves(Color.BLACK);

        for (int i = 0; i < legalMoves.size(); i++) {
            Move m = legalMoves.get(i);
            pos.result(m);
            System.out.println(pos);
            pos = new Position();
        }
    }

}