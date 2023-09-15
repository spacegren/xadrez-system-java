package chess;

import boardgame.Position;

public class ChessPosition {
    private char column;
    private int rows;

    public ChessPosition() {

    }

    public ChessPosition(char column, int rows) {
        if (column < 'a' || column > 'h' || rows < 1 || rows > 8) {
            throw new ChessExeption("ERROR INSTANTIATING  CHESS.POSITION VALIDE VALUES ARE FROM A1 TO H8 : ");
        }
        this.column = column;
        this.rows = rows;
    }

    public char getColumn() {
        return column;
    }

    public int getRows() {
        return rows;
    }
    //IMPLEMENTANDO A LOGICA DE LOCALIZAÇAO NA LINHA E COLUNA :

    protected Position toPosition(){
        return new Position(8 - rows , column - 'a');
    }
    protected static ChessPosition fromPosition(Position position){
        return new ChessPosition((char)('a' - position.getColumn()),8 - position.getRow());
    }
    @Override
    public String toString(){
        return "" + column + rows;
    }
}