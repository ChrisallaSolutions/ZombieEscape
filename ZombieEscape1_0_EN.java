package myfirstcode;

import java.security.SecureRandom;
import java.util.Scanner;

public class ZombieEscape1_0 {

    public static void main(String[] args) {
        int alt = 0; // Alternatives counter 
        int i = 0; // Array counter
        int j = 0; // Array counter
        int n = 0; // Starting point
        int m = 0; // Starting point
        int y = 0; // Exit   
        int z = 0; // Exit
        int s = 0; // Current position value 
        int h = 0; // Healing position
        int l = 0; // Healing position
        int live = 0; // Exit switch
        int lvl = 0; // Level selection
        int xy = 0; // Level size
        int zed = 0; //Zombiefrequency with Random
        int zomb[] = {2, 3, 6, 1, 3}; // CQC,Strength,Endurance,Damage Capacity, Courage/Horror
        int zhp = 0; // Zombie life counter
        int plyr[] = {3, 3, 3, 3, 3}; // CQC,Strength,Endurance,Damage Capacity, Courage/Horror
        int dplyr = 0; // Player dice roll
        int dzed = 0; // Zombie dice roll
        int weapon = 0; // Weapon available?
        int w = 0; // Number of weapons to find
        int waf = 0; // Weapons position 
        int fe = 0; // Weapons position 
        int cqc = 0; // CQC var
        int str = 0; // Strength var
        String fight = "";

        SecureRandom rand = new SecureRandom();
        Scanner insert = new Scanner(System.in);
        String act = "";

        do {
            System.out.println("Please choose difficulty:\n"
                    + "1: 8 x 8 sized room\n2: 16 x 16 sized room\n3: 24 x 24 sized room");
            try {
            	lvl = insert.nextInt();
            } catch (Exception e) {
            	System.out.println("Please only choose from the given numbers");
            	insert = new Scanner(System.in);
            	lvl = insert.nextInt();
            } 
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

        int[][] field = new int[xy][xy]; // Fill up the field

        while (j < xy) {
            i = 0;
            for (; i < xy; i++) {

                field[j][i] = rand.nextInt(zed) + 1;
                //System.out.print(field[j][i]);
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

        //exit
        y = rand.nextInt(xy) + 0;
        z = rand.nextInt(xy) + 0;
        field[y][z] = 99;

        if (lvl == 3) {
            // healing
            h = rand.nextInt(xy) + 0;
            l = rand.nextInt(xy) + 0;
            field[h][l] = 112;
        }

        //weapons
        for (int k = 0; k < w / 2; k++) {

            waf = rand.nextInt(xy) + 0;
            fe = rand.nextInt(xy) + 0;
            field[waf][fe] = 42;

            waf = rand.nextInt(xy) + 0;
            fe = rand.nextInt(xy) + 0;
            field[waf][fe] = 43;
        }
        /* i = 0; //check field
        j = 0;
        while (j < xy) {
            i = 0;
            for (; i < xy; i++) {

                System.out.print(field[j][i] + "\t");
            }
            j++;
            System.out.println("\n ");
        }
         */
        System.out.println("You wake up dazed in a huge dark room with very bad light conditions,"
                + "\nyou can see shadows moving all around you. The constant sound of moaning and the occassional\n"
                + "screams of a fight for life and death makes your blood freeze. You want to get out!!! \n\n");

        while (live == 0) {

           /* i = 0; //check field
            j = 0;
            while (j < xy) {
                i = 0;
                for (; i < xy; i++) {

                    System.out.print(field[j][i] + "\t");
                }
                j++;
                System.out.println("\n ");
            }*/

            do {
                System.out.println("Where do you want to go, to the North(N), South(S), West(W) or East(E)?\n\n");
                act = insert.next();
            } while (!act.equalsIgnoreCase("N") && !act.equalsIgnoreCase("S") && !act.equalsIgnoreCase("W")  && !act.equalsIgnoreCase("E"));

            if (act.equalsIgnoreCase("N")) {
                n = n - 1;
                if (n < 0) {
                    System.out.println("Oh no! There's a wall, go another way\n\n");
                    n = n + 1;
                    s = 101;

                } else {
                    s = field[n][m];
                }
            } else if (act.equalsIgnoreCase("S")) {
                n = n + 1;
                if (n > xy - 1) {
                    System.out.println("Damn! There's a wall, go another way\n\n");
                    n = n - 1;
                    s = 101;

                } else {
                    s = field[n][m];
                }
            } else if (act.equalsIgnoreCase("W")) {
                m = m - 1;
                if (m < 0) {
                    System.out.println("Ouch! There's a wall, go another way\n\n");
                    m = m + 1;
                    s = 101;
                } else {
                    s = field[n][m];
                }

            } else {
                m = m + 1;
                if (m > xy - 1) {
                    System.out.println("Shoot! There's a wall, go another way\n\n");
                    m = m - 1;
                    s = 101;

                } else {
                    s = field[n][m];
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
                    //System.out.println("Life: " +plyr[3]);

                    switch (alt) {
                        case 1:
                            System.out.println("Aaahh I think I'm having a heart attack!!");
                            break;

                        case 2:
                            System.out.println("Don't always scare me like that!");
                            break;

                        case 3:
                            System.out.println("Dammit again!!");
                            break;

                        case 4:
                            System.out.println("Oh boy are you ugly!");
                            break;

                        case 5:
                            System.out.println("Oh another one!");
                            break;

                        case 6:
                            System.out.println("Come here I'll end you");
                            break;

                        case 7:
                            System.out.println("Finally I was getting bored");
                            break;

                        default:
                            System.out.println("Wait come on I'll put you out of your misery!");
                            break;
                    }
                    dplyr = rand.nextInt(6) + 1;
                    dzed = rand.nextInt(6) + 1;
                    fight = "y";

                    if (plyr[4] + dplyr >= zomb[4] + dzed) { //Horrortest
                        System.out.println("No problem, I can get around this one");
                        System.out.println("Should I fight it (y/n)\n\n");

                        do {
                            fight = insert.next();
                        } while (!fight.equalsIgnoreCase("y") && !fight.equalsIgnoreCase("n"));

                        if (fight.equalsIgnoreCase("n")) {
                            break;
                        }
                    }

                    while (fight.equalsIgnoreCase("y")) {
                        cqc = plyr[0];
                        str = plyr[1];
                        zhp = zomb[3];
                        if (weapon == 1) {
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
                                    field[n][m] = 3;
                                    fight = "n";
                                    switch (alt) {
                                        case 1:
                                            System.out.println("Oh my god, I made it!!\n\n");
                                            break;

                                        case 2:
                                            System.out.println("Take this zombie!\n\n");
                                            break;

                                        case 3:
                                            System.out.println("This is too easy...\n\n");
                                            break;

                                        default:
                                            System.out.println("...and dead!\n\n");
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
                                        live = 1;
                                        System.out.println("While you drop down to the floor totally exhausted, your blood is squirting out of you and you slowly "
                                                + "fade to black, \nyou realize how the zombie starts to gnaw on you...\n\nYou are dead!!");
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
                            System.out.println("Everything is okay head on to the exit...\n\n");
                            break;

                        case 2:
                            System.out.println("The exit should come any minute...\n\n");
                            break;

                        case 3:
                            System.out.println("What was that? Stay away from me...\n\n");
                            break;

                        default:
                            System.out.println("Is that a light back there??? ...\n\n");
                            break;
                    }
                    break;
                case 3:
                    switch (alt) {

                        case 1:
                            System.out.println("Is that a light back there??? ...\\n\n");
                            break;

                        case 2:
                            System.out.println("Everything is okay head on to the exit...\n\n");
                            break;

                        case 3:
                            System.out.println("The exit should come any minute...\n\n");
                            break;

                        default:
                            System.out.println("What was that? Stay away from me...\n\n");
                            break;
                    }
                    break;
                case 4:
                    switch (alt) {

                        case 1:
                            System.out.println("What was that? Stay away from me...\n\n");
                            break;

                        case 2:
                            System.out.println("Is that a light back there??? ...\\n\n");
                            break;

                        case 3:
                            System.out.println("Everything is okay head on to the exit...\n\n");
                            break;

                        default:
                            System.out.println("The exit should come any minute...\n\n");
                            break;
                    }
                    break;
                case 5:
                    switch (alt) {

                        case 1:
                            System.out.println("The exit should come any minute...\n\n");
                            break;

                        case 2:
                            System.out.println("What was that? Stay away from me...\n\n");
                            break;

                        case 3:
                            System.out.println("Is that a light back there??? ...\\n\n");
                            break;

                        default:
                            System.out.println("Everything is okay head on to the exit...\n\n");
                            break;
                    }
                    break;
                case 42:
                    System.out.println("Hey must be my lucky day, a knife, I'm getting home, come on you monsters!\n\n");
                    weapon = 1;
                    field[n][m] = 3;
                    break;

                case 43:
                    System.out.println("I can't believe it, a baseball bat, I can still make it out of here!\n\n");
                    weapon = 1;
                    field[n][m] = 3;
                    break;

                case 101:
                    System.out.println("Now now, hurry on out\n\n");
                    break;

                case 99:
                    System.out.println("There's a door....\n...freedom! I'm still alive!");
                    live = 1;
                    if (lvl == 3) System.out.println("Congratulations you have escaped the horde, for more zombie adventures wait for Zombie Escape 2.0...");
                    break;

                case 112:
                    System.out.println("Thank god, supplies and a first aid kit...\n\nI feel refreshed\n\n");
                    plyr[3] = 3;
                    break;

            }
        }

    }
}
