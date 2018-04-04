public class Piece {
    Color color;
    Type type;

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public String toString() {
        return color.toString().substring(0,1) + type.toString().substring(0,1);
    }
}

enum Color {
    WHITE, BLACK
}

enum Type {
    PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
}
