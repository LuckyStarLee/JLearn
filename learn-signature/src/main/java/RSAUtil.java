import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JLearn
 * @author: lining
 * @create: 2018-05-01 11:31
 **/
public class RSAUtil {
    private static Map<String, Object> keys = new ConcurrentHashMap<>();

    static {
        init();
    }

    synchronized static void init() {
        String strPfx = "00010000new2048.pfx";
        String strPassword = "000000";
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            InputStream fis = ReadPFX.class.getClassLoader().getResourceAsStream(strPfx);
            char[] nPassword = null;
            if ((strPassword == null) || strPassword.trim().equals("")) {
                nPassword = null;
            } else {
                nPassword = strPassword.toCharArray();
            }
            ks.load(fis, nPassword);
            fis.close();
            System.out.println("keystore type=" + ks.getType());
            Enumeration enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements())// we are readin just one certificate.
            {
                keyAlias = (String) enumas.nextElement();
                System.out.println("alias=[" + keyAlias + "]");
            }
            // Now once we know the alias, we could get the keys.
            System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
            keys.put("privateKey", prikey);
            Certificate cert = ks.getCertificate(keyAlias);
            PublicKey pubkey = cert.getPublicKey();
            keys.put("publicKey1", pubkey);
            System.out.println("cert class = " + cert.getClass().getName());
            System.out.println("cert = " + cert);
            System.out.println("public key = " + pubkey);
            System.out.println("private key = " + prikey);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
        //加载公钥
        String cerName = "acp_test_verify_sign.cer";
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            InputStream fis = ReadPFX.class.getClassLoader().getResourceAsStream(cerName);
            X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(fis);
            fis.close();
            System.out.println("读取Cer证书信息...");
            System.out.println("x509Certificate_SerialNumber_序列号___:" + x509Certificate.getSerialNumber());
            System.out.println("x509Certificate_getIssuerDN_发布方标识名___:" + x509Certificate.getIssuerDN());
            System.out.println("x509Certificate_getSubjectDN_主体标识___:" + x509Certificate.getSubjectDN());
            System.out.println("x509Certificate_getSigAlgOID_证书算法OID字符串___:" + x509Certificate.getSigAlgOID());
            System.out.println("x509Certificate_getNotBefore_证书有效期___:" + x509Certificate.getNotAfter());
            System.out.println("x509Certificate_getSigAlgName_签名算法___:" + x509Certificate.getSigAlgName());
            System.out.println("x509Certificate_getVersion_版本号___:" + x509Certificate.getVersion());
            System.out.println("x509Certificate_getPublicKey_公钥___:" + x509Certificate.getPublicKey());
            keys.put("publicKey", x509Certificate.getPublicKey());
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String signData(String data) {

        try {
            Signature sign = Signature.getInstance("SHA256WithRSA");//SHA256WithRSA
            sign.initSign((PrivateKey) keys.get("privateKey"));//设置私钥
            sign.update(data.getBytes());//设置明文
            byte[] signRstByte = sign.sign();//加密
            BASE64Encoder encoder = new BASE64Encoder();
            String signValue = encoder.encodeBuffer(signRstByte);//BASE64编码
            return signValue;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean verify(String sign,String origContext) {


        try {
            Signature publicVerify = Signature.getInstance("SHA256WithRSA");//SHA256WithRSA
            publicVerify.initVerify((PublicKey) keys.get("publicKey1"));//设置公钥
            publicVerify.update(origContext.getBytes());
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decodeSign = decoder.decodeBuffer(sign);
            return publicVerify.verify(decodeSign);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String sign = signData("1111");
        System.out.println("签名串------> " + sign);
        System.out.println(verify(sign,"1111"));

    }
}
