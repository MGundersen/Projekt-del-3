/*
        MADE BY Mathias Gundersen(MGUND15), D3 AND Daniel Jørgensen(DANJO14), D3
*/

public class DictBinTree implements Dict {

    // Current size of the tree
    Integer size = 0;

    // Root of the tree(top/header element)
    Element header;

    // Counter used for inserting elements in the right order when calling orderedTraversal
    private Integer counter = 0;

    // An array with integers
    public int[] anArray;


    // Insert takes an integer k, which is the key we want to insert.
    // This key will be placed at its correct place in the tree by
    // comparing each key in the tree with the new one.
    @Override
    public void insert(Element z) {
        // Element z is created by using the key, which is going to be inserted into the tree.
//        Element z = new Element(k);

        // Now that we insert an element, we increase the size of the tree by 1
        size ++;

        // We set Y to null
        Element y = null;

        // We set element x to our header element
        Element x = header;

        // If our header(element x) is not empty
        while ( x != null ) {
            // If header was not null, we save header in Y
            y = x;
            if (z.getValue() < x.getValue()){
                // Element z's key was smaller than the header's key, so we go to the left
                // So we set X to it's left child(We still have 'x'/header element saved in Y
                x = x.getLeft();
            } else {
                // Element z's key was larger than the header's key, so we go to the right
                // So we set X to it's right child(We still have 'x'/header element saved in Y
                x = x.getRight();
            }
        }

        // If header was not null, we set Z's parent to Y
        // If header was null, we set Z's parent to null
        z.setParent(y);
        if ( y == null ) {
            //Tree was empty, we set our element Z as the root/header element
            header = z;
        } else if (z.getValue() < y.getValue()) {
            // We set our element z to the left child of Y, because it had a lower key
            y.setLeft(z);
        } else {
            // We set our element z to the right child of Y, because it had a higher key
            y.setRight(z);
        }

    }


    // orderedTraversal has to go through the tree, and return an array of Integers.
    // It calls orderedTraversalInner, which uses recursion to go through all the elements in the key
    @Override
    public int[] orderedTraversal() {
        // Initializes anArray with the current size of our tree
        anArray = new int[size];
        //Calls orderedTraversalInner with our header element(This is where we start)
        orderedTraversalInner(header);
        //When we are done adding elements to our array, we return anArray
        return anArray;
    }

    private void orderedTraversalInner(Element x) {
        // First we check if our current element is null
        if (x != null) {
            // If the current element is not null, we go to the left
            // We start doing this until we can't go anymore to the left, so we
            // basically find the element with the smallest key
            orderedTraversalInner(x.left);
            // We add our element's key to our array with help from our counter
            anArray[counter] = x.getKey();
            // We add 1 to our counter, so our next element is put at the next index
            counter++;
            // We go right to search for more elements at our current position
            orderedTraversalInner(x.right);
        }
    }


    // Search takes an integer(key), which it will search for in the tree
    // It calls SearchInner which uses recursion to search for an element with the key K
    @Override
    public Boolean search(Integer k) {
        return searchInner(header,k);
    }

    private Boolean searchInner(Element x, Integer k) {

        // Returning false if tree is empty = element not in the tree
        if (header == null) {
            return false;
        }
        // Returns true if the current element we are looking at has the correct key
        if (k == x.getKey()) {
            return true;
        }
        // If we did not return yet, we check if the current element has a higher or smaller key,
        // that we are looking for, and we move down the tree accordingly
        if (k < x.getKey()) {
            if ( x.getLeft() == null) {
                return false;
            } else return searchInner(x.getLeft(),k);
        } else{
            if ( x.getRight() == null) {
                return false;
            } else return searchInner(x.getRight(),k);
        }
    }
}
