// Program Header
// Saunders, John
// CISP 401
// 10/3/21
// Word Game


package com.company;

import com.company.models.WordList;
import com.company.views.CmdLineView;
import com.company.controllers.GetWord;

public class Main {

    
    public static String[] letters;
    public static int numLetters = 0;
    public static int numGuesses;
    public static int guessCount;
    public static String theGuess;

    public static String[] hints;
    public static String[] correctLetters;
    public static  CmdLineView view;

    public static void main(String[] args) {

        numGuesses = 6;
        guessCount = 0;


        GetWord getWord = new GetWord();
        String theWord = getWord.getTheWord();

        WordList word = new WordList(theWord);

        //WordList word = new WordList(getWord.getTheWord());

        letters = calculateLetters(word.getTheWord());

        view = new CmdLineView(letters);
        view.startGame();
        view.cheat(word.getTheWord());
        boolean winCheck = false;
//        boolean guessLimit = false;


        System.out.println("Letter Length: " + letters.length + " \n");
        String[] hints = new String[letters.length];
         correctLetters = new String[letters.length];

         // Runs while user has not exceeded guess count or has not finished the word.
        while(guessCount < 3 && winCheck == false){

            correctLetters = hints;
            hints = guess(hints);

            view.displayHints(hints);


//            view.displayHints2(correctLetters);

//            correctLetters = hints;

            //display
//          System.out.println(hints);

            // This if statement checks if the guess is part of the word. If it is incorrect you lose a try.
            // When the user exceeds the limit the game will end.

            if (!(theWord.contains(theGuess))){
                System.out.println("That is incorrect \n");
                guessCount++;
                System.out.println("Incorrect guesses: " + guessCount + " \n");

            }


            if (guessCount > 3){
                System.out.println("You've exceeded your number of guesses. The game is over \n");
                System.exit(0);
            }

            int underScoreCount = 0;
            for (int j = 0; j < letters.length; j++){
                if(hints[j].contains("_")){
//                    System.out.println("Under score count: " + underScoreCount);
                    underScoreCount++;
                }

            }
            if(underScoreCount == 0){
                winCheck = true;
                System.out.println("You Won! \n");
            }
        }
    }

    private static String[] calculateLetters(String theWord){
        String[] letters = theWord.split("");
        numLetters = letters.length;
        return letters;
    }

    private static String[] guess(String[] hints){

//        for(int i = 0; i < letters.length; i++){
//            letterCheck[i] = letters[i];
//            }

        theGuess = view.makeAGuess();

//



        String msg = "";

//        String[] hints = new String[letters.length];
//        String[] correctLetters = new String[letters.length];



        // Does not reset values of hints array every time

        for(int i = 0; i < letters.length; i++){
//            System.out.println("Letter: "+ i + ": " + hints[i]);
            if(letters[i].equals(theGuess)){
                hints[i] = theGuess;

            }

            else if(hints[i] == null) {
                hints[i] = "_";
            }

        }



        return hints;
//        return correctLetters;
    }


}
