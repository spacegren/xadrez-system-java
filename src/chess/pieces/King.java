package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChassMatch;
import chess.ChessPeace;
import chess.Color;

public class King  extends ChessPeace {

    private ChassMatch chassMatch;


    public King(Board board , Color color , ChassMatch chassMatch) {
        super(board , color);
        this.chassMatch = chassMatch;
    }
    @Override
    public String toString(){
        return "K";
    }

    private boolean canMove(Position position){
        ChessPeace p = (ChessPeace)getBoard().peace(position);
        return p == null || p.getColor() != getColor();
    }

    private boolean testRookCastling(Position position){
        ChessPeace p = (ChessPeace) getBoard().peace(position);
            return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount()== 0;
    }

    @Override
    public boolean [][] possibleMoves(){
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        //above
        p.setValues(position.getRow() -1 , position.getColumn());
        if (getBoard().positionExists(p) && (canMove(p))){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //bellow
        p.setValues(position.getRow() +1 , position.getColumn());
        if (getBoard().positionExists(p) && (canMove(p))) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        //left
        p.setValues(position.getRow(), position.getColumn() -1);
        if (getBoard().positionExists(p) && (canMove(p))){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //right
        p.setValues(position.getRow(), position.getColumn() +1);
        if (getBoard().positionExists(p) && (canMove(p))){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //nw
        p.setValues(position.getRow() -1, position.getColumn() -1);
        if (getBoard().positionExists(p) && (canMove(p))){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //ne
        p.setValues(position.getRow() -1 , position.getColumn() +1 );
        if (getBoard().positionExists(p) && (canMove(p))){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //sw
        p.setValues(position.getRow() +1, position.getColumn() -1);
        if (getBoard().positionExists(p) && (canMove(p))){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //se
        p.setValues(position.getRow() +1 , position.getColumn() +1);
        if (getBoard().positionExists(p) && (canMove(p))){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // specialmove castling

        if (getMoveCount() == 0 && !chassMatch.getCheck()){
            //specialmove castling kingsiderook

            Position position1 = new Position(position.getRow(), position.getColumn() +3);
            if (testRookCastling(position1)){
                Position p1 = new Position(position.getRow(), position.getColumn() +1);
                Position p2 = new Position(position.getRow(), position.getColumn()+2);
                if (getBoard().peace(p1) == null && getBoard().peace(p2) == null){
                    mat[position.getRow()][position.getColumn() +2] = true;
                }
            }
            //specialmove castling queenside rook

            Position position2 = new Position(position.getRow(), position.getColumn() -4);
            if (testRookCastling(position2)){
                Position p1 = new Position(position.getRow(), position.getColumn() -1);
                Position p2 = new Position(position.getRow(), position.getColumn()-2);
                Position p3 = new Position(position.getRow() , position.getColumn()-3);
                if (getBoard().peace(p1) == null && getBoard().peace(p2) == null && getBoard().peace(p3) == null){
                    mat[position.getRow()][position.getColumn() -2] = true;
                }
            }
        }
        return mat;
    }


}
