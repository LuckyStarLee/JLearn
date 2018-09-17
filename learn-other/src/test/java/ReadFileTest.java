import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class ReadFileTest {

    @Test
    public void read() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ReadFileTest.class.getResourceAsStream("aa.txt")));
        String str = bufferedReader.readLine();
        while (str != null) {
            System.out.println(str.replace(" ", ""));
            str = bufferedReader.readLine();
        }

    }
}
