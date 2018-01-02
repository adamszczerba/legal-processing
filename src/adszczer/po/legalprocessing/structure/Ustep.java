package adszczer.po.legalprocessing.structure;

import java.util.ArrayList;
import java.util.List;

public class Ustep implements DocumentElement {
    private final int number;

    private Artykul parent = null;
    private List<Punkt> children = new ArrayList<>();
    private String text;

    public Ustep(int number) {
        this.number = number;
    }

    @Override
    public Artykul getParent() {
        return this.parent;
    }

    @Override
    public Punkt[] getChildren() {
        return children.toArray(new Punkt[0]);
    }

    @Override
    public Punkt getChild(int index) {
        return children.get(index);
    }

    @Override
    public String getTitle() {
        return number + ".";
    }

    public void addPunkt(Punkt punkt) {
        children.add(punkt);
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {

        StringBuilder ret = new StringBuilder();
        ret.append(getTitle());
        ret.append(' ');
        ret.append(getText());
        ret.append('\n');

        for (Punkt ch : children) {
            ret.append(ch);
        }

        return ret.toString();

    }
}

