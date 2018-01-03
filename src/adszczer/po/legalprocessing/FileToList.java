package adszczer.po.legalprocessing;

import java.io.*;
import java.util.*;


public class FileToList {

    public List<String> processFileToLineList(String filename) throws IOException {
        if(filename == null){
            throw new IllegalArgumentException("filename is null");
        }

        List<String> fileByLines = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = in.readLine()) != null) {
                fileByLines.add(line);
            }
        }

        return fileByLines;
    }


}


