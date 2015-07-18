//Kyle Lehtinen - Program 4 - An application that plays the game "Go Fish!"
//and also provides a testing environment to manipulate digital playing cards

import java.util.*;

class card{
    private int _value;
    private int _suit;
    private static String[] convSuite = {"Hearts","Spades","Diamonds","Clubs"};
    private static String[] convValue ={
        "Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine",
        "Ten","Jack","Queen","King"
    };
    
    public card(int value, int suit){
        _value = value;
        _suit = suit;
    }
    
    public int getSuit(){
        return _suit;
    }
    
    public int getValue(){
        return _value;
    }
    
    public String toString(){
        return convValue[_value] + " of " + convSuite[_suit];  
    }
}

class deckHand{
    private card[] _deck;
    private int _size;
    private static Random randNumber = new Random();
    private final int  MAX_SIZE = 52;
    
    public deckHand(){
        _deck = new card[MAX_SIZE]; 
        _size = 0;
    }   
    
    public void addCard(card newCard){
 
        if (_size >= _deck.length){
            card[] temp = new card[_deck.length + MAX_SIZE];
            for (int ix = 0; ix < _size; ix++){
                temp[ix] = _deck[ix];
            }
            _deck = temp;
        }
        _deck[_size] = newCard;
        _size++;
    }
    
    public card deleteCard(int value){
        card result;
        for(int ix = 0; ix < _size; ix++){
            if(_deck[ix].getValue() == value){
                result = _deck[ix];
                _deck[ix] = _deck[_size-1];
                _size--;
                return result;
            }   
        }
        return null;
    }
    
    public card deletAnyCard(){
        card result;
        int thisRand = 0;
        
        if (_size == 0) return null;
        
        if (_size == 1){
            thisRand = randNumber.nextInt(_size);
        }
        else{
            thisRand = randNumber.nextInt(_size-1);
        }
        result = _deck[thisRand];
        _deck[thisRand] = _deck[_size-1];
        _size--;
        
        return result;
    }
    
    public int getCount(int value){
        int thisCount = 0;
        
        for(int ix = 0; ix < _size; ix++){
            if(_deck[ix].getValue() == value){
                thisCount++;    
            }
        }
        return thisCount;
    }
    
    public int getSize(){
        return _size;
    }
    
    public String toString(){
        String result;
        
        result = "\n--Top--\n"; 
        for(int ix = 0; ix <= _size - 1; ix++){
            result = result + _deck[ix]+"\n";
        }
        result = result + "--Bottom--\n";
        return result;
    }
    
}
public class program4 {

    public static Scanner keyb = new Scanner(System.in);
    
    public static void main (String[] args){
        int mainSelect;
        
        System.out.print("---Tinker with playing cards or play Go Fish!---\n");
        
        do{
            mainSelect = displayMainMenu();

            if (mainSelect == 1)
                testApp();
            else if (mainSelect == 2)
                playGoFish(); 
        } while (mainSelect != 3);
        
        System.out.print("\nThank you for using this application!");
    }
    
