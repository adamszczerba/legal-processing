package adszczer.po.legalprocessing.cli;

import adszczer.po.legalprocessing.FileToList;
import adszczer.po.legalprocessing.parser.Preparser;
import adszczer.po.legalprocessing.structure.Artykul;
import adszczer.po.legalprocessing.structure.Document;
import org.apache.commons.cli.*;
import java.io.IOException;
import java.util.List;


public class OptionInterpreter {
    private static final Option[] OPTIONS = new Option[]{
            Option.builder("f").argName("plik").hasArg().desc("podaj ścieżkę do pliku").build(),
            Option.builder("h").argName("pomoc").desc("wyświetl pomoc").build(),
            Option.builder("S").argName("spisTreści").desc("wyświetl spis treści: działy,rozdziały,tytuły").build(),
            Option.builder("s").argName("spisTreściDziału").hasArgs().desc("wyświetl spis treści działu").build(),
            Option.builder("d").argName("dział").hasArg().desc("wskaż dział").build(),
            Option.builder("r").argName("rozdział").hasArg().desc("wskaż rozdział").build(),
            Option.builder("a").argName("artykuł").hasArg().desc("wskaż artykuł").build(),
            Option.builder("A").argName("artykuły").numberOfArgs(2).desc("wskaż przedział artykułów").build(),
            Option.builder("u").argName("ustęp").hasArg().desc("wskaż ustęp").build(),
            Option.builder("p").argName("punkt").hasArg().desc("wskaż punkt").build(),
            Option.builder("q").argName("podpunkt").hasArg().desc("wskaż podpunkt").build(),

    };

    private final CommandLine commandLine;
    private Options options = new Options();

    public OptionInterpreter(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();

        for (Option opt : OPTIONS) {
            options.addOption(opt);
        }

        commandLine = parser.parse(options, args);
    }

    public void runOptions() throws IOException {
        if (commandLine.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("legal-processing", options);
            return;
        }

        String filename = commandLine.getOptionValue("f");

        FileToList filetolist = new FileToList();
        if(filename == null){
            System.out.println("Proszę podać ścieżkę bezwzględną do pliku");
            System.exit(1);
        }
        List<String> linelist = filetolist.processFileToLineList(filename);

        Preparser preparser = new Preparser(linelist);
        List<String> cleanlist = preparser.preparse();

        adszczer.po.legalprocessing.parser.Parser parser = new adszczer.po.legalprocessing.parser.Parser(cleanlist);
        Document doc = parser.parse(filename);

        if (commandLine.hasOption("A")) {
            String[] parts = commandLine.getOptionValues("A");
            String from = parts[0];
            String to = parts[1];

            for(Artykul a : doc.getArtykuly(from, to)){
                System.out.println(a);
            }

            return;
        }

        if (commandLine.hasOption("S")) {
            System.out.println(doc.toTOC());
            return;
        }

        if (commandLine.hasOption("s")) {
            String dzialNum = commandLine.getOptionValue("s");
            System.out.println(doc.getDzial(dzialNum).toTOC());
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

        if (a == null) {
            System.err.println("Potrzeba -a!");
            return;
        }

        if (q == null && p == null && u == null) {
            //zwroc caly artykul
            System.out.println(doc.getArtykul(a));

        } else if (q == null && p == null) {
            //zwroc artykul i ustep
            System.out.println(doc.getArtykul(a).getUstep(u));
        } else if (q == null) {
            //zwroc art, ust, punkt
            System.out.println(doc.getArtykul(a).getUstep(u).getPunkt(p));
        } else {
            //zwroc art, ust, punkt, podpunkt
            System.out.println(doc.getArtykul(a).getUstep(u).getPunkt(p).getPodpunkt(q));
        }
    }
}
