package application;

import boardgame.Peace;
import chess.ChassMatch;
import chess.ChessPeace;
import chess.ChessPosition;
import chess.Color;

import java.util.*;
import java.util.stream.Collectors;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // https://stackoverflow.com/questions/2979383/java-clear-the-console


    public static void clearScreen(){
        System.out.println("clearing screen.....");
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }


    public static ChessPosition readChessPosition(Scanner scanner){
        try {
            String s = scanner.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        }
        catch (RuntimeException e){
            throw new InputMismatchException("ERROR READING CHASSPOSITION. VALID VALUES ARE FROM A1 TO H8");
        }
    }
    public static void printMatch(ChassMatch chassMatch , List<ChessPeace> captured){
        printBoard(chassMatch.getPeace());
        System.out.println();
        printCapturedPeace(captured);
        System.out.println();
        System.out.println("turn : " + chassMatch.getTurn());

        if (!chassMatch.getCheckMate()) {
            System.out.println("waiting player :" + chassMatch.getCurrentPlayer());

            if (chassMatch.getCheck()) {
                System.out.println("CHECK");
            }
        }else {
            System.out.println("CHECKMATE");
            System.out.println("WINNER : " + chassMatch.getCurrentPlayer());

        }


    }

    public static void printBoard(ChessPeace[][] peaces){
        for (int i=0; i< peaces.length; i++){
            System.out.print((8 - i) + " ");
            for (int j=0; j< peaces.length; j++){
                printPeace(peaces[i][j] , false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h ");

    }

    public static void printBoard(ChessPeace[][] peaces , boolean[][] possibleMoves){
        for (int i=0; i< peaces.length; i++){
            System.out.print((8 - i) + " ");
            for (int j=0; j< peaces.length; j++){
                printPeace(peaces[i][j] , possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h ");

    }

    private static void printPeace(ChessPeace peace , boolean background){
        if (background){
            System.out.print(ANSI_BLUE);
        }
        if (peace ==null){
            System.out.print("-" + ANSI_RESET);
        }else{
            if (peace.getColor() == Color.WHITE){
                System.out.print(ANSI_WHITE + peace + ANSI_RESET);
            }else {
                System.out.print(ANSI_YELLOW + peace + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printCapturedPeace(List<ChessPeace> captured){
        List<ChessPeace> white = captured.stream().filter( x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPeace> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
        System.out.println("captures peaces ");
        System.out.println(white);
        System.out.println(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.println(ANSI_RESET);
        System.out.println(black);
        System.out.println(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.println(ANSI_RESET);
    }

}
