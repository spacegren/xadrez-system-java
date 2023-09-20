package chess;

import boardgame.Board;
import boardgame.BoardExeption;
import boardgame.Peace;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChassMatch {
    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;


    private List<Peace> peacesOnTheBoards = new ArrayList<>();
    private List<Peace> capturedPeaces = new ArrayList<>();

    public ChassMatch(){
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
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
    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.peace(position).possibleMoves();
    }
    public ChessPeace performChessMove(ChessPosition sourcePosition , ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPostion(source ,target);
        Peace capturedPeace = makeMove(source , target);
        nextTurn();
        return (ChessPeace) capturedPeace;
    }

    private Peace makeMove(Position source , Position target){
        Peace p = board.removePeace(source);
        Peace capturedPeace = board.removePeace(target);
        board.placePeace(p , target);

        if (capturedPeace != null){
            peacesOnTheBoards.remove(capturedPeace);
            capturedPeaces.add(capturedPeace);

        }
        return capturedPeace;

    }

    private void UndoMove(Position source , Position target , Peace capturedPeace){
        Peace p = board.removePeace(target);
        board.placePeace(p , source);

        if (capturedPeace != null){
            board.placePeace(capturedPeace , target);
            capturedPeaces.remove(capturedPeace);
            peacesOnTheBoards.add(capturedPeace);
        }
    }

    private void validateSourcePosition(Position position){
        if (!board.thereIsaPeace(position)){
            throw new BoardExeption("there is no peace on the  source position");
        }
        //ultilizando o downcasting
        if (currentPlayer != ((ChessPeace)board.peace(position)).getColor());{
            throw new ChessExeption("the chosen peace is not yours :");
        }
        //if (!board.peace(position).isThereAnyPossibleMoves()){
        //    throw new ChessExeption("theres is no possible moves for the chosen peace :");
       // }
    }

    private void validateTargetPostion(Position source , Position target){
        if (!board.peace(source) .possibleMoves()[target.getRow()][target.getColumn()]){
            throw new ChessExeption("the chosen peace can´t move to target");
        }
    }

    // trocadeturno
    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;


    }

    private Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPeace king(Color color){
        List<Peace> list = peacesOnTheBoards.stream().filter(x -> ((ChessPeace)x).getColor() ==color).collect(Collectors.toList());
        for (Peace p : list){
            if (p instanceof King){
                return (ChessPeace) p;
            }
        }
        throw new IllegalStateException("there is no "+ color + "king on the board :");
        
    }
    private void placeNewPiece(char column , int row , ChessPeace peace){
        board.placePeace(peace , new ChessPosition(column , row).toPosition());
        peacesOnTheBoards.add(peace);
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