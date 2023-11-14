import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Logic {

    Logic(Scanner scanner, Path inputdirectory, Path outputDirectory) {
        this.inputdirectory = inputdirectory;
        this.scanner = scanner;
        this.outputDirectory = outputDirectory;
    }

    //----------------------------------------------
    public void run() throws IOException {
        printList();
        sort();
    }

    //-----------------------------------------------
    private final Path inputdirectory;
    private Scanner scanner;
    private final Path outputDirectory;
    private DirectoryStream<Path> dstream;
    private String filetype;
    private String filename;
    //-----------------------------------------------
    private final ArrayList<String> filelist = new ArrayList<String>();
    //-----------------------------------------------

    public void sort() throws IOException {
        sortDirectorys();
        sortFiles();
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
            else {


            }

        }

    }

    public void sortFiles() throws IOException {

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


    public void printFileType() {
        System.out.println(filename + " | " + filetype);
    }

    public void printList() {

        for (int i = 0; i < filelist.size(); i++) {

            System.out.println(filelist.get(i));

        }

    }

}



