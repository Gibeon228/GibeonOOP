package com.mygdx.game;


import com.mygdx.game.chars.NPC;
import com.mygdx.game.chars.States;
import com.mygdx.game.chars.Vector2;
import java.util.Collections;

public class ConsoleView {
    public static int step = 0;
    private static final String top10 = "╭─" + String.join("─", Collections.nCopies(9, " ")) + "─╮";
    private static final String bottom10 = "╰─" + String.join("─", Collections.nCopies(9, " ")) + "─╯";

    public ConsoleView() {
    }

    public static void viewTop() {
        System.out.println(top10);
    }

    public static void viewBottom() {
        System.out.println(bottom10);
    }

    public static void view() {
        if (step++ == 0) {
            System.out.println("\u001b[31mFirst step!\u001b[0m");
        } else {
            System.out.println("\u001b[31mStep: " + step + "\u001b[0m");
        }

        viewTop();

        for(int i = 1; i < 10; ++i) {
            System.out.println("|");

            for(int j = 1; j < 10; ++j) {
                System.out.print(getChar(new Vector2(i, j)));
            }

            System.out.println("|");
            String str1 = "  " + ((NPC)Main.whiteTeam.get(i)).getInfo();
            String str2 = progressBar((int)((NPC)Main.whiteTeam.get(i)).getHealth(), (int)((NPC)Main.whiteTeam.get(i)).getMaxHealth());
            String str3 = "  " + ((NPC)Main.darkTeam.get(i)).getInfo();
            String str4 = progressBar((int)((NPC)Main.darkTeam.get(i)).getHealth(), (int)((NPC)Main.darkTeam.get(i)).getMaxHealth());
            System.out.format("%-26s %20s %26s %20s \n", str1, str2, str3, str4);
        }

        viewBottom();
    }

    public static String getFirstLetter(NPC hero) {
        String var10000 = hero.getClass().getSimpleName();
        return "" + var10000.charAt(0);
    }

    public static String getChar(Vector2 position) {
        String str = "  ";

        for(int i = 0; i < 10; ++i) {
            boolean alive = true;
            if (((NPC)Main.whiteTeam.get(i)).getPosition().isEqual(position)) {
                if (((NPC)Main.whiteTeam.get(i)).getState() == States.DEAD) {
                    alive = false;
                    str = "\u001b[31m" + getFirstLetter((NPC)Main.whiteTeam.get(i)) + "\u001b[0m";
                } else {
                    str = "\u001b[34m" + getFirstLetter((NPC)Main.whiteTeam.get(i)) + "\u001b[0m";
                }
            }

            if (((NPC)Main.darkTeam.get(i)).getPosition().isEqual(position) && alive) {
                if (((NPC)Main.darkTeam.get(i)).getState().equals(States.DEAD)) {
                    str = " \u001b[31m" + getFirstLetter((NPC)Main.darkTeam.get(i)) + "\u001b[0m";
                } else {
                    str = " \u001b[32m" + getFirstLetter((NPC)Main.darkTeam.get(i)) + "\u001b[0m";
                }
            }
        }

        return str;
    }

    private static String progressBar(int remain, int total) {
        int maxBarSize = 10;
        int remainPercent = 100 * remain / total / maxBarSize;
        char defaultChar = '-';
        String icon = "*";
        String bar = (new String(new char[maxBarSize])).replace('\u0000', defaultChar) + "]";
        String var10000 = icon.repeat(Math.max(0, remainPercent));
        String barDone = "[" + var10000;
        String barRemain = bar.substring(remainPercent);
        return barDone + barRemain + remainPercent * 10 + "%";
    }
}
