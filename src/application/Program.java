package application;

import boardgame.Board;
import boardgame.Position;
import chess.ChassMatch;

import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("PROJETO DE XADREZ : ");
    System.out.println("APLICANDO TODOS OS CONCEITOS VISTO ATE AGORA NO CURSO :");

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        ChassMatch chassMatch = new ChassMatch();
        UI.printBoard(chassMatch.getPeace());


    }
}
