/*
* What this class does is to display error messages and main menus
* I refractored it in order for me not to clutter the code as much as I can
* */

public class Displays {
    //Displays the option menu
    public void displayMenu() {
        System.out.println("=================================");
        System.out.println("<1> View Entry");
        System.out.println("<2> Add Entry");
        System.out.println("<3> Exit");
        System.out.println("=================================");

    }
    //This function is used for to display my custom error message if a character ends up being typed
    //for an integer data type
    public void displayCharacterInputError()
    {
        System.out.println("Error! Invalid Input!");
        System.out.println("Characters are not allowed");
        System.out.println("Must be a positive number or at least zero");
        System.out.println("Please try again!");
    }
    //This function displays my custom error message if a negative number were to be typed in input
    public void displayNegativeNumericalError(int type)
    {
        //type == 1 if 0 is allowed
        if (type == 1)
        {
            System.out.println("Error! Invalid Input!");
            System.out.println("Must be a positive number or at least 0");
            System.out.println("Please try again!");
        }
        //type == 0 if 0 is not allowed
        else if (type == 0)
        {
            System.out.println("Error! Invalid Input!");
            System.out.println("Must be a positive number");
            System.out.println("Please try again!");
        }
    }

    //This function is for displayed a searched item with a specific id
    public void displaySearchedItem(int id, String name, int quantity, int paymentType, String paymentReference)
    {
        System.out.println("\n============================================");
        System.out.printf("Item ID: %d \n", id);
        System.out.printf("Item name: %s \n", name);
        System.out.printf("Item quantity: %d \n", quantity);
        System.out.printf("Item payment type: %d \n", paymentType);
        System.out.printf("Item payment reference: %s \n", paymentReference);
        System.out.println("=============================================\n");

    }

    //This is just the title card hahaha I was planning to make it look grand or something but
    //but decided against it because I realized I needed something simpler
    public void displayNameOfApp()
    {
        System.out.println("=================================");
        System.out.println("         Purchase History        ");
        System.out.println("=================================");

    }
}
