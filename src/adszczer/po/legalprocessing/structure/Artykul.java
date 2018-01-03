package adszczer.po.legalprocessing.structure;

import java.util.ArrayList;
import java.util.List;

public class Artykul implements DocumentElement {
    private final String number;
    private final String subnumber;

    private DocumentElement parent = null;
    private List<Ustep> children = new ArrayList<>();

    private String text;

    public Artykul(String number, String subnumber) {
        this.number = number;
        this.subnumber = subnumber;
    }


    @Override
    public DocumentElement getParent() {
        return null;
    }

    @Override
    public Ustep[] getChildren() {
        return children.toArray(new Ustep[0]);
    }

    @Override
    public Ustep getChild(int index) {
        return children.get(index);
    }

    @Override
    public String getTitle() {
        return "Art. " + number + subnumber + '.';
    }

    public void addChild(Ustep child) {
        children.add(child);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(getTitle());
        ret.append('\n');
        ret.append(getText());
        ret.append('\n');

        for (Ustep u : children) {
            ret.append(u);
        }

        return ret.toString();

    }

    public String getNumber() {
        return number;
    }

    public Ustep getUstep(String num) {
        if(num.equals("0")) num = null;

        for (Ustep u : children) {
            if (u.getNumber().equals(num)) {
                return u;
            }
        }

        return null;
    }

}
