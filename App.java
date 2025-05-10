import java.io.*;

//the flow of my code goes like this SQL_SORTER + DISPLAYS -> APP + DISPLAYS -> DRIVER class
//To make it simpler
public class App {

    //Made these guys static because I need to be able access them through my functions
    //And also I am more comfortable using buffered reader than scanner as a personal preference
    public static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    public static Displays display = new Displays();
    public static SQL_Sorter sort = new SQL_Sorter();

    //Filter functions
    //What these functions do is to filter out characters and negative integers especially
    //if the input requires an integer
    //namely id_purchase, quantity, purchase_reference, and purchase_type

    //This function is for the id_purchase
    public int promptID()
    {
        //Made it negative one so that the while loop would initialize
        int id = -1;

        //I am assuming that the id of purchase history cannot take any negatives since
        //I haven't seen any IDs that goes like -12345
        while (id < 0)
        {
            System.out.print("Enter ID: ");
            try
            {
                id = Integer.parseInt(read.readLine());
                if (id < 0)
                {
                    display.displayNegativeNumericalError(1);
                }
            }
            catch (Exception e)
            {
                display.displayCharacterInputError();
            }
        }
        return id;
    }

    //this function is for quantity
    public int promptQuantity()
    {
        int quantity = 0;

        //This is to ensure that we don't get -1234 as a quantity because who does that haha
        while (quantity <= 0)
        {
            try
            {
                System.out.print("Enter quantity: ");
                quantity = Integer.parseInt(read.readLine());
                if (quantity <= 0)
                {
                    display.displayNegativeNumericalError(0);
                }
            }
            catch (Exception e)
            {
                display.displayCharacterInputError();
            }
        }
        return quantity;
    }

    //this function is for payment type
    int promptPaymentType(int id)
    {
        int paymentType = -1;

        //I am not sure what payment type, but I am assuming that it doesn't any negatives
        while (paymentType < 0)
        {
            try
            {
                System.out.printf("Enter payment type of id %d: ", id);
                paymentType = Integer.parseInt(read.readLine());
                if (paymentType < 0)
                    display.displayNegativeNumericalError(1);
            }
            catch (Exception e)
            {
                display.displayCharacterInputError();
            }
        }

        return paymentType;
    }

    //Command line interface
    public void commandLine()
    {
        //These are the values that are going to be passed down to a parameter
        //NOTE: I did it in snake case because it was easier for me to read that in an SQL file
        String item_name;
        int quantity;
        int id_purchase_history;
        int payment_type;
        String payment_reference; //I am not sure what payment reference is, this is coming from me who prefers to bury their money
                                  //than go to the bank due to my distrust to big businesses
        boolean flag = true;
        int input;
        display.displayNameOfApp();

        //Used a flag to make it simpler to operate and as a personal preference
        while(flag)
        {
            try {
                display.displayMenu();
                System.out.print("What will be your input: ");
                input = Integer.parseInt(read.readLine());
                switch (input) {
                    case 1:
                        System.out.println("Entry is now being viewed");
                        System.out.print("Search ID: ");
                        id_purchase_history = Integer.parseInt(read.readLine());
                        sort.searchItem(id_purchase_history);
                        break;
                    case 2:
                        System.out.println("Entry is now being entered");
                        id_purchase_history = promptID();
                        System.out.print("Enter item name: ");
                        item_name = read.readLine();
                        quantity = promptQuantity();
                        payment_type = promptPaymentType(id_purchase_history);
                        System.out.printf("What is the payment reference of %d: ", id_purchase_history);
                        payment_reference = read.readLine();

                        sort.insertItem(id_purchase_history, item_name, quantity, payment_type, payment_reference);
                        break;
                    case 3:
                        System.out.println("Bye");
                        flag = false;
                        break;
                    default:
                        System.out.println("Error: Invalid Input!");
                        System.out.println("Program cannot understand what the user wants");
                        break;
                }
            }
            catch (Exception e)
            {
                display.displayCharacterInputError();
            }
        }
    }
}
