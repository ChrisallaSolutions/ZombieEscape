package myfirstcode;

import java.security.SecureRandom;
import java.util.Scanner;

public class ZombieEscape1_0 {

    public static void main(String[] args) {
        int alt = 0; // Alternativenzähler
        int i = 0; // Arrayzähler
        int j = 0; // Arrayzähler
        int n = 0; // Startpunkt
        int m = 0; // Startpunkt
        int y = 0; // Ausgang
        int z = 0; // Ausgang
        int s = 0; // Aktueller Standort Wert
        int h = 0; // Heilungstandort
        int l = 0; // Heilungstandort
        int lebe = 0; // Ausgangsschalter
        int lvl = 0; // Levelauswahl
        int xy = 0; // Levelgröße
        int zed = 0; //Zombiehäufigkeit durch Random
        int zomb[] = {2, 3, 6, 1, 3}; // CQC,Strength,Endurance,Damage Capacity, Courage/Horror
        int zhp = 0; // Zombielebenspunktezähler
        int plyr[] = {3, 3, 3, 3, 3}; // CQC,Strength,Endurance,Damage Capacity, Courage/Horror
        int dplyr = 0; // Spielerwürfel
        int dzed = 0; // Zombiewürfel
        int waffe = 0; // Waffe vorhanden?
        int w = 0; // Anzahl Waffen zu finden
        int waf = 0; // Waffenstandort
        int fe = 0; // Waffenstandort
        int cqc = 0; // CQC var
        int str = 0; // Strength var
        String fight = "";

        SecureRandom rand = new SecureRandom();
        Scanner eingabe = new Scanner(System.in);
        String act = "";

        do {
            System.out.println("Bitte Schwierigkeitsgrad wählen:\n"
                    + "1: 8 x 8 großer Raum\n2: 16 x 16 großer Raum\n3: 24 x 24 großer Raum");
            lvl = eingabe.nextInt();
        } while (lvl != 1 && lvl != 2 && lvl != 3);
        switch (lvl) {
            case 1:
                w = 2;
                xy = 8;
                zed = 5;
                for (int k = 0; k < 4; k++) {
                    plyr[k] = 4;
                }
                // {4,4,4,4,3} CQC,Strength,Endurance,Damage Capacity, Courage/Horror
                break;

            case 2:
                w = 4;
                xy = 16;
                zed = 4;
                plyr[0] = 4;
                // {4,3,3,3,3} CQC,Strength,Endurance,Damage Capacity, Courage/Horror
                break;

            case 3:
                w = 6;
                xy = 24;
                zed = 3;
                plyr[4] = 2;
                zomb[2] = 7;
                // {3,3,3,3,2} CQC,Strength,Endurance,Damage Capacity, Courage/Horror
                break;
        }

        int[][] feld = new int[xy][xy]; // Feld auffüllen

        while (j < xy) {
            i = 0;
            for (; i < xy; i++) {

                feld[j][i] = rand.nextInt(zed) + 1;
                //System.out.print(feld[j][i]);
            }
            j++;
            //System.out.println(" ");
        }

        System.out.println("\n\n************************************************");
        System.out.println("***************\tZombie Escape\t****************");
        System.out.println("************************************************\n\n");

        //start
        n = rand.nextInt(xy) + 0;
        m = rand.nextInt(xy) + 0;

        //Ausgang
        y = rand.nextInt(xy) + 0;
        z = rand.nextInt(xy) + 0;
        feld[y][z] = 99;

        if (lvl == 3) {
            // Heilung
            h = rand.nextInt(xy) + 0;
            l = rand.nextInt(xy) + 0;
            feld[h][l] = 112;
        }

        //Waffen
        for (int k = 0; k < w / 2; k++) {

            waf = rand.nextInt(xy) + 0;
            fe = rand.nextInt(xy) + 0;
            feld[waf][fe] = 42;

            waf = rand.nextInt(xy) + 0;
            fe = rand.nextInt(xy) + 0;
            feld[waf][fe] = 43;
        }
        /* i = 0; //Feld prüfen
        j = 0;
        while (j < xy) {
            i = 0;
            for (; i < xy; i++) {

                System.out.print(feld[j][i] + "\t");
            }
            j++;
            System.out.println("\n ");
        }
         */
        System.out.println("Du wachst benebelt in einem riesigen schlecht beleuchteten Raum auf, "
                + "\num dich herum siehst du Schatten die sich bewegen. Das ständige Stöhnen gefolgt von gelegentlichem\n"
                + "Aufschreien eines Todeskampfes lässt dein Blut gefrieren. Du willst weg!!! \n\n");

        while (lebe == 0) {

           /* i = 0; //Feld prüfen
            j = 0;
            while (j < xy) {
                i = 0;
                for (; i < xy; i++) {

                    System.out.print(feld[j][i] + "\t");
                }
                j++;
                System.out.println("\n ");
            }*/

            do {
                System.out.println("Wohin willst du gehen, nach Norden(N), Süden(S), Westen(W) oder Osten(O)?\n\n");
                act = eingabe.next();
            } while (!act.equalsIgnoreCase("N") && !act.equalsIgnoreCase("S") && !act.equalsIgnoreCase("W")  && !act.equalsIgnoreCase("O"));

            if (act.equalsIgnoreCase("N")) {
                n = n - 1;
                if (n < 0) {
                    System.out.println("Oh Nein! Da ist eine Wand, geh wo anders lang\n\n");
                    n = n + 1;
                    s = 101;

                } else {
                    s = feld[n][m];
                }
            } else if (act.equalsIgnoreCase("S")) {
                n = n + 1;
                if (n > xy - 1) {
                    System.out.println("Verflixt! Da ist eine Wand, geh wo anders lang\n\n");
                    n = n - 1;
                    s = 101;

                } else {
                    s = feld[n][m];
                }
            } else if (act.equalsIgnoreCase("W")) {
                m = m - 1;
                if (m < 0) {
                    System.out.println("Autsch! Da ist eine Wand, geh wo anders lang\n\n");
                    m = m + 1;
                    s = 101;
                } else {
                    s = feld[n][m];
                }

            } else {
                m = m + 1;
                if (m > xy - 1) {
                    System.out.println("Mist! Da ist eine Wand, geh wo anders lang\n\n");
                    m = m - 1;
                    s = 101;

                } else {
                    s = feld[n][m];
                }
            }
            /*
            System.out.println("n: " + n);
            System.out.println("m: " + m);
            System.out.println("y: " + y);
            System.out.println("z: " + z);
            System.out.println("s: " + s);
            */

            switch (s) {

                case 1:
                    alt++;
                    System.out.println("Aaargh...Zombie!!!\n\n");
                    //System.out.println("Leben: " +plyr[3]);

                    switch (alt) {
                        case 1:
                            System.out.println("Aaahh ich glaube ich bekomme einen Herzinfarkt!!");
                            break;

                        case 2:
                            System.out.println("Erschreckt mich nicht immer so!!");
                            break;

                        case 3:
                            System.out.println("Verdammt schon wieder!!");
                            break;

                        case 4:
                            System.out.println("Mann ist der hässlich!");
                            break;

                        case 5:
                            System.out.println("Ah noch einer!");
                            break;

                        case 6:
                            System.out.println("Komm her ich mach die fertig");
                            break;

                        case 7:
                            System.out.println("Endlich war schon fast langweilig");
                            break;

                        default:
                            System.out.println("Warte ich erlöse dich aus deinem Leid");
                            break;
                    }
                    dplyr = rand.nextInt(6) + 1;
                    dzed = rand.nextInt(6) + 1;
                    fight = "j";

                    if (plyr[4] + dplyr >= zomb[4] + dzed) { //Horrortest
                        System.out.println("Kein Problem, an dem komme ich vorbei");
                        System.out.println("Soll ich kämpfen (j/n)\n\n");

                        do {
                            fight = eingabe.next();
                        } while (!fight.equalsIgnoreCase("j") && !fight.equalsIgnoreCase("n"));

                        if (fight.equalsIgnoreCase("n")) {
                            break;
                        }
                    }

                    while (fight.equalsIgnoreCase("j")) {
                        cqc = plyr[0];
                        str = plyr[1];
                        zhp = zomb[3];
                        if (waffe == 1) {
                            cqc = cqc + 1;
                            str = str + 1;
                        }

                        while (plyr[3] != 0 || zhp != 0) {
                            dplyr = rand.nextInt(6) + 1;
                            // System.out.println(dplyr +"fight p");
                            dzed = rand.nextInt(6) + 1;
                            //System.out.println(dzed +"fight z");

                            if ((dplyr + cqc) >= (dzed + zomb[0])) { //Attack

                                dplyr = rand.nextInt(6) + 1;
                                /*System.out.println(dplyr);
                                System.out.println("dmg plyr:" +(plyr[1]+dplyr));
                                System.out.println("fight result:" + ((plyr[1] + dplyr)/zomb[2]));*/

                                if ((str + dplyr) / zomb[2] >= 1) { //Damagetest
                                    zhp--;
                                    feld[n][m] = 3;
                                    fight = "n";
                                    switch (alt) {
                                        case 1:
                                            System.out.println("Oh mein Gott, ich hab's geschafft!!\n\n");
                                            break;

                                        case 2:
                                            System.out.println("Nimm das Zombie!\n\n");
                                            break;

                                        case 3:
                                            System.out.println("Das läuft ja wie geschmiert...\n\n");
                                            break;

                                        default:
                                            System.out.println("...und tot!\n\n");
                                            break;
                                    }

                                    break;

                                }

                            }

                            dzed = rand.nextInt(6) + 1;
                            //System.out.println(dzed +"fight back z");
                            dplyr = rand.nextInt(6) + 1;
                            //System.out.println(dplyr +"fight back p");

                            if ((dplyr + cqc) < (dzed + zomb[0])) { //Zed Attack

                                dzed = rand.nextInt(6) + 1;
                                //System.out.println(dzed);
                                //System.out.println("dmg zed:" +(zed[1]+dzed));

                                if ((zomb[1] + dzed) / plyr[2] >= 1) { //Zed Damagetest
                                    plyr[3] = plyr[3] - ((zomb[1] + dzed) / plyr[2]);

                                    if (plyr[3] == 0) {
                                        fight = "n";
                                        lebe = 1;
                                        System.out.println("Während du erschöpft zu Boden fällst, dein Blut überall rausspritzt und dir langsam Schwarz vor den "
                                                + "Augen wird, \nmerkst du noch wie der Zombie anfängt an dir zu knabbern...\n\nDu bist tot!");
                                        break;
                                    }
                                }

                            }
                        }

                    }

                    break;

                case 2:
                    switch (alt) {
                        case 1:
                            System.out.println("Alles klar weiter zum Ausgang...\n\n");
                            break;

                        case 2:
                            System.out.println("Der Ausgang muss gleich kommen...\n\n");
                            break;

                        case 3:
                            System.out.println("Was war das? Bleibt weg von mir...\n\n");
                            break;

                        default:
                            System.out.println("Ist da hinten ein Licht ??? ...\n\n");
                            break;
                    }
                    break;
                case 3:
                    switch (alt) {

                        case 1:
                            System.out.println("Ist da hinten ein Licht ??? ...\n\n");
                            break;

                        case 2:
                            System.out.println("Alles klar weiter zum Ausgang...\n\n");
                            break;

                        case 3:
                            System.out.println("Der Ausgang muss gleich kommen...\n\n");
                            break;

                        default:
                            System.out.println("Was war das? Bleibt weg von mir...\n\n");
                            break;
                    }
                    break;
                case 4:
                    switch (alt) {

                        case 1:
                            System.out.println("Was war das? Bleibt weg von mir...\n\n");
                            break;

                        case 2:
                            System.out.println("Ist da hinten ein Licht ??? ...\n\n");
                            break;

                        case 3:
                            System.out.println("Alles klar weiter zum Ausgang...\n\n");
                            break;

                        default:
                            System.out.println("Der Ausgang muss gleich kommen...\n\n");
                            break;
                    }
                    break;
                case 5:
                    switch (alt) {

                        case 1:
                            System.out.println("Der Ausgang muss gleich kommen...\n\n");
                            break;

                        case 2:
                            System.out.println("Was war das? Bleibt weg von mir...\n\n");
                            break;

                        case 3:
                            System.out.println("Ist da hinten ein Licht ??? ...\n\n");
                            break;

                        default:
                            System.out.println("Alles klar weiter zum Ausgang...\n\n");
                            break;
                    }
                    break;
                case 42:
                    System.out.println("Hey mein Glückstag ein Messer, jetzt geht's nach Hause, kommt nur her ihr Monster!\n\n");
                    waffe = 1;
                    feld[n][m] = 3;
                    break;

                case 43:
                    System.out.println("Ich werd verrückt ein Baseballschläger, ich schaffe es hier noch  raus!\n\n");
                    waffe = 1;
                    feld[n][m] = 3;
                    break;

                case 101:
                    System.out.println("Jetz aber schnell weiter\n\n");
                    break;

                case 99:
                    System.out.println("Da ist eine Tür....\n...Freiheit! Ich lebe noch");
                    lebe = 1;
                    if (lvl == 3) System.out.println("Glückwunsch du bist der Horde entkommen, für mehr Zombie Abenteuer freu dich auf Zombie Escape 2.0...");
                    break;

                case 112:
                    System.out.println("Gott sei Dank, Verpflegung und Verbandszeug...\n\nIch fühle mich erholt\n\n");
                    plyr[3] = 3;
                    break;

            }
        }

    }
}
