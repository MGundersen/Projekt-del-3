/*
        MADE BY Mathias Gundersen(MGUND15), D3 AND Daniel Jørgensen(DANJO14), D3
*/

public class Element {

    //Attributes of our Element, key, left child, right child and parent
    Integer key;
    Integer value = 1;
    Element left = null;
    Element right = null;
    Element parent = null;

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void incValue(){
        this.value++;
    }

    public Integer getValue(){
        return value;
    }

    public Integer getKey() {
        return key;
    }

    public Element getLeft() {
        return left;
    }

    public Element getRight() {
        return right;
    }

    public Element getParent() {
        return parent;
    }

    public void setLeft(Element left) {
        this.left = left;
    }

    public void setRight(Element right) {
        this.right = right;
    }

    public void setParent(Element parent) {
        this.parent = parent;
    }
}
