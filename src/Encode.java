import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MGund on 4/27/2016.
 */
public class Encode {

    public static void main(String[] args) {

        //FileInputStream inFile = new FileInputStream(args[0]);
        //FileOutputStream outFile = new FileOutputStream(args[1]);

        try {
            File inFile = new File("C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Algoritmer og datastrukturer\\Projekt del 3\\out\\production\\Projekt del 3\\DNA.txt");
            File outFile = new File("C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Algoritmer og datastrukturer\\Projekt del 3\\out\\production\\Projekt del 3\\newDNA.txt");

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

}

