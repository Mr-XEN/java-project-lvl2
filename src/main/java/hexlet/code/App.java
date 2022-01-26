package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {

    @Parameters(index = "0", description = "path to first file")
    private File filepath1;

    @Parameters(index = "1", description = "path to second file")
    private File filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;


    public static void main(String[] args) throws IOException {
        System.out.println(Differ.generate());
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

//        System.out.println(Differ.parse(Differ.file1));
//        System.out.println(Differ.parse("file5.json"));
    }

    @Override
    public String call() throws Exception {

        byte[] fileContents1 = Files.readAllBytes(filepath1.toPath());
        byte[] fileContents2 = Files.readAllBytes(filepath2.toPath());

        System.out.println(Differ.parse3(fileContents1));
        System.out.println(Differ.parse3(fileContents2));


        return null;
    }
}
