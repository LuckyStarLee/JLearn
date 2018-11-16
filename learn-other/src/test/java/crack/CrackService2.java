package crack;

import org.springframework.util.Base64Utils;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class CrackService2 {
    //    static String strPfx = "xlzf-unionpay.pfx";
    static String strPfx = "unionpay.pfx";
    //密码可能会包含的字符集合
    static char[] charSource = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'C', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'X',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '@'};
    //    static char[] charSource = {
//            '1', '2', '3', '4', '5', '6'};
    static int sLength = charSource.length; //字符集长度，26个字符即可抽象成26进制系统的基本数

    static LinkedBlockingQueue<String> myList = new LinkedBlockingQueue<>();
    private static ExecutorService es = Executors.newFixedThreadPool(50);
    private static ExecutorService generPasswd = Executors.newFixedThreadPool(10);
    private static ScheduledExecutorService check = Executors.newSingleThreadScheduledExecutor();
    private static boolean result = false;

    static InputStream fis = RSAUtil.class.getClassLoader().getResourceAsStream(strPfx);
    static int maxLength = 8; //设置可能最长的密码长度

    public static void main(String[] args) {
        long beginMillis = System.currentTimeMillis();
        System.out.println(beginMillis);//开始时间
        for (int i = 0; i < 10000; i++) {
            es.submit(() -> {
                while (true) {
                    String passwd = myList.poll();
                    if (passwd != null) {
                        System.out.println("pull=" + passwd + ",size=" + myList.size());
                        loadPfx(myList.poll());
                        if (result) {
                            break;
                        }
                    }

                }
            });
        }
        check.schedule(() -> {
            if (result) {
                try {
                    es.shutdown();
                    generPasswd.shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 15, TimeUnit.MINUTES);

        CrackPass(maxLength);

    }

    //得到密码长度从 1到maxLength的所有不同长的密码集合
    public static void CrackPass(int maxLength) {
        for (int j = 6; j <= maxLength; j++) {
            generPasswd.submit(new GeneratePasswdRunner(j));
        }


    }

    static class GeneratePasswdRunner implements Runnable {
        private int len;

        public GeneratePasswdRunner(int len) {
            this.len = len;
        }

        @Override
        public void run() {
            for (int i = len; i <= len; i++) {
                char[] list = new char[i];
                Crack(list, i);

            }
        }
    }

    //得到长度为len所有的密码组合，在字符集charSource中
    //递归表达式：fn(n)=fn(n-1)*sLenght; 大致是这个意思吧
    private static void Crack(char[] list, int len) {

        if (len == 0) {  //递归出口，list char[] 转换为字符串，并打印
            if (list[0] == '@' || (list[0] >= '0' && list[0] <= '9')) return;
            System.out.println("offer=" + ArrayToString(list));
            myList.offer(ArrayToString(list));
        } else {
            for (int i = 0; i < sLength; i++) {
                list[len - 1] = charSource[i];
                Crack(list, len - 1);
            }
        }
    }

    //list char[] 转换为字符串
    private static String ArrayToString(char[] list) {
        if (list == null || list.length == 0)
            return "";
        StringBuilder buider = new StringBuilder(list.length * 2);
        for (int i = 0; i < list.length; i++) {
            buider.append(list[i]);
        }
        return buider.toString();
    }


    private static void loadPfx(String strPassword) {
        if (strPassword == null) return;
//        String strPassword = "123456";
//        System.out.println("===========" + strPfx + "==============");
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");

            char[] nPassword;
            if (strPassword.trim().equals("")) {
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

            /* 写入Txt文件 */
            File writename = new File("crack.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));

            // Now once we know the alias, we could get the keys.
            out.write("strPassword=" + strPassword + "\n");
            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
            out.write("privateKeyStr:::\n" + new String(Base64Utils.encode(prikey.getEncoded())));
            Certificate cert = ks.getCertificate(keyAlias);
            PublicKey pubkey = cert.getPublicKey();
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            System.out.println("password=" + strPassword + ",result = true");
            result = true;
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | UnrecoverableKeyException e) {
//            e.printStackTrace();
            System.out.println("password=" + strPassword + ",result = false");
        }
    }
}