    public static void playGoFish(){
        int nextMove,playerMoveCheck,cardsFound, bookCheck, turnValue;
        int playerBooks = 0;
        int computerBooks = 0;
        String playerName;
        boolean playerTurn, cardFound, gameOver;
        card compCard = null, stockCard = null, tradeCard = null;

        deckHand stock = makeDeck();
        deckHand player = new deckHand();
        deckHand computer = new deckHand();
        
        //deal player hands, starting with computer
        for (int ix = 0; ix < 7; ix++){
            computer.addCard(stock.deletAnyCard());
            player.addCard(stock.deletAnyCard());
        }
        
        System.out.print("\nHello Player! Well, maybe your name isn't player..."
                + "\nHow about you enter your name below?\nName: ");
        playerName = keyb.next();
        System.out.print("\nGreat to meet you "+playerName+"! My name is "
                + "Computer. Let's play Go Fish!");
        
        //begin main game loop
        gameOver = false;
        playerTurn = true;
        do{ //check for gameover condition
            if (player.getSize() == 0 || computer.getSize() == 0 || 
                    stock.getSize() == 0){
                gameOver = true;
            } else {
            //Player turn segment
            System.out.print("\nHere are your cards:"+player);
            System.out.print("\n"+playerName.toUpperCase()+": Computer,"
                    + " do you have any...\nEnter the card rank: ");
            nextMove = keyb.nextInt();
            playerMoveCheck = player.getCount(nextMove - 1);
            while (nextMove <= 0 && nextMove > 13 || playerMoveCheck == 0){
                System.out.print("\nThat is not a valid card in your hand, "
                        + "please try again: ");
                nextMove = keyb.nextInt();
                playerMoveCheck = player.getCount(nextMove - 1);
            }
            cardsFound = computer.getCount(nextMove - 1);
            //players requested value is found
            if (cardsFound > 0){
                for(int ix = 0; ix < cardsFound; ix++){
                    tradeCard = computer.deleteCard(nextMove - 1);
                    player.addCard(tradeCard);  
                } 
                //check for new books
                if(GFBookCheck(player,computer,playerTurn,tradeCard)){
                        player = GFBookChange(player,computer,playerTurn,
                                tradeCard);
                        playerBooks++;
                        System.out.print("\n*You got a new book! You now have "
                            +playerBooks+" books!*");
                    }
                System.out.print("\nCOMPUTER: Yeah, here you go...\n");
            } 
            //players requested value is not found
            else if (cardsFound == 0){ 
                System.out.print("\nCOMPUTER: Go fish!");
                stockCard = stock.deletAnyCard();
                player.addCard(stockCard);
                //check for new player books
                if(GFBookCheck(player,computer,playerTurn,stockCard)){
                    player = GFBookChange(player,computer,playerTurn,
                            stockCard);
                    playerBooks++;
                    System.out.print("\n*You got a new book! You now have "
                        +playerBooks+" books!*");
                } 
                //check for turn continue by drawing requested card from stock
                if(stockCard.getValue() == (nextMove - 1)){
                    System.out.print("\n"+playerName+": Oh! I got the card I "
                            + "was looking for! I get to go again!\n");
                } 
                else{ 
                    //begin computer turn segment
                    playerTurn = false;
                    System.out.print("\n*You have drawn a new card. Here is your"
                            + " hand*\n"+player);
                    holdScreen();
                    System.out.print("\nCOMPUTER: My turn!");
                    do{ 
                        //enter computer turn loop
                        if (player.getSize() == 0 || computer.getSize() == 0 ||
                                stock.getSize() == 0){
                            gameOver = true;
                            playerTurn = true;
                        } else {
                        compCard = computer.deletAnyCard();
                        computer.addCard(compCard);
                        System.out.print("\nCOMPUTER: Hmmm... do you "
                                + "have any "+(compCard.getValue()+1)+"'s?");
                        cardsFound = player.getCount(compCard.getValue());
                        //computer requested value is found
                        if (cardsFound > 0){ 
                            System.out.print("\n"+playerName.toUpperCase()+": Yes, "
                                    + "here you go...\n*Card(s) are exchanged*\n");
                            holdScreen();
                            for(int ix = 0; ix < cardsFound; ix++){
                                computer.addCard(player.deleteCard(compCard.getValue()));
                            } 
                            //check for new computer books
                            if(GFBookCheck(player,computer,playerTurn,compCard)){
                                computer = GFBookChange(player,computer,playerTurn,
                                compCard);
                                computerBooks++;
                                System.out.print("\nCOMPUTER: I got a book! I "
                                        + "now have "+computerBooks+" book(s)!\n");
                            }
                        }
                        else{ 
                            //computer requested value not found
                            System.out.print("\n"+playerName.toUpperCase()+": "
                                    + "Go fish Computer.\nCOMPUTER: Okay...\n");
                            stockCard = stock.deletAnyCard();
                            computer.addCard(stockCard);
                            //check for new books
                            if(GFBookCheck(player,computer,playerTurn,stockCard)){
                                computer = GFBookChange(player,computer,playerTurn,
                                stockCard);
                                computerBooks++;
                                System.out.print("\nCOMPUTER: I got a book! I "
                                        + "now have "+computerBooks+" book(s)!\n");
                            } 
                            //check for match from stock
                            if(stockCard.getValue() == compCard.getValue()){
                                System.out.print("\nCOMPUTER: Awesome! I drew "
                                        + "the card I wanted! I go again...\n");
                            }
                            else{ //exist computer turn segment
                                playerTurn = true;
                            }
                        }
                    } 
                }while(!playerTurn);
             }
           } 
         }
        }while(!gameOver);
        
        //game results
        System.out.print("\nCOMPUTER: Welp looks like the game is over! Let's see"
                + " who won...\nCOMPUTER: I had "+computerBooks+" books! You had"
                + "..."+playerBooks+"!");
        if(playerBooks>computerBooks)
            System.out.print("\nCOMPUTER: You won "+playerName+"! It was a great"
                    + " game. Lets play again sometime!\n\n");
        else if (computerBooks > playerBooks)
            System.out.print("\nCOMPUTER: I won!\nCOMPUTER: It was a great game. "
                    + "Come play again if you want, okay?\n\n");
        else if (computerBooks == playerBooks)
            System.out.print("\nCOMPUTER: Wow! We both won!\nCOMPUTER: It was a "
                    + "great game. Come play again if you want, okay?\n\n");
    }  

