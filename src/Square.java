public class Square {
    private Piece piece;

    Square() {
    }

    Square(Piece piece) {
        this.piece = piece;
    }

    Piece getPiece() {
        return piece;
    }

    void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return isEmpty() ? "_" : piece.toString();
    }

    boolean isEmpty() {
        return piece == null;
    }

    Square deepCopy () {
        if(isEmpty()) {
            return new Square();
        }
        else {
            return new Square(new Piece(piece.color, piece.type));
        }
    }
}
