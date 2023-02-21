package program;

import helpers.Console;

import java.io.*;
import java.util.Scanner;

public class FileIOProgram
{
    private Console console = new Console();

    public static void main(String[] args)
    {
        //create a file directory if it doesn't exists
        File filesDir = new File("files");
        if (!filesDir.exists())
        {
            filesDir.mkdir();
            System.out.println("'files' directory created");
        }

        FileIOProgram program = new FileIOProgram();
        program.welcome();
        program.runProgram();
    }

    private void welcome()
    {
        console.println("Welcome to my File IO program!");
        console.println("------------------------------");
        console.newLine();
    }

    private void runProgram()
    {
        int userChoice = -1;
        while (userChoice != 4)
        {
            printMenu();
            userChoice = console.getInt("Enter a choice");

            switch (userChoice)
            {
                case 1: printFiles(); break;
                case 2: openFile(); break;
                case 3: printFile(); break;
            }
        }
    }

    private void printFiles()
    {
        File dir = new File("files");
        File[] contents = dir.listFiles();

        for (int i = 0; i < contents.length; i++)
        {
            File thing = contents[i];
            if (thing.isFile())
            {
                System.out.println(thing.getAbsolutePath());
            }
        }
        console.newLine();
    }

    private void openFile()
    {
        String name = console.getString("Enter a file name");
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File("files/" + name))))
        {
            int lines = console.getInt("How many lines?");
            for (int i = 0; i < lines; i++)
            {
                String line = console.getString("Enter a line of text");
                writer.println(line);
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error opening file: " + ex.getMessage());
        }
        console.newLine();
    }

    private void printFile()
    {
        String name = console.getString("Enter a file name");
        try (Scanner reader = new Scanner(new FileInputStream(new File("files/" + name))))
        {
            while (reader.hasNextLine())
            {
                System.out.println(reader.nextLine());
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error opening file: " + ex.getMessage());
        }
        console.newLine();
    }

    private void printMenu()
    {
        console.println("1. print files directory");
        console.println("2. open file");
        console.println("3. print file");
        console.println("4. exit");
    }
}