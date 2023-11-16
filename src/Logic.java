import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Logic {

    Logic(Scanner scanner, Path inputdirectory, Path outputDirectory) {
        this.inputdirectory = inputdirectory;
        this.scanner = scanner;
        this.outputDirectory = outputDirectory;
    }

    //----------------------------------------------
    public void run() throws IOException {
        confirmPath();
        printList();
        sort();
    }

    //-----------------------------------------------
    private final Path inputdirectory;
    private Scanner scanner;
    private Path outputDirectory;
    private DirectoryStream<Path> dstream;
    private String filetype;
    private String filename;
    //-----------------------------------------------
    private final ArrayList<String> filelist = new ArrayList<String>();
    private final ArrayList<String> confirmchoice = new ArrayList<String>(Arrays.asList("YES", "NO"));
    //-----------------------------------------------

    private void sort() throws IOException {
        sortDirectorys();
        sortFiles();
    }

    public void confirmPath() throws IOException {

        System.out.println("Confirm path ( YES / NO ): ");
         String confirmuserchoice = scanner.nextLine().toUpperCase();

        while(!confirmchoice.contains(confirmuserchoice)){
            System.out.println("Wrong answer, pick YES/NO: ");
                confirmuserchoice = scanner.nextLine().toUpperCase();
        }

        if(confirmuserchoice.equals("YES")){
            askSortType();
        }
        else if (confirmuserchoice.equals("NO")){
            System.out.println("Do you want to change the path? ( YES / NO )");
            confirmuserchoice = scanner.nextLine().toUpperCase();

            while(!confirmchoice.contains(confirmuserchoice)){
                System.out.println("Wrong answer, pick YES/NO: ");
                confirmuserchoice = scanner.nextLine().toUpperCase();
            }

            if(confirmuserchoice.equals("NO")){
                System.exit(0);
            }

            else if (confirmuserchoice.equals("YES")){
                System.out.println("Enter the new file output path: ");
                outputDirectory = Path.of(scanner.nextLine());
                System.out.println("New file output path is: " + outputDirectory);
                confirmPath();
            }
        }

    }

    public void askSortType() throws IOException {
        ArrayList<String> choice = new ArrayList<String>(Arrays.asList("1" , "2"));

        System.out.println("What way do you want the sorting to work?\n1 - Sort only whats inside the folder on top level\n2 - Sort all the files in the folder including subpaths\n: ");
        String userchoice = scanner.nextLine();

        while (!choice.contains(userchoice)){
            System.out.println("Wrong answer, pick 1 or 2.");
            userchoice = scanner.nextLine();
        }

        if (userchoice.equals("1")){
            sort();
            System.exit(0);
        }
        else if (userchoice.equals("2")){
            sortAll(inputdirectory);
        }

    }

    public void sortAll(Path inputdirectory) throws IOException {

        dstream = Files.newDirectoryStream(inputdirectory);

        for (Path i : dstream) {
            filename = String.valueOf(i.getFileName());

            int index = filename.lastIndexOf(".");
            if (index > 0) {
                filetype = filename.substring(index + 1);
            }
            printFileType();

            Path outputfolder2 = Path.of(outputDirectory + "\\" + filetype);
            Path directoriespath2 = Path.of(outputDirectory + "\\" + filetype + "\\" + filename);

            if (Files.isDirectory(i)) {
                sortAll(i);  // Recursively process subdirectories
            }
            else {
                if (!Files.exists(outputfolder2)) {
                    Files.createDirectory(outputfolder2);  // Create directories only if they don't exist
                }
                if(Files.exists(directoriespath2)){
                    //Do nothing
                }
                else{
                    Files.createFile(directoriespath2);
                }

            }

        }

    }

    private void sortDirectorys() throws IOException {

        dstream = Files.newDirectoryStream(inputdirectory);

        for (Path i : dstream) {
            filename = String.valueOf(i.getFileName());

            int index = filename.lastIndexOf(".");
            if (index > 0) {
                filetype = filename.substring(index + 1);
            }
            printFileType();

            Path outputfolder = Path.of(outputDirectory + "\\Directories");
            Path directoriespath = Path.of(outputDirectory + "\\Directories" + "\\" + filename);

            if (Files.isDirectory(i)) {
                if (Files.exists(outputfolder)) {
                    Files.createDirectory(directoriespath);
                }
                else {
                    Files.createDirectory(outputfolder);
                }
            }
            else {}

        }

    }

    private void sortFiles() throws IOException {

        dstream = Files.newDirectoryStream(inputdirectory);

        for (Path i : dstream) {
            filename = String.valueOf(i.getFileName());

            int index = filename.lastIndexOf(".");
            if (index > 0) {
                filetype = filename.substring(index + 1);
            }
            printFileType();

            Path outputfolder1 = Path.of(outputDirectory + "\\" + filetype);
            Path directoriespath1 = Path.of(outputDirectory + "\\" + filetype + "\\" + filename);

            if (Files.isDirectory(i)) {

            }
            else {
                if (Files.exists(outputfolder1)) {
                    Files.createFile(directoriespath1);
                }
                else {
                    Files.createDirectory(outputfolder1);
                    Files.createFile(directoriespath1);
                }
            }

        }
    }


    private void printFileType() {
        System.out.println(filename + " | " + filetype);
    }

    private void printList() {

        for (int i = 0; i < filelist.size(); i++) {

            System.out.println(filelist.get(i));

        }

    }

}