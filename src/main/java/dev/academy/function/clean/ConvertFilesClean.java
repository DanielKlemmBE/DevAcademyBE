package dev.academy.function.clean;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class ConvertFilesClean {

    public static void main(String[] args) {

        // parameters
        Parameters parameters = readParameters(args);
        // read the files
        List<FileData> filesData = readFiles(parameters._inputPath);
        // converting
        // write File


        new ConvertFilesClean().doMain(parameters, filesData);
    }

    private static List<FileData> readFiles(String inputPath) {
        // read start file
        File inputFile = new File(inputPath);
        System.out.println("input path: " + inputFile);

        // read all files
        List<File> files = new FilesClean(inputFile, ".csv").getFiles();
        List<FileData> filesData = new ArrayList<>();
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
            filesData.add(new FileData(lines));
        }
        return filesData;
    }

    private static Parameters readParameters(String[] args) {
        Parameters parameters = new Parameters();
        CmdLineParser parser = new CmdLineParser(parameters);
        try {
            // parse arguments
            parser.parseArgument(args);
            if (!isNotNull(parameters._inputPath, parameters._outputFilePath)) {
                throw new CmdLineException(parser, "-inputPath and -ouputFile has to be set",
                        new IllegalStateException());
            }
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
        return parameters;
    }

    void doMain(Parameters parameters, List<FileData> filesData) {
        try {
            // read start file
            File inputFile = new File(parameters._inputPath);
            System.out.println("input path: " + inputFile);

            // read all files
            List<String> headerOutputLine = new ArrayList<>();
            List<String> dataOutputLines = new ArrayList<>();
            boolean headerDone = false;
            for (FileData fileData : filesData) {
                // convert fileData
                List<String> headerRow = convertStringToList(fileData._lines.get(0), parameters._separator);
                List<String> dataLines = new ArrayList<>();
                for (int i = 1; i < fileData._lines.size(); i++) {
                    List<String> line = convertStringToList(fileData._lines.get(i), parameters._separator);
                    if (!headerDone) {
                        // create header for all fileData once
                        for (int j = 1; j < headerRow.size(); j++) {
                            headerOutputLine.add(headerRow.get(j) + "_" + line.get(0));
                        }
                    }
                    // convert data rows into one row
                    for (int j = 1; j < line.size(); j++) {
                        dataLines.add(line.get(j));
                    }
                }
                dataOutputLines.add(convertListToString(dataLines, parameters._separator));
                headerDone = true;
            }

            // write output file
            File outputFile = new File(parameters._outputFilePath);
            outputFile.createNewFile();
            System.out.println("output path: " + outputFile.getAbsolutePath());
            try (FileWriter fw = new FileWriter(outputFile)) {
                fw.write(convertListToString(headerOutputLine, parameters._separator) + "\n");
                for (String dataLine : dataOutputLines) {
                    fw.write(dataLine + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IllegalStateException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    static String convertListToString(List<String> list, String splitter) {
        return list.stream().collect(joining(splitter));
    }

    static List<String> convertStringToList(String line, String splitter) {
        return Arrays.stream(line.split(splitter)).toList();
    }

    static boolean isNotNull(String... args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                return false;
            }
        }
        return true;
    }
}
