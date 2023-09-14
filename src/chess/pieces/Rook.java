package chess.pieces;

import boardgame.Board;
import boardgame.Peace;
import chess.ChessPeace;
import chess.Color;

public class Rook extends ChessPeace {

    public Rook(Board board , Color color){
        super(board, color);
    }
    @Override
    public String toString(){
        return "R";

    }
}
