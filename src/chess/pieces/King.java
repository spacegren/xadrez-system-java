package chess.pieces;

import boardgame.Board;
import chess.ChessPeace;
import chess.Color;

public class King  extends ChessPeace {

    public King(Board board , Color color){
        super(board , color);
    }
    @Override
    public String toString(){
        return "K";
    }
}
