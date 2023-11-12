import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Logic {

    Logic(Scanner scanner, InputStream extensionreader, Path inputdirectory, Path outputDirectory) {

        this.extensionreader = extensionreader;
        this.extensionScanner = extensionScanner;
        this.inputdirectory = inputdirectory;
        this.scanner = scanner;
        this.outputDirectory = outputDirectory;
    }

    //----------------------------------------------
    public void run() throws IOException {
        makeExtensionList();
        printList();
        sortFiles();


    }

    //-----------------------------------------------
    private InputStream extensionreader;
    private final Path inputdirectory;
    private Scanner extensionScanner;
    private Scanner scanner;
    private final Path outputDirectory;
    private DirectoryStream<Path> dstream;
    private String filetype;
    private String filename;
    //-----------------------------------------------
    private final ArrayList<String> filelist = new ArrayList<String>();
    private final ArrayList<String> extensionlist = new ArrayList<String>();

    //-----------------------------------------------


    public void sortFiles() throws IOException {

        dstream = Files.newDirectoryStream(inputdirectory);

        for (Path i : dstream) {
            filename = String.valueOf(i.getFileName());

            int index = filename.lastIndexOf(".");
            if (index > 0) {
                filetype = filename.substring(index + 1);
            }

            printFileType();

            if (extensionlist.contains(filetype)) {

                Path outputfolder = Path.of(outputDirectory + "\\" + filetype);
                Path finaloutputdir = Path.of(outputfolder + "\\" + filename);

                if (Files.exists(outputfolder)) {
                    Files.createFile(finaloutputdir);
                }
                else{
                    Files.createDirectory(outputfolder);
                    Files.createFile(finaloutputdir);
                }

                if (Files.isDirectory(i)) {
                    outputfolder = Path.of(outputDirectory + "\\Directories");
                    finaloutputdir = Path.of(outputfolder + "\\" + filename);

                    if (Files.exists(outputfolder)) {
                        Files.createFile(finaloutputdir);
                    }
                    else {
                        Files.createDirectory(outputfolder);
                        Files.createFile(finaloutputdir);
                    }
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

    public void makeExtensionList() {

        extensionScanner = new Scanner(extensionreader);

        while (extensionScanner.hasNextLine()) {
            extensionlist.add(extensionScanner.nextLine());
        }

    }

}



