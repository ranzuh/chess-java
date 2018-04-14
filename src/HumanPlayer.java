import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    Move chooseMove(Position pos) {
        ArrayList<Move> legalMoves = pos.getLegalMoves();
        Scanner scanner = new Scanner(System.in);

        System.out.println(pos);

        Move move = null;

        boolean repeat = true;

        while(repeat) {
            try {
                System.out.print("Choose your move: ");
                move = new Move(scanner.nextLine());
                if(legalMoves.contains(move)) {
                    return move;
                }
                else {
                    System.out.println("thats not a legal move");
                    throw new Exception();
                }

            }  catch (Exception e) {
                repeat = true;
                System.out.println("write your move formated like this: a0 h7");
            }
        }

    return null;

    }
}
