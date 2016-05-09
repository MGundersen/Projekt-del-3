/**
 * Created by MGund on 5/8/2016.
 */
public class Node {

    private Element left;
    private Element right;

    public Node(Element left, Element right) {
        this.left = left;
        this.right = right;
    }

    public Element getLeft() {
        return left;
    }

    public Element getRight() {
        return right;
    }
}
