import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Arrays;

public class Position {
    private Square[][] board;

    public Color getMovesNext() {
        return movesNext;
    }

    private Color movesNext;

    // initial position (state)
    public Position() {
        movesNext = Color.WHITE;

        board = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square();
            }
        }

        board[0][0].setPiece(new Piece(Color.WHITE, Type.KING));
        board[0][1].setPiece(new Piece(Color.BLACK, Type.KING));


        board[0][0].setPiece(new Piece(Color.BLACK, Type.ROOK));
        board[0][1].setPiece(new Piece(Color.BLACK, Type.KNIGHT));
        board[0][2].setPiece(new Piece(Color.BLACK, Type.BISHOP));
        board[0][3].setPiece(new Piece(Color.BLACK, Type.QUEEN));
        board[0][4].setPiece(new Piece(Color.BLACK, Type.KING));
        board[0][5].setPiece(new Piece(Color.BLACK, Type.BISHOP));
        board[0][6].setPiece(new Piece(Color.BLACK, Type.KNIGHT));
        board[0][7].setPiece(new Piece(Color.BLACK, Type.ROOK));
//
//        for (int i = 0; i < 8; i++) {
//            board[1][i].setPiece(new Piece(Color.BLACK, Type.PAWN));
//        }
//
        board[7][0].setPiece(new Piece(Color.WHITE, Type.ROOK));
        board[7][1].setPiece(new Piece(Color.WHITE, Type.KNIGHT));
        board[7][2].setPiece(new Piece(Color.WHITE, Type.BISHOP));
        board[7][3].setPiece(new Piece(Color.WHITE, Type.QUEEN));
        board[7][4].setPiece(new Piece(Color.WHITE, Type.KING));
        board[7][5].setPiece(new Piece(Color.WHITE, Type.BISHOP));
        board[7][6].setPiece(new Piece(Color.WHITE, Type.KNIGHT));
        board[7][7].setPiece(new Piece(Color.WHITE, Type.ROOK));
