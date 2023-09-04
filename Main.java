import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class dnmIntArray {
  // Create a dynamic integer array
  // Give credits when using this!
  // The dynamic array: 
  int[] array = new int[0];
  // Append function
  void append(final int initElement) {
      // The new array used to replace the old one
      final int[] newArray = new int[(array.length + 1)];
      int args = 0;

      // Copy all elements to the new array
      for (final int arg: array) {
        newArray[args] = arg;
        args += 1;
      }

      // Config the last element
      newArray[(newArray.length - 1)] = initElement;
      // Replace the array with a new one
      array = newArray;
  }
}

public class Main {
// Unused
  public static String getAnsiRed() {
		return ANSI_RED;
	}

	public static String getAnsiReset() {
		return ANSI_RESET;
	}
	
// Unicode colours
public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLUE = 	"\u001B[34m";

  public static String cRed() {
	  return ANSI_RED;
  }

  public static String cReset() {
	  return ANSI_RESET;
  }

  public static String cBlue() {
    return ANSI_BLUE;
  }

@Override
public String toString() {
	return "Main []";
}

public static void main(final String[] argv) {

    System.out.println(cBlue() + "Prime Number Lister" + cReset());
    System.out.println("Enter number start: ");
    // Variables setup
    boolean inputComplete1 = false;
    int startNumber = 0, endNumber = 0;
    // Repeat until input is valid
    do {
      // Input scanner setup
      final Scanner sc = new Scanner(System.in);
      try {
        startNumber = sc.nextInt();
        inputComplete1 = true;
      } 
      catch (final InputMismatchException e) {
	// Exception handle for incorrect input
        System.out.println(cRed() + "Not a valid number" + cReset() );
        inputComplete1 = false;
      }
    } while (!inputComplete1);
	
    System.out.println("Enter number end: ");
    boolean inputComplete2 = false;
    do {
      final Scanner sc2 = new Scanner(System.in);
      try {
        endNumber = sc2.nextInt();
        inputComplete2 = true;
      } 
      catch (final InputMismatchException e) {
        System.out.println(cRed() + "Not a valid number" + cReset() );
        inputComplete2 = false;
      }
    } while (!inputComplete2);

    // Create the prime number array
    final dnmIntArray primeNumbers = new dnmIntArray();
    // Loop (End Number - Start Number) Times
    for (int x = startNumber; x<=endNumber; x++) {
      // Number detect
      switch (x) {
        // Whitelist 2,3,5,7
        case 2:
        case 3:
        case 5:
        case 7:
          primeNumbers.append(x);
          continue;
        default:
          if (x == 1)
            continue;
          if (x % 2 == 0)
            continue;
          if (x % 3 == 0)
            continue;
          if (x % 5 == 0)
            continue;
          if (x % 7 == 0)
            continue;
          primeNumbers.append(x);
      }
    }
      try {
	// Read file
        final File myObj = new File("result.txt");
	// Create file
        if (myObj.createNewFile()) {
          System.out.println("File created, check for results: " + myObj.getName());
        } else {
          System.out.println("File <result.txt> already exists. Check for results");
        }
      } catch (final IOException e) {
	// Handle unexpected errors
        System.out.println(cRed() + "Could not create/read file. Results will be printed." + cReset());
      }
      String trace = "";
      try {
	// Write result to file
        final FileWriter clear = new FileWriter("result.txt",false);
        clear.write("");
     } catch (IOException e) {
        System.out.println(cRed() + "File could not be edited." + cReset());
     }
      for (final int y: primeNumbers.array) {
        try {
          final FileWriter myWriter = new FileWriter("result.txt",true);
          myWriter.write("\n" + y);
          // Close the file
          myWriter.close();
        } catch (IOException e) {
	  // Print result if error
          System.out.println(y);
        }
      }
      System.out.println(trace);
    }
  }
