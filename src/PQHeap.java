/**
      _             _      __ _  _                                              _ __ _____
     | |           (_)    /_ | || |     ___                                    | /_ | ____|
   __| | __ _ _ __  _  ___ | | || |_   ( _ )    _ __ ___   __ _ _   _ _ __   __| || | |__
  / _` |/ _` | '_ \| |/ _ \| |__   _|  / _ \/\ | '_ ` _ \ / _` | | | | '_ \ / _` || |___ \
 | (_| | (_| | | | | | (_) | |  | |   | (_>  < | | | | | | (_| | |_| | | | | (_| || |___) |
  \__,_|\__,_|_| |_| |\___/|_|  |_|    \___/\/ |_| |_| |_|\__, |\__,_|_| |_|\__,_||_|____/
                  _/ |                                     __/ |
                 |__/                                     |___/
*/

import java.util.*;

public class PQHeap implements PQ {

    // Number of maximum elements that can be put in our priority queue
    private int maxElms;

    // Size of current priority Queue
    private int priorityQueueSize = 0;

    // List for priority queue
    private ArrayList<Element> priorityList;

    /*
     * Our constructor, here we instantiate our list with our maximum number of elements.
     * We also add a dummy element to be placed at index 0. We will not use this element,
     * and the purpose of this is because we have followed the pseoudo code from the textbook,
     * and those examples always started at index 1.
    */
    PQHeap(int maxElms) {
        this.maxElms = maxElms;
        priorityList = new ArrayList<>(maxElms);
        Element b = new Element();
        b.setKey(-100);
        priorityList.add(b);
    }

    /*
     * Here we extract our element with the smallest key,
     * where we switch places with the first and the last element in the list to ruin our heaporder.
     * The last element is then removed, as it is the smallest in our heap, and minHeapify is called,
     * to rebuild our heaporder again.
     */
    public Element extractMin() {
        if (priorityQueueSize < 1){
            System.out.println("[ERROR] The list is empty.");
            return null;
        }
        Element min = priorityList.get(1);
        //System.out.println(min.getData());
        Collections.swap(priorityList, 1, priorityQueueSize);
        priorityList.remove(priorityQueueSize);
        priorityQueueSize--;
        MinHeapify(1);

        return min;
    }

    /*
     * Insert takes 1 element e as input,
     * creates a new element and adds the new element to the list.
     * It then calls decreaseKey with the size of our current priority queue,
     * and the element e.
     */
    public void insert(Element e) {
        if (priorityQueueSize == maxElms) {
            System.out.println("The list is full");
        } else {
            Element d = new Element();
            d.setKey(-100);
            priorityList.add(d);
            priorityQueueSize++;
            decreaseKey(priorityQueueSize, e);
        }
    }

    /*
     * Our decreaseKey takes 2 inputs, an integer i and an element e.
     * At first we set our element at index i to our element e,
     * which we had just created before in the insert method.
     * It then starts a while loop, where it checks whether our i
     * (position of the element we are currently checking)
     * is bigger than 1. Which means we basically just check if the
     * element is on top or not, if it's on top, we just stop the
     * while loop, if it's not, we continue. Our while loop then
     * checks if our current element e is smaller than the
     * element's parent. If they both are true, then we swap the
     * two elements, and set our i to our current element's position.
     * Now we check again until our element is at the correct position,
     * if it is, then the while loop ends and we stop.
     */
    private void decreaseKey(int i, Element e) {
        if (e.getKey() < priorityList.get(i).getKey()) {
            System.out.println("The new value is smaller than the current key");
        }
        priorityList.set(i, e);
        while (i > 1 && priorityList.get(Parent(i)).getKey() > priorityList.get(i).getKey()) {
            Collections.swap(priorityList, i, Parent(i));
            i = Parent(i);
        }
    }

    private int Parent(int i) {
        return i/2;
    }

    private int Left(int i) {
        return 2*i;
    }

    private int Right(int i) {
        return (2*i)+1;
    }

    private void MinHeapify(int i) {
        int Smallest;
        int L = Left(i);
        int R = Right(i);

        if (L <= priorityQueueSize && priorityList.get(L).getKey() < priorityList.get(i).getKey()) {
            Smallest = L;
        } else {
            Smallest = i;
        }
        if (R <= priorityQueueSize && priorityList.get(R).getKey() < priorityList.get(Smallest).getKey()) {
            Smallest = R;
        }
        if (Smallest != i) {
            Collections.swap(priorityList, i, Smallest);
            MinHeapify(Smallest);
        }
    }

}

