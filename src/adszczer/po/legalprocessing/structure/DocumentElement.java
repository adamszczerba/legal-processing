package adszczer.po.legalprocessing.structure;

public interface DocumentElement {

    public DocumentElement getParent();

    public DocumentElement[] getChildren();

    /**
     * Zwróć dziecko o indeksie {@code index}.
     */
    public DocumentElement getChild(int index);

    public String getTitle();
}
