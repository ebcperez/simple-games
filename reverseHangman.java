import java.util.*;
public class reverseHangman {
	public static final int MAX_INCORRECT_GUESSES = 6;
	public static final int MAX_LENGTH = 8;
	public static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) {
	   
    	boolean morePlayer = true;
	    Scanner console = new Scanner(System.in);
	      
	      
	    while(morePlayer) {
	       int totalPlay = 0;
	       int totalWin = 0;
	       giveIntro();
	       boolean more = true;
	         
	       while(more) {
	    	   int letters = getLengthWord(console);
	           System.out.println();
	           console.nextLine();
	           boolean result = playGame(letters, console);
	           if(result) {
	              totalWin++;
	           }
	           String prompt = "\n\nDo you want to play another game? (y/n)";
	           totalPlay++;
	           if(!yesTo(prompt, console)) {
	              more = false;
	           }
	      }
	      System.out.println("\n");
          printStat(totalPlay, totalWin);
       	  morePlayer = yesTo("Do you want to play the game?(y/n)", console);
	   }
    }
	    public static boolean playGame(int letters, Scanner console) {
	      boolean result = false;
	      String guesses = "";
	      int correct = 0;
	      int incorrect = 0;
	      String lettersInYourWord = "";
	      
	      Random rand = new Random();
	      
	      printGallowsBase();
	            
	      while(correct < letters && incorrect < MAX_INCORRECT_GUESSES) {
	         int c = 0;
	         char r;
	         do { 
	         c = rand.nextInt((int)'z'-(int)'a'+1)+(int)'a';//;random ascii code of a-z
	         r = (char)c;
	         } while(guesses.indexOf(r+"") != -1);//letter has been picked before
	            guesses = guesses + r;//adds randomly generated letter to guesses string
	            
	         System.out.println("I have gotten " + correct + " out of " + letters + " letters in your word."); 
	         System.out.println("I guess " + r);
	         
	         if(yesTo("Is that letter in the word?(Please enter y/n): ", console)) {
	            System.out.print("How many times does the letter appear in the word? ");
	            int count = 0;
	            while(!console.hasNextInt()) {
	               System.out.print("Invalid. Please enter an integer: ");//only accepts integers
	               console.next();
	            }
	            if(console.hasNextInt()) {
	               count = console.nextInt();
	               while(count > letters) {
	                  System.out.print("Invalid. It cannot exceed the number of letters in your word: ");//cant exceed number of letters in the word
	                  count = console.nextInt();
	               }
	            }
	            correct = correct + count;
	            lettersInYourWord += r;
	         }
	         else {
	            incorrect++;
	         }
	         printGallows(incorrect);
	      }
	      System.out.println("Letters guessed by computer: " + guesses);
	      printNotGuessedLetters(guesses);
	      System.out.println("Letters in your word: " + lettersInYourWord);
	      
	      if(incorrect == MAX_INCORRECT_GUESSES) {
	         System.out.print("I am out of guesses. \nI lost.\nNow tell me your word: ");
	         String word = console.nextLine();
	         while(word.length() != letters) {
	            System.out.println("You said your word was " + letters + " letters long.\nWhat is your word?: ");
	            word = console.next();
	         }
	      }
	      return correct == letters;
	   }
	   public static void printNotGuessedLetters(String guesses) {
		   //prints letters not guessed by computer
	      char[] c = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	      for(int i = 0; i < guesses.length(); i++) {
	         int r = guesses.charAt(i);
	         c[r - 97 ] = '_';
	      }
	      System.out.println("Remaining letters not guessed by computer: " + Arrays.toString(c));
	   }
	   public static int getLengthWord(Scanner console) {
		   //asks user how many letters are in the word
	      System.out.println("How many letters are in your word? (choose a number between 1 and 8): ");
	      int length = 0;
	      while(!console.hasNextInt()) {
	            System.out.print("Invalid. Please enter an integer: ");
	            console.next();
	      }
	      if(console.hasNextInt()) {
	         length = console.nextInt();
	         while(length < 1 || length > 8) {
	            System.out.print("Please enter an integer between 1 and 8: ");
	            console.next();
	         }
	      }
	      return length;
	   }
	   public static void printStat(int totalPlay, int totalWin) {
	   //prints out the stats(number played, lost, won)
	      System.out.println("                     RESULT");
	      System.out.println("-------------------------------------------------");
	      System.out.println("  #played        Number lost        number won");
	      System.out.println("        " + totalPlay + "                  " + (totalPlay - totalWin) + "                " + totalWin);
	   }
	   public static void giveIntro() {
	      System.out.println("This program plays a game of reverse hangman.");
	      System.out.println("You think up a word and I'll try to guess the letters.");
	      System.out.println("The maximum number of incorrect guesses for me is 6.");
	      System.out.println("**********************************************************\n");
	   }
	   public static boolean yesTo(String prompt, Scanner console) {
		   //if yes to prompt, return true
	      System.out.println(prompt);
	      String answer = "";
	      answer = console.nextLine();
	      
	      while(!(answer.equalsIgnoreCase("y")||answer.equalsIgnoreCase("n"))) {
	         System.out.println("Please enter y/n: ");
	         answer = console.nextLine();
	      }
	      return answer.equalsIgnoreCase("y");
	   }
	   public static void printGallows(int incorrect) {
		   //prints gallows according to incorrect guesses
	      if(incorrect == 0){
	         printGallowsBase();
	      }
	      switch(incorrect) {
	      case 1:
	    	  printGallowsHead();
	    	  break;
	      case 2:
	    	  printGallowsBody();
	    	  break;
	      case 3:
	    	  printGallowsRA();
	    	  break;
	      case 4:
	    	  printGallowsLA();
	    	  break;
	      case 5:
	    	  printGallowsRL();
	    	  break;
	      case 6:
	    	  printGallowsLL();
	    	  break;
	      }
	   }
	   public static void printGallowsBase() {
	      System.out.println("+--+");
	      System.out.println("|  |");
	      System.out.println("|   ");
	      System.out.println("|   ");
	      System.out.println("|   ");
	      System.out.println("|   ");
	      System.out.println("+-----");
	   }
	   public static void printGallowsHead() {
	      System.out.println("+--+");
	      System.out.println("|  |");
	      System.out.println("|  O");
	      System.out.println("|   ");
	      System.out.println("|   ");
	      System.out.println("|   ");
	      System.out.println("+-----");
	   }
	   public static void printGallowsBody() {
	      System.out.println("+--+");
	      System.out.println("|  |");
	      System.out.println("|  O");
	      System.out.println("|  |");
	      System.out.println("|   ");
	      System.out.println("|   ");
	      System.out.println("+-----");
	   }
	   //RA = Right Arm
	   public static void printGallowsRA() {
	      System.out.println("+--+");
	      System.out.println("|  |");
	      System.out.println("|  O");
	      System.out.println("|  |\\");
	      System.out.println("|   ");
	      System.out.println("|   ");
	      System.out.println("+-----");
	   }
	   // LA = Left Arm
	   public static void printGallowsLA() {
	      System.out.println("+--+");
	      System.out.println("|  |");
	      System.out.println("|  O");
	      System.out.println("| /|\\");
	      System.out.println("|   ");
	      System.out.println("|   ");
	      System.out.println("+-----");
	   }
	   // RL = Right Leg
	   public static void printGallowsRL() {
	      System.out.println("+--+");
	      System.out.println("|  |");
	      System.out.println("|  O");
	      System.out.println("| /|\\");
	      System.out.println("|   \\");
	      System.out.println("|   ");
	      System.out.println("+-----");
	   }
	   // LL = Left Leg
	   public static void printGallowsLL() {
	      System.out.println("+--+");
	      System.out.println("|  |");
	      System.out.println("|  O");
	      System.out.println("| /|\\");
	      System.out.println("| / \\");
	      System.out.println("|   ");
	      System.out.println("+-----");
	   }
}
