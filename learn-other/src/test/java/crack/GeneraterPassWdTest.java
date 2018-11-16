package crack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class GeneraterPassWdTest {
    static char[] charSource = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'C', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'X',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '@'};
    static int sLength = charSource.length;

    @Test
    public void gen() {
        int passwdLen = 6;
        char[] list = new char[passwdLen];
        Crack(list, passwdLen);
    }

    private void Crack(char[] list, int len) {

        if (len == 0) {  //递归出口，list char[] 转换为字符串，并打印
            if (list[0] == '@' || (list[0] >= '0' && list[0] <= '9')) return;
            log.info(ArrayToString(list));
        } else {
            for (int i = 0; i < sLength; i++) {
                list[len - 1] = charSource[i];
                Crack(list, len - 1);
            }
        }
    }

    private String ArrayToString(char[] list) {
        if (list == null || list.length == 0)
            return "";
        StringBuilder buider = new StringBuilder(list.length * 2);
        for (int i = 0; i < list.length; i++) {
            buider.append(list[i]);
        }
        return buider.toString();
    }
}
