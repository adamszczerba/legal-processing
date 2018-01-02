package adszczer.po.legalprocessing.structure;

import java.util.ArrayList;
import java.util.List;

public class Rozdzial implements DocumentElement {

    private final String number;
    private Dzial parent = null;
    private List<Artykul> children = new ArrayList<>();


    public Rozdzial(String number) {
        this.number = number;
    }

    @Override
    public Dzial getParent() {
        return parent;
    }

    @Override
    public Artykul[] getChildren() {
        return children.toArray(new Artykul[0]);
    }

    @Override
    public Artykul getChild(int index) {
        return children.get(index);
    }

    @Override
    public String getTitle() {
        return "Rozdział " + number;
    }

    public void addChild(Artykul artykul) {
        children.add(artykul);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(getTitle());
        ret.append('\n');

        for (Artykul ch : children) {
            ret.append(ch);
        }

        return ret.toString();

    }
}
