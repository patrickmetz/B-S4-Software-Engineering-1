package Einzelaufgaben.a91;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UppercaseDecorator extends InputStream {
    FileInputStream in;

    public UppercaseDecorator(FileInputStream fileInputStream) {
        in = fileInputStream;
    }

    /**
     * Reference: http://www.asciitable.com/
     * @return
     * @throws IOException
     */
    @Override
    public int read() throws IOException {
        int asciiChar = in.read();

        if (asciiChar >= 97 && asciiChar <= 122) {
            asciiChar -= 32;
        }
        return asciiChar;
    }
}
