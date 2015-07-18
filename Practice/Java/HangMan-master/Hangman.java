/**********************************************************************
*Programmer: Kyle Lehtinen        CSC110 Hangman
*Date: March 27, 2015
*Description:
* A class that has all variable and methods needed to play a game of hangman
* using the word "Happiness"
**********************************************************************/
import java.util.Scanner;

public class Hangman {
    private String secretWord;
    private String disguisedWord;
    private String wrongLetters;
    private int totalGuesses;
    private int wrongGuesses;
        
/**********************************************************************
*Method: initialize
*Description: Initializes the secretWord, disguisedWord, wrongLetters totalGuesses, 
* and wrongGuesses variables for the game
*Input: The secret word to be used for the hangman game as String
*Output: None
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set secretWord equal to passed String parameter
*   Set disguisedWord equal to an empty string
*   For integer i being 0, while less than the length of secretWord, increase i by one and 
*    do the following
*   Begin
*       Reset disguisedWord equal to disquisedWord plus a '?'
*   End
*   Set wrongLetters equal to an empty string
*   Set totalGuesses equal to zero
*   Set wrongGuesses equal to zero
* End
**********************************************************************/
    public void initialize(String givenSecretWord){
        secretWord = givenSecretWord.toLowerCase();
        
        disguisedWord = "";
        for (int i = 0; i < secretWord.length(); i++){
            disguisedWord = disguisedWord + "?";
        }
        
        wrongLetters = "";
        totalGuesses = 0;
        wrongGuesses = 0;
    }
    
/**********************************************************************
*Method: makeGuess
*Description: Method that takes a given character from the user, updates totalGuesses 
* and/or wrongGuesses variables accordingly, and updates disguisedWord variable if the
* given letter matches a letter used in the secretWord
*Input: letter guess, type String
*Output: disguisedWord is updated accordingly, and a message is displayed if more than 
* one character is entered as a guess.
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Set newDisguisedWord equal to blank
*   Set nextIndext equal to zero
*   If guess is more than one letter
*   Begin
*       Display "Sorry, bad guess. Need a single letter."
*       Increase totalGuesses by 1
*       Increase wrongGuesses by 1
*   End
*   Else if guess is part of secretWord and not part of the disguisedWord
*   Begin
*      if the last occurrence of guess occurs as the first letter
*      Begin
*         set disguisedWord equal to guess plus remainder of disguisedWord after index 0
*      End
*      Else
*      Begin
*          Set temp equal to secretWord
*          Do the following while nextIndex is greater or equal to zero
*          Begin
*              Reset nextIndex equal to index of guess in temp
*              If newDisguisedWord is empty
*              Begin
*                 If guess occurs as the first letter of secretWord
*                 Begin
*                    Reset newDisguisedWord equal to guess
*                    Reset temp equal to rest of secretWord after the first letter
*                 End
*                 Else
*                 Begin
*                     Reset newDisguisedWord equal to disguisedWord from first letter to 
*                       same number of letter places as the next occurrence of guess in secretWord
*                       starting at the beginning of secretWord plus guess
*                     Reset temp equal to substring of secretWord starting at the first occurrence
*                       of guess plus 1
*                 End
*              End
*              Else if nextIndex is greater or equal to 0
*              Begin
*                  Reset tempDisguisedWord equal to substring of disguisedWord starting
*                   at the index equal to the length of newDisguisedWord
*                  Reset newDisguisedWord equal to newDisguisedWord plus substring of 
*                   tempDisguisedWord from beginning to index where guess occurs in temp
*                   plus guess
*                  Reset temp equal to substring of temp starting at the index where guess
*                   occurs plus 1
*              End
*              Else
*              Begin
*                  Reset newDisguisedWord equal to newDisguisedWord plus substring of 
*                   disguisedWord starting at the index equal to the length of newDisguisedWord
*              End
*          End
*          End do loop
*          Reset disguisedWord equal to newDisguisedWord
*      End
*      Increase totalGuesses by 1
*   End
*   Else
*   Begin
*       Increase totalGuesses by 1
*       Increase wrongGuesses by 1
*       Add guess to wrongLetters and resort alphabetically
*   End
* End
**********************************************************************/
    private void makeGuess(String guess){
        String temp;
        String newDisguisedWord = "";
        String tempDisguisedWord;
        int nextIndex = 0;
        
        if (guess.length() > 1){
            System.out.println("Sorry, bad guess. Need a single letter.");
        } else if (secretWord.indexOf(guess) >= 0 && disguisedWord.indexOf(guess) < 0){
            if (secretWord.lastIndexOf(guess) == 0){
                disguisedWord = guess + disguisedWord.substring(1);
            } else {
                temp = secretWord;
                do{
                    nextIndex = temp.indexOf(guess);
                    if (newDisguisedWord.isEmpty()){
                        if (secretWord.indexOf(guess) == 0){
                            newDisguisedWord = guess;
                            temp = secretWord.substring(1);
                        } else {
                            newDisguisedWord = disguisedWord.substring(0,secretWord.indexOf(guess))
                                    + guess;
                            temp = secretWord.substring(secretWord.indexOf(guess) + 1);
                        }
                    } else if (nextIndex >= 0) {
                        tempDisguisedWord = disguisedWord.substring(newDisguisedWord.length());
                        newDisguisedWord = newDisguisedWord + tempDisguisedWord.substring(0,temp.indexOf(guess)) 
                                + guess;
                        temp = temp.substring(temp.indexOf(guess) + 1);
                    } else {
                        newDisguisedWord = newDisguisedWord 
                           + disguisedWord.substring(newDisguisedWord.length());
                    }
                } while (nextIndex  >= 0);
                disguisedWord = newDisguisedWord;
            }
            totalGuesses++;
        } else {
            totalGuesses++;
            wrongGuesses++;
            sortWrongLetters(guess);
        }
    }
    
/**********************************************************************
*Method: getSecretWord
*Description: Return value of secretWord variable
*Input: none
*Output: secretWord as String
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Return secretWord
* End
**********************************************************************/
    public String getSecretWord(){
        return secretWord;
    }

/**********************************************************************
*Method: getDisguisedWord
*Description: Return value of disguisedWord variable
*Input: none
*Output: disguisedWord as String
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   Return disguisedWord
* End
**********************************************************************/
    public String getDisguisedWord(){
        return disguisedWord;
    }
    
/**********************************************************************
*Method: isFound
*Description: Checks if the disguised word has been guessed by checking disguised 
* word against secret word for equality.
*Input: none
*Output: true for match, false for no match
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   If disguisedWord is equal to secretWord returns true
*   else returns false
*   End if
* End
**********************************************************************/
    private boolean isFound(){
        if (disguisedWord.equals(secretWord))
            return true;
        else
            return false;
    }
    
/**********************************************************************
*Method: playGame
*Description: Controls the hangman game logic
*Input: User enters a letter stored in guess as type String
*Output: The values of disguisedWord, totalGuesses, wrongGuesses, and wrongLetters is shown
* along with messages to the user to guide the flow of the game.
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   While the result of isFound is not true
*   Begin
*       Display "The disguised word is " + getDisguisedWord
*       Prompt user for and set guess equal to user specified value
*       Run makeGuess method with guess as the parameter
*       Display "Guesses made " + totalGuesses + " with " + wrongeGuesses + " wrong "
*           + (" + wrongLetters + ")"
*   End while loop
*   Display "Congratulations, you found the secret word: " + getSecretWord
*   Display "Thanks for playing Hangman."
* End
**********************************************************************/
    public void playGame(){
        String guess;
        Scanner keyb = new Scanner(System.in);
        
        while (!isFound()){
            System.out.println("\nThe disguised word is <" + getDisguisedWord() + ">");
            System.out.print("Guess a letter: ");
            guess = keyb.next();
            while (disguisedWord.indexOf(guess) >= 0 || wrongLetters.indexOf(guess) >= 0){
                System.out.println("Already guessed that one, try again.");
                System.out.print("Guess a letter: ");
                guess = keyb.next();
            }
            makeGuess(guess);
            System.out.println("Guesses made " + totalGuesses + " with " + 
                    wrongGuesses + " wrong (" + wrongLetters + ")");
        }
        System.out.println("Congratulations, you found the secret word: " + getSecretWord());
        System.out.print("\nThanks for playing Hangman.");
    }

/**********************************************************************
*Method: sortWrongLetters
*Description: Rebuilds the wrongLetters string to incorporate new letters in 
* and maintain alphabetical order
*Input: new letter to be added, type string
*Output: the wrongLetters string is resorted alphabetically
* --------------------------------------------------------------
* Pseudocode for this solution:
* Begin
*   if length of wrongLetters is equal to zero
*   Begin
*       Set wrongLetters equal to value of newLetter
*   End
*   if the new letter is less than the first letter in wrongLetters
*   Begin
*       Reset wrongLetters to newLetter + wrongLetters
*   End
*   Else if new newLetter is greater than the last letter in wrongLetters
*   Begin
*       Reset wrongLetters to wrongLetters plus newLetter
*   End
*   Else
*   Begin
*       For int i being 1, j being i + 1, and j is less than length of wrongLetters, increase i and j by 1
*       Begin
*           If newLetter is not greater than substring of wrongLetters between i and j
*           Begin
*               reset wrongLetters equal to substring of wrongLetters from index 0 to i
*                   + newLetter + subString of wrongLetters from index j to end
*           End
*       End
*   End
*   End if conditions
* End
**********************************************************************/
    public void sortWrongLetters(String newLetter){
        String temp = "";

        if (wrongLetters.length() == 0){
            wrongLetters = newLetter;
        } else if (newLetter.compareTo(wrongLetters.substring(0, 1)) < 0){
            wrongLetters = newLetter + wrongLetters;
        } else if (newLetter.compareTo(wrongLetters.substring(wrongLetters.length() - 1)) > 0){
            wrongLetters = wrongLetters + newLetter;
        } else {
            for (int i = 1, j = 2; i < wrongLetters.length(); i++, j++){
                if (!(newLetter.compareTo(wrongLetters.substring(i,j)) > 0)){
                    temp = wrongLetters.substring(0,i - 1) + newLetter + wrongLetters.substring(i - 1);
                }
            }
            wrongLetters = temp;
        }
    }

    public static void main(String[] args) {
        Hangman game = new Hangman();
        game.initialize("Happiness");
        System.out.println("Lets play a round of hangman.");
        game.playGame();
    }
}
