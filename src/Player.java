import java.util.ArrayList;

public abstract class Player {
    String name;

    public Player(String name) {
        this.name = name;
    }

    abstract Move chooseMove(Position pos);
}
