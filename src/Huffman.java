import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

/**
 * Created by MGund on 10-May-16.
 */
public class Huffman {

    public HashMap<Integer, String> hashMap = new HashMap<>();

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

    public void nodeWalk (Element e) {

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

    private void innerNodeWalk (StringBuilder sb, Object e) {

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

    public void createFile(File infile, File outfile, int[] freq) {

        try {
            FileInputStream inFile = new FileInputStream(infile);
            FileOutputStream outFile = new FileOutputStream(outfile);
            BitInputStream in = new BitInputStream(inFile);
            BitOutputStream out = new BitOutputStream(outFile);


            //Indsætter vores freq tabel
            for ( int i : freq ) {
                out.writeInt(i);
            }

            //Encoder vores fil
            int inBit;
            while ((inBit = inFile.read()) != -1) {
                String outBit = hashMap.get(inBit);
                //System.out.println( "inBit: " + inBit + " - outBit: " +  outBit );

                for (String s : outBit.split("")) {
                    //System.out.println(Integer.parseInt(s));
                    out.writeBit(Integer.parseInt(s));
                }
            }

            out.writeBit(0);
            out.writeBit(1);
            inFile.close();
            outFile.close();
        } catch (Exception i) {i.printStackTrace();}
    }

}
