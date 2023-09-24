package chess;

import boardgame.Board;
import boardgame.Peace;
import boardgame.Position;

public abstract class ChessPeace extends Peace {

    private Color color;
    private int moveCount;


    public ChessPeace(Board board , Color color){
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void encreaseCount(){
        moveCount++;
    }
    public void decreaseMoveCount(){
        moveCount--;
    }


    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);
    }

    protected boolean isThereOpponentPeace(Position position){
        ChessPeace p = (ChessPeace) getBoard().peace(position);
        return p != null && p.getColor() != color;

    }

}
