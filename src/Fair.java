import java.lang.reflect.Array;
import java.util.*;

public class Fair {
    private static String amounts;
    private static String names;
    private static ArrayList<Winner> winners = new ArrayList<>();

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Enter lottery amounts separated by commas: ");

        if (userInput.hasNext()) {
            amounts = userInput.next();
        }

        System.out.print("Enter the contestants names separated by commas: ");

        if (userInput.hasNext()) {
            names = userInput.next();
        }

        String amountArray[] = amounts.split(",");
        String namesList[] = names.split(",");

        // Convert strings to numbers in the amountArray
        Integer[] amountsList = new Integer[ amountArray.length ];
        for( int i = 0; i < amountArray.length; ++i ) {
            amountsList[i] = Integer.parseInt( amountArray[i] );
        }

        award(namesList, amountsList);
    }

    // Method that awards the amounts to the winners
    public static void award(String names[], Integer amountsList[]) {
        // Randomize the contestants then add to winner arraylist as Winner object for ease of use
        ArrayList<String> namesList = shuffleList( names );
        for( int i = 0; i < names.length; ++i  ) {
            Winner winner = new Winner( namesList.get(i) );
            winners.add( winner );
        }

        // Sort prizes in descending order
        Arrays.sort(amountsList, Collections.reverseOrder());

        // Initialize arraylist to keep track of chosen amounts
        ArrayList<Integer> chosenAmounts = new ArrayList<>();

        // Assign amounts in descending order first while keeping track of chosen amounts
        for( int i = 0; i < winners.size(); ++i ) {
            winners.get(i).addAmount( amountsList[i] );
            chosenAmounts.add( amountsList[i] );
        }

        // Loop through the winners from index 1 while adding the remaining amounts
        for( int i = 1; i < winners.size(); ++i ){
            // Loop checking if amount selected is already chosen
            for( int j = 0; j < amountsList.length; ++j ) {
                // boolean to check if amount is chosen
                boolean chosen = false;

                for( int k = 0; k < chosenAmounts.size(); ++k ) {
                    if( amountsList[j] == chosenAmounts.get(k) ) {
                        chosen = true;
                    }
                }

                // If amount is not already choose, check if when added, it does not exceed the first winner amount
                // If it exceeds, don't add
                // If it does not exceed, add amount to current winner and add amount to chosen amounts
                if( chosen == false ) {
                    if( winners.get(0).getAmountTotal() >= ( winners.get(i).getAmountTotal() + amountsList[j] ) ) {
                        winners.get(i).addAmount( amountsList[j] );
                        chosenAmounts.add( amountsList[j] );
                    }
                }
            }
        }

        // Print out results
        for( int i = 0; i < winners.size(); ++i ) {
            System.out.print( winners.get(i).toString() );
        }
    }

    // Shuffle names list using Collections
    private static ArrayList shuffleList( String list[] ) {
        ArrayList<String> solution = new ArrayList<>();
        for (int i = 0; i < list.length; ++i) {
            solution.add(list[i]);
        }
        Collections.shuffle(solution);

        return solution;
    }
}