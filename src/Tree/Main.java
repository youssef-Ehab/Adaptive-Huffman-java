package Tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Main {
    public static Vector compression(String input) throws IOException {
        File f = new File("output.txt");
        FileWriter writer = new FileWriter("output.txt");
        f.createNewFile();

        tree test = new tree();
        test.AddtoASCII();
        for (int i = 0; i < input.length(); i++) {
            test.AddNode(input.charAt(i));
        }
        test.removeNYT();
        System.out.println(test.GetEncoded());
        writer.append(test.GetEncoded().toString());
        writer.close();
        return test.GetEncoded();
    }

    public static void main(String[] args) throws IOException {
        Vector<String> vec = compression("ABBCCCAAAA");
        //compression("ABCCCAAAA");

        tree test = new tree();
        test.AddtoASCII();
        test.decompression(vec);
    }
}
