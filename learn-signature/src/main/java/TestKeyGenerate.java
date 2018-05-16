import com.alipay.util.key.KeyTool;
import com.alipay.util.key.KeyType;

/**
 * @program: JLearn
 * @author: lucky lee
 * @create: 2018-05-16 14:00
 **/
public class TestKeyGenerate {
    public static void main(String[] args) throws Exception {
       // KeyToolPane.main(args);
       String[] reslust=KeyTool.generateKey(KeyType.RSA2048);
        System.out.println("privateKey:::\n"+reslust[0]);
        System.out.println("publicKey:::\n"+reslust[1]);
    }
}
