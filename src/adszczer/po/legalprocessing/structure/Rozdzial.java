package adszczer.po.legalprocessing.structure;

import java.util.ArrayList;
import java.util.List;

public class Rozdzial implements DocumentElement {

    private final String title;
    private final String number;
    private Dzial parent = null;
    private List<Artykul> children = new ArrayList<>();


    public Rozdzial(String number, String title) {
        this.number = number;
        this.title = title;
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
        if(number != null){
            return "Rozdział " + number + " : " + title;
        }else{
            return "";
        }
    }

    public void addChild(Artykul artykul) {
        children.add(artykul);
    }

    public String toTOC() {
        StringBuilder ret = new StringBuilder();
        if (title != null) {
            ret.append(getTitle());
            ret.append('\n');
        }


        return ret.toString();

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

    public String getNumber() {
        return number;
    }

    public Artykul getArtykul(String num) {
        if(num.equals("0")) num = null;
        for(Artykul a : children){
            if(a.getNumber().equals(num)){
                return a;
            }
        }

        return null;
    }


}


