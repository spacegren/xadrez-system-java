package chess;

import boardgame.Board;
import boardgame.BoardExeption;
import boardgame.Peace;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChassMatch {
    private Board board;

    public ChassMatch(){
        board = new Board(8,8);
        initialSetup();
    }
    public ChessPeace[][] getPeace(){
        ChessPeace[][] mat = new ChessPeace[board.getRows()][board.getColumns()];
        for (int i=0; i< board.getRows(); i++){
            for (int j=0; j< board.getColumns(); j++){
                mat[i][j] = (ChessPeace) board.peace(i,j);
            }
        }
        return mat;
    }
    public ChessPeace performChessMove(ChessPosition sourcePosition , ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Peace capturedPeace = makeMove(source , target);
        return (ChessPeace) capturedPeace;
    }

    private Peace makeMove(Position source , Position target){
        Peace p = board.removePeace(source);
        Peace capturedPeace = board.removePeace(target);
        board.placePeace(p , target);
        return capturedPeace;
    }

    private void validateSourcePosition(Position position){
        if (!board.thereIsaPeace(position)){
            throw new BoardExeption("there is no peace on source position");
        }
    }
    private void placeNewPiece(char column , int row , ChessPeace peace){
        board.placePeace(peace , new ChessPosition(column , row).toPosition());
    }
    private void initialSetup(){

        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new King(board, Color.BLACK));








    }
}