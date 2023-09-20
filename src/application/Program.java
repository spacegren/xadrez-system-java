package application;

import boardgame.Board;
import boardgame.Position;
import chess.ChassMatch;
import chess.ChessExeption;
import chess.ChessPeace;
import chess.ChessPosition;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        System.out.println("PROJETO DE XADREZ : ");
    System.out.println("APLICANDO TODOS OS CONCEITOS VISTO ATE AGORA NO CURSO :");

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        ChassMatch chassMatch = new ChassMatch();
        List<ChessPeace> captured = new ArrayList<>();

       while (true){
           try {
               UI.clearScreen();
               UI.printMatch(chassMatch , captured);
               System.out.println();
               System.out.print("source : ");
               ChessPosition source = UI.readChessPosition(scanner);

               boolean[][] possibleMoves = chassMatch.possibleMoves(source);
               UI.clearScreen();
               UI.printBoard(chassMatch.getPeace() , possibleMoves);
               System.out.println();
               System.out.println("target : ");
               ChessPosition target = UI.readChessPosition(scanner);

               ChessPeace capturedPeace = chassMatch.performChessMove(source, target);

               if (capturedPeace != null){
                   captured.add(capturedPeace);

               }
           }
           catch (ChessExeption e){
               System.out.println(e.getMessage());
               scanner.nextLine();
           }
           catch (InputMismatchException e){
               System.out.println(e.getMessage());
               scanner.nextLine();
           }


       }

    }
}
