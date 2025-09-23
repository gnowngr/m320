package ch.tbz.gino_goncalo.V3.menu;

import ch.tbz.gino_goncalo.V3.figuren.AxtKrieger;
import ch.tbz.gino_goncalo.V3.figuren.DolchSchurke;
import ch.tbz.gino_goncalo.V3.figuren.Figur;
import ch.tbz.gino_goncalo.V3.figuren.StabMagier;
import ch.tbz.gino_goncalo.V3.waffen.Axt;
import ch.tbz.gino_goncalo.V3.waffen.Dolch;
import ch.tbz.gino_goncalo.V3.waffen.Stab;
import ch.tbz.gino_goncalo.V3.waffen.WaffenVerhalten;

import java.util.Scanner;

public final class Menu {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Figur wählen: 1) Krieger  2) Magier  3) Schurke");
        int f = readChoice(1, 3);

        System.out.println("Waffe wählen: 1) Axt  2) Stab  3) Dolch");
        int w = readChoice(1, 3);

        Figur figur = createFigure(f, createWeapon(w));
        System.out.println(figur.laufen());
        System.out.println(figur.kaempfen());
    }

    private int readChoice(int min, int max) {
        while (true) {
            String input = scanner.nextLine();
            try {
                int val = Integer.parseInt(input.trim());
                if (val >= min && val <= max) return val;
            } catch (NumberFormatException ignored) {}
            System.out.printf("Bitte Zahl zwischen %d und %d eingeben:%n", min, max);
        }
    }

    private WaffenVerhalten createWeapon(int choice) {
        return switch (choice) {
            case 1 -> new Axt();
            case 2 -> new Stab();
            default -> new Dolch();
        };
    }

    private Figur createFigure(int choice, WaffenVerhalten waffe) {
        return switch (choice) {
            case 1 -> new AxtKrieger("Krieger", waffe);
            case 2 -> new StabMagier("Magier", waffe);
            default -> new DolchSchurke("Schurke", waffe);
        };
    }
}


