package chess.pieces;

import boardgame.Board;
import boardgame.Peace;
import boardgame.Position;
import chess.ChessPeace;
import chess.Color;

public class Queen extends ChessPeace {

    public Queen (Board board , Color color){
        super(board, color);
    }
    @Override
    public String toString(){
        return "Q";
    }

    @Override
    public boolean[][] possibleMoves(){
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);

        //above
        //LOGICA PARA MARVAS AS POSIÇOES VALIDAS ACIMA DA PEÇA

        p.setValues(position.getRow() -1 , position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsaPeace(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPeace(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //left

        p.setValues(position.getRow() , position.getColumn() -1);
        while (getBoard().positionExists(p) && !getBoard().thereIsaPeace(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() -1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPeace(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //right

        p.setValues(position.getRow() , position.getColumn() +1);
        while (getBoard().positionExists(p) && !getBoard().thereIsaPeace(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() +1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPeace(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //bellow

        p.setValues(position.getRow() +1 , position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsaPeace(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() +1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPeace(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        // NW

        //LOGICA PARA MARVAS AS POSIÇOES VALIDAS ACIMA DA PEÇA

        p.setValues(position.getRow() -1 , position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().thereIsaPeace(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() -1 , p.getColumn() -1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPeace(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //NE

        p.setValues(position.getRow() -1 , position.getColumn() +1);
        while (getBoard().positionExists(p) && !getBoard().thereIsaPeace(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() -1 , p.getColumn() +1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPeace(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //SE

        p.setValues(position.getRow() +1 , position.getColumn() +1);
        while (getBoard().positionExists(p) && !getBoard().thereIsaPeace(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() +1 , p.getColumn() +1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPeace(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //SW

        p.setValues(position.getRow() +1 , position.getColumn() -1);
        while (getBoard().positionExists(p) && !getBoard().thereIsaPeace(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() +1 , p.getColumn() -1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPeace(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        return mat;
    }

}
