package application;

import boardgame.Position;

import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("PROJETO DE XADREZ : ");
    System.out.println("APLICANDO TODOS OS CONCEITOS VISTO ATE AGORA NO CURSO :");

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);


        Position position = new Position(3,5);
        System.out.println(position);
    }
}