    public static void testApp() {
        int iChooseFunc, iChooseNext, iSelected, iGivenValue, iGivenSuit;
        String sCurrentDeck;
        deckHand adjunct;
        
        deckHand deck = makeDeck();
        deckHand hand = new deckHand();
        
        //Main testing loop
        displayTestMenu();
        iChooseNext = keyb.nextInt();
        while (iChooseNext != 3){
            
            if(iChooseNext == 1){
                sCurrentDeck = "full";
                adjunct = deck;
            }
            else{
                sCurrentDeck = "empty";
                adjunct = hand;
            }
          
            //deckhand function manipulation loop
            do{
                iChooseFunc = displayTestFuncMenu(sCurrentDeck);
                if(iChooseFunc == 1){
                    if(adjunct == deck){
                        deckHand temp = new deckHand();
                         for(int ix = 0; ix < 4; ix++){
                            for(int dx = 0; dx < 13; dx++){
                                card tempCard = new card(dx,ix);
                                temp.addCard(tempCard);
                            }
                        }
                        deck = temp;
                        adjunct = deck;
                    }
                    else if(adjunct == hand){
                        deckHand temp = new deckHand();
                        hand = temp;
                        adjunct = hand;
                    }  
                    System.out.print("\nTask Complete!\n");
                }
                else if (iChooseFunc == 2){
                    System.out.print("\nPlease enter the value of the card: ");
                    iGivenValue = keyb.nextInt();
                    System.out.print("\nPlease enter the suit of the card: ");
                    iGivenSuit = keyb.nextInt();
                    card newCard = new card(iGivenValue,iGivenSuit);
                    adjunct.addCard(newCard);
                    System.out.print("\nTask Complete!\n");
                }
                else if (iChooseFunc == 3){
                    System.out.print("\nPlease enter the value of the card"
                            + " to be deleted: ");
                    iGivenValue = keyb.nextInt();
                    System.out.print("\nCard deleted:\n"
                            +adjunct.deleteCard(iGivenValue)+"\n");
                    System.out.print("\nTask Complete!\n");
                }
                else if (iChooseFunc == 4){
                    System.out.print("\nCard deleted:\n"
                            +adjunct.deletAnyCard()+"\nTask Complete!\n");
                }
                else if (iChooseFunc == 5){
                    System.out.print("\nPlease enter the value of the card: ");
                    iGivenValue = keyb.nextInt();
                    System.out.print("\nThere are "+adjunct.getCount(iGivenValue)
                           +" cards with that value.\n");
                }
                else if (iChooseFunc == 6){
                    System.out.print("\nThe current deck size is "+
                            adjunct.getSize()+"\n");
                }
                else if (iChooseFunc == 7){
                    System.out.print(adjunct+"\n");
                }        
            }while (iChooseFunc != 8);
            
            displayTestMenu();
            iChooseNext = keyb.nextInt();
        }

        System.out.print("\nThank you for testing this application!");
    }
    
    //initialize full deck and load
    public static deckHand makeDeck(){
        deckHand newDraw = new deckHand();
        for(int ix = 0; ix < 4; ix++){
            for(int dx = 0; dx < 13; dx++){
                card tempCard = new card(dx,ix);
                newDraw.addCard(tempCard);
            }
        } 
        return newDraw;
    }  
    
    public static int displayMainMenu(){
        int select;
        System.out.print("Please make a selection from one of the options below"
                + ".\n1. Test the DeckHand.\n2. Play Go Fish!\n3. Quit.\n"
                + "Selection: ");
        select = keyb.nextInt();
        return select;
    }
    
    public static void displayTestMenu(){
        System.out.print("\nFor this test two decks have been created. One"
                    + " full deck with 52 cards, and one empty deck with \nno"
                    + " cards.\nPlease choose which deck you would like to test:"
                    + "\n1. The full deck.\n2. The empty deck.\n3. Go back.\n"
                    + "Selection: ");
    }
    
    public static int displayTestFuncMenu(String selected){
        int selection;
        System.out.print("\nYou are currently editing the "+selected+" deck."
                + "\nPlease choose an operation from the choices below:\n1. "
                + "Re-construct the deck.\n2. Insert a given card.\n3. Delete a "
                + "given card.\n4. Delete a random card.\n5. Count up the number"
                + " of time a given card occurs in the deck.\n6. Return the "
                + "current number of cards in the deck.\n7. List the cards "
                + "currently in the deck.\n8. Go back.\nSelection: ");
        selection = keyb.nextInt();
        return selection;
    }
    
    public static void holdScreen(){
        char hold;
        System.out.print("\nType a letter and press enter to continue: ");
        hold = keyb.next().charAt(0);
    }
    
    public static int GFplayerSelect(){
        int GFselect;
        System.out.print("\nPlease make your next selection from the options "
                + "below.\n1. Choose next card to fish for.\n2. Quit game.\n"
                + "Selection: ");
        GFselect = keyb.nextInt();
        return GFselect;
    }
    
    public static boolean GFBookCheck(deckHand player, deckHand computer, 
            boolean playerTurn, card newCard){
        boolean result;
        
        if(playerTurn){
            if(player.getCount(newCard.getValue()) == 4)
                result = true;
            else
                result = false;
        }
        else{
            if(computer.getCount(newCard.getValue()) == 4)
                result = true;
            else
                result = false;
        }
        return result;
    }
    
    public static deckHand GFBookChange(deckHand player, deckHand computer, 
            boolean playerTurn, card newCard){
        if (playerTurn){
            for(int dx = 0; dx < 4; dx++){
                player.deleteCard(newCard.getValue());
            } 
            return player;
        }
        else{
            for(int dx = 0; dx < 4; dx++){
                computer.deleteCard(newCard.getValue());
            } 
            return computer; 
        }
    }
}
