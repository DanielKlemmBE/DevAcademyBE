package dev.academy.function.unclean;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class ConvertFiles {

    @Option(name = "-inputPath", aliases = {
            "-i"}, required = true, usage = "Sets a path to the root folder you want to parse")
    private String _inputPath;

    @Option(name = "-ouputFile", aliases = {"-o"}, required = true, usage = "Sets the file path to output in")
    private String _outputFilePath;

    @Option(name = "-seperator", aliases = {
            "-s"}, required = false, usage = "Sets a specific seperator. Default is ','")
    private String _seperator = ",";

    public static void main(String[] args) {
        new ConvertFiles().doMain(args);
    }

    private void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            // parse arguments
            parser.parseArgument(args);
            if (!isNotNull(_inputPath, _outputFilePath)) {
                throw new CmdLineException(parser, "-inputPath and -ouputFile has to be set",
                        new IllegalStateException());
            }

            // read start file
            File inputFile = new File(_inputPath);
            System.out.println("input path: " + inputFile);

            // read all files
            List<File> files = new Files(inputFile, ".csv").getFiles();
            List<String> headerOutputLine = new ArrayList<>();
            List<String> dataOutputLines = new ArrayList<>();
            boolean headerDone = false;
            for (File file : files) {
                System.out.println("Parsing " + file.getAbsolutePath());
                // read data file
                ArrayList<String> lines = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        lines.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // convert file
                List<String> headerRow = convertStringToList(lines.get(0), _seperator);
                List<String> dataLines = new ArrayList<>();
                for (int i = 1; i < lines.size(); i++) {
                    List<String> line = convertStringToList(lines.get(i), _seperator);
                    if (!headerDone) {
                        // create header for all file once
                        for (int j = 1; j < headerRow.size(); j++) {
                            headerOutputLine.add(headerRow.get(j) + "_" + line.get(0));
                        }
                    }
                    // convert data rows into one row
                    for (int j = 1; j < line.size(); j++) {
                        dataLines.add(line.get(j));
                    }
                }
                dataOutputLines.add(convertListToString(dataLines, _seperator));
                headerDone = true;
            }

            System.out.println("output path: " + _outputFilePath);
            // write output file
            try (FileWriter fw = new FileWriter(_outputFilePath)) {
                fw.write(convertListToString(headerOutputLine, _seperator) + "\n");
                for (String dataLine : dataOutputLines) {
                    fw.write(dataLine + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return;
        } catch (IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static String convertListToString(List<String> list, String splitter) {
        return list.stream().collect(joining(splitter));
    }

    public static List<String> convertStringToList(String line, String splitter) {
        return Arrays.stream(line.split(splitter)).toList();
    }

    private static boolean isNotNull(String... args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                return false;
            }
        }
        return true;
    }
}