//
//        for (int i = 0; i < 8; i++) {
//            board[6][i].setPiece(new Piece(Color.WHITE, Type.PAWN));
//        }



    }

    // copy position with this constructor
    public Position(Position pos) {
        Square[][] boardCopy = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardCopy[i][j] = pos.board[i][j].deepCopy();
            }
        }
        this.board = boardCopy;
        this.movesNext = pos.movesNext;
    }

    public Position result(Move move) {
        Position posCopy = new Position(this);

        Piece p = posCopy.board[move.oldY][move.oldX].getPiece();
        posCopy.board[move.oldY][move.oldX].setPiece(null);
        posCopy.board[move.newY][move.newX].setPiece(p);

        if (posCopy.movesNext == Color.WHITE) {
            posCopy.movesNext = Color.BLACK;
        }
        else {
            posCopy.movesNext = Color.WHITE;
        }

        return posCopy;
    }

    public Piece getPieceAt(int x, int y) {
        return board[y][x].getPiece();
    }

    public Location getPieceLocation(Piece piece) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!board[i][j].isEmpty()) {
                    Piece p = board[i][j].getPiece();
                    if (piece.equals(p)) {
                        return new Location(j, i);
                    }
                }

            }
        }
        return null;
    }

    public ArrayList<Piece> getPieces() {
        ArrayList<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!board[i][j].isEmpty()) {
                    Piece p = board[i][j].getPiece();
                    if (p.color == movesNext) {
                        pieces.add(p);
                    }
                }
            }
        }

        return pieces;
    }

    void printLegalMoves() {
        ArrayList<Move> legalMoves = getLegalMoves();

        for (int i = 0; i < legalMoves.size(); i++) {

            System.out.println("Move " + i + ": " + getPieceAt(legalMoves.get(i).oldX, legalMoves.get(i).oldY).type.toString() + " to " + legalMoves.get(i).newX +
            ", " + legalMoves.get(i).newY);
        }
    }

    void printMove(Move move) {
        System.out.println(getPieceAt(move.oldX, move.oldY).type.toString() + " to " + move.newX +
                ", " + move.newY);
    }

    ArrayList<Move> getLegalMoves() {

        ArrayList<Move> legalMoves = new ArrayList<>();

        for (Piece piece : getPieces()) {
            Type type = piece.type;

            int x = getPieceLocation(piece).x;
            int y = getPieceLocation(piece).y;


            switch (type) {
                case PAWN:

                    if (movesNext == Color.WHITE) {
                        if (y == 6) {
                            legalMoves.add(new Move(x, y, x, y - 2));
                            legalMoves.add(new Move(x, y, x, y - 1));
                        } else {
                            Move m = new Move(x, y, Direction.UP);
                            if (m.newY >= 0) {
                                legalMoves.add(m);
                            }
                        }
                    } else {
                        if (y == 1) {
                            legalMoves.add(new Move(x, y, x, y + 2));
                            legalMoves.add(new Move(x, y, x, y + 1));
                        } else {
                            Move m = new Move(x, y, Direction.DOWN);
                            if (m.newY <= 7) {
                                legalMoves.add(m);
                            }
                        }

                    }

                    break;

                case ROOK:
                    for (Direction dir : new Direction[]{Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT}) {
                        Move m = new Move(x, y, dir);

                        while (m.newX >= 0 && m.newX <= 7 && m.newY >= 0 && m.newY <= 7) {
                            if (board[m.newY][m.newX].isEmpty()) {
                                legalMoves.add(new Move(x, y, m.newX, m.newY));
                            }
                            else if (board[m.newY][m.newX].getPiece().color != movesNext){
                                legalMoves.add(new Move(x, y, m.newX, m.newY));
                                break;
                            }
                            else {
                                break;
                            }
                            m = new Move(m.newX, m.newY, dir);
                        }

                    }

                    break;

                case KNIGHT:
                    Move[] moves = {
                            new Move(x, y, x + 1, y - 2),
                            new Move(x, y, x + 2, y - 1),
                            new Move(x, y, x + 2, y + 1),
                            new Move(x, y, x + 1, y + 2),
                            new Move(x, y, x - 1, y + 2),
                            new Move(x, y, x - 2, y + 1),
                            new Move(x, y, x - 2, y - 1),
                            new Move(x, y, x - 1, y - 2)

                    };

                    for (Move m : moves) {
                        if (m.newX >= 0 && m.newX <= 7 && m.newY >= 0 && m.newY <= 7) {
                            if (board[m.newY][m.newX].isEmpty() || board[m.newY][m.newX].getPiece().color != movesNext) {
                                legalMoves.add(m);
                            }
                        }
                    }

                    break;

                case BISHOP:
                    for (Direction dir : new Direction[]{Direction.NE, Direction.SE, Direction.SW, Direction.NW}) {
                        Move m = new Move(x, y, dir);

                        while (m.newX >= 0 && m.newX <= 7 && m.newY >= 0 && m.newY <= 7) {
                            if (board[m.newY][m.newX].isEmpty()) {
                                legalMoves.add(new Move(x, y, m.newX, m.newY));

                            }
                            else if (board[m.newY][m.newX].getPiece().color != movesNext){
                                legalMoves.add(new Move(x, y, m.newX, m.newY));
                                break;
                            }
                            else {
                                break;
                            }
                            m = new Move(m.newX, m.newY, dir);
                        }

                    }
                    break;

                case QUEEN:
                    for (Direction dir : Direction.values()) {
                        Move m = new Move(x, y, dir);

                        while (m.newX >= 0 && m.newX <= 7 && m.newY >= 0 && m.newY <= 7) {
                            if (board[m.newY][m.newX].isEmpty()) {
                                legalMoves.add(new Move(x, y, m.newX, m.newY));

                            }
                            else if (board[m.newY][m.newX].getPiece().color != movesNext){
                                legalMoves.add(new Move(x, y, m.newX, m.newY));
                                break;
                            }
                            else {
                                break;
                            }
                            m = new Move(m.newX, m.newY, dir);
                        }

                    }
                    break;

                case KING:

                    for (Direction dir : Direction.values()) {
                        Move m = new Move(x, y, dir);
                        if (m.newX >= 0 && m.newX <= 7 && m.newY >= 0 && m.newY <= 7) {
                            if (board[m.newY][m.newX].isEmpty() || board[m.newY][m.newX].getPiece().color != movesNext) {
                                legalMoves.add(m);
                            }
                        }
                    }

                    break;
            }
        }

        return legalMoves;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("  a b c d e f g h \n");
        int i = 0;

        for (Square[] row : board) {
            sb.append(i + " ");
            for (Square s : row) {
                sb.append(s + " ");
            }
            sb.append(i + "\n");
            i++;
        }
        sb.append("  a b c d e f g h \n");
        return sb.toString();
    }
}

class Location {
    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
