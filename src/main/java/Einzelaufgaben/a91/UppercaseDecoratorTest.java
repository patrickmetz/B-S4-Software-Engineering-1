package Einzelaufgaben.a91;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UppercaseDecoratorTest {
    @Test
    void read_whenFeedingLowerCaseInput_returnsUppercaseOutput () throws IOException {
        InputStream in = new UppercaseDecorator(new FileInputStream(
                System.getProperty("user.dir") + "/src/Einzelaufgaben/a91/Test.txt"));
        int character;

        StringBuilder sb = new StringBuilder(100);

        while((character = in.read()) != -1){
            sb.append((char)character);
        }

        String text = sb.toString();

        Pattern lowercase = Pattern.compile("[a-z]");
        Pattern uppercase = Pattern.compile("[A-Z]");

        assertFalse(lowercase.asPredicate().test(text));
        assertTrue(uppercase.asPredicate().test(text));

        System.out.print(text);
    }
}