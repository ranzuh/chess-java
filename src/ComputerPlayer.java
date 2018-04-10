import java.util.ArrayList;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name) {
        super(name);
    }

    @Override
    Move chooseMove(ArrayList<Move> legalMoves) {
        return null;
    }


}
