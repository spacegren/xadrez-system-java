package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChassMatch;
import chess.ChessPeace;
import chess.Color;

public class Pawn extends ChessPeace {

    private ChassMatch chassMatch;


    public Pawn(Board board, Color color, ChassMatch chassMatch) {
        super(board, color);
        this.chassMatch = chassMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            p.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsaPeace(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() - 2, position.getColumn());
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsaPeace(p) && getBoard().positionExists(p2) && !getBoard().thereIsaPeace(p2) && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPeace(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPeace(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            //# special move enpasssant white

            if (position.getRow() == 3){
                Position Left = new Position(position.getRow() , position.getColumn() -1);
                if (getBoard().positionExists(Left) && isThereOpponentPeace(Left) && getBoard().peace(Left) == chassMatch.getEnpassantVulnerable()){
                    mat[Left.getRow() -1][Left.getColumn()] = true;
                }
                Position Right = new Position(position.getRow() , position.getColumn() +1);
                if (getBoard().positionExists(Right) && isThereOpponentPeace(Right) && getBoard().peace(Right) == chassMatch.getEnpassantVulnerable()){
                    mat[Right.getRow() -1 ][Right.getColumn()] = true;
                }
            }
        }
        else {
            p.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsaPeace(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() + 2, position.getColumn());
            Position p2 = new Position(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().thereIsaPeace(p) && getBoard().positionExists(p2) && !getBoard().thereIsaPeace(p2) && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(p) && isThereOpponentPeace(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(p) && isThereOpponentPeace(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            //# special move enpasssant Black

            if (position.getRow() == 4){
                Position Left = new Position(position.getRow() , position.getColumn() -1);
                if (getBoard().positionExists(Left) && isThereOpponentPeace(Left) && getBoard().peace(Left) == chassMatch.getEnpassantVulnerable()){
                    mat[Left.getRow() +1][Left.getColumn()] = true;
                }
                Position Right = new Position(position.getRow() , position.getColumn() +1);
                if (getBoard().positionExists(Right) && isThereOpponentPeace(Right) && getBoard().peace(Right) == chassMatch.getEnpassantVulnerable()){
                    mat[Right.getRow() +1 ][Right.getColumn()] = true;
                }
            }
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }

}