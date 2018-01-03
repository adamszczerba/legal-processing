package adszczer.po.legalprocessing.cli;

import org.apache.commons.cli.*;


public class OptionInterpreter {
    private static final Option[] OPTIONS = new Option[]{
            Option.builder("h").argName("pomoc").longOpt("help").desc("wyświetl pomoc").build(),
            Option.builder("d").argName("dział").hasArg().desc("wskaż dział").build(),
            Option.builder("r").argName("rozdział").hasArg().desc("wskaż rozdział").build(),
            Option.builder("a").argName("artykuł").hasArg().desc("wskaż artykuł").build(),
            Option.builder("u").argName("ustęp").hasArg().desc("wskaż ustęp").build(),
            Option.builder("p").argName("punkt").hasArg().desc("wskaż punkt").build(),
            Option.builder("q").argName("podpunkt").hasArg().desc("wskaż podpunkt").build(),

            Option.builder("A").argName("artykuły").numberOfArgs(2).desc("wskaż przedział artykułów").valueSeparator(',').build(),
            Option.builder("S").argName("SpisTreści").hasArgs().desc("wyświetl spis treści: działy,rozdziały,tytuły").build(),
            Option.builder("s").argName("SpisTreściDziału").hasArgs().desc("wyświetl spis treści działu").build(),
    };
    private final CommandLine commandLine;
    private Options options = new Options();

    public OptionInterpreter(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        commandLine = parser.parse(options, args);
    }

    public void runOptions() {
        if (commandLine.hasOption("h")) {   // jesli da h to zawsze help
            //printHelp();
            return;
        }

        if (commandLine.hasOption("A")) {
            commandLine.getOptionValue("A");

            // TODO
            return;
        }

        if (commandLine.hasOption("S")) {

            //wypisz spis tresci calosci: dzialy rozdzialy tytuly
            return;
        }

        if (commandLine.hasOption("s")) {
            //wypisz spis dzialu o numerze
            return;
        }

        String a = null, u = null, p = null, q = null;

        if (commandLine.hasOption("a")) {
            a = commandLine.getOptionValue("a");
        }

        if (commandLine.hasOption("u")) {
            u = commandLine.getOptionValue("u");
        }

        if (commandLine.hasOption("p")) {
            p = commandLine.getOptionValue("p");
        }

        if (commandLine.hasOption("q")) {
            q = commandLine.getOptionValue("q");
        }

        if(q == null && p == null && u == null){
            //zwroc caly artykol

        }else if(q == null && p == null){
            //zwroc artykul i ustep

        }else if(q == null){
            //zwroc art, ust, punkt

        }else{
            //zwroc art, ust, punkt, podpunkt

        }

    }


}
