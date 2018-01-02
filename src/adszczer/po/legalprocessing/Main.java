package adszczer.po.legalprocessing;

import adszczer.po.legalprocessing.parser.Parser;
import adszczer.po.legalprocessing.parser.Preparser;
import adszczer.po.legalprocessing.structure.Document;

import java.io.IOException;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        String filename = "/home/adam/Desktop/PO/poProj1/konstytucja.txt";

        FileToList filetolist = new FileToList();
        List<String> linelist = filetolist.processFileToLineList(filename);

        Preparser preparser = new Preparser(linelist);
        List<String> cleanlist = preparser.preparse();

        Parser parser = new Parser(cleanlist);
        Document doc = parser.parse("UOKIK");

        System.out.println(doc);



    }

}
