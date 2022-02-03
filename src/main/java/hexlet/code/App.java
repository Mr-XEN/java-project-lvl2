package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
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

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }

    @Override
    public String call() throws Exception {

        byte[] fileContents1 = Files.readAllBytes(filepath1.toPath());
        byte[] fileContents2 = Files.readAllBytes(filepath2.toPath());

        Map<String, String> map1 = Differ.parse(fileContents1);
        Map<String, String> map2 = Differ.parse(fileContents2);

        String s = Differ.generate(map1, map2);
        System.out.println(s);

        return s;
    }
}
