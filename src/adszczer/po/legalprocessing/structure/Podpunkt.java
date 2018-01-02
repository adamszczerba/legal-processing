package adszczer.po.legalprocessing.structure;

public class Podpunkt implements DocumentElement{

    private final String letter;

    private Ustep parent = null;
    private String text;

    public Podpunkt(String letter) {
        this.letter = letter;
    }

    @Override
    public DocumentElement getParent() {
        return null;
    }

    @Override
    public DocumentElement[] getChildren() {
        return null;
    }

    @Override
    public DocumentElement getChild(int index) {
        return null;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public String getTitle() {
        return letter + ")";
    }

    @Override
    public String toString(){
        StringBuilder ret = new StringBuilder();
        ret.append(getTitle());
        ret.append(' ');
        ret.append(getText());
        ret.append('\n');

        return ret.toString();

    }
}
