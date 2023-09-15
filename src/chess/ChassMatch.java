package chess;

import boardgame.Board;
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
    //OPERAÇAO DE COLOCLAR PEÇAS PASSANDO A POSIÇAO  NAS COORDENADAS DO XADREZ
    private void placeNewPiece(char column , int rows , ChessPeace peace){
        board.placePeace(peace , new ChessPosition(column ,rows).toPosition());

    }
    private void initialSetup(){
        placeNewPiece('b' , 6 , new Rook(board , Color.WHITE));
        placeNewPiece('e' , 8 , new King(board ,Color.BLACK));
        placeNewPiece('e' , 1, new King(board , Color.WHITE));

    }


}