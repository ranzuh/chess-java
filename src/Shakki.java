public class Shakki {
    public static void main(String[] args) {
        Position pos = new Position();
        System.out.println(pos);

        Location kingLocation = pos.getPieceLocation(Color.WHITE, Type.KING);
        pos = new Position(pos, new Move(kingLocation.x, kingLocation.y, Direction.UP));
        System.out.println(pos);

        kingLocation = pos.getPieceLocation(Color.WHITE, Type.KING);
        pos = new Position(pos, new Move(kingLocation.x, kingLocation.y, Direction.UP));
        System.out.println(pos);
    }

}