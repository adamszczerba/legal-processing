package adszczer.po.legalprocessing.parser;

import adszczer.po.legalprocessing.structure.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    //potrzebne
    private static final Pattern DZIAL = Pattern.compile("DZIAŁ\\s([A-Z]+)");

    private static final Pattern ROZDZIAL = Pattern.compile("Rozdział\\s([I,V,X]+)");

    private static final Pattern ARTYKUL = Pattern.compile("Art\\.\\s([0-9]+)([a-z]?)\\.");

    private static final Pattern USTEP = Pattern.compile("([0-9]+)\\. (.*)");

    private static final Pattern PUNKT = Pattern.compile("([0-9]+)\\) (.*)");

    private static final Pattern PODPUNKT = Pattern.compile("([a-z]+)\\) (.*)");

    private static final Pattern ELEMENT = Pattern.compile(
            "(" + DZIAL + "|" +
                    ROZDZIAL + "|" +
                    ARTYKUL + "|" +
                    USTEP + "|" +
                    PUNKT + "|" +
                    PODPUNKT + ")");

    private static final Pattern DRUK_DO_POMINIĘCIA = Pattern.compile("(.*)()");

    private final List<String> contents;
    private int line = 0;

    public Parser(List<String> contents) {
        this.contents = contents;
    }

    public Document parse(String title) {
        Document doc = new Document(title);
        while (hasLines()) {
            doc.addDzial(parseDzial());
        }

        return doc;
    }

    private boolean hasLines() {
        return line < contents.size();
    }

    private String nextLine() {
        return contents.get(line++);
    }

    private String peekLine() {
        return contents.get(line);
    }

    private boolean isElement() {
        return ELEMENT.matcher(peekLine()).matches();
    }

    public Dzial parseDzial() {
        String title;

        String number;
        Matcher matcher = DZIAL.matcher(peekLine());
        if (!matcher.matches()) {
            number = null;
            title = null;
        } else {
            nextLine();

            number = matcher.group(1);
            title = nextLine();
        }

        Dzial ret = new Dzial(number, title);

        while (hasLines() && !DZIAL.matcher(peekLine()).matches()) {
            ret.addRozdzial(parseRozdzial());
        }

        return ret;
    }

    private Rozdzial parseRozdzial() {
        String number;
        Matcher matcher = ROZDZIAL.matcher(peekLine());
        if (!matcher.matches()) {
            number = null;
        } else {
            nextLine();

            number = matcher.group(1);
        }

        Rozdzial ret = new Rozdzial(number);

        while (
                hasLines() &&
                        !DZIAL.matcher(peekLine()).matches() &&
                        !ROZDZIAL.matcher(peekLine()).matches()
                ) {
            ret.addChild(parseArtykul());
        }

        return ret;
    }

    private Artykul parseArtykul() {
        int number;
        String sub;
        Matcher matcher = ARTYKUL.matcher(peekLine());
        if (!matcher.matches()) {
            number = 0;
            sub = null;
        } else {
            nextLine();

            number = Integer.parseInt(matcher.group(1));
            sub = matcher.group(2);
        }

        Artykul ret = new Artykul(number, sub);

        StringBuilder text = new StringBuilder();

        while (hasLines() && !isElement()) {
            text.append(nextLine());
            text.append('\n');
        }

        ret.setText(text.toString());

        while (hasLines()) {
            Ustep parsed = parseUstep();
            if (parsed == null) break;

            ret.addChild(parsed);
        }

        if(ret.getChildren().length <= 0 && text.length() <= 0){
            return null;
        }

        return ret;
    }


    private Ustep parseUstep() {
        int number;
        StringBuilder text = new StringBuilder();
        Matcher matcher = USTEP.matcher(peekLine());
        if (!matcher.matches()) {
            number = 0;
        } else {
            nextLine();

            number = Integer.parseInt(matcher.group(1));
            text.append(matcher.group(2));
            text.append('\n');
        }

        Ustep ret = new Ustep(number);

        while (hasLines() && !isElement()) {
            text.append(nextLine());
            text.append('\n');
        }

        ret.setText(text.toString());

        while (hasLines()) {
            Punkt parsed = parsePunkt();
            if (parsed == null) break;

            ret.addPunkt(parsed);
        }

        if(ret.getChildren().length <= 0 && text.length() <= 0){
            return null;
        }

        return ret;
    }

    private Punkt parsePunkt() {
        int number;
        StringBuilder text = new StringBuilder();
        Matcher matcher = PUNKT.matcher(peekLine());
        if (!matcher.matches()) {
            return null;
        } else {
            nextLine();

            number = Integer.parseInt(matcher.group(1));
            text.append(matcher.group(2));
            text.append('\n');
        }

        Punkt ret = new Punkt(number);

        while (hasLines() && !isElement()) {
            text.append(nextLine());
            text.append('\n');
        }

        ret.setText(text.toString());

        while (hasLines()) {
            Podpunkt parsed = parsePodpunkt();
            if(parsed == null) break;

            ret.addPodpunkt(parsed);
        }

        return ret;
    }

    private Podpunkt parsePodpunkt() {
        String number;
        StringBuilder text = new StringBuilder();
        Matcher matcher = PODPUNKT.matcher(peekLine());
        if (!matcher.matches()) {
            return null;
        } else {
            nextLine();

            number = matcher.group(1);
            text.append(matcher.group(2));
            text.append('\n');
        }

        Podpunkt ret = new Podpunkt(number);

        while (hasLines() && !isElement()) {
            text.append(nextLine());
            text.append('\n');
        }

        ret.setText(text.toString());

        return ret;
    }


    private String parseText() {
        StringBuilder text = new StringBuilder();

        while (
                hasLines() &&
                        !DZIAL.matcher(peekLine()).matches() &&
                        !ROZDZIAL.matcher(peekLine()).matches() &&
                        !ARTYKUL.matcher(peekLine()).matches()
                ) {
            text.append(nextLine());
            text.append('\n');
        }

        return text.toString();
    }
}