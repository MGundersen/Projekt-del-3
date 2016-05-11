import java.io.*;

/*
 * Created by MGund15, Danjo14 & Jopha15
 */
public class Encode {

    public static void main(String[] args) {



        try {
            File inFile = new File(args[0]);
            File outFile = new File(args[1]);


            Huffman hf = new Huffman();

            /* Laver vores hyppighedstabel */
            int[] freq = createFreq(inFile);

            /* Fylder vores hashMap */
            hf.nodeWalk(hf.huffmannStart(freq));

            /* Skriver til vores nye fil */
            hf.createFile(inFile,outFile, freq);

        } catch (Exception e) {e.printStackTrace();}

    }

    private static int[] createFreq(File file) throws Exception {
        /* Creates a fileinputstream with our file */
        FileInputStream inFile = new FileInputStream(file);
        /* Instantiates an array with 256 indices */
        int[] freq = new int[256];

        /*
         * Reads a byte from the stream and maps it our int array.
         * Index = ascii value for character, number on index = frequency of the character
         */
        int intIn;
        while ((intIn = inFile.read()) != -1) {
            freq[intIn] = freq[intIn] + 1;
        }
        /* Closes our input stream */
        inFile.close();
        return freq;
    }

}

