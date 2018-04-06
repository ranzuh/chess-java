import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Arrays;

public class Position {
    private Square[][] board;


    // initial position (state)
    public Position() {
        board = new Square[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square();
            }
        }


//        board[0][0].setPiece(new Piece(Color.BLACK, Type.ROOK));
//        board[0][1].setPiece(new Piece(Color.BLACK, Type.KNIGHT));
//        board[0][2].setPiece(new Piece(Color.BLACK, Type.BISHOP));
//        board[0][3].setPiece(new Piece(Color.BLACK, Type.QUEEN));
//        board[0][4].setPiece(new Piece(Color.BLACK, Type.KING));
//        board[0][5].setPiece(new Piece(Color.BLACK, Type.BISHOP));
//        board[0][6].setPiece(new Piece(Color.BLACK, Type.KNIGHT));
//        board[0][7].setPiece(new Piece(Color.BLACK, Type.ROOK));
//
//        for (int i = 0; i < 8; i++) {
//            board[1][i].setPiece(new Piece(Color.BLACK, Type.PAWN));
//        }
//
//        board[7][0].setPiece(new Piece(Color.WHITE, Type.ROOK));
//        board[7][1].setPiece(new Piece(Color.WHITE, Type.KNIGHT));
//        board[7][2].setPiece(new Piece(Color.WHITE, Type.BISHOP));
//        board[7][3].setPiece(new Piece(Color.WHITE, Type.QUEEN));
//        board[7][4].setPiece(new Piece(Color.WHITE, Type.KING));
//        board[7][5].setPiece(new Piece(Color.WHITE, Type.BISHOP));
//        board[7][6].setPiece(new Piece(Color.WHITE, Type.KNIGHT));
//        board[7][7].setPiece(new Piece(Color.WHITE, Type.ROOK));
//
//        for (int i = 0; i < 8; i++) {
//            board[6][i].setPiece(new Piece(Color.WHITE, Type.PAWN));
//        }

        board[0][0].setPiece(new Piece(Color.WHITE, Type.KING));
        board[0][1].setPiece(new Piece(Color.BLACK, Type.KING));


    }

    public Position(Square[][] board) {
        Square[][] boardCopy = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }
        this.board = boardCopy;
    }

    public Position result(Move move) {
        Piece p = board[move.oldY][move.oldX].getPiece();
        board[move.oldY][move.oldX].setPiece(null);
        board[move.newY][move.newX].setPiece(p);

        return new Position(board);
    }

    public Square[][] getBoard() {
        return board;
    }

    public Piece getPieceAt(int x, int y) {
        return board[x][y].getPiece();
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

    public ArrayList<Piece> getPieces(Color color) {
        ArrayList<Piece> pieces = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!board[i][j].isEmpty()) {
                    Piece p = board[i][j].getPiece();
                    if (p.color == color) {
                        pieces.add(p);
                    }
                }
            }
        }

        return pieces;
    }

    ArrayList<Move> getLegalMoves(Color color) {

        ArrayList<Move> legalMoves = new ArrayList<>();

        for (Piece piece : getPieces(color)) {
            Type type = piece.type;

            int x = getPieceLocation(piece).x;
            int y = getPieceLocation(piece).y;


            switch (type) {
                case PAWN:

                    if (color == Color.WHITE) {
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
                            } else {
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
                            if (board[m.newY][m.newX].isEmpty()) {
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
                            } else {
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

                            } else {
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
                            if (board[m.newY][m.newX].isEmpty() || board[m.newY][m.newX].getPiece().color != color) {
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
        StringBuilder sb = new StringBuilder("  0 1 2 3 4 5 6 7 \n");
        int i = 0;

        for (Square[] row : board) {
            sb.append(i + " ");
            for (Square s : row) {
                sb.append(s + " ");
            }
            sb.append(i + "\n");
            i++;
        }
        sb.append("  0 1 2 3 4 5 6 7 \n");
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
