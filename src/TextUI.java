import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TextUI {
    private Scanner scan = new Scanner(System.in);

    public void displayMsg(String msg){
        System.out.println(msg);
    }

    public int displayHomeMenu(String msg) {
        displayMsg("1. Find movie or series. \n2. Search genre. \n3. See previously watched movies and series. \n4. See watchlist. \n5. Log out");
        int choice = promptNumeric(msg);
        return choice;
    }




    public int promptNumeric(String msg) {
        System.out.println(msg);              // Stille brugeren et spørgsmål
        String input = scan.nextLine();
        int number;
        // Give brugere et sted at placere sit svar og vente på svaret
        try {
            number = Integer.parseInt(input);
        }
        catch(NumberFormatException e){
            displayMsg("Please type a number");
            number = promptNumeric(msg);
        }
        return number;
    }

    public String promptText(String msg){
        System.out.println(msg);//Stille brugeren et spørgsmål
        String input = scan.nextLine();
        return input;
    }

    public ArrayList<String> promptChoice(ArrayList<String> options, int limit, String msg){
        ArrayList<String> choices = new ArrayList<String>();  //Lave en beholder til at gemme brugerens valg
        int count = 1;
        while(choices.size() < limit){             //tjekke om brugeren skal vælge flere drinks
            String choice = promptText(count+":");
            choices.add(choice);
            count++;
        }
        return choices;
    }
    /*public boolean promptBinary(String msg){
            String input = promptText(msg);
            if(input.equalsIgnoreCase("Y")){
                return true;
            }
            else if(input.equalsIgnoreCase("N")){
                return false;
            }
            return promptBinary(msg);
        }

    public void displayList(ArrayList<String> options, String msg){
        System.out.println("*******");
        System.out.println(msg);
        System.out.println("*******");

        int i = 1;

        for (String option : options) {
            System.out.println(i+": "+option);
            i++;
        }
    }
     */
}