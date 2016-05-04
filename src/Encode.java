import java.io.*;
import java.util.ArrayList;

/**
 * Created by MGund on 4/27/2016.
 */
public class Encode {

        public static void main(String[] args) throws Exception {

            //FileInputStream inFile = new FileInputStream(args[0]);
            //FileOutputStream outFile = new FileOutputStream(args[1]);

            FileInputStream inFile = new FileInputStream(new File("C:\\Users\\Daniel\\Desktop\\DNA.txt"));

            ArrayList<Element> freq = new ArrayList<>();

            int nr;
            while ((nr = inFile.read()) != -1) {
                boolean found = false;
                for (Element x : freq) {
                    if (x.getKey() == nr) {
                        found = true;
                        x.incValue();
                    }
                }
                if (!found) {
                    Element x = new Element();
                    x.setKey(nr);
                    freq.add(x);
                }
            }

            for (Element x : freq) {
                System.out.println("Key: " + x.getKey() + " - value: " + x.getValue());
            }

//            System.out.println(huffmann(freq)); // root value

            huffmann(freq);

        }
            // Huffmann

            public static Element huffmann(ArrayList<Element> freq){
                PQHeap pqh = new PQHeap(100);
                int n = freq.size();
                for (Element q : freq){
                    pqh.insert(q);
                }

                for (int i = 1; i < n-1; i++){
                    Element z = new Element();

                    // Element x
                    Element x = pqh.extractMin();
                    z.setLeft(pqh.extractMin());

                    // Element y
                    Element y = pqh.extractMin();
                    z.setRight(pqh.extractMin());

                    z.value = x.value + y.value;

                    pqh.insert(z);
                }
                return pqh.extractMin();
            }


        }

