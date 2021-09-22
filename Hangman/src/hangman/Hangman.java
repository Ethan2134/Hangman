package hangman;

import java.util.Random;
import java.util.Scanner;


public class Hangman {
    static int wrong = 0;
    static boolean play = false;
    static boolean guessed = false;
    //creates the word in asterisk form
    public static char[] buildArray(String s){
        char[] c = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            c[i] = '*';
        }
        return c;
    }
    //return a random word to use for the game
    public static String getWord(String[] a){
        Random rand = new Random();
        int n = rand.nextInt(a.length-1);
        return a[n];
    }
    //checks if the letter is contained or guessed
    public static void checkWord(String s, char[] c, String b){
        int check = 0;
        for (int i = 0; i < c.length; i++) {
            if(s.toLowerCase().charAt(0) == c[i]){
                System.out.println("You have already guessed that letter!");
                return;
            }
            else if(s.toLowerCase().charAt(0) == b.charAt(i)){
                c[i] = b.charAt(i);
                check++;
            }
        }
        if(check == 0)
            wrong++;
    }
    //add the next body part
    public static void buildHangman(int guess, char[] c){
        int g = guess;
        for (int i = 0; i < 8; i++) {
            System.out.print("|");
            if(i == 1)
                System.out.print("______");
            if(i == 2 && g >= 1)
                System.out.print("     |");
            if(i == 3 && g >= 2)
                System.out.print("     O");
            if(i == 4){
                if(g == 3)
                    System.out.print("     |");
                if(g == 4){
                    System.out.print("    \\|");
                }
                if(g > 4)
                    System.out.print("    \\|/");
            }
            if(i == 5 && g >= 6)
                System.out.print("     |");
            if(i == 6){
                if(g == 7)
                    System.out.print("    /");
                if(g > 7)
                    System.out.print("    / \\");
            }
            if(i == 7)
                System.out.print("__________");

            System.out.println("");
        }
        System.out.println("Secret Word: ");
        for (int i = 0; i < c.length; i++) 
            System.out.print(c[i]);
        System.out.println("");
        if(wrong == 8){
            play = false;
        }
    }
    //check if they got the word
    public static void isGuessed(char[] c){
        int asterisk = 0;
        for (int i = 0; i < c.length; i++) {
            if(c[i] == '*')
                asterisk++;
        }
        if(asterisk == 0)
            guessed = true;
    }
    //main loop
    public static void gameLoop(){
        Scanner in = new Scanner(System.in);
        //ADD NEW WORDS HERE
        String a[] = {"technology", "variable", "square", "program", "documentation", "methods", "character", "poison", "basketball", "sources"};
        
        String s = getWord(a);
        char[] c = buildArray(s);
        System.out.println("Welcome to Hangman!");
        System.out.println("Typing in more than one letter results in the first letter being used as your guess.\nYou have 8 attempts to guess the word.");
        play = true;
        guessed = false;
        wrong = 0;
        buildHangman(wrong,c);
        while(play && !guessed){
            String guess = in.next();
            checkWord(guess,c,s);
            isGuessed(c);
            buildHangman(wrong,c);
        }
        buildHangman(wrong,c);
        play = false;
        if(guessed)
            System.out.println("You correctly guessed the word '"+s+"' with "+(8-wrong)+" trie(s) remaining!");
        else
            System.out.println("The correct word was: "+s); 
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int choice = 1;
        while(!play && choice == 1){
            gameLoop();
            System.out.println("Would you like to play again?");
            System.out.println("1: Yes\n2: No");
            choice = in.nextInt();
        }
        System.out.println("Thanks for playing!\nGoodbye."); 
    }
}
