package application;

import boardgame.Board;
import boardgame.Position;
import chess.ChassMatch;
import chess.ChessPeace;
import chess.ChessPosition;

import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("PROJETO DE XADREZ : ");
    System.out.println("APLICANDO TODOS OS CONCEITOS VISTO ATE AGORA NO CURSO :");

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        ChassMatch chassMatch = new ChassMatch();

       while (true){
           UI.printBoard(chassMatch.getPeace());
           System.out.println();
           System.out.print("source : ");
           ChessPosition source = UI.readChessPosition(scanner);

           System.out.println();
           System.out.println("target : ");
           ChessPosition target = UI.readChessPosition(scanner);

           ChessPeace capturedPeace = chassMatch.performChessMove(source , target);
       }



    }
}
