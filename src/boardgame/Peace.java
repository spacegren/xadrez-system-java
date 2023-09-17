package boardgame;

public abstract class Peace {
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

    public abstract boolean [][] possibleMoves();

    //ULTILIAZNDO UM CONCEITO DE ROOK METHODS GANCHO;

    public boolean possibleMoves(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMoves(){
        boolean[][] mat = possibleMoves();
        for (int i=0; i<mat.length; i++){
            for (int j=0; j<mat.length; j++){
                if (mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
}