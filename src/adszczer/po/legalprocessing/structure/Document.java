package adszczer.po.legalprocessing.structure;

import java.util.ArrayList;
import java.util.List;

public class Document implements DocumentElement {

    private final String title;
    private List<Dzial> children = new ArrayList<>();

    public Document(String title) {
        this.title = title;
    }

    @Override
    public DocumentElement getParent() {
        return null;
    }

    @Override
    public Dzial[] getChildren() {
        return children.toArray(new Dzial[0]);
    }

    @Override
    public Dzial getChild(int index) {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    public void addDzial(Dzial child) {
        children.add(child);
    }

    public String toTOC() {
        StringBuilder ret = new StringBuilder();
        ret.append(title);
        ret.append('\n');

        for (Dzial ch : children) {
            ret.append(ch.toTOC());
        }

        return ret.toString();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(title);
        ret.append('\n');

        for (Dzial ch : children) {
            ret.append(ch);
        }

        return ret.toString();
    }

    public Dzial getDzial(String num) {
        for (Dzial dz : children) {
            if (dz.getNumber().equals(num)) {
                return dz;
            }
        }

        return null;
    }

    public Artykul getArtykul(String number) {
        for (Dzial d : children) {
            for (Rozdzial r : d.getChildren()) {
                for (Artykul a : r.getChildren()) {
                    if (a.getNumber().equals(number)) {
                        return a;
                    }
                }
            }
        }

        return null;
    }
}
