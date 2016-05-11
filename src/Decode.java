import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/*
 * Created by MGund15, Danjo14 & Jopha15
 */

public class Decode {

    /*
     * Hashmap that contains our binary representation and our character value created from our frequency table
     */
    private static HashMap<Integer, String> hashMap;

    public static void main(String[] args) {

        try {
            /* Creates our 3 input, output and bitInput streams */
            FileInputStream inFile = new FileInputStream(args[0]);
            FileOutputStream outFile = new FileOutputStream(args[1]);
            BitInputStream in = new BitInputStream(inFile);

            /* Reconstructs our int array from the first 256 integers in our decoded file */
            int[] freq = new int[256];
            int bit;
            int counter = 0;
            while ( counter < 256 ) {
                bit = in.readInt();
                freq[counter] = bit;
                counter++;
            }
            /* Creates our huffmann tree and fill our hashmap again same way as Encode */
            Huffman hf = new Huffman();
            hf.nodeWalk(hf.huffmannStart(freq));
            hashMap = hf.hashMap;


            /*
             * We go through every bit of our file. If the current bit is in our hashmap, we write the corresponding key
             * to the new file, and restart/delete the characters in our StringBuilder. If the bit is not in our hashmap
             * we check the next bit, and combine it with the last bit, and check again if it is in our hashmap and so on
             */
            StringBuilder sb = new StringBuilder();

            while ((bit = in.readBit())!= -1) {
                sb.append(bit);
                if ( checkMap(sb.toString()) ) {
                    for (Map.Entry<Integer, String> s : hashMap.entrySet() ) {
                        if (s.getValue().equals(sb.toString())) {
                            //System.out.println( "Writing " + s.getKey() + " to the new file.." );
                            outFile.write(s.getKey());
                        }
                    }
                    sb.delete(0, sb.length());
                }
            }
            in.close();
            inFile.close();
            outFile.close();


        } catch (Exception e) {e.printStackTrace();}

        }

    /* Method for checking the values in our hashmap and compares them to the inputted string */
    private static Boolean checkMap(String bit) {
        for (String s : hashMap.values() ) {
            if (s.equals(bit)) {
                return true;
            }
        }
        return false;
    }

}
