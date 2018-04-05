import java.util.ArrayList;

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


        board[0][0].setPiece(new Piece(Color.BLACK, Type.ROOK));
        board[0][1].setPiece(new Piece(Color.BLACK, Type.KNIGHT));
        board[0][2].setPiece(new Piece(Color.BLACK, Type.BISHOP));
        board[0][3].setPiece(new Piece(Color.BLACK, Type.QUEEN));
        board[0][4].setPiece(new Piece(Color.BLACK, Type.KING));
        board[0][5].setPiece(new Piece(Color.BLACK, Type.BISHOP));
        board[0][6].setPiece(new Piece(Color.BLACK, Type.KNIGHT));
        board[0][7].setPiece(new Piece(Color.BLACK, Type.ROOK));

        board[7][0].setPiece(new Piece(Color.WHITE, Type.ROOK));
        board[7][1].setPiece(new Piece(Color.WHITE, Type.KNIGHT));
        board[7][2].setPiece(new Piece(Color.WHITE, Type.BISHOP));
        board[7][3].setPiece(new Piece(Color.WHITE, Type.QUEEN));
        board[7][4].setPiece(new Piece(Color.WHITE, Type.KING));
        board[7][5].setPiece(new Piece(Color.WHITE, Type.BISHOP));
        board[7][6].setPiece(new Piece(Color.WHITE, Type.KNIGHT));
        board[7][7].setPiece(new Piece(Color.WHITE, Type.ROOK));


    }


    // resulting position after a move (result(action))
    public Position(Position pos, Move move) {
        this.board = pos.board;
        Piece p = this.board[move.oldY][move.oldX].getPiece();
        this.board[move.oldY][move.oldX].setPiece(null);
        this.board[move.newY][move.newX].setPiece(p);
    }

    public void result(Move move) {
        Piece p = board[move.oldY][move.oldX].getPiece();
        board[move.oldY][move.oldX].setPiece(null);
        board[move.newY][move.newX].setPiece(p);
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
                            if (board[m.newY][m.newX].isEmpty()) {
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
