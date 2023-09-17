package chess;

import boardgame.Board;
import boardgame.Peace;

public abstract class ChessPeace extends Peace {

    private Color color;

    public ChessPeace(Board board , Color color){
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
