package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
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

    @Override
    public final String call() throws Exception {

        String result = Differ.run(filepath1, filepath2);
        System.out.println(result);
        return result;
    }


    public static void main(String[] args) throws IOException {

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }


}
