import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MGund on 4/27/2016.
 */
public class Encode {

    private static HashMap<Integer, Integer> hashMap = new HashMap<>();

    public static void main(String[] args) {

        //FileInputStream inFile = new FileInputStream(args[0]);
        //FileOutputStream outFile = new FileOutputStream(args[1]);

        try {
            FileInputStream inFile = new FileInputStream(new File("C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Algoritmer og datastrukturer\\Projekt del 3\\out\\production\\Projekt del 3\\DNA.txt"));

            //System.out.println( huffmann(createFreq(inFile)).getKey() );
            //createFile(huffmann(createFreq(inFile)));

            nodeWalk(huffmann(createFreq(inFile)));

        } catch (Exception e) {e.printStackTrace();}

    }

    private static int[] createFreq(FileInputStream inFile) throws Exception {
        int[] freq = new int[256];

        int bit;
        while ((bit = inFile.read()) != -1) {
            freq[bit] = freq[bit] + 1;
        }

        return freq;
    }

    private static Element huffmann(int[] freq) {
        Integer heapSize = 0;
        for (int i : freq)
            if (i != 0) heapSize++;

        PQHeap pq = new PQHeap(heapSize);
        for (int i = 0; i < 256; i++)
            if (freq[i] != 0) {
                Element x = new Element();
                x.setKey(freq[i]);
                x.setData(i);
                //System.out.println( "Key: " + x.getKey() + " - Data: " + x.getData() );
                pq.insert(x);
            }

        for (int i = 0 ; i < heapSize-1 ; i++) {
            Object left = pq.extractMin();
            Object right = pq.extractMin();
            Integer newFreq = ((Element)left).getKey() + ((Element)right).getKey();

            if (Node.class.isInstance( ((Element)left).getData() )) {
                left = ((Element)left).getData();
            }
            if (Node.class.isInstance( ((Element)right).getData() )) {
                right = ((Element)right).getData();
            }

            Node n = new Node(left, right );
            Element e = new Element();
            e.setKey(newFreq);
            e.setData( n );

            pq.insert( e );
        }

        return pq.extractMin();
    }

    private static void createFile(Element e) {

        try {
            FileInputStream inFile = new FileInputStream(new File("C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Algoritmer og datastrukturer\\Projekt del 3\\out\\production\\Projekt del 3\\DNA.txt"));
            int bit;
            while ((bit = inFile.read()) != -1) {

            }
        } catch (Exception i) {i.printStackTrace();}
    }

    private static void nodeWalk (Object e) {

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        // First we check if our current element is null
        if (e != null) {
            //If our current Element has a node as data
            if (Node.class.isInstance( ((Element)e).getData()) ) {
                //Then we continue our walk to the left
                System.out.println( "Walking left" );
                sb1.append(0);
                innerNodeWalk(sb1, ((Node)((Element)e).getData()).getLeft());
                System.out.println( "Walking right" );
                sb2.append(1);
                innerNodeWalk(sb2,  ((Node)((Element)e).getData()).getRight());
            }
        }
    }

    private static void innerNodeWalk (StringBuilder sb, Object e) {

        // First we check if our current element is null
        if (e != null) {
            //If our current Element has a node as data
            if (Node.class.isInstance( ((Element)e).getData()) ) {
                //Then we continue our walk to the left
                System.out.println( "Walking left" );
                sb.append(0);
                innerNodeWalk(sb,  ((Node)((Element)e).getData()).getLeft());
                sb.deleteCharAt(sb.length()-1);
                sb.append(1);
                System.out.println( "Walking right" );
                innerNodeWalk(sb,  ((Node)((Element)e).getData()).getRight());
            } else {
                System.out.println( "Adding element: " + ((int)((Element) e).getData()) + " - value: " + ((Element) e).getKey() );
                hashMap.put(((int)((Element) e).getData()),((Element) e).getKey());
            }


        }
    }
}

