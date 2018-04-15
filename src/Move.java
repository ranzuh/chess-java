public class Move {
    int oldX;
    int oldY;
    int newX;
    int newY;

    Move(int oldX, int oldY, int newX, int newY) {
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
    }

    Move(String move) {
        String letters = "abcdefgh";

        this.oldX = letters.indexOf(move.charAt(0));
        this.oldY = Character.getNumericValue(move.charAt(1));
        this.newX = letters.indexOf(move.charAt(3));
        this.newY = Character.getNumericValue(move.charAt(4));


    }

    Move(int oldX, int oldY, Direction dir) {
        this.oldX = oldX;
        this.oldY = oldY;

        switch (dir) {
            case UP:
                newX = oldX;
                newY = oldY - 1;
                break;
            case DOWN:
                newX = oldX;
                newY = oldY + 1;
                break;
            case LEFT:
                newX = oldX - 1;
                newY = oldY;
                break;
            case RIGHT:
                newX = oldX + 1;
                newY = oldY;
                break;
            case NE:
                newX = oldX + 1;
                newY = oldY - 1;
                break;
            case SE:
                newX = oldX + 1;
                newY = oldY + 1;
                break;
            case SW:
                newX = oldX - 1;
                newY = oldY + 1;
                break;
            case NW:
                newX = oldX - 1;
                newY = oldY - 1;
                break;
        }
    }

    @Override
    public String toString() {
        String letters = "abcdefgh";
        return "" + letters.charAt(oldX) + oldY + " " + letters.charAt(newX) + newY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return oldX == move.oldX &&
                oldY == move.oldY &&
                newX == move.newX &&
                newY == move.newY;
    }

}

enum Direction {
    UP, DOWN, LEFT, RIGHT, NE, SE, SW, NW;
}
