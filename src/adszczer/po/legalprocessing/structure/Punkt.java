package adszczer.po.legalprocessing.structure;

import java.util.ArrayList;
import java.util.List;

public class Punkt implements DocumentElement {

    private final int number;

    private Ustep parent = null;
    private List<Podpunkt> children = new ArrayList<>();
    private String text;

    public Punkt(int number) {
        this.number = number;
    }

    @Override
    public Ustep getParent() {
        return this.parent;
    }

    @Override
    public Punkt[] getChildren() {
        return children.toArray(new Punkt[0]);
    }

    @Override
    public Podpunkt getChild(int index) {
        return children.get(index);
    }

    @Override
    public String getTitle() {
        return number + ")";
    }

    public void addPodpunkt(Podpunkt punkt) {
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

        for (Podpunkt ch : children) {
            ret.append(ch);
        }

        return ret.toString();

    }
}
