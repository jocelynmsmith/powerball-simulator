/*******************************************************************************************************************
  * Powerball Simulator
  * Author: Jocelyn Smith
  ******************************************************************************************************************/
import java.util.Scanner;
public class Powerball_Simulator {

   static Scanner keyboard = new Scanner(System.in);
 
  
    //Declare global constants for winnings
    private static final int FIVE_NUMBERS_PLUS_POWERBALL = 100000000;
    private static final int FIVE_NUMBERS = 1000000;
    private static final int FOUR_NUMBERS_PLUS_POWERBALL = 50000;
    private static final int FOUR_NUMBERS = 100;
    private static final int THREE_NUMBERS_PLUS_POWERBALL = 100;
    private static final int THREE_NUMBERS = 7;
    private static final int TWO_NUMBERS_PLUS_POWERBALL = 7;
    private static final int TWO_NUMBERS = 4;
    private static final int ONE_NUMBER_PLUS_POWERBALL = 4;
    private static final int POWERBALL_ONLY = 4;
    //Declare global constant to be able to cheat
    private static final int WINNING_NUMBER = -267549;
    //Declare global constants for lottery ticket price and amount of starting money
    private static final int LOTTERY_TICKET_PRICE = 2;
    private static final int START_MONEY = 1000;
    //Declare global constant for size of array 
    private static final int SIZE = 5;
    //Declare global constants for ranges of lottery ticket numbers
    private static final int LOW = 1;
    private static final int HIGH_LOTTERY = 69;
    private static final int HIGH_POWERBALL = 26;
    
    //Main
    public static void main(String[] args)  {
    //Declare variable to hold actual amount of money player has  
      int ticketsSold = 1;
      int playerPowerballNumber = 0;
      int totalMoney = START_MONEY;
    //Display welcome message to explain how to win
    displayWelcomeMessage();
    //Display total amount of money user has to start with
    displayStartingTotal();
    //Prompt user for ticket sales and return amount 
    ticketsSold = getTicketsSold(totalMoney);
    do{
    //Create array to hold winning lottery ticket numbers
    int [] winningLotteryNum = new int [SIZE];
    //Initialize winning lottery ticket array with unique values
    initializeArrayWithUniqueNumbers(winningLotteryNum);
    //Generate winning Powerball Number
    int winPowerballNumber = getRandomNumber(LOW, HIGH_POWERBALL);
    //Sort winning ticket array into ascending order
    sortWinningTicket(winningLotteryNum);
    //Display winning lottery tickets followed by winning Powerball Number
    displayWinningNumbers(winningLotteryNum, winPowerballNumber);
    
   //Create array to hold player's lottery ticket numbers
     displayLotteryMsg();
    //For-loop to loop initializing and displaying tickets for amount of tickets sold
    for (int i = 0; i < ticketsSold; i++) {
      int[] playerLotteryNum = new int[SIZE];
   //Initialize player's ticket lottery with unique random numbers
     initializeTicketWithUniqueNumbers(playerLotteryNum);
   //Generate player's Powerball Number
     playerPowerballNumber = getRandomNumber(LOW, HIGH_POWERBALL);
   //Sort player's ticket into ascending order
     sortPlayerTicket(playerLotteryNum);
      //Create winning ticket if secret cheat code is entered
     if (ticketsSold == WINNING_NUMBER) {
       for (int a = 0; i < winningLotteryNum.length; a++) {
         for (int j = 0; j < playerLotteryNum.length; j++) {
         playerLotteryNum[j] = winningLotteryNum[a];
       }
       }
     }
   //Display player's lottery tickets followed by winning Powerball Number and amount won
     displayPlayerTicket(winningLotteryNum, playerLotteryNum, playerPowerballNumber, winPowerballNumber);
   //Return count of matching lottery ticket numbers
     int count = getMatchingNumberAmount(winningLotteryNum, playerLotteryNum);
   //Return count of matching powerball numbers
     int pbCount = getMatchingPowerballAmount(playerPowerballNumber, winPowerballNumber);
   //Call function to determine amount of money won per ticket and adds to total money
     int tempSum = getWinningTicketAmount(count, pbCount);
     totalMoney = totalMoney + tempSum;
    } //loops through amount of tickets
    //Call function to calculate new total
     totalMoney = getNewTotal(totalMoney, ticketsSold);
    //Display new total to user and ask if they'd like to buy more tickets
     System.out.println("You have $" + totalMoney);
     ticketsSold = getTicketsSold(totalMoney);
   
    } while (ticketsSold != 0);

    System.out.println("Goodbye!");
  }//end main
  
