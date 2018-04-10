public class Piece {
    Color color;
    Type type;

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public String toString() {
        if (color == Color.WHITE) {
            switch (type) {
                case PAWN:
                    return "P";
                case ROOK:
                    return "R";
                case KNIGHT:
                    return "N";
                case BISHOP:
                    return "B";
                case QUEEN:
                    return "Q";
                case KING:
                    return "K";
            }
        } else {
            switch (type) {
                case PAWN:
                    return "p";
                case ROOK:
                    return "r";
                case KNIGHT:
                    return "n";
                case BISHOP:
                    return "b";
                case QUEEN:
                    return "q";
                case KING:
                    return "k";
            }
        }
        return "error";
    }
}

enum Color {
    WHITE, BLACK
}

enum Type {
    PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
}
