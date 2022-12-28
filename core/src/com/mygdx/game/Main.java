package com.mygdx.game;

import com.mygdx.game.chars.Crossbowman;
import com.mygdx.game.chars.Monk;
import com.mygdx.game.chars.NPC;
import com.mygdx.game.chars.Peasant;
import com.mygdx.game.chars.Robber;
import com.mygdx.game.chars.Sniper;
import com.mygdx.game.chars.Spearman;
import com.mygdx.game.chars.Wizard;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final int GANG_SIZE = 10;
    public static List<NPC> whiteTeam;
    public static List<NPC> darkTeam;

    public Main() {
    }

    public static void main(String[] args) {
        init();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            ConsoleView.view();
            System.out.println("Press ENTER");
            scanner.nextLine();
            setPriority();
        }
    }

    public static void setPriority() {
        int i;
        String clazz;
        for(i = 0; i < 10; ++i) {
            clazz = ((NPC)whiteTeam.get(i)).getClass().toString();
            if (clazz.contains("Sniper")) {
                ((NPC)whiteTeam.get(i)).step(darkTeam);
            }

            clazz = ((NPC)darkTeam.get(i)).getClass().toString();
            if (clazz.contains("Crossbowman")) {
                ((NPC)darkTeam.get(i)).step(whiteTeam);
            }
        }

        for(i = 0; i < 10; ++i) {
            clazz = ((NPC)whiteTeam.get(i)).getClass().toString();
            if (clazz.contains("Robber")) {
                ((NPC)whiteTeam.get(i)).step(darkTeam);
            }

            clazz = ((NPC)darkTeam.get(i)).getClass().toString();
            if (clazz.contains("Spearman")) {
                ((NPC)darkTeam.get(i)).step(whiteTeam);
            }
        }

        for(i = 0; i < 10; ++i) {
            clazz = ((NPC)whiteTeam.get(i)).getClass().toString();
            if (clazz.contains("Monk")) {
                ((NPC)whiteTeam.get(i)).step(darkTeam);
            }

            clazz = ((NPC)darkTeam.get(i)).getClass().toString();
            if (clazz.contains("Wizard")) {
                ((NPC)darkTeam.get(i)).step(whiteTeam);
            }
        }

        for(i = 0; i < 10; ++i) {
            clazz = ((NPC)whiteTeam.get(i)).getClass().toString();
            if (clazz.contains("Peasant")) {
                ((NPC)whiteTeam.get(i)).step(darkTeam);
            }

            clazz = ((NPC)darkTeam.get(i)).getClass().toString();
            if (clazz.contains("Peasant")) {
                ((NPC)darkTeam.get(i)).step(whiteTeam);
            }
        }

    }

    private static void init() {
        whiteTeam = new ArrayList();
        darkTeam = new ArrayList();
        int x = 1;
        int y = 1;

        int i;
        for(i = 0; i < 10; ++i) {
            switch ((new Random()).nextInt(4)) {
                case 0:
                    whiteTeam.add(new Peasant(whiteTeam, x++, y));
                    break;
                case 1:
                    whiteTeam.add(new Robber(whiteTeam, x++, y));
                    break;
                case 2:
                    whiteTeam.add(new Sniper(whiteTeam, x++, y));
                    break;
                default:
                    whiteTeam.add(new Monk(whiteTeam, x++, y));
            }
        }

        x = 0;
        y = 9;

        for(i = 0; i < 10; ++i) {
            switch ((new Random()).nextInt(4)) {
                case 0:
                    darkTeam.add(new Peasant(darkTeam, x++, y));
                    break;
                case 1:
                    darkTeam.add(new Spearman(darkTeam, x++, y));
                    break;
                case 2:
                    darkTeam.add(new Crossbowman(darkTeam, x++, y));
                    break;
                default:
                    darkTeam.add(new Wizard(darkTeam, x++, y));
            }
        }

    }
}