  //Module to display welcome message with winnings
  private static void displayWelcomeMessage() {
    System.out.println("****************************************************************************************************");
    System.err.println("                                     Let's play Powerball!                                              ");
    System.out.println("****************************************************************************************************");
    System.out.println("5 numbers correct plus powerball = $"+ String.format("%,d", FIVE_NUMBERS_PLUS_POWERBALL)); //add formatter to display commas with integer
    System.out.println("5 numbers correct, no powerball = $" + String.format("%,d", FIVE_NUMBERS));
    System.out.println("4 numbers correct plus powerball = $" + String.format("%,d", FOUR_NUMBERS_PLUS_POWERBALL));
    System.out.println("4 numbers correct, no powerball = $" + FOUR_NUMBERS);
    System.out.println("3 numbers correct plus powerball = $" + THREE_NUMBERS_PLUS_POWERBALL);
    System.out.println("3 numbers correct, no powerball = $" + THREE_NUMBERS);
    System.out.println("2 numbers correct plus powerball = $" + TWO_NUMBERS_PLUS_POWERBALL);
    System.out.println("2 numbers correct, no powerball = $" + TWO_NUMBERS);
    System.out.println("1 number correct plus powerball = $" + ONE_NUMBER_PLUS_POWERBALL);
    System.out.println("0 numbers correct plus powerball = $" + POWERBALL_ONLY);
    System.out.println("****************************************************************************************************");
    }//end module

  //Module to display starting amount of money player has
  private static void displayStartingTotal() {
  
  System.out.println("You have $" + START_MONEY);
}//end module
  
  //Function to sell tickets and decline purchases that surpass player's total money
  private static int getTicketsSold (int totalMoney) {
  int ticketsSold = getInteger ("How many $" + LOTTERY_TICKET_PRICE + " lottery cards do you want to purchase?");
  int ticketTotal = ticketsSold * LOTTERY_TICKET_PRICE;
  
  //While-statement to loop with error message when player tries to buy more tickets than they can afford or less tickets than possible
  while (ticketsSold != WINNING_NUMBER && ticketsSold < 0 || ticketTotal > totalMoney) {
  if (ticketsSold < 0) {
      System.err.println("You can't buy negative tickets!");
  } else if (ticketTotal > totalMoney && ticketsSold != WINNING_NUMBER) {
      System.err.println("You can't afford " + ticketsSold + " tickets! You have $" + totalMoney + ".");
  }
  ticketsSold = getInteger ("Please try again. How many $" + LOTTERY_TICKET_PRICE + " lottery cards do you want to purchase?");
  ticketTotal = ticketsSold * LOTTERY_TICKET_PRICE;
  }
  if (ticketsSold == WINNING_NUMBER) {
     System.out.println("YOU GET A CAR! AND YOU GET A CAR! EVERYBODY GETS A CAR!!"); //display if secret code worked
  }
   if (ticketsSold == 1 && ticketsSold != WINNING_NUMBER) {
       System.out.println("You have purchased " + ticketsSold + " ticket.");
  } else if (ticketsSold > 1) {
       System.out.println("You have purchased " + ticketsSold + " tickets.");
  }
  return ticketsSold;
  }//end function
  
  //Initialize winning ticket array
  private static void initializeArrayWithUniqueNumbers (int [] winningLotteryNum) {
    for (int x = 0; x < winningLotteryNum.length; x++) {
      winningLotteryNum[x] = getUniqueRandomNumber(winningLotteryNum);
      }
    }//end module
  
  //Function to check if number is unique before adding to winning lottery ticket
  public static int getUniqueRandomNumber (int [] winningLotteryNum) {
  int randomNbr = getRandomNumber(LOW, HIGH_LOTTERY); 
  while (isDuplicate(winningLotteryNum, randomNbr)) {
    randomNbr = getRandomNumber(LOW, HIGH_LOTTERY);
  }
  return randomNbr; //return unique number
  }//end function
  
