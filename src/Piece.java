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
                    return "♙";
                case ROOK:
                    return "♖";
                case KNIGHT:
                    return "♘";
                case BISHOP:
                    return "♗";
                case QUEEN:
                    return "♕";
                case KING:
                    return "♔";
            }
        } else {
            switch (type) {
                case PAWN:
                    return "♟";
                case ROOK:
                    return "♜";
                case KNIGHT:
                    return "♞";
                case BISHOP:
                    return "♝";
                case QUEEN:
                    return "♛";
                case KING:
                    return "♚";
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
