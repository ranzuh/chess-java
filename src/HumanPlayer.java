import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    Move chooseMove(ArrayList<Move> legalMoves) {
        Scanner scanner = new Scanner(System.in);

        return legalMoves.get(scanner.nextInt());
    }
}
