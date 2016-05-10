import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MGund on 4/27/2016.
 */
public class Encode {

    private static HashMap<Integer, String> hashMap = new HashMap<>();

    public static void main(String[] args) {

        //FileInputStream inFile = new FileInputStream(args[0]);
        //FileOutputStream outFile = new FileOutputStream(args[1]);

        try {
            File inFile = new File("C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Algoritmer og datastrukturer\\Projekt del 3\\out\\production\\Projekt del 3\\DNA.txt");
            File outFile = new File("C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Algoritmer og datastrukturer\\Projekt del 3\\out\\production\\Projekt del 3\\newDNA.txt");


            /* Laver vores hyppighedstabel */
            int[] freq = createFreq(inFile);
            /* Fylder vores hashMap */
            nodeWalk(huffmann(freq));

            /* Skriver til vores nye fil */
            createFile(inFile,outFile);

        } catch (Exception e) {e.printStackTrace();}

    }

    private static int[] createFreq(File file) throws Exception {
        FileInputStream inFile = new FileInputStream(file);
        int[] freq = new int[256];

        int intIn;
        while ((intIn = inFile.read()) != -1) {
            //System.out.println( "Adding 1 more to index: " + intIn  );
            freq[intIn] = freq[intIn] + 1;
        }

        inFile.close();
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
            //System.out.println( "Extracting 2 objects" );
            Element left = pq.extractMin();
            Element right = pq.extractMin();
            Integer newFreq = left.getKey() + right.getKey();

            //System.out.println( "Creating a new node n and adding left and right object" );
            Node n = new Node( left, right );
            //System.out.println( "Creating a new element and adding our freq and data to it" );
            Element e = new Element();
            e.setKey(newFreq);
            e.setData( n );
            //System.out.println( "Inserting our new element into our heap " + e.getKey() + " - " + e.getData() );
            pq.insert( e );
        }

        return pq.extractMin();
    }

    private static void createFile(File infile, File outfile) {

        try {
            FileInputStream inFile = new FileInputStream(infile);
            FileOutputStream outFile = new FileOutputStream(outfile);
            BitInputStream in = new BitInputStream(inFile);
            BitOutputStream out = new BitOutputStream(outFile);

            int inBit;
            while ((inBit = inFile.read()) != -1) {
                String outBit = hashMap.get(inBit);
                System.out.println( "inBit: " + inBit + " - outBit: " +  outBit );

                for (String s : outBit.split(""))
                    //System.out.println( Integer.parseInt(s) );
                    out.writeBit( Integer.parseInt(s) );
            }

            out.writeBit(0);
            out.writeBit(1);
            inFile.close();
            outFile.close();
        } catch (Exception i) {i.printStackTrace();}
    }

    private static void nodeWalk (Element e) {

        StringBuilder sb = new StringBuilder();
        // First we check if our current element is null
        if (e != null) {
            //If our current Element has a node as data
            if (Node.class.isInstance( e.getData()) ) {
                //Then we continue our walk to the left
                sb.append(0);
                //System.out.println( "Walking left - sb: " + sb );
                innerNodeWalk(sb, ((Node)((Element)e).getData()).getLeft());
                sb.deleteCharAt(sb.length()-1);
                sb.append(1);
                //System.out.println( "Walking right - sb: " + sb );
                innerNodeWalk(sb,  ((Node)((Element)e).getData()).getRight());
                sb.deleteCharAt(sb.length()-1);
            } else {
                //Hvis data ikke var en node
            }
        }
    }

    private static void innerNodeWalk (StringBuilder sb, Object e) {

        // First we check if our current element is null
        if (e != null) {
            //If our current Element has a node as data
            if (Node.class.isInstance( ((Element)e).getData()) ) {
                //Then we continue our walk to the left
                sb.append(0);
                //System.out.println( "Walking left - sb: " + sb );
                innerNodeWalk(sb,  ((Node)((Element)e).getData()).getLeft() );
                sb.deleteCharAt(sb.length()-1);
                sb.append(1);
                //System.out.println( "Walking right - sb: " + sb );
                innerNodeWalk(sb,  ((Node)((Element)e).getData()).getRight());
                sb.deleteCharAt(sb.length()-1);
            } else {
                //System.out.println( "Adding data: " + ((Element) e).getData() + " with the value: " + sb.toString() );
                hashMap.put(((int)((Element) e).getData()), sb.toString());
            }
        }
    }
}

