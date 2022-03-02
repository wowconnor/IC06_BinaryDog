import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String name = "", breed = "";
        int age, count = 0;;
        Dog[] dogPound = new Dog[10];

        File binaryFile = new File("Dogs.dat");
        // check non-zero size
        System.out.println("Previously saved files from binary file:");
        if(binaryFile.exists() && binaryFile.length()>1L)
        {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                // returns object datatype, typecast
                dogPound = (Dog[]) fileReader.readObject();
                // loop through array
                while(dogPound[count] != null)
                    System.out.println(dogPound[count++]);
                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        else
            System.out.println("[None, please enter new dog data]");
        do
        {
            System.out.print("Please enter dog's name (or \"quit\" to exit): ");
            name = keyboard.nextLine();
            if("quit".equalsIgnoreCase(name))
                break;
            System.out.print("Please enter dog's breed: ");
            breed = keyboard.nextLine();
            System.out.print("Please enter dog's age: ");
            age = keyboard.nextInt();
            System.out.println();

            dogPound[count++] = new Dog(name, breed, age);

            keyboard.nextLine();


        }while(true);

        // write the array to the binary file.
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWriter.writeObject(dogPound);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
}
