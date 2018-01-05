package adszczer.po.legalprocessing.parser;

import java.util.*;
import java.util.regex.Pattern;


public class Preparser {
    private static final Pattern KANCELARIA_SEJMU = Pattern.compile("^.Kancelaria Sejmu.*");
    private static final Pattern DATA = Pattern.compile("^[0-9]{4}-(0[0-9]|1[0-2])-[0-3][0-9]");
    private static final Pattern POJEDYNCZY_ZNAK = Pattern.compile(".");
    private static final Pattern UCHYLONY = Pattern.compile(".*\\(uchylony\\)");
    private static final Pattern POMINIETE = Pattern.compile(".*\\(pominięte\\)");
    private static final Pattern BRZYDKI_ARTYKUL = Pattern.compile("^Art\\. [0-9]+[a-z]?\\.[^\\n].*");
    private static final Pattern POCZATEK_DOKUMENTU = Pattern.compile("^(Rozdział\\s.*|DZIAŁ\\s.*)");

    private final List<String> fileContents;

    public Preparser(List<String> fileContents){
        this.fileContents = fileContents;
    }

    public List<String> preparse() {
        Iterator<String> iter = fileContents.iterator();
        String line;

        do{
            line = iter.next();
        }while(!POCZATEK_DOKUMENTU.matcher(line).matches());

        List<String> cleanList = new ArrayList<>();

        processLine(line, iter, cleanList);

        while(iter.hasNext()){
            processLine(null, iter, cleanList);
        }
        
        return cleanList;
    }

    private void processLine(String lastLine, Iterator<String> iterator, List<String> cleanList) {
        String line = lastLine == null ? iterator.next() : lastLine;

        //do pominiecia
        if (
                KANCELARIA_SEJMU.matcher(line).matches() ||
                DATA.matcher(line).matches() ||
                POJEDYNCZY_ZNAK.matcher(line).matches() ||
                UCHYLONY.matcher(line).matches() ||
                POMINIETE.matcher(line).matches()) return;

        // enter po art.number. w uokik
        if (BRZYDKI_ARTYKUL.matcher(line).matches()) {
            int cutPoint = line.indexOf(" ", 5);

            String firstPart = line.substring(0, cutPoint);
            String secondPart = line.substring(cutPoint + 1);

            cleanList.add(firstPart);
            line = secondPart;
        }

        // polaczenie przerwanego slowa
        if(line.endsWith("-")){
            String nextLine = iterator.next();

            int indexOfSpace = nextLine.indexOf(" ");

            String toAdd;
            if(indexOfSpace == -1){
                toAdd = nextLine;
            }else {
                toAdd = nextLine.substring(0, indexOfSpace);
            }

            int lineLength = line.length();
            line = line.substring(0,lineLength - 1);
            line += toAdd;

            cleanList.add(line);

            nextLine = nextLine.substring(indexOfSpace+1);
            if(!nextLine.isEmpty()) processLine(nextLine, iterator, cleanList);

            return;
        }

        cleanList.add(line);
    }
}
