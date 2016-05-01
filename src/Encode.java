import java.io.*;
import java.util.ArrayList;

/**
 * Created by MGund on 4/27/2016.
 */
public class Encode {

        public static void main(String[] args) throws Exception {

            //FileInputStream inFile = new FileInputStream(args[0]);
            //FileOutputStream outFile = new FileOutputStream(args[1]);

            FileInputStream inFile = new FileInputStream( new File( "C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Algoritmer og datastrukturer\\Projekt del 3\\out\\production\\Projekt del 3\\DNA.txt" ) );

            ArrayList<kb> freq = new ArrayList<>();

            int nr;
            while ( (nr = inFile.read()) != -1 ) {
                boolean found = false;
                for (kb x : freq) {
                    if ( x.getKey() == nr ) {
                        found = true;
                        x.incValue();
                    }
                }
                if (!found) {
                    freq.add( new kb(nr) );
                }
            }

            for (kb x : freq) {
                System.out.println( "Key: " + x.getKey() + " - value: " + x.getValue() );
            }


        }
}
