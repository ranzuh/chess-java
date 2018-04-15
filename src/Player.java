public abstract class Player {
    String name;

    Player(String name) {
        this.name = name;
    }

    abstract Move chooseMove(Position pos);
}
