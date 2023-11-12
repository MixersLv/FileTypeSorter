import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        InputStream extensionreader = Files.newInputStream(Path.of("extensions.txt"));

        System.out.println("Input a directory path: ");
            Path inputdirectory = Path.of(scanner.nextLine());
        System.out.println("Enter a output directory path: ");
            Path outputDirectory = Path.of(scanner.nextLine());

        Logic logicClass = new Logic(scanner,extensionreader ,inputdirectory, outputDirectory);

        logicClass.run();


    }

}
