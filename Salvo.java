/****************************************
 * Salvo.java
 * 
 * Author: Browning Keith Smith
 * 
 * Last Updated: March 23, 2015
 * 
 * Compilation: javac Salvo.java
 * Execution: java Salvo
 * Requires: java.io.InputStreamReader
 *           java.io.BufferedReader
 *           java.io.IOException
 *           Math
 * 
 ****************************************/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Salvo {
    
    /*******************************************************************
     * void main
     * takes String[] args
     * 
     * Upon starting, welcomes the user.
     * Asks the user if they would prefer a one-player battle
     * or a two-player battle, and executes the desired battle
     * 
     * After the battle has concluded, asks if the user would like to
     * play again
     *******************************************************************/
    public static void main(String[] args) throws IOException {
        
        //Greet the user, and explain how to exit the game
        System.out.println("\n\n\nGreetings, Admiral! Welcome to Naval Combat Simulation SALVO.\n"); //Greet the user.
        
        //Ask the user if they want a one-player game, a two-player game, or to read the instructions. If 'players' is 1, the user wants a one-player
        // game. If players is 2, the user wants a 2-player game. If players is 3, the user wants to read the instructions. This method displays the
        // rules of the game, and then asks again if the user wants a one-player game or a two-player game
        while (true) {
            
            int players = 0;                //Set 'players' to 0. If players is 1, a one player game will start
                                            // if it is 2, a two-player game will start
                                            // if it is 3, the instructions will be displayed
            boolean invalidResponse = true; //Declare 'invalidResponse' and set to true. This will be true as long as the user's response is invalid
            while(invalidResponse) {        //While the user entered an invalidResponse:
                
                invalidResponse = false; //Assume the response is valid to start. set invalidResponse to false.
                
                System.out.println("Type 'one' for a one-player game versus the CPU, or type 'two' for a two-player game, facing an opponent.\n"); //Tell the user how to start a game, and print a blank line.
                System.out.println("For instructions on how to play the game, type 'ins'.\n"); //Tell the user how to display the instructions
                System.out.println("Type 'end' at any time to end the simulation.\n");         //remind the user how to exit the simulation
                String response = prompt("Would you prefer a one-player game or a two-player game?"); //Declare 'response' and ask the user what game they want to play
                
                //Interpret response:
                int doubleEntry = 0; //Declare doubleEntry, and set to 0. If doubleEntry is 2 by the end of the interpretation, invalidResponse is set to true
                
                if (response.contains("one") || response.contains("One") || response.contains("ONE")) { //if 'response' contains "one":
                    players = 1;      //Set 'players' to 1
                    doubleEntry += 1; //Increment doubleEntry
                }
                if (response.contains("two") || response.contains("Two") || response.contains("TWO")) { //if 'response' contains "two":
                    players = 2;      //set 'players' to 2
                    doubleEntry += 1; //Increment doubleEntry
                }
                if (response.contains("ins") || response.contains("Ins") || response.contains("INS")) { //If 'response' contains "instr":
                    players = 3;      //set 'players' to 3
                    doubleEntry += 1; //Increment doubleEntry
                }
                
                //if players is still 0, then the entry was invalid
                if (players == 0) {                                    //If players == 0:
                    System.out.println("Your response is invalid.\n"); //Print an error message
                    invalidResponse = true;                            //set invalidResponse to true. This will repeat the loop
                }
                //if players is 3, then display the instructions for Salvo
                if (players == 3) {
                    System.out.println("Salvo is a classic naval combat simulation.");
                    System.out.println("Each player is allocated their own 10x10 grid of open sea, where they can deploy their ships.");
                    System.out.println("There are 5 classes of ships, each taking up a certain amount of space on the board.");
                    System.out.println("Both players must deploy all 5 ships before the simulation can begin.");
                    System.out.println();
                    System.out.println("     Patrol Boat      [P]        2 Spaces");
                    System.out.println("     Submarine        [S]        3 Spaces");
                    System.out.println("     Destroyer        [D]        3 Spaces");
                    System.out.println("     Battleship       [B]        4 Spaces");
                    System.out.println("     Aircraft Carrier [C]        5 Spaces");
                    System.out.println();
                    System.out.println("Player One will now select the spot he or she wishes to attack first.");
                    System.out.println("The player cannot see the placement of the enemy ships on the enemy's grid. He or she must guess what they believe to be the most probable location.");
                    System.out.println("The player selects a location to attack by typing a letter and a number. The letter denotes the row, and the number denotes the column.");
                    System.out.println("If part of an enemy ship takes up this space, it is considered a HIT, and an 'X' will appear on the board there.");
                    System.out.println("If the round hits open water, then it is a MISS, and a '~' will appear in that space.");
                    System.out.println("If you get a hit, then you may fire another round. If you miss, it is then the enemy's turn to fire.");
                    System.out.println("The game is over when all spaces containing a player's ships have been hit, thereby sinking all the ships.");
                    System.out.println("The player with some ships still partially intact is declared the winner!");
                    System.out.println(); //Print a blank line
                    
                    
                    invalidResponse = true; //Set invalidResponse to true, This will repeat the loop
                    
                }
                //if doubleEntry is 2 or greater, then the entry was invalid
                if (doubleEntry >= 2) {                                //If doubleEntry >= 2:
                    System.out.println("Your response is invalid.\n"); //Print an error message
                    invalidResponse = true;                            //set invalidResponse to true, This will repeat the loop
                }
                
                //At this point, if the user entered a valid response, then 
                // invalidRepsonse should be false, and players should either be 1 or 2. If that is the case,
                // The program will now initialize the proper game
            }
            
            //Initialize the proper game
            if (players == 1) { //If players is 1:
               
                selectOnePlayerGame(); //Select the game difficulty and execute the game.
            }
            if (players == 2) { //If players is 2:
                System.out.println("\n\n\n\nYou have started a two-player game against a human opponent.\n"); //Tell the user that they have begun a two-player game
                
                twoPlayerGame(); //Begin a two-player game
            }
            
            //The loop will repeat, asking to start another game.
        }
    }
    
    /****************************************************************
     * String prompt
     * takes String text
     * 
     * Prompts the user to type a line of text.
     * 
     * If the line of text is found to contain the word 'end', 
     * asks the user if they truly wish to end the game. If the next
     * line they type contains 'yes', this method closes all
     * input streams, displays a goodbye message, and exits 
     * the JVM. Otherwise, it returns the line
     ****************************************************************/
    public static String prompt(String text) throws IOException {
        
        InputStreamReader isr = new InputStreamReader(System.in); //Create InputStreamReader name 'isr and set to a new InputStreamReader, reading from System.in
        BufferedReader br = new BufferedReader(isr);           //Create a BufferedReader name 'br' and set to a new BufferedReader, reading from 'isr'
            
        System.out.println(text);               //Display the 'text' parameter, to ask the user to input something
        System.out.print("--> ");               //Display an arrow, prompting further the user to type something
        String input = br.readLine();           //Declare 'input' and set it to the line of text the user enters
        System.out.println();                   //Print a blank line below the user's input
            
        if (input.contains("end") || input.contains("End") || input.contains("END")) {     //If the line of text contains "end":
            System.out.println("Are you sure you want to exit the game? Type yes or no."); //Ask if the user really wants to exit.
            System.out.print("--> ");                                                      //Display the typing prompt
            String yes = br.readLine();                                                    //Declare 'yes' and set it to the line the user enters
            System.out.println();                                                          //Print a blank line
                
            if (yes.contains("yes") || yes.contains("Yes") || yes.contains("YES")) { //If 'yes' contains "yes":
                //close 'isr' and 'br'
                if (isr != null) { //If 'isr' does not point to null:
                    isr.close();   //close 'isr'
                }
                if (br != null) {  //If 'br' does not point to null:
                    br.close();    //close 'br'
                }
                    
                System.out.println("Naval Combat Simulation SALVO terminated. Have a nice day, Admiral!\n"); //Print a goodbye message
                    
                System.exit(0); //Exit the JVM
            }
        }
            
        return input; //Return 'input'
        
    }
    
    /******************************************
     * char[][] newBoard
     * 
     * creates a new empty game board
     * can be used to clear an existing board:
     * char[][] board = newBoard();
     ******************************************/
    public static char[][] newBoard() {
        char[][] board = new char[10][10]; //Create a char[][] name 'board' and set it to a 10x10 character array
        
        for (int r=0; r<10; r++) {      //r from 0 to 9:
            for (int c=0; c<10; c++) {  //c from 0 to 9:
                board[r][c] = ' ';      //Set the board spot to a space ' '.
            }
        }
        
        return board; //Return the game board
    }
    
    /***************************************************
     * void showGoodBoard
     * takes char[10][10]
     * 
     * Prints out the game board, showing coordinates, 
     * hits, misses, ship designations, and empty spaces
     ***************************************************/
    public static void showGoodBoard(char[][] board) {
        //Print the game board
        System.out.println("     1   2   3   4   5   6   7   8   9   10 "); //Print column numbers, divided by '|'
        System.out.println("   |---|---|---|---|---|---|---|---|---|---|"); //Print a horizontal divider
        
        for (int r=0; r<10; r++) {                        //For r from 0 to 9:
            System.out.print(((char) (r+65))+"  |");      //Print the letter of the row, two spaces, and a divider. 65 is ASCII for 'A'
            
            for (int c=0; c<10; c++) {                    //For c from 0 to 9
                System.out.print(" "+board[r][c]+" |");   //Print a space, the board symbol, a space, and a divider
            }
            
            System.out.println();                                               //Print a new line
            System.out.println("   |---|---|---|---|---|---|---|---|---|---|"); //Print a horizontal divider
        }
        System.out.println(); //Print a blank line at the end to complete the board
    }
    
   /***************************************************
     * void showBadBoard
     * takes char[10][10]
     * 
     * Prints out the game board, showing coordinates, 
     * hits, misses, empty spaces, but no ship designations
     ***************************************************/
    public static void showBadBoard(char[][] board) {
        //Print the game board
        System.out.println("     1   2   3   4   5   6   7   8   9   10 "); //Print column numbers, divided by '|'
        System.out.println("   |---|---|---|---|---|---|---|---|---|---|"); //Print a horizontal divider
        
        for (int r=0; r<10; r++) {                        //For r from 0 to 9:
            System.out.print(((char) (r+65))+"  |");      //Print the letter of the row, two spaces, and a divider. 65 is ASCII for 'A'
            
            for (int c=0; c<10; c++) {                    //For c from 0 to 9
                
                if ((board[r][c] == 'P') || (board[r][c] == 'S') || (board[r][c] == 'D') || (board[r][c] == 'B') || (board[r][c] == 'C')) { //If space contains a ship designation:
                    System.out.print("   |"); //Print three spaces and a divider
                }
                else {
                    System.out.print(" "+board[r][c]+" |");   //Print a space, the board symbol, a space, and a divider
                }
            }
            
            System.out.println();                                               //Print a new line
            System.out.println("   |---|---|---|---|---|---|---|---|---|---|"); //Print a horizontal divider
        }
        System.out.println(); //Print a blank line at the end to complete the board
    }
    
    /***************************************************
     * void showBadBoardWithDot
     * takes char[10][10], int selectR, int selectC
     * 
     * Prints out the game board, showing coordinates, 
     * hits, misses, empty spaces, but no ship designations
     * 
     * Prints a '*' to mark selected coordinates
     ***************************************************/
    public static void showBadBoardWithDot(char[][] board, int selectR, int selectC) {
        //Print the game board
        System.out.println("     1   2   3   4   5   6   7   8   9   10 "); //Print column numbers, divided by '|'
        System.out.println("   |---|---|---|---|---|---|---|---|---|---|"); //Print a horizontal divider
        
        for (int r=0; r<10; r++) {                        //For r from 0 to 9:
            System.out.print(((char) (r+65))+"  |");      //Print the letter of the row, two spaces, and a divider. 65 is ASCII for 'A'
            
            for (int c=0; c<10; c++) {                    //For c from 0 to 9
                
                if ((r == selectR) && (c == selectC)) {   //If the current coordinates are the ones the user selected
                    System.out.print(" * |"); //Print a space, a star, a space, and a divider
                }
                else if ((board[r][c] == 'P') || (board[r][c] == 'S') || (board[r][c] == 'D') || (board[r][c] == 'B') || (board[r][c] == 'C')) { //If space contains a ship designation:
                    System.out.print("   |"); //Print three spaces and a divider
                }
                else {
                    System.out.print(" "+board[r][c]+" |");   //Print a space, the board symbol, a space, and a divider
                }
            }
            
            System.out.println();                                               //Print a new line
            System.out.println("   |---|---|---|---|---|---|---|---|---|---|"); //Print a horizontal divider
        }
        System.out.println(); //Print a blank line at the end to complete the board
    }
    
    /*******************************************************
     * void blankLines
     * takes int lines
     * 
     * prints a number of blank lines to clear the screen
     ******************************************************/
    public static void blankLines(int lines) {
        for (int n=0; n<lines; n++) { //for 'lines' iterations:
            System.out.println(); //Print a blank line
        }
    }
    
    /***************************************
     * void selectOnePlayerGame
     * 
     * selects a game type based on the 
     * player's entry of a difficulty
     ***************************************/
    public static void selectOnePlayerGame() throws IOException {
        
    	String response; //This will hold the value of the user's response
    	int doubleEntry; //If this is ever 2 or greater, the user's input is invalid
    	int selection;   //This represents the user's selection as follows
    		//0 invalid input
    		//1 Easy difficulty
    		//2 Normal difficulty
    		//3 Hard difficulty
    		//4 Suicidal difficulty
    	
    	while(true) { //Infinite loop. Will terminate once the user enters valid input
    		selection = 0; //Set selection to invalid response
    		doubleEntry = 0; //Set doubleEntry to 0
    		
    		//Give instructions for choosing difficulty
    		
    		System.out.println("Please select the difficulty of your adversary:\n");
    		System.out.println("     EASY:     Simple. The AI takes potshots throughout the game, not targeting specific ships.");
    		System.out.println("     NORMAL:   The AI fires randomly around the board, but will attempt to destroy an entire ship once it hits one.");
    		System.out.println("     HARD:     The AI calculates the most probable location of your ships based on how much empty space is on the board.");
    		System.out.println("     SUICIDAL: You are not going to win.");
    		System.out.println();
    		
    		response = prompt("");
    		
    		if (response.contains("eas") || response.contains("Eas") || response.contains("EAS")) {
    			selection = 1; //Set selection to easy difficulty
    			doubleEntry += 1; //Increment double entry
    		}
    		if (response.contains("nor") || response.contains("Nor") || response.contains("NOR")) {
    			selection = 2; //Set selection to normal difficulty
    			doubleEntry += 1; //Increment double entry
    		}
    		if (response.contains("har") || response.contains("Har") || response.contains("HAR")) {
    			selection = 3; //Set selection to hard difficulty
    			doubleEntry += 1;
    		}
    		if (response.contains("sui") || response.contains("Sui") || response.contains("SUI")) {
    			selection = 4; //Set selection to suicidal difficulty
    			doubleEntry += 1;
    		}
    		if (doubleEntry >= 2) {
    			selection = 0; //Set selection to invalid input
    		}
    		
    		if (selection == 0) {
    			System.out.println("Invalid response.\n"); //Print an error message
    			
    			continue; //repeat the loop so the user can enter a different selection
    		}
    		if (selection == 1) {
    			System.out.println("You have chosen Easy difficulty!\n");
    			
    			System.out.println("Apologies, but the AI handling Easy difficulty has not been programmed yet. :(\n\n\n");
    			
    			
    			return; //The game is over. This will redirect the process back to main to start a new game
    		}
    		if (selection == 2) {
    			System.out.println("You have chosen Normal difficulty!\n");
    			
    			System.out.println("Apologies, but the AI handling Normal difficulty has not been programmed yet. :(\n\n\n");
    			
    			return; //The game is over. This will redirect the process back to main to start a new game
    		}
    		if (selection == 3) {
    			System.out.println("You have chosen Hard difficulty!\n");
    			
    			System.out.println("Apologies, but the AI handling Hard difficulty has not been programmed yet. :(\n\n\n");
    			
    			
    			return; //The game is over. This will redirect the process back to main to start a new game
    		}
    		if (selection == 4) {
    			System.out.println("You have chosen Suicidal difficulty!\n");
    			
    			System.out.println("Apologies, but the AI handling Suicidal difficulty has not been programmed yet. Consider yourself lucky! ;)\n\n\n");
    			
    			return; //The game is over. This will redirect the process back to main to start a new game
    		}

    		
    	}
    	
        
        
    }
    
    /**********************************
     * void twoPlayerGame
     * 
     * runs a two-player game of Salvo
     **********************************/
    public static void twoPlayerGame() throws IOException {
        
        //Player names
        String[] playerName = {"PLAYER ONE", "PLAYER TWO"};

        //Set up game boards
        System.out.println(playerName[0]+", deploy your fleet!\n"); //Tell player one to deply their fleet
        char[][] playerOneBoard = setBoard();     //create a char[][] name 'playerOneBoard' and set it to a manually set board
        
        blankLines(500); //Print 500 blank lines
        
        System.out.println(playerName[1]+", deploy your fleet!\n"); //Tell player two to deploy their fleet
        char[][] playerTwoBoard = setBoard();    //create a char[][] name 'playerTwoBoard' and set it to manually set board
        
        blankLines(500); //Print 500 blank lines
        
        //Initialize ship data
        String[] shipName = {"Patrol Boat", "Submarine", "Destroyer", "Battleship", "Aircraft Carrier"}; //Ship names
        char[] shipDesignation = {'P', 'S', 'D', 'B', 'C'}; //Ship designations
        int[] shipSpaces = {2, 3, 3, 4, 5}; //Ship spaces
        
        //Player one data
        int[] playerOneShipHealth = {2, 3, 3, 4, 5}; //Player one ship health
        int playerOneShipsRemaining = 5; //Ships remaining
        boolean playerOneHitNews = false; //If this is true, then Player 2 hit a ship, and Player 1 should be notified
        int[] playerOneSunkNews = {-1, -1, -1, -1, -1}; //If any of these are 0-4, then that ship was sunk, and Player 1
                                                        //Needs to be notified
        int playerOneSunkNewsStart = 0;                 //Point Start to the beginning
        int playerOneSunkNewsNextEntry = 0;             //Point next entry to the beginning
        
        //Player two data
        int[] playerTwoShipHealth = {2, 3, 3, 4, 5}; //Ship health
        int playerTwoShipsRemaining = 5; //Ships remaining
        boolean playerTwoHitNews = false; //If this is true, then Player 1 hit a ship, and Player 2 should be notified
        int[] playerTwoSunkNews = {-1, -1, -1, -1, -1}; //If any of these are 0-4, then that ship was sunk, and Player 2
                                                        //Needs to be notified
        int playerTwoSunkNewsStart = 0;                 //Point Start to the beginning
        int playerTwoSunkNewsNextEntry = 0;             //Point next entry to the beginning
        
        //Begin the game:
        
        System.out.println("Initiating Naval Combat Simulation:\n"); //Tell the users the game has begun
        
        for (int round=1; round < 101; round++) {      //For 'round' for 1 to 100:
            //Player 1's turn
            
            //Show round #
            System.out.println("ROUND "+round+"\n");
            

            //Show Player 2's game board
            showBadBoard(playerTwoBoard);
            
            //Display updates
            if (playerOneHitNews || (playerOneSunkNews[playerOneSunkNewsStart] != -1)) { //If there is any form of news to display:
                if (playerOneSunkNews[playerOneSunkNewsStart] != -1) { //If there is a sinking to report:
                    while (playerOneSunkNewsStart < playerOneSunkNewsNextEntry) { //As long as the starting pointer of the news array is less than the pointer to the next empty entry:
                        //Tell the user that a ship has been sunk
                        System.out.println(playerName[1]+" has sunk your "+shipName[playerOneSunkNews[playerOneSunkNewsStart]]+"!");
                        //Point playerOneSunkNewsStart to the next entry in the news array
                        playerOneSunkNewsStart += 1;
                    }
                }
                if (playerOneHitNews) { //If a ship was hit but not destroyed:
                    System.out.println(playerName[1]+" has damaged one of your ships!");
                    playerOneHitNews = false; //Set news to false
                }
            }
            
            System.out.println(); //Print an extra line after updates
            
            //Say it is Player 1's turn
            System.out.println(playerName[0]+", it is your turn!\n");
            
            while (true) { //Infinite loop. Will break if the game ends, or if Player 1 gets a miss
                //Have Player 1 attack
                int[] coordinates = target(playerTwoBoard);
                
                //Fire on the board
                int ship = fire(coordinates[0], coordinates[1], playerTwoBoard, shipDesignation); //Init ship, and set it to the value of the ship that was just hit
                
                //Show round #
                System.out.println("ROUND "+round+"\n");
                

                //Show Player 2's game board
                showBadBoard(playerTwoBoard);
                
                //Tell if it is a hit or a miss or a sink
                if (ship == -1) { //If the result of the attack is a miss:
                    System.out.println("The attack on "+((char) (coordinates[0]+65))+(coordinates[1]+1)+" was a miss.\n"); //Tell the user it was a miss
                    System.out.println("It is now "+playerName[1]+"'s turn.\n"); //Tell the user to switch turns
                    prompt("Press 'ENTER' to confirm."); //tell the user to confirm
                    
                    //Print some blank lines
                    blankLines(10);
                    
                    break; //Break out of the loop, to begin Player 2's turn
                }
                else { //the result was a hit!
                    System.out.println("The attack on "+((char) (coordinates[0]+65))+(coordinates[1]+1)+" was a hit!\n"); //Tell the user it was a hit
                    
                    //Update playerTwoHitNews
                    playerTwoHitNews = true;
                    
                    //Check if the hit ship is about to sink. If so, decrement playerTwoShipsRemaining and tell the user
                    if (playerTwoShipHealth[ship] == 1) {
                        playerTwoShipsRemaining -= 1; //Decrement ships remaining
                        
                        playerTwoHitNews = false; //Disregard hit news update
                        
                        playerTwoSunkNews[playerTwoSunkNewsNextEntry] = ship; //Update sunk news
                        playerTwoSunkNewsNextEntry += 1; //Increment next entry
                        
                        System.out.println("Good news, Admiral! You have sunk "+playerName[1]+"'s "+shipName[ship]+"!\n");
                    }
                    
                    //Decrement the ship's health
                    playerTwoShipHealth[ship] -= 1;
                    
                    //Check to see if the game is over
                    if (playerTwoShipsRemaining == 0) {
                        blankLines(3); //Print some blank lines
                        
                        System.out.println("Congratulations, "+playerName[0]+"! You have won the battle!\n"); //Tell the user they have won
                        
                        System.out.println("GAME OVER\n");
                        
                        prompt("Press 'ENTER' to continue.");
                        
                        blankLines(10); //Print some blank lines
                        
                        return; //Exit the function. The game is over
                    }
                }
                
                //Say it is Player 1's turn
                System.out.println(playerName[0]+", it is your turn!\n");
                
                
            }
            
            //Player 2's turn
            
            //Show round #
            System.out.println("ROUND "+round+"\n");
            

            //Show Player 1's game board
            showBadBoard(playerOneBoard);
            
            //Display updates
            if (playerTwoHitNews || (playerTwoSunkNews[playerTwoSunkNewsStart] != -1)) { //If there is any form of news to display:
                if (playerTwoSunkNews[playerTwoSunkNewsStart] != -1) { //If there is a sinking to report:
                    while (playerTwoSunkNewsStart < playerTwoSunkNewsNextEntry) { //As long as the starting pointer of the news array is less than the pointer to the next empty entry:
                        //Tell the user that a ship has been sunk
                        System.out.println(playerName[0]+" has sunk your "+shipName[playerTwoSunkNews[playerTwoSunkNewsStart]]+"!");
                        //Point playerTwoSunkNewsStart to the next entry in the news array
                        playerTwoSunkNewsStart += 1;
                    }
                }
                if (playerTwoHitNews) { //If a ship was hit but not destroyed:
                    System.out.println(playerName[0]+" has damaged one of your ships!");
                    playerTwoHitNews = false; //Set news to false
                }
            }
            
            System.out.println(); //Print an extra line after updates
            
            //Say it is Player 2's turn
            System.out.println(playerName[1]+", it is your turn!\n");
            
            while (true) { //Infinite loop. Will break if the game ends, or if Player 2 gets a miss
                //Have Player 2 attack
                int[] coordinates = target(playerOneBoard);
                
                //Fire on the board
                int ship = fire(coordinates[0], coordinates[1], playerOneBoard, shipDesignation); //Init ship, and set it to the value of the ship that was just hit
                
                //Show round #
                System.out.println("ROUND "+round+"\n");
               

                //Show Player 1's game board
                showBadBoard(playerOneBoard);
                
                //Tell if it is a hit or a miss or a sink
                if (ship == -1) { //If the result of the attack is a miss:
                    System.out.println("The attack on "+((char) (coordinates[0]+65))+(coordinates[1]+1)+" was a miss.\n"); //Tell the user it was a miss
                    System.out.println("It is now "+playerName[0]+"'s turn.\n"); //Tell the user to switch turns
                    prompt("Press 'ENTER' to confirm."); //tell the user to confirm
                    
                    //Print some blank lines
                    blankLines(10);
                    
                    break; //Break out of the loop, to begin Player 1's turn
                }
                else { //the result was a hit!
                    System.out.println("The attack on "+((char) (coordinates[0]+65))+(coordinates[1]+1)+" was a hit!\n"); //Tell the user it was a hit
                    
                    playerOneHitNews = true; //Update hit news
                    
                    //Check if the hit ship is about to sink. If so, decrement playerTwoShipsRemaining and tell the user
                    if (playerOneShipHealth[ship] == 1) {
                        playerOneShipsRemaining -= 1; //Decrement ships remaining
                        
                        playerOneHitNews = false; //Disregard hit news
                        
                        playerOneSunkNews[playerOneSunkNewsNextEntry] = ship; //Update sunk news
                        playerOneSunkNewsNextEntry += 1; //Increment next entry
                        
                        System.out.println("Good news, Admiral! You have sunk "+playerName[0]+"'s "+shipName[ship]+"!\n");
                    }
                    
                    //Decrement the ship's health
                    playerOneShipHealth[ship] -= 1;
                    
                    //Check to see if the game is over
                    if (playerOneShipsRemaining == 0) {
                        blankLines(3); //Print some blank lines
                        
                        System.out.println("Congratulations, "+playerName[1]+"! You have won the battle!\n"); //Tell the user they have won
                        
                        System.out.println("GAME OVER\n");
                        
                        prompt("Press 'ENTER' to continue.");
                        
                        blankLines(10); //Print some blank lines
                        
                        return; //Exit the function. The game is over
                    }
                }
                
                
                //Say it is Player 2's turn
                System.out.println(playerName[1]+", it is your turn!\n");
                
                
                
            }
        }
        
        
        
    }
    
    /******************************************************************************
     * int fire
     * takes int r, int c, char[][] board, char[] shipDesignation, int[] shipHealth
     * 
     * This method handles firing a round at the board. Checks to see if it
     * is a hit or a miss. If it is a miss, it updates the board with a '~', and
     * returns -1. If it is a hit, updates the board with a 'X', and returns an integer 
     * indicating which ship was hit
     * 
     * Does not update shipsRemaining
     * 
     * This method only operates when hitting an empty space, or hitting a ship
     * space. It will not work hitting an already attacked space
     *******************************************************************************/
    public static int fire(int r, int c, char[][] board, char[] shipDesignation) {
        //This method should only hit open sea or a ship. Never an 'X' or a '~'
        
        int hit = -1; //hit is -1, assume it is a miss
        
        if (board[r][c] == ' ') { //If the round hit open sea:
            board[r][c] = '~';    //Set the space to a miss: '~'
            return hit;           //Return hit as a miss
        }
        else {
            for (int n=0; n<5; n++) { //For n through all ship indexes:
                if (board[r][c] == shipDesignation[n]) { //If the space matches the ship designation n:
                    hit = n; //Set hit to n
                    
                    
                    break; //Break out of the for loop
                }
            }
            
            board[r][c] = 'X'; //Set the space to a hit: 'X'
            
            return hit;       //Return index of the hit ship
        }
        
    }
    
    /****************************************************************
     * int[] target
     * takes char[][] board
     * 
     * Handles the user targeting a space on the board. 
     * 
     * Tells the user to enter the coordinates they wish to attack.
     * If the space is a 'X' or a '~', it will ask the user to input
     * another set of coordinates. If the space is ' ', the method
     * will print out the game board with a '*' marking the desired
     * coordinates, and ask the user to confirm the attack by typing
     * 'yes', or choose another by typing 'no'. Returns a 1x2 array
     * of the r and c coordinates of the attack.
     * 
     * Will not allow the user to attack a space that has already been
     * marked.
     ******************************************************************/
    public static int[] target(char[][] board) throws IOException {
        int[] coordinates = {0, 0}; //Initialize coordinates at 0.
        
        String response; //This will hold the line of user input
        int rowDoubleEntry; //If this is ever 2 or greater, the user entered invalid input
        int columnDoubleEntry; //If this is ever 2 or greater, the user entered invalid input
        int rowSelection;
        // if it is 0, input is invalid
        // if it is 1, user entered 'a'
        // if it is 2, user entered 'b'
        // if it is 3, user entered 'c'
        // if it is 4, user entered 'd'
        // if it is 5, user entered 'e'
        // if it is 6, user entered 'f'
        // if it is 7, user entered 'g'
        // if it is 8, user entered 'h'
        // if it is 9, user entered 'i'
        // if it is 10, user entered 'j'
        // if it is 11, user entered 'yes'
        // if it is 12, user entered 'no'
        
        int columnSelection;
        // if it is 0, input is invalid
        // if it is 1, user entered 1
        // if it is 2, user entered 2
        // if it is 3, user entered 3
        // if it is 4, user entered 4
        // if it is 5, user entered 5
        // if it is 6, user entered 6
        // if it is 7, user entered 7
        // if it is 8, user entered 8
        // if it is 9, user entered 9
        // if it is 10, user entered 10
        
        while (true) { //Infinite loop. Will break once the user enters valid input
            rowSelection = 0; //Set rowSelection to invalid input
            columnSelection = 0; //Set columnSelection to invalid input
            rowDoubleEntry = 0; //Set rowDoubleEntry to 0
            columnDoubleEntry = 0; //Set columnDoubleEntry to 0
            
            //Give instructions for how to enter coordinates
            response = prompt("Enter the coordinates you would like to attack. Ex: 'a10', 'i8'");
            
            //read and interpret selection
            if (response.contains("a") || response.contains("A")) { //if response contains 'a'
                rowSelection = 1; //Set rowSelection to 1
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("b") || response.contains("B")) { //if response contains 'b'
                rowSelection = 2; //Set rowSelection to 2
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("c") || response.contains("C")) { //if response contains 'c'
                rowSelection = 3; //Set rowSelection to 3
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("d") || response.contains("D")) { //if response contains 'd'
                rowSelection = 4; //Set rowSelection to 4
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("e") || response.contains("E")) { //if response contains 'e'
                rowSelection = 5; //Set rowSelection to 5
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("f") || response.contains("F")) { //if response contains 'f'
                rowSelection = 6; //Set rowSelection to 6
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("g") || response.contains("G")) { //if response contains 'g'
                rowSelection = 7; //Set rowSelection to 7
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("h") || response.contains("H")) { //if response contains 'h'
                rowSelection = 8; //Set rowSelection to 8
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("i") || response.contains("I")) { //if response contains 'i'
                rowSelection = 9; //Set rowSelection to 9
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("j") || response.contains("J")) { //if response contains 'j'
                rowSelection = 10; //Set rowSelection to 10
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("1")) { // if response contains "1":
                if (response.contains("10")) { // response could also be 10:
                    columnSelection = 10; //Set columnSelection to 10
                }
                else {
                    columnSelection = 1; //Set columnSelection to 1
                }
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("2")) { // if response contains "2":
                columnSelection = 2; //Set columnSelection to 2
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("3")) { // if response contains "3":
                columnSelection = 3; //Set columnSelection to 3
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("4")) { // if response contains "4":
                columnSelection = 4; //Set columnSelection to 4
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("5")) { // if response contains "5":
                columnSelection = 5; //Set columnSelection to 5
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("6")) { // if response contains "6":
                columnSelection = 6; //Set columnSelection to 6
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("7")) { // if response contains "7":
                columnSelection = 7; //Set columnSelection to 7
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("8")) { // if response contains "8":
                columnSelection = 8; //Set columnSelection to 8
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("9")) { // if response contains "9":
                columnSelection = 9; //Set columnSelection to 9
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (rowDoubleEntry > 1) { //If a double entry occured:
                rowSelection = 0; //Set rowSelection to 0, invalid input
            }
            if (columnDoubleEntry > 1) { //if a double entry occured:
                columnSelection = 0; //Set columnEntry to 0, invalid input
            }
            
            //process the selection
            if ((rowSelection == 0) || (columnSelection == 0)) { //If invalid coordinates were entered
                System.out.println("Invalid coordinates.\n"); //Print an error message
                
                continue; //repeat the loop, to ask the user to enter a different pair of coordinates
            }
            else {
                //Record coordinates entries
                int r = rowSelection-1;
                int c = columnSelection-1;
                
                if (board[r][c] == '~') { //If this location was already a miss:
                    System.out.println("You have already fired on coordinates "+((char) (r+65))+(c+1)+". It was a miss.\n"); //Tell the user they already attacked there
                    
                    continue; //repeat the loop, to ask the user to enter a different pair of coordinates
                }
                else if (board[r][c] == 'X') { //If this location was already a hit:
                    System.out.println("You have already fired on coordinates "+((char) (r+65))+(c+1)+". It was a hit!\n"); //Tell the user they already attacked there
                    
                    continue; //repeat the loop, to ask the user to enter a different pair of coordinates
                }
                else {
                    showBadBoardWithDot(board,r,c); //Display the board with a dot marking the coordinates
                    
                    while (true) { //Infinite loop. Will break when the user enters valid input
                        rowSelection = 0; //Set rowSelection to 0, invalid input
                        rowDoubleEntry = 0; //Set rowDoubleEntry to 0
                        response = prompt("Are you sure you want to attack coordinates "+((char) (r+65))+(c+1)+"? Type 'yes' or 'no'."); //Ask the user to confirm
                        
                        //Interpret response
                        if (response.contains("yes") || response.contains("Yes") || response.contains("YES")) { //if response contains 'yes':
                            rowSelection = 11; //Set rowSelection to 11
                            rowDoubleEntry += 1; //Increment rowDoubleEntry
                        }
                        if (response.contains("no") || response.contains("No") || response.contains("NO")) { //if response contains 'no':
                            rowSelection = 12; //Set rowSelection to 12
                            rowDoubleEntry += 1; //Increment rowDoubleEntry
                        }
                        if (rowDoubleEntry > 1) { //if a double entry occured:
                            rowSelection = 0; //Set rowSelection to 0, invalid entry
                        }
                        
                        
                        //Process selection
                        if (rowSelection == 11) { //if the user entered yes:
                            //Enter the coordinates into the coordinates array
                            coordinates[0] = r;
                            coordinates[1] = c;
                            
                            //Tell the user that the shot is firing
                            System.out.println("Coordinates confirmed. Firing.\n");
                            
                            //Return the coordinates
                            return coordinates;
                        }
                        else if (rowSelection == 12) {
                            break; //Break out of this loop, so the user can enter a new set of coordinates
                        }
                        else {
                            System.out.println("Invalid response.\n"); //Print an error message
                        }
                        
                        //If the user did not enter yes or no, this loop will repeat
                        
                    }
                    
                }
                //If the user did not enter valid coordinates, or coordinates that have already been fired on, this loop will repeat
            }
        }
        
        
    }
    
    /**********************************************************************
     * char[][] randomBoard
     * takes char[] shipDesignation, int[] shipSpaces, boolean[] shipPlaced
     * 
     * Creates a board with the ships in a random arrangement
     * Sets all of shipPlaced to 'true', but does not
     * change the value of shipsRemaining in setBoard
     **********************************************************************/
    public static char[][] randomBoard(char[] shipDesignation, int[] shipSpaces, boolean[] shipPlaced) {
        
        char[][] board = newBoard();  //Create an empty board
        
        for (int ship=4; ship >= 0; ship--) { //Init ship to 4, the carrier. While it is greater than -1, keep trying to place ships
            while (true) { //Infinite loop. Will run until the current ship has been placed
                int r = (int) (Math.random()*10); //Random row
                int c = (int) (Math.random()*10); //Random column
                int o = (int) (Math.random()*4);  //Random orientation
                
                if (board[r][c] == ' ') { //If the board is empty at that location
                    if (o == 0) {        //If the orientation is up
                         boolean allClear = true; //Set allClear to true. If this is true, then the ship can be placed
                         for (int n=(r-1); n>(r-shipSpaces[ship]); n--) { //For n from one above r to spaces+1 above r
                             if (n<0) { //If n is out of bounds
                                 allClear = false; //Set allClear to false
                             }
                             else if (board[n][c] != ' ') { //If that spot on the board is not empty:
                                 allClear = false; //Set allClear to false
                             }
                         }
                         
                         if (allClear) { //If it is clear to place a ship:
                             //Place the ship
                             for (int n=r; n>(r-shipSpaces[ship]); n--) {
                                 board[n][c] = shipDesignation[ship]; //Place the ship
                             }
                             //Break out of loop
                             break;
                         }  
                         
                    } else if (o == 1) { //If the orientation is down
                         boolean allClear = true; //Set allClear to true. If this is true, then the ship can be placed
                         for (int n=(r+1); n<(r+shipSpaces[ship]); n++) { //For n from one below r to spaces-1 below r
                             if (n>9) { //If n is out of bounds
                                 allClear = false; //Set allClear to false
                             }
                             else if (board[n][c] != ' ') { //If that spot on the board is not empty:
                                 allClear = false; //Set allClear to false
                             }
                         }
                         
                         if (allClear) { //If it is clear to place a ship:
                             //Place the ship
                             for (int n=r; n<(r+shipSpaces[ship]); n++) {
                                 board[n][c] = shipDesignation[ship]; //Place the ship
                             }
                             //Break out of loop
                             break;
                         }  
                    } else if (o == 2) { //If the orientation is left
                         boolean allClear = true; //Set allClear to true. If this is true, then the ship can be placed
                         for (int n=(c-1); n>(c-shipSpaces[ship]); n--) { //For n from one to the left of c to spaces+1 left of c
                             if (n<0) { //If n is out of bounds
                                 allClear = false; //Set allClear to false
                             }
                             else if (board[r][n] != ' ') { //If that spot on the board is not empty:
                                 allClear = false; //Set allClear to false
                             }
                         }
                         
                         if (allClear) { //If it is clear to place a ship:
                             //Place the ship
                             for (int n=c; n>(c-shipSpaces[ship]); n--) {
                                 board[r][n] = shipDesignation[ship]; //Place the ship
                             }
                             //Break out of loop
                             break;
                         }  
                    } else {             //If the orientation is right
                         boolean allClear = true; //Set allClear to true. If this is true, then the ship can be placed
                         for (int n=(c+1); n<(c+shipSpaces[ship]); n++) { //For n from one to the right of c  to spaces+1 right of c
                             if (n>9) { //If n is out of bounds
                                 allClear = false; //Set allClear to false
                             }
                             else if (board[r][n] != ' ') { //If that spot on the board is not empty:
                                 allClear = false; //Set allClear to false
                             }
                         }
                         
                         if (allClear) { //If it is clear to place a ship:
                             //Place the ship
                             for (int n=c; n<(c+shipSpaces[ship]); n++) {
                                 board[r][n] = shipDesignation[ship]; //Place the ship
                             }
                             //Break out of loop
                             break;
                         }  
                    }
                } 
                
                //Ship was not placed. Otherwise this loop would have been broken out of
                //Repeat the loop to generate new coordinates
            }
            
            //set shipPlaced[ship] to true
            shipPlaced[ship] = true;
           
        }
        
        return board; //Return the board
        
    }
    
    /*******************************************************
     * char[][] setBoard
     * 
     * runs the process of deploying the fleet, and returns
     * the completed game board
     *******************************************************/
    public static char[][] setBoard() throws IOException {
        
        char[][] board = newBoard(); //Create a char[][] name 'board' and set it to a new board
        
        //Initialize ship data:
        String[] shipName = {"Patrol Boat", "Submarine", "Destroyer", "Battleship", "Aircraft Carrier"};
        char[] shipDesignation = {'P', 'S', 'D', 'B', 'C'};
        int[] shipSpaces = {2, 3, 3, 4, 5};
        boolean[] shipPlaced = {false, false, false, false, false};
        int shipsRemaining = 5;
        
        //Show the board, and then ask the user what to do
        
        int doubleEntry = 0; //Set doubleEntry to 0. If this is 2 or greater, the response is invalid
        int action = 1; //Set action to 1. Action determines the step this process will take
        String response = null; //Declare 'response' and set it to null. This represents one line of user input
        // 0 means invalid response
        // 1 means a ship has been placed, and the user will be asked to place another
        // 2 means Patrol Boat is selected
        // 3 means Submarine is selected
        // 4 means Destroyer is selected
        // 5 means Battleship is selected
        // 6 means Carrier is selected
        // 7 means remove Patrol Boat
        // 8 means remove Submarine
        // 9 means remove Destroyer
        // 10 means remove Battleship
        // 11 means remove Carrier
        // 12 means the user wants a random arrangement
        // 13 means the user wants to clear the board
        // 14 means the board is complete
        // 15 means yes, the user does want to clear the board
        // 16 means no, the user does not want to clear the board
        // 18 means no, the user is not finished placing their ships
        
        while (action == 1) { //Displays the game board and status of ships
            
            showGoodBoard(board); //Display the game board.
            
            System.out.print("   Patrol Boat      [P]        2 Spaces     Status: ");
            if (shipPlaced[0]) System.out.println("Placed"); //If the Patrol Boat is placed, say so
            else System.out.println("Not placed");
            System.out.print("   Submarine        [S]        3 Spaces     Status: ");
            if (shipPlaced[1]) System.out.println("Placed"); //If the Submarine is placed, say so
            else System.out.println("Not placed");
            System.out.print("   Destroyer        [D]        3 Spaces     Status: ");
            if (shipPlaced[2]) System.out.println("Placed"); //If the Destoyer is placed, say so
            else System.out.println("Not placed");
            System.out.print("   Battleship       [B]        4 Spaces     Status: ");
            if (shipPlaced[3]) System.out.println("Placed"); //If the Battleship is placed, say so
            else System.out.println("Not placed");
            System.out.print("   Aircraft Carrier [C]        5 Spaces     Status: ");
            if (shipPlaced[4]) System.out.println("Placed"); //If the Carrier is placed, say so
            else System.out.println("Not placed");
            System.out.println();
            
            
            //If all ships have been placed, ask if the user is finished placing their ships.
            if (shipsRemaining == 0) {
                action = 0; //Set action to 0
                while (true) { //Infinite loop, will end when user enters valid input
                    doubleEntry = 0; //set double entry to 0
                    response = prompt("Are you finished arranging your ships?");
                    
                    //Read response and find selection
                    if (response.contains("yes") || response.contains("Yes") || response.contains("YES")) { //If response contains "Yes"
                        action = 14; //Set action to 14. The board is complete
                        doubleEntry += 1; //Increment doubleEntry
                    }
                    if (response.contains("no") || response.contains("No") || response.contains("NO")) { //If response contains "No"
                        action = 18; //Set action to 18. The user is not done placing ships
                        doubleEntry += 1; //Increment doubleEntry
                    }
                    //If a double entry occured:
                    if (doubleEntry > 1) {
                        action = 0; //Set action to 0
                    }
                    
                    //Process action
                    if (action == 14) { //If the user entered yes:
                        System.out.println("Fleet deployed.\n"); //Tell the user the fleet has been deployed
                        
                        return board; //Return the board
                    }
                    else if (action == 18) { //The user entered no:
                        break; //Break out of this loop. Program will jump to the next loop to display board and ship information
                    }
                    else {
                        System.out.println("Invalid response.\n"); //Print an error message
                    }
                    
                    //If no action occured, repeat the loop
                    
                }
            }

            // Ask the user what action to perform
            
            action = 0; //Set action to 0, invalid response
            while (action == 0) { //Give instructions on how to place ships, and interpret response
                if (shipsRemaining > 0) { //I only want this to display if there are ships remaining to deploy.
                    System.out.println("You have "+shipsRemaining+" ships left to deploy."); //Tell the user how many ships are left
                }
                System.out.println("To select a ship to place or move, type the ship's name.");
                System.out.println("To remove a ship, type 'remove' and then type the ship's name.");
                System.out.println("For a random arrangement, type 'random'.");
                System.out.println("To clear the board, type 'clear'.\n");
                
                response = prompt("What would you like to do, Admiral?"); //Ask the user to enter a response
                doubleEntry = 0; //Set double entry to zero
                if (response.contains("pat") || response.contains("Pat") || response.contains("PAT") || 
                    response.contains("boat") || response.contains("Boat") || response.contains("BOAT")) { //If response contains "Patrol Boat"
                    action = 2; //Set action to 2
                    doubleEntry += 1; //Increment doubleEntry
                }
                if (response.contains("sub") || response.contains("Sub") || response.contains("SUB")) { //If response contains "Submarine"
                    action = 3; //Set action to 3
                    doubleEntry += 1; //increment doubleEntry
                }
                if (response.contains("des") || response.contains("Des") || response.contains("DES")) { //If response contains "Destroyer"
                    action = 4; //Set action to 4
                    doubleEntry += 1; //increment doubleEntry
                }
                if (response.contains("bat") || response.contains("Bat") || response.contains("BAT")) { //If response contains "Battleship"
                    action = 5; //Set action to 5
                    doubleEntry += 1; //Increment doubleEntry
                }
                if (response.contains("air") || response.contains("Air") || response.contains("AIR") || 
                    response.contains("car") || response.contains("Car") || response.contains("CAR")) { //If response contains "Aircraft Carrier"
                    action = 6; //Set action to 6
                    doubleEntry += 1; //Increment doubleEntry
                }
                if (response.contains("ran") || response.contains("Ran") || response.contains("RAN")) { //If response contains "Random"
                    action = 12; //Set action to 12
                    doubleEntry += 1; //Increment doubleEntry
                }
                if (response.contains("clear") || response.contains("Clear") || response.contains("CLEAR")) { //If response contains "Clear"
                    action = 13; //Set action to 13
                    doubleEntry += 1; //Increment doubleEntry
                }
                if (response.contains("rem") || response.contains("Rem") || response.contains("REM")) { //If response contains "Remove"
                    action = 0; //Set action to 0, invalid response
                    doubleEntry = 0; //Set doubleEntry to 0
                    
                    if (response.contains("pat") || response.contains("Pat") || response.contains("PAT") || 
                        response.contains("boat") || response.contains("Boat") || response.contains("BOAT")) { //If response contains "Patrol Boat"
                        action = 7; //Set action to 7
                        doubleEntry += 1; //Increment doubleEntry
                    }
                    if (response.contains("sub") || response.contains("Sub") || response.contains("SUB")) { //If response contains "Submarine"
                        action = 8; //Set action to 7
                        doubleEntry += 1; //Increment doubleEntry
                    }
                    if (response.contains("des") || response.contains("Des") || response.contains("DES")) { //If response contains "Destroyer"
                        action = 9; //Set action to 7
                        doubleEntry += 1; //Increment doubleEntry
                    }
                    if (response.contains("bat") || response.contains("Bat") || response.contains("BAT")) { //If response contains "Battleship"
                        action = 10; //Set action to 7
                        doubleEntry += 1; //Increment doubleEntry
                    }
                    if (response.contains("air") || response.contains("Air") || response.contains("AIR") || 
                        response.contains("car") || response.contains("Car") || response.contains("CAR")) { //If response contains "Aircraft Carrier"
                        action = 11; //Set action to 7
                        doubleEntry += 1; //Increment doubleEntry
                    }
                }
                if (doubleEntry >= 2) { //if a double entry occured:
                    action = 0; //Set action to 0, invalid response
                }
                
                if (action != 0) break; //Break out of this loop to continue on to process the action
                
                System.out.println("Invalid response.\n"); //Print an error message
            }
            
            //Process the action:
            
            if ((action > 1) && (action < 7)) { //if a boat was selected
                //boat index should be equal to action-2
                
                if (shipPlaced[action-2]) { //If the ship is already placed:
                    board = clearShip(board, shipDesignation[action-2], shipSpaces[action-2]); //remove the ship from the board
                    shipPlaced[action-2] = false; //Set the ship to not placed
                    shipsRemaining += 1; //Increment remaining ships
                }
                
                if (placeShip(board, shipName[action-2], shipDesignation[action-2], shipSpaces[action-2])) { //Place a ship. If successful, say it is placed and decrement shipsRemaining
                    shipPlaced[action-2] = true; //Say that the ship is placed
                    shipsRemaining -= 1; //decrement ships remaining
                }
                
                action = 1; //set action to 1, to display the game board and ask user what to do next
                
            } else if ((action > 6) && (action < 12)) { //If remove a boat was selected:
                //boat index should be equal to action-7
                
                if (shipPlaced[action-7]) { //If the ship is placed:
                    board = clearShip(board, shipDesignation[action-7], shipSpaces[action-7]); //remove the ship from the board
                    shipPlaced[action-7] = false; //Set the ship to not placed
                    shipsRemaining += 1; //Increment remaining ships
                    
                    System.out.println(shipName[action-7]+" removed from board.\n"); //Tell the user the ship has been removed
                }
                else {  //If the ship is not placed:
                    System.out.println(shipName[action-7]+" is not on the board.\n"); //Tell the user the ship is not on the board
                }
                
                action = 1; //Set action to 1, to display the board and show ship status
            } else if (action == 12) { //If random is selected
                board = randomBoard(shipDesignation, shipSpaces, shipPlaced); //Create a random arrangement
                
                shipsRemaining = 0; //Set shipsRemaining to 0
                
                System.out.println("Random arrangement generated.\n"); //Tell the user a random arrangement has been generated
                
                action = 1; //Set action to 1, to display the game board and ask the user what to do next.
            } else if (action == 13) { //If clear is selected
                
                //ask if the user really wants to clear the board
                
                action = 0; //Set action to 0, invalid response
                while (action == 0) { //While the user input is an invalid response:
                    response = prompt("Are you sure you want to clear the board? This will remove all of your ships. ");
                    doubleEntry = 0; //Set double entry to zero
                    
                    if (response.contains("yes") || response.contains("Yes") || response.contains("YES")) { //If response contains "Yes":
                        action = 15; //Set action to 15
                        doubleEntry += 1; //increment doubleEntry
                    }
                    if (response.contains("no") || response.contains("No") || response.contains("NO")) { //If response contains "No"
                        action = 16; //Set action to 16
                        doubleEntry += 1; //increment doubleEntry
                    }
                    if (doubleEntry >= 2) { //If response is greater than or equal to 2:
                        action = 0; //Set action to 0, invalid response
                    }
                    if (action != 0) break; //If a valid action was entered, exit this loop
                    
                    System.out.println("Invalid response.\n"); //Print an error message
                }
                
                //process the action:
                if (action == 15) {             //If the user does want to clear the board:
                    board = newBoard();         //clear the board
                    
                    shipsRemaining = 5;         //set ships remaining to 5
                    for (int i=0; i<5; i++) {   //for i from 0 to 4:
                        shipPlaced[i] = false;  //set that ship as not placed
                    }
                    
                    System.out.println("Board cleared successfully.\n"); //Tell the user the board has been cleared
                    
                    action = 1; //Set action to 1, to repeat the loop, which will display the game board and ship status
                    
                } else if (action == 16) {      //If the user does not want to clear the board
                    System.out.println("The board has not been cleared.\n"); //Tell the user the board has not been cleared
                    
                    action = 1; //Set action to 1, to repeat the loop, which will display the game board and ship status
                }
                
            }
            
            
        }
        
        return board; //Compiler needs this here.
       
    }
    
    /*****************************************************
     * char[][] clearShip
     * takes char[][] board, char s, and int total
     * 
     * removes all characters 's' from the game board.
     * will only remove up to 'total' of the characters.
     *****************************************************/
    public static char[][] clearShip(char[][] board, char s, int total) {
        int erased = 0; //set erased to 0. Number of spaces erased needs to equal total to know that the ship has been cleared
        while (erased < total) { //while erased is less than the total:
            for (int r=0; r<10; r++) { //For rows 0 through 9:
                for (int c=0; c<10; c++) { //For rows 0 through 9:
                    if (board[r][c] == s) { //If the space is of character s:
                        board[r][c] = ' '; //Set the spot equal to a space
                        erased += 1;       //Increment erased
                    }
                }
            }
        }
        
        return board;
    }
    
    /**************************************************************************************
     * boolean placeShip
     * takes char[][] board, String shipName, char shipDesignation, int shipSpaces
     * 
     * Runs the process of placing a ship on the board.
     * The user selects the coordinates on which to place the ship.
     * The method then displays the board with a '*' showing the starting coordinates of
     * the ship's location. Then the user types 'up' 'down' 'left' or 'right' to 
     * orient the ship. This method checks to see if the ship is clear to be placed there
     * 
     * Since the board is an object, this method does not need to return the board. Returns
     * true if the ship was placed, and false otherwise.
     **************************************************************************************/
    public static boolean placeShip(char[][] board, String shipName, char shipDesignation, int shipSpaces) throws IOException {
        int r; //Declare r, board row
        int c; //Declare c, board column
        
        String response = null; //Create new String name 'response'. This will serve as user input
        int rowDoubleEntry; //Declare rowDoubleEntry
        int columnDoubleEntry; //Declare columnDoubleEntry
        int orientationDoubleEntry; //Declare orientationDoubleEntry
        int rowSelection; //Declare rowSelection
        // if it is 0, invalid input
        // if it is 1, the user selected "A"
        // if it is 2, the user selected "B"
        // if it is 3, the user selected "C"
        // if it is 4, the user selected "D"
        // if it is 5, the user selected "E"
        // if it is 6, the user selected "F"
        // if it is 7, the user selected "G"
        // if it is 8, the user selected "H"
        // if it is 9, the user selected "I"
        // if it is 10, the user selected "J"
        // if it is 11, the user typed 'cancel'
        int columnSelection; //Declare columnSelection
        // if it is 0, invalid input
        // if it is 1, the user selected 1
        // if it is 2, the user selected 2
        // if it is 3, the user selected 3
        // if it is 4, the user selected 4
        // if it is 5, the user selected 5
        // if it is 6, the user selected 6
        // if it is 7, the user selected 7
        // if it is 8, the user selected 8
        // if it is 9, the user selected 9
        // if it is 10, the user selected 10
        int orientationSelection; //Declare orientationSelection
        // if it is 0, invalid input
        // if it is 1, user selected up
        // if it is 2, user selected down
        // if it is 3, user selected left
        // if it is 4, user selected right
        
        // this loop will tell the user to enter coordinates, or type cancel to select another ship
        rowSelection = 0;
        columnSelection = 0;
        orientationSelection = 0;
        while (true) { //Infinite loop. Will break once the user enters valid input 
            //Give user instructions
            System.out.println("Type the coordinates at which you wish to begin placing your "+shipName+".");
            System.out.println("Letters designate rows, and numbers designate columns. Ex: 'a10', 'i8'");
            System.out.println("Type 'cancel' at any time to select a different ship.\n");
            
            //Read user input and set selections
            response = prompt("Type the coordinates at which to place your "+shipName+".");
            rowDoubleEntry = 0;
            columnDoubleEntry = 0;
            boolean cancelTyped = false; //If cancel has been typed, this is set to true
            if (response.contains("a") || response.contains("A")) { //If response contains "A":
                if (response.contains("cancel") || response.contains("Cancel") || response.contains("CANCEL")) { //User may have typed cancel
                    rowSelection = 11;  //Set rowSelection to 11
                    cancelTyped = true; //Say that cancel has indeed been typed
                }
                else {
                    rowSelection = 1; //Set rowSelection to 1
                }
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("b") || response.contains("B")) { //If response contains "B":
                rowSelection = 2; //Set rowSelection to 2
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("c") || response.contains("C")) { //If response contains "C":
                if (!(cancelTyped)) { //if cancel has not already been typed
                    rowSelection = 3; //Set rowSelection to 3
                    rowDoubleEntry += 1; //Increment rowDoubleEntry
                }
            }
            if (response.contains("d") || response.contains("D")) { //If response contains "D":
                rowSelection = 4; //Set rowSelection to 4
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("e") || response.contains("E")) { //If response contains "E":
                if (!(cancelTyped)) { //if cancel has not already been typed:
                    rowSelection = 5; //Set row selection to 5
                    rowDoubleEntry += 1; //Increment rowDoubleEntry
                }
            }
            if (response.contains("f") || response.contains("F")) { //If response contains "F":
                rowSelection = 6; //Set rowSelection to 6
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("g") || response.contains("G")) { //If response contains "G":
                rowSelection = 7; //Set rowSelection to 7
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("h") || response.contains("H")) { //If response contains "H":
                rowSelection = 8; //Set rowSelection to 8
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("i") || response.contains("I")) { //If response contains "I":
                rowSelection = 9; //Set rowSelection to 9
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("j") || response.contains("J")) { //If response contains "J":
                rowSelection = 10; //Set rowSelection to 10
                rowDoubleEntry += 1; //Increment rowDoubleEntry
            }
            if (response.contains("1")) { //If response contains 1:
                if (response.contains("10")) { //If response contains 10:
                    columnSelection = 10; //Set columnSelection to 10
                }
                else {
                    columnSelection = 1; //Set columnSelection to 1
                }
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("2")) { //If response contains 1:
                columnSelection = 2; //Set columnSelection to 2
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("3")) { //If response contains 1:
                columnSelection = 3; //Set columnSelection to 3
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("4")) { //If response contains 1:
                columnSelection = 4; //Set columnSelection to 4
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("5")) { //If response contains 1:
                columnSelection = 5; //Set columnSelection to 5
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("6")) { //If response contains 1:
                columnSelection = 6; //Set columnSelection to 6
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("7")) { //If response contains 1:
                columnSelection = 7; //Set columnSelection to 7
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("8")) { //If response contains 1:
                columnSelection = 8; //Set columnSelection to 8
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            if (response.contains("9")) { //If response contains 1:
                columnSelection = 9; //Set columnSelection to 9
                columnDoubleEntry += 1; //Increment columnDoubleEntry
            }
            
            //If double entries were entered, make selection invalid
            if (rowDoubleEntry > 1) { //if a double entry occured:
                rowSelection = 0; //Set rowSeleciton to 0, invalid input
            }
            if (columnDoubleEntry > 1) { //if a double entry occured:
                columnSelection = 0; //Set columnSelection to 0, invalid input
            }
            
            //Process selections
            if (rowSelection == 11) { //If the user entered cancel:
                System.out.println("Placement cancelled. "+shipName+" not placed.\n"); //Tell the user that the ship was not placed
                
                return false; //return false, because the ship was not placed
            } else if ((rowSelection > 0) && (rowSelection < 11) && (columnSelection > 0) && (rowSelection < 11)) { //If coordinates are within bounds
                //Set r and c
                r = rowSelection-1; //set r to rowSelection-1
                c = columnSelection-1; //set c to columnSelection-1
                
                //Check to see if there is already a ship there
                if (board[r][c] != ' ') { //If that spot is not a space:
                    System.out.println("There is already a ship in that location!\n"); //Tell the user there is already a ship in that location
                    
                    continue; //Continue the loop
                }
                else { //If that spot is empty
                    board[r][c] = '*'; //Set that spot to a star
                    showGoodBoard(board); //Display the board with the star
                    
                    // this loop will tell the user to enter an orientation
                    rowSelection = 0;
                    orientationSelection = 0;
                    while (true) { //Infinite loop. Will end when the user enters valid orientation
                        //Read user input and find selections
                        response = prompt("Type 'up', 'down', 'left', or 'right' to orient the ship from this point."); //Tell the user how to orient the ship
                        orientationDoubleEntry = 0;
                        if (response.contains("up") || response.contains("Up") || response.contains("UP")) { //If response contains "up"
                            orientationSelection = 1; //Set orientationSelection to 1
                            orientationDoubleEntry += 1; //Increment orientationDoubleEntry
                        }
                        if (response.contains("down") || response.contains("Down") || response.contains("DOWN")) { //If response contains "down"
                            orientationSelection = 2; //Set orientationSelection to 2
                            orientationDoubleEntry += 1; //Increment orientationDoubleEntry
                        }
                        if (response.contains("left") || response.contains("Left") || response.contains("LEFT")) { //If response contains "left"
                            orientationSelection = 3; //Set orientationSelection to 3
                            orientationDoubleEntry += 1; //Increment orientationDoubleEntry
                        }
                        if (response.contains("right") || response.contains("Right") || response.contains("RIGHT")) { //If response contains "right"
                            orientationSelection = 4; //Set orientationSelection to 4
                            orientationDoubleEntry += 1; //Increment orientationDoubleEntry
                        }
                        if (response.contains("cancel") || response.contains("Cancel") || response.contains("CANCEL")) { //If response contains "Cancel"
                            rowSelection = 11; //Set rowSelection to 11
                        }
                        //check if double entries occured:
                        if (orientationDoubleEntry > 1) { //If a double entry occured
                            orientationSelection = 0; //Set orientationSelection to 0, invalid selection
                        }
                        
                        //process selections
                        if (rowSelection == 11) { //If the user cancelled
                            board[r][c] = ' '; //Erase the star
                            System.out.println("Placement cancelled. "+shipName+" not placed.\n"); //Tell the user that the ship was not placed
                
                            return false; //return false, because the ship was not placed
                            
                        } else if (orientationSelection == 1) { //If the user selected up
                           boolean allClear = true; //Set allClear to true. If this is true, then the ship can be placed
                           for (int n=(r-1); n>(r-shipSpaces); n--) { //For n from one above r to spaces+1 above r
                               if (n<0) { //If n is out of bounds
                                   allClear = false; //Set allClear to false
                               }
                               else if (board[n][c] != ' ') { //If that spot on the board is not empty:
                                   allClear = false; //Set allClear to false
                               }
                           }
                           
                           if (allClear) {
                               for (int n=r; n>(r-shipSpaces); n--) { //For n from r to spaces+1 above r:
                                   board[n][c] = shipDesignation;     //Place the ship on the board
                               }
                               
                               System.out.println("Your "+shipName+" was placed successfully!\n"); //Tell the user the ship was placed successfully
                               
                               return true; //return true, because the ship was placed successfully
                           }
                           else {
                               System.out.println("You cannot place a ship there! There is an obstruction.\n"); //Tell the user there is somehting blocking the path
                               
                               continue; //continue through the loop
                           }
                               
                        } else if (orientationSelection == 2) { //If the user selected down
                           boolean allClear = true; //Set allClear to true. If allClear is true, then the ship can be placed
                           for (int n=(r+1); n<(r+shipSpaces); n++) {    //For n from r+1 to shipSpaces-1 below r
                               if (n>9) { //If n is out of bounds:
                                   allClear = false; //Set allClear to false
                               }
                               else if (board[n][c] != ' ') { //If there is an obstruction:
                                   allClear = false; //Set allClear to false
                               }
                           }
                           
                           if (allClear) { //If it is clear to place a ship:
                               for (int n=r; n<(r+shipSpaces); n++) { //For n from r to shipSpaces-1 below r:
                                   board[n][c] = shipDesignation; //Place the ship on the board
                               }
                               
                               System.out.println("Your "+shipName+" was placed successfully!\n"); //Tell the user the ship was placed
                               
                               return true; //return true, because the ship was placed successfully
                           }
                           else { //if it is not clear to place the ship:
                               System.out.println("You cannot place a ship there! There is an obstruction.\n"); //Tell the user there is something blocking the path
                               
                               continue; //continue through the loop
                           }
                           
                        } else if (orientationSelection == 3) { //If the user selected left
                            boolean allClear = true; //Set allClear to true. If it is true, the ship can be placed
                            for (int n=(c-1); n>(c-shipSpaces); n--) { //For n from c-1 to shipSpaces-1 to the left of c
                                if (n<0) { //If it is out of bounds:
                                    allClear = false;
                                }
                                else if (board[r][n] != ' ') { //If the spot there is not empty:
                                    allClear = false;
                                }
                            }
                            
                            if (allClear) { //If it is clear to place a ship
                                for (int n=c; n>(c-shipSpaces); n--) { //For n from c to shipSpaces-1 to the left of c:
                                    board[r][n] = shipDesignation; //Place the ship on the board
                                }
                                
                                System.out.println("Your "+shipName+" was placed successfully!\n"); //Tell the user that the ship has been placed
                                
                                return true; //Return true, because the ship was placed successfully
                            }
                            else {
                                System.out.println("You cannot place a ship there! There is an obstruction.\n"); //Tell the user there is something blocking the path
                               
                                continue; //continue through the loop
                            }
                            
                        } else if (orientationSelection == 4) { //If the user selected right
                            boolean allClear = true; //Set allClear to true. If this is true, the ship can be placed
                            for (int n=(c+1); n<(c+shipSpaces); n++) { //For n from c+1 to shipSpaces-1 to the right of c
                                if (n>9) { //If it is out of bounds:
                                    allClear = false;
                                }
                                else if (board[r][n] != ' ') { //If the space is not empty:
                                    allClear = false;
                                }
                            }
                            
                            if (allClear) { //If it is all clear to place a ship
                                for (int n=c; n<(c+shipSpaces); n++) { //For n from c to shipSpaces-1 to the right of c
                                    board[r][n] = shipDesignation; //Place the ship on the board
                                }
                                
                                System.out.println("Your "+shipName+" was placed successfully!\n"); //Tell the user the ship was placed successfully
                                
                                return true; //Return true, because the ship was placed successfully
                            }
                            else { //If it is not clear to place a ship
                                System.out.println("You cannot place a ship there! There is an obstruction.\n"); //Tell the user there is something blocking the path
                               
                                continue; //continue through the loop
                            }
                            
                        } else {
                            System.out.println("Invalid response!\n"); //Print an error message
                        }
                        
                        //If no selections have been processed, repeat the loop
                    }
                }
            } else { //If the coordinates are out of bounds
                System.out.println("Invalid coordinates!\n"); //Tell the user the coordinates are invalid
                
            }
            
            //If no selections were processed, repeat the loop
        }
        
        
    }
}