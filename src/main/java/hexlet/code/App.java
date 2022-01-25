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
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")

public class App {

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;


    public static void main(String[] args) throws IOException {
//        System.out.println(Differ.generate());
//        int exitCode = new CommandLine(new App()).execute(args);
//        System.exit(exitCode);

        System.out.println(Differ.parse(Differ.file1));
        System.out.println(Differ.parse(Differ.file2));




    }
}
