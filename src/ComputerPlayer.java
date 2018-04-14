import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name) {
        super(name);
    }

    @Override
    Move chooseMove(Position pos) {
        Random random = new Random();
        ArrayList<Move> legalMoves = pos.getLegalMoves();
        return legalMoves.get(random.nextInt(legalMoves.size()));
    }


}
