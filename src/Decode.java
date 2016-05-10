import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by MGund on 10-May-16.
 */
public class Decode {

    private static HashMap<Integer, String> hashMap;

    public static void main(String[] args) {

        try {
            FileInputStream inFile = new FileInputStream(new File("C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Algoritmer og datastrukturer\\Projekt del 3\\out\\production\\Projekt del 3\\newDNA.txt"));
            FileOutputStream outFile = new FileOutputStream(new File("C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Algoritmer og datastrukturer\\Projekt del 3\\out\\production\\Projekt del 3\\newnewDNA.txt"));
            BitInputStream in = new BitInputStream(inFile);
            BitOutputStream out = new BitOutputStream(outFile);

            int[] freq = new int[256];
            int bit;
            int counter = 0;
            while ( counter < 256 ) {
                bit = in.readInt();
                //System.out.println("Counter: " + counter + " - bit: " + bit2);
                freq[counter] = bit;
                counter++;
            }
            Huffman hf = new Huffman();

            hf.nodeWalk(hf.huffmannStart(freq));
            hashMap = hf.hashMap;


            for (Map.Entry<Integer, String> s : hashMap.entrySet() ) {
                System.out.println( "Key: " + s.getKey() + " - value: " + s.getValue());
            }


            StringBuilder sb = new StringBuilder();

            while ((bit = in.readBit())!= -1) {
                sb.append(bit);
                if ( checkMap(sb.toString()) ) {
                    for (Map.Entry<Integer, String> s : hashMap.entrySet() ) {
                        if (s.getValue().equals(sb.toString())) {
                            //System.out.println( "Writing " + s.getKey() + " to the new file.." );
                            out.writeInt(s.getKey());
                        }
                    }
                    sb.delete(0, sb.length());
                }
            }

        } catch (Exception e) {e.printStackTrace();}

        }

    private static Boolean checkMap(String bit) {
        for (String s : hashMap.values() ) {
            if (s.equals(bit)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isInOurList (int bit) {
        return true;
    }
}
