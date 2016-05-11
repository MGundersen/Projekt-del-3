import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

/*
 * Created by MGund15, Danjo14 & Jopha15
 */

public class Huffman {

    //Hashmap to quickly get our binary representation of our characters
    public HashMap<Integer, String> hashMap = new HashMap<>();

    /*
     * huffmannStart takes an int array as input, instantiates our minHeap with the amount of character
     * with a frequecny larger than 0. It creates an element for every index with a value higher than 0, and inserts
     * the element into our minHeap. Afterwards it loops over the size of our minHeap-1, so we remain with 1 element in
     * our minHeap. The loop extracts 2 elements, creates a new node referenceing to our 2 elements, and inserts this
     * node into a new element with a key value of the previous 2 element's frequency combined.
     */

    public Element huffmannStart(int[] freq) {
        Integer heapSize = 0;
        for (int i : freq)
            if (i != 0) heapSize++;

        PQHeap pq = new PQHeap(heapSize);
        for (int i = 0; i < 256; i++)
            if (freq[i] != 0) {
                Element x = new Element();
                x.setKey(freq[i]);
                x.setData(i);
                pq.insert(x);
            }

        for (int i = 0 ; i < heapSize-1 ; i++) {
            Element left = pq.extractMin();
            Element right = pq.extractMin();
            Integer newFreq = left.getKey() + right.getKey();

            Node n = new Node( left, right );
            Element e = new Element();
            e.setKey(newFreq);
            e.setData( n );
            pq.insert( e );
        }

        return pq.extractMin();
    }

    /*
     * Nodewalk and innerNodewalk walks through our huffman tree, and when it goes to the left it appends a 0 to our
     * stringbuilder, and a 1 when it goes to the right. And when it finds an element(and not another node) it
     * puts this element into our hashMap with the data of our element as key, and the string we created on the way
     * as the value.
     */

    public void nodeWalk (Element e) {

        StringBuilder sb = new StringBuilder();
        // First we check if our current element is null
        if (e != null) {
            //If our current Element has a node as data
            if (Node.class.isInstance( e.getData()) ) {
                //Then we continue our walk to the left
                sb.append(0);
                innerNodeWalk(sb, ((Node)((Element)e).getData()).getLeft());
                sb.deleteCharAt(sb.length()-1);
                sb.append(1);
                innerNodeWalk(sb,  ((Node)((Element)e).getData()).getRight());
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    private void innerNodeWalk (StringBuilder sb, Object e) {

        // First we check if our current element is null
        if (e != null) {
            //If our current Element has a node as data
            if (Node.class.isInstance( ((Element)e).getData()) ) {
                //Then we continue our walk to the left
                sb.append(0);
                innerNodeWalk(sb,  ((Node)((Element)e).getData()).getLeft() );
                sb.deleteCharAt(sb.length()-1);
                sb.append(1);
                innerNodeWalk(sb,  ((Node)((Element)e).getData()).getRight());
                sb.deleteCharAt(sb.length()-1);
            } else {
                hashMap.put(((int)((Element) e).getData()), sb.toString());
            }
        }
    }

    /*
     * Creates 3 streams, an input, output and wraps our output with an BitOutputStream. We write our table to the file
     * so we can extract it when we decode our file. Then we read our original file, and writes our binary representation
     * to the decoded file.
     */

    public void createFile(File infile, File outfile, int[] freq) {

        try {
            FileInputStream inFile = new FileInputStream(infile);
            FileOutputStream outFile = new FileOutputStream(outfile);
            BitOutputStream out = new BitOutputStream(outFile);


            //Indsætter vores freq tabel
            for ( int i : freq ) {
                out.writeInt(i);
            }

            //Encoder vores fil
            int inBit;
            while ((inBit = inFile.read()) != -1) {
                String outBit = hashMap.get(inBit);
                for (String s : outBit.split(""))
                    out.writeBit(Integer.parseInt(s));
            }

            out.writeBit(0);
            out.writeBit(1);
            out.close();
            outFile.close();
            inFile.close();

        } catch (Exception i) {i.printStackTrace();}
    }

}
