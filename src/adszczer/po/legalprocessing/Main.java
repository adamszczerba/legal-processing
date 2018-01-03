package adszczer.po.legalprocessing;

import adszczer.po.legalprocessing.cli.OptionInterpreter;
import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        new OptionInterpreter(args).runOptions();
    }
}
