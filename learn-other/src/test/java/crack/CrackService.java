package crack;

import org.springframework.util.Base64Utils;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Enumeration;

public class CrackService {
    //密码可能会包含的字符集合
    static char[] charSource = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n',  'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' ,
            '0',  '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static int sLength = charSource.length; //字符集长度，26个字符即可抽象成26进制系统的基本数
    public void main(String[] args) {

        long beginMillis = System.currentTimeMillis();
        System.out.println(beginMillis);//开始时间

        int maxLength = 5; //设置可能最长的密码长度
        int counter = 0;//计数器，多线程时可以对其加锁，当然得先转换成Integer类型。
        StringBuilder buider = new StringBuilder();

        while (buider.toString().length() <= maxLength) {
            buider = new StringBuilder(maxLength*2);
            int _counter = counter;
            while (_counter >= sLength) {//10进制转换成26进制
                buider.insert(0, charSource[_counter % sLength]);//获得低位
                _counter = _counter / sLength;
                _counter--;//精髓所在，处理进制体系中只有10没有01的问题，在穷举里面是可以存在01的
            }
            buider.insert(0,charSource[_counter]);//最高位
            counter++;
//            System.out.println(buider.toString());
            loadPfx(buider.toString());
        }

        long endMillis = System.currentTimeMillis();
        System.out.println(endMillis);//结束时间

        System.out.println(endMillis - beginMillis);//总耗时，毫秒
    }

    private static void loadPfx(String strPassword) {
        String strPfx = "xlzf-unionpay.pfx";
//        String strPassword = "123456";
//        System.out.println("===========" + strPfx + "==============");
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            InputStream fis = RSAUtil.class.getClassLoader().getResourceAsStream(strPfx);
            char[] nPassword = null;
            if ((strPassword == null) || strPassword.trim().equals("")) {
                nPassword = null;
            } else {
                nPassword = strPassword.toCharArray();
            }
            ks.load(fis, nPassword);
            fis.close();
            Enumeration enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements())// we are readin just one certificate.
            {
                keyAlias = (String) enumas.nextElement();
                // System.out.println("alias=[" + keyAlias + "]");
            }
            // Now once we know the alias, we could get the keys.
            System.out.println("strPassword=" + strPassword);
            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
            System.out.println("privateKeyStr:::\n" + new String(Base64Utils.encode(prikey.getEncoded())));
            Certificate cert = ks.getCertificate(keyAlias);
            PublicKey pubkey = cert.getPublicKey();
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
