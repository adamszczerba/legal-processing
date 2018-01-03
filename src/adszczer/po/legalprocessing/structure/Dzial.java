package adszczer.po.legalprocessing.structure;

import java.util.ArrayList;
import java.util.List;

public class Dzial implements DocumentElement {

    private final String number;
    private final String title;
    private Document parent = null;
    private List<Rozdzial> children = new ArrayList<>();

    public Dzial(String number, String title) {
        this.title = title;
        this.number = number;
    }


    @Override
    public DocumentElement getParent() {
        return this.parent;
    }

    @Override
    public Rozdzial[] getChildren() {
        return children.toArray(new Rozdzial[0]);
    }

    @Override
    public Rozdzial getChild(int index) {
        return children.get(index);
    }

    @Override
    public String getTitle() {
        return "Dział " + number + " : " + title;
    }

    public void addRozdzial(Rozdzial rozdzial) {
        children.add(rozdzial);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (title != null) {
            ret.append(getTitle());
        }
        ret.append('\n');

        for (Rozdzial ch : children) {
            ret.append(ch);
        }

        return ret.toString();

    }

    public String toTOC() {
        StringBuilder ret = new StringBuilder();
        if (title != null) {
            ret.append(getTitle());
            ret.append('\n');
        }

        for (Rozdzial ch : children) {
            ret.append(ch.toTOC());
        }

        return ret.toString();
    }


    public String getNumber() {
        return number;
    }

    public Rozdzial getRozdzial ( String num){
        if(num.equals("0")) num = null;
        for(Rozdzial r : children){
            if(r.getNumber().equals(num)){
                return r;
            }
        }

        return null;
    }


}
