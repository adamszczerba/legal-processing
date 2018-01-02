package adszczer.po.legalprocessing.structure;

import java.util.ArrayList;
import java.util.List;

public class Dzial implements DocumentElement{

    private final String number;
    private Document parent = null;
    private List<Rozdzial> children = new ArrayList<>();
    private final String title;

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
        return "Dział " + number + ": " + title;
    }

    public void addRozdzial(Rozdzial rozdzial) {
        children.add(rozdzial);
    }

    @Override
    public String toString(){
        StringBuilder ret = new StringBuilder();
        ret.append(getTitle());
        ret.append('\n');

        for(Rozdzial ch : children){
            ret.append(ch);
        }

        return ret.toString();

    }

}
