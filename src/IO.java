/**
 *This class reads and writes from files.
 *
 *@author Ariel Wigdorowitz
 *@version 2022.06.19
 */

import java.util.*;
import java.io.*;

public class IO
{
    //the input file
    public static final String FILE_INPUT = "vehicles.txt";
    //the output file
    public static final String FILE_OUTPUT = "output.txt";

    public IO ()
    {
        readFile();
    }

    /**
     *Reads from a file and returns the contents of the file.
     *@return returns the contents of a file.
     */

    public String readFile()
    {
        String contents = "";
        try
        {
            FileReader reader = new FileReader(FILE_INPUT);
            Scanner sc = new Scanner(reader);

            while (sc.hasNextLine())
            {
                contents += sc.nextLine() + "\n";
            }
            reader.close();
        }
        catch (FileNotFoundException fnfo)
        {
            System.out.println("Error: File not Found !");
        }

        catch (IOException ioe)
        {
            System.out.println("Error: an error with I/O!");
        }

        catch (Exception e)

        {
            System.out.println("Error: an error occured!");
        }

        return contents;
    }

    /**
     *Writes content to a specified file
     *@param contents - the contents to be read to a file
     */

    public void writeFile (String contents)
    {
        try
        {
            FileWriter writer = new FileWriter (FILE_OUTPUT, true);

            writer.append(contents + "\n");
            writer.close();

        }

        catch (Exception e)
        {
            System.out.println("Error writing to file !");
        }
    }
}

