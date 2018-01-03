package adszczer.po.legalprocessing.structure;

public interface DocumentElement {

    public DocumentElement getParent();

    public DocumentElement[] getChildren();

    public DocumentElement getChild(int index);

    public String getTitle();
}
