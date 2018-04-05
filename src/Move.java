public class Move {
    int oldX;
    int oldY;
    int newX;
    int newY;

    public Move(int oldX, int oldY, int newX, int newY) {
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
    }

    public Move(int oldX, int oldY, Direction dir) {
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
        return "Move{" +
                "oldX=" + oldX +
                ", oldY=" + oldY +
                ", newX=" + newX +
                ", newY=" + newY +
                '}';
    }
}

enum Direction {
    UP, DOWN, LEFT, RIGHT, NE, SE, SW, NW;
}