  //Create boolean to check if random number is unique to lottery ticket
  public static boolean isDuplicate(int [] winningLotteryNum, int valueToCheck){
    for (int x = 0; x < winningLotteryNum.length; x++) {
      if (valueToCheck == winningLotteryNum[x]) {
        return true;  //it's a duplicate!
      }
    }
       return false; //the number is unique!
  }//end boolean
  
  //Module to sort winning ticket in ascending order
  private static void sortWinningTicket (int [] winningLotteryNum) {
    int tempNum; //variable to hold number while being compared
 //Create for-loop to loop and sort through all of the winning lottery ticket numbers until they've all been compared
             for (int i = 0; i < winningLotteryNum.length; i++) {
                 for (int j = i + 1; j < winningLotteryNum.length; j++) {
                     if (winningLotteryNum[i] > winningLotteryNum[j]) {
                           tempNum = winningLotteryNum[i];
                           winningLotteryNum[i] = winningLotteryNum[j];
                           winningLotteryNum[j] = tempNum;
                    }
                 }
               } 
    }//end module
  
  //Module to display winning lottery tickets followed by Powerball Number
  private static void displayWinningNumbers(int [] winningLotteryNum, int winPowerballNumber) {
  System.out.println("The winning lottery numbers, followed by the Powerball Number, are:");
      for (int i = 0; i < winningLotteryNum.length; i++) {
          System.out.print(winningLotteryNum[i] + " ");
      }
      System.err.print(winPowerballNumber);
  }//end module
 
 
  //Initialize player ticket array with random unique values
  private static void initializeTicketWithUniqueNumbers (int [] playerLotteryNum) {
    for (int x = 0; x < playerLotteryNum.length; x++) {
      playerLotteryNum[x] = getUniqueRandomTicket(playerLotteryNum);
      }
    }//end module
  
  //Function to check if number is unique before adding to player lottery ticket
  public static int getUniqueRandomTicket (int [] playerLotteryNum) {
  int randomNbr = getRandomNumber(LOW, HIGH_LOTTERY); 
  while (isDuplicateTicket(playerLotteryNum, randomNbr)) {
    randomNbr = getRandomNumber(LOW, HIGH_LOTTERY);
  }
  return randomNbr; //return unique number
  }//end function
  
  //Create boolean to check if random number is unique to player's lottery ticket
  public static boolean isDuplicateTicket(int [] playerLotteryNum, int valueToCheck){
    for (int x = 0; x < playerLotteryNum.length; x++) {
      if (valueToCheck == playerLotteryNum[x]) {
        return true;  //it's a duplicate!
      }
    }
       return false; //the number is unique!
  }//end boolean
  
  //Module to sort player's ticket in ascending order
  private static void sortPlayerTicket (int [] playerLotteryNum) {
    int tempNum; //variable to hold number while being compared
 //Create for-loop to loop and sort through all of the winning lottery ticket numbers until they've all been compared
             for (int i = 0; i < playerLotteryNum.length; i++) {
                 for (int j = i + 1; j < playerLotteryNum.length; j++) {
                     if (playerLotteryNum[i] > playerLotteryNum[j]) {
                           tempNum             = playerLotteryNum[i];
                           playerLotteryNum[i] = playerLotteryNum[j];
                           playerLotteryNum[j] = tempNum;
                    }
                 }
               } 
    }//end module
  
