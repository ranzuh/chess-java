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



        board[4][4].setPiece(new Piece(Color.WHITE, Type.KING));

    }


    // resulting position after a move (result(action))
    public Position(Position pos, Move move) {
        this.board = pos.board;
        Piece p = board[move.oldY][move.oldX].getPiece();
        board[move.oldY][move.oldX].setPiece(null);
        board[move.newY][move.newX].setPiece(p);
    }

    public Piece getPieceAt(int x, int y) {
        return board[x][y].getPiece();
    }

    public Location getPieceLocation(Color color, Type type){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!board[i][j].isEmpty()) {
                    Piece p = board[i][j].getPiece();
                    if (p.color == color && p.type == type) {
                        return new Location(i,j);
                    }
                }

            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Square[] row : board) {
            for (Square s : row) {
                sb.append(s);
            }
            sb.append("\n");
        }

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
