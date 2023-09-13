package boardgame;

public class Peace {
    protected Position position;
    private Board board;

    public Peace(){

    }
    public Peace(Board board){
        this.board = board;
        position = null;
    }

    protected Board getBoard() {
        return board;
    }

}