import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Input a directory path: ");
        Path inputdirectory = Path.of(scanner.nextLine());
        System.out.println("Enter a output directory path: ");
        Path outputDirectory = Path.of(scanner.nextLine());

        FileSorter logicClass = new FileSorter(inputdirectory, outputDirectory);
        logicClass.run();

        System.out.println("Files have been successfully sorted!");

    }

}
