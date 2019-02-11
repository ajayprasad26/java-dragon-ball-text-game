import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.Scanner;
import java.util.Random;

public class test1{
    public static void main(String[] args){

        File bigbang = new File("C:\\Users\\ajay\\Downloads\\Music\\bigbang.wav");
        File finalflash = new File("C:\\Users\\ajay\\Downloads\\Music\\finalflash.wav");
        File finalshine = new File("C:\\Users\\ajay\\Downloads\\Music\\finalshine.wav");
        File galicgun = new File("C:\\Users\\ajay\\Downloads\\Music\\galicgun.wav");
        File senzu = new File("C:\\Users\\ajay\\Downloads\\Music\\smb_powerup.wav");

        // System things
        Scanner uin = new Scanner(System.in);
        Random random = new Random();

        //game things
        String[] enemies = {"GOKU", "GOHAN", "PIKOLO", "NO 17"};
        int maxenemyhealth = 75;
        int maxenemypower = 60;

        // VEGETA things
        int vegetahealth = 100;
        int vegetaattack = 75;
        int beans = 3;
        int beanpower = 30;
        int beandrop = 100;

        boolean running = true;

        System.out.println("    WELCOME TO VEGETA DEFEATS GAME ! ! !");

        GAME:

        while(running){
            System.out.println("==============================================");

            int enemyhealth = random.nextInt(maxenemyhealth);
            String enemy = enemies[random.nextInt(enemies.length)];

            System.out.println("\t****** " + enemy + " has appeared ******\n");

            while(enemyhealth > 0){

                System.out.println("\tVegeta's HP : " + vegetahealth);
                System.out.println("\t" + enemy +" HP : " + enemyhealth);
                System.out.println("\n\tWhat will you do...??");
                System.out.println("\tAttack");
                System.out.println("\tTake a bean");
                System.out.println("\tRun away");

                String val = uin.nextLine();

                if(val.equals("Attack")) {

                    int damagedealt = random.nextInt(vegetaattack);
                    int damagetaken = random.nextInt(maxenemypower);

                    System.out.print("\t Choose an Attack : " );
                    System.out.println("Finalflash, bigbang, galicgun, finalshine");

                    String attackchoosen = uin.nextLine().toLowerCase();

                    if(attackchoosen.equals("finalflash" )){
                                PlaySound(finalflash);

                    }
                    else if(attackchoosen.equals("bigbang")){
                        PlaySound(bigbang);
                    }
                    else if(attackchoosen.equals("galicgun")){
                        PlaySound(galicgun);
                    }
                    else if(attackchoosen.equals("finalshine")){
                        PlaySound(finalshine);
                    }

                    enemyhealth -= damagedealt;
                    vegetahealth -= damagetaken;

                    System.out.println("\t You attacked " + enemy + " for " + damagedealt + " damage");
                    System.out.println("\t Vegeta received " + damagetaken + " in return");

                    if(vegetahealth < 1) {
                        System.out.println("vegeta has low health and cannot continue");
                        break;
                    }

                }

                else if(val.equals("Take a bean")){
                    if(beans > 0){
                        PlaySound(senzu);
                        vegetahealth += beanpower;
                        beans --;
                        System.out.println("\tYou've taken a bean. Your energy increased !!!!"
                                + "\n\t>Vegeta's increased health : " + vegetahealth + "\n\t>Beans left " + beans);
                    }

                    else{
                        System.out.println("No beans remaining");
                    }

                }

                else if (val.equals("Run away")){
                    System.out.println("You ran away from " + enemy);
                    continue GAME;

                }

                else{
                    System.out.println("invalid command. Try again.");

                }


            }

            if(vegetahealth < 1){
                System.out.println("Vegeta died");
                break ;
            }

            System.out.println("==============================================");
            System.out.println(" > Vegeta defeated " + enemy );
            System.out.println(" > Vegeta health : " + vegetahealth);

            if(random.nextInt(100) < beandrop){
                beans++;
                System.out.println("Vegeta collected a bean from enemy ");
                System.out.println("Beans you have : " + beans);
            }
            System.out.println("==============================================");
            System.out.println("ready to fight again......??");
            System.out.println("Yes or no...??");

            String endin = uin.nextLine();

            while(!endin.equals("Yes")  && !endin.equals("no")){
                System.out.println("Invalid command");
                System.out.println("Try again");

                endin = uin.nextLine();

                if(endin.equals("Yes")){
                    System.out.println("keep fighting");
                }
                else {
                    System.out.println("GOOD FIGHTING");
                    break GAME;
                }
            }

        }


        System.out.println("######################################");
        System.out.println("======================================");
        System.out.println("       THANKS YOU FOR PALYING         ");
        System.out.println("######################################");
        System.out.println("======================================");

    }

    public static void PlaySound(File Sound){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();

            try {
                Thread.sleep(clip.getMicrosecondLength()/1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}