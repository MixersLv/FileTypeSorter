import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSorter {

    private final Path inputDirectory;
    private final Path outputDirectory;

    FileSorter(Path inputDirectory, Path outputDirectory) {
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
    }

    public void run() throws IOException {
        makeOutputFolder();
        sortAll(inputDirectory);
    }

    public void makeOutputFolder() throws IOException {
        if (!Files.exists(outputDirectory)) {
            Files.createDirectory(outputDirectory);
        }

    }

    public void sortAll(Path inputdirectory) throws IOException {

        DirectoryStream<Path> dstream = Files.newDirectoryStream(inputdirectory);

        for (Path currentfile : dstream) {
            String filename = String.valueOf(currentfile.getFileName());


            int index = filename.lastIndexOf(".");
            String filetype = "";

            if (index > 0) {
                filetype = filename.substring(index + 1);
            }
            printFileType(filename, filetype);

            Path filetypeFolder = outputDirectory.resolve(filetype); // works the same as beneath
            Path finalOutputPath = Path.of(outputDirectory + "\\" + filetype + "\\" + filename);

            if (Files.isDirectory(currentfile)) {
                sortAll(currentfile);  // Recursively process subdirectories
            } else {
                if (!Files.exists(filetypeFolder)) {
                    Files.createDirectory(filetypeFolder);  // Create directories only if they don't exist
                }
                if (!Files.exists(finalOutputPath)) {
                    Files.copy(currentfile, finalOutputPath);
                }

            }
        }

    }


    private void printFileType(String filename, String filetype) {
        System.out.println(filename + " | " + filetype);
    }
}