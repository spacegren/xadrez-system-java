package chess;

import boardgame.Board;
import boardgame.BoardExeption;
import boardgame.Peace;
import boardgame.Position;
import chess.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChassMatch {
    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;

    private ChessPeace enpassantVulnerable;


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

    public boolean getCheck(){
        return check;
    }
    public boolean getCheckMate(){
        return checkMate;
    }

    public ChessPeace getEnpassantVulnerable(){
        return enpassantVulnerable;
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

        if (testCheck(currentPlayer)){
            UndoMove(source, target , capturedPeace);
            throw new ChessExeption("you can´t put yourself in check");
        }

        ChessPeace movedPeace =(ChessPeace)board.peace(target);

        check = (testCheck(opponent(currentPlayer))) ? true:false;

        if (testCheckMate(currentPlayer)){
            checkMate = true;
        }
        else {
            nextTurn();
        }

        //special move enpassant
        if (movedPeace instanceof Pawn && (target.getRow() == source.getRow() -2  || target.getRow() == source.getRow() +2)){
            enpassantVulnerable = movedPeace;
        }else {
            enpassantVulnerable = null;

        }
        return (ChessPeace) capturedPeace;
    }

    private Peace makeMove(Position source , Position target){
        ChessPeace p = (ChessPeace)board.removePeace(source);
        p.encreaseCount();
        Peace capturedPeace = board.removePeace(target);
        board.placePeace(p , target);

        if (capturedPeace != null){
            peacesOnTheBoards.remove(capturedPeace);
            capturedPeaces.add(capturedPeace);

        }

        // #specialmove castling kingside rook
        if (p instanceof  King && target.getColumn() == source.getColumn() +2){
            Position sourceT = new Position(source.getRow() , source.getColumn() +3);
            Position targetT = new Position(source.getRow(), source.getColumn()+1);
            ChessPeace rook = (ChessPeace) board.removePeace(sourceT);
            board.placePeace(rook,targetT);
            rook.encreaseCount();
        }

        // #specialmove castling queenside rook
        if (p instanceof  King && target.getColumn() == source.getColumn() -2){
            Position sourceT = new Position(source.getRow() , source.getColumn() -4);
            Position targetT = new Position(source.getRow(), source.getColumn() -1);
            ChessPeace rook = (ChessPeace) board.removePeace(sourceT);
            board.placePeace(rook,targetT);
            rook.encreaseCount();
        }

        // special move en passant
        if (p instanceof Pawn){
            if (source.getColumn() != target.getColumn() && capturedPeaces == null) {
                Position pawPosition;
                if (p.getColor() == Color.WHITE) {
                    pawPosition = new Position(target.getRow() + 1, target.getColumn());
                } else {
                    pawPosition = new Position(target.getRow() - 1, target.getColumn());
                }
                capturedPeace = board.removePeace(pawPosition);
                capturedPeaces.add(capturedPeace);
                peacesOnTheBoards.remove(capturedPeace);
            }
        }
        return capturedPeace;
    }

    private void UndoMove(Position source , Position target , Peace capturedPeace){
        ChessPeace p =(ChessPeace) board.removePeace(target);
        p.decreaseMoveCount();
        board.placePeace(p , source);

        if (capturedPeace != null){
            board.placePeace(capturedPeace , target);
            capturedPeaces.remove(capturedPeace);
            peacesOnTheBoards.add(capturedPeace);
        }

        // #specialmove castling kingside rook
        if (p instanceof  King && target.getColumn() == source.getColumn() +2){
            Position sourceT = new Position(source.getRow() , source.getColumn() +3);
            Position targetT = new Position(source.getRow(), source.getColumn()+1);
            ChessPeace rook = (ChessPeace) board.removePeace(targetT);
            board.placePeace(rook,sourceT);
            rook.decreaseMoveCount();
        }

        // #specialmove castling queenside rook
        if (p instanceof  King && target.getColumn() == source.getColumn() -2){
            Position sourceT = new Position(source.getRow() , source.getColumn() -4);
            Position targetT = new Position(source.getRow(), source.getColumn() -1);
            ChessPeace rook = (ChessPeace) board.removePeace(targetT);
            board.placePeace(rook,sourceT);
            rook.decreaseMoveCount();
        }

        if (p instanceof Pawn){
            if (source.getColumn() != target.getColumn() && capturedPeaces == enpassantVulnerable) {

                ChessPeace paw = (ChessPeace)board.removePeace(target);
                Position pawPosition;
                if (p.getColor() == Color.WHITE) {
                    pawPosition = new Position( 3, target.getColumn());
                } else {
                    pawPosition = new Position(4, target.getColumn());
                }
                board.placePeace(paw,pawPosition);
            }
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

    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Peace> opponentPeaces = peacesOnTheBoards.stream().filter(x -> ((ChessPeace)x).getColor() == opponent(color)).collect(Collectors.toList());

        for (Peace p : opponentPeaces){
            boolean [][] mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            }
        }
        return false;
    }


    //logica check mate

    private boolean testCheckMate(Color color){
        if (!testCheck(color)){
            return false;
        }
        List<Peace> list = peacesOnTheBoards.stream().filter(x -> ((ChessPeace)x).getColor() == color).collect(Collectors.toList());
        for (Peace p : list){
            boolean[][] mat = p.possibleMoves();
            for(int i=0; i<board.getRows(); i++){
                for (int j=0; j< board.getColumns(); j++){
                    if (mat[i][j]){
                        Position source = ((ChessPeace)p).getChessPosition().toPosition();
                        Position target = new Position(i,j);
                        Peace capturedPeace = makeMove(source , target);
                        boolean testCheck = testCheck(color);
                        UndoMove(source ,target , capturedPeace);
                        if (!testCheck){
                        return false;
                         }
                    }
                }
            }
        }
        return true;
    }

    private void placeNewPiece(char column , int row , ChessPeace peace){
        board.placePeace(peace , new ChessPosition(column , row).toPosition());
        peacesOnTheBoards.add(peace);
    }
    private void initialSetup(){



        // nesta posiçao de teste para nao perder muito tempo fics mais facil para dar um check mate;

       /* placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));

        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));

        */


        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE , this));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE , this));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE , this));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE , this));


        /*placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        * placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        *  placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        * placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        * placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        * placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        * placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        * placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
         */


        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.WHITE));
        placeNewPiece('c', 8, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 8, new Queen(board, Color.WHITE));
        placeNewPiece('e', 8, new King(board, Color.BLACK ,this));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK , this));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK , this));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK , this));


       /* placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        *placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        *placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
        */
    }
}