package dev.academy.function.clean;

import org.kohsuke.args4j.Option;

public class Parameters {
    @Option(name = "-inputPath", aliases = {
            "-i"}, required = true, usage = "Sets a path to the root folder you want to parse")
    public String _inputPath;

    @Option(name = "-outputFile", aliases = {"-o"}, required = true, usage = "Sets the file path to output in")
    public String _outputFilePath;

    @Option(name = "-separator", aliases = {
            "-s"}, required = false, usage = "Sets a specific separator. Default is ','")
    public String _separator = ",";


    public Parameters(){

    }
}