  //Module to display winning lottery tickets followed by Powerball Number
  private static void displayPlayerTicket(int [] winningLotteryNum, int[] playerLotteryNum, int playerPowerballNumber, int winPowerballNumber) {
    int count = 0;
    int pbCount = 0;
          for (int i = 0; i < playerLotteryNum.length; i++){
            for (int j = 0; j < winningLotteryNum.length; j++){
              if (playerLotteryNum[i] == winningLotteryNum[j]) {
                 System.err.print(playerLotteryNum[i] + " ");
                 j = winningLotteryNum.length + 1;   // exit loop once matching number is found
                 count++;
          }
              else if (j == winningLotteryNum.length - 1) {
          System.out.print(playerLotteryNum[i] + " ");
            }
        }  
          }
      if (playerPowerballNumber == winPowerballNumber) {
        System.err.print(playerPowerballNumber + "   ");
        pbCount++;
      }
      else {
         System.out.print(playerPowerballNumber + "   ");
    
      
  }   //If-else statements to display amount of money won 
      if (count == 0 && pbCount == 1) {
        System.out.print("You won $" + POWERBALL_ONLY + "!");
      } else if (count == 1 && pbCount == 1) {
          System.out.print("You won $" + ONE_NUMBER_PLUS_POWERBALL + "!");
      } else if (count == 2 && pbCount == 1) {
          System.out.print("You won $" + TWO_NUMBERS_PLUS_POWERBALL + "!");
      } else if (count == 3 && pbCount == 0) {
          System.out.print("You won $" + THREE_NUMBERS + "!");
      } else if (count == 3 && pbCount == 1) {
          System.out.print("You won $" + THREE_NUMBERS_PLUS_POWERBALL + "!");
      } else if (count == 4 && pbCount == 0) {
          System.out.print("You won $" + FOUR_NUMBERS + "!");
      } else if (count == 4 && pbCount == 1) {
          System.out.print("You won $" + String.format("%,d", FOUR_NUMBERS_PLUS_POWERBALL) + "!");
      } else if (count == 5 && pbCount == 0) {
          System.out.print("You won $" + String.format("%,d", FIVE_NUMBERS) + "!");
      } else if (count == 5 && pbCount == 1) {
          System.out.print("You won $" + String.format("%,d", FIVE_NUMBERS_PLUS_POWERBALL) + "!");
      }//end if
      System.out.println("");
      }//end module


     //Module to display message before player's ticket numbers
    private static void displayLotteryMsg() {
    System.out.println("");
    System.out.println("Your lottery numbers, followed by the Powerball Number, are: ");
    }//end module
    
    //Create function to count and return number of matching lottery ticket numbers
    private static int getMatchingNumberAmount(int [] winningLotteryNum, int[] playerLotteryNum) {
    int count = 0;
          for (int i = 0; i < playerLotteryNum.length; i++){
            for (int j = 0; j < winningLotteryNum.length; j++){
              if (playerLotteryNum[i] == winningLotteryNum[j]) {
                 count++;
              }
            }
          }
        return count;
        }//end function
    
   //Create function to count and return number of matching powerball tickets
    private static int getMatchingPowerballAmount(int playerPowerballNumber, int winPowerballNumber) {
    int pbCount = 0;
    if (playerPowerballNumber == winPowerballNumber) {
        pbCount++;
        }
       return pbCount;
  }//end function
    
  //Create function to return value of winning ticket
    private static int getWinningTicketAmount(int count, int pbCount) {
    int sum = 0;
    //Give new value to sum when right parameters are met
      if (count == 0 && pbCount == 1) {
          sum = POWERBALL_ONLY;
      } else if (count == 1 && pbCount == 1) {
          sum = ONE_NUMBER_PLUS_POWERBALL;
      } else if (count == 2 && pbCount == 1) {
          sum = TWO_NUMBERS_PLUS_POWERBALL;
      } else if (count == 3 && pbCount == 0) {
          sum = THREE_NUMBERS;
      } else if (count == 3 && pbCount == 1) {
          sum = THREE_NUMBERS_PLUS_POWERBALL;
      } else if (count == 4 && pbCount == 0) {
          sum = FOUR_NUMBERS;
      } else if (count == 4 && pbCount == 1) {
          sum = FOUR_NUMBERS_PLUS_POWERBALL;
      } else if (count == 5 && pbCount == 0) {
          sum = FIVE_NUMBERS;
      } else if (count == 5 && pbCount == 1) {
          sum = FIVE_NUMBERS_PLUS_POWERBALL;
      } //end if
     return sum;
    }//end function
    
    //Function to calculate and return player's new total amount of money
    private static int getNewTotal(int totalMoney, int ticketsSold) {
    int newTotal = totalMoney - (ticketsSold * LOTTERY_TICKET_PRICE);
   return newTotal;
    }//end function
    
    //Function to generate random number, inclusive
     public static int getRandomNumber (int low, int high) {
       return (int)(Math.random() * ((high + 1) - low)) + low;
     }//end function
    
     //Function to get integer input
     public static int getInteger(String msg) {
      System.out.println(msg);
      while (!keyboard.hasNextInt()) {
         keyboard.nextLine();
         System.err.println("Invalid integer. Try again.");
      }
      int number = keyboard.nextInt();
      keyboard.nextLine(); //flushes the buffer
      return number;
   }//end function
    
}//end class