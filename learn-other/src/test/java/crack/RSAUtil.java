package crack;

import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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
    private static String KEY_ALGORITHM = "RSA";

    static {
        init();
    }

    synchronized static void init() {
        String strPfx = "xlzf-unionpay.pfx";
//        String strPassword = "123456";
        String strPassword = "111111";
        System.out.println("==========="+strPfx+"==============");
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
           // System.out.println("keystore type=" + ks.getType());
            Enumeration enumas = ks.aliases();
            String keyAlias = null;
            if (enumas.hasMoreElements())// we are readin just one certificate.
            {
                keyAlias = (String) enumas.nextElement();
               // System.out.println("alias=[" + keyAlias + "]");
            }
            // Now once we know the alias, we could get the keys.
           // System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
            keys.put("privateKey", prikey);
            keys.put("privateKeyStr", new String(Base64Utils.encode(prikey.getEncoded())));
            System.out.println("privateKeyStr:::\n"+keys.get("privateKeyStr"));
            System.out.println("encode again privateKeyStr:::\n"+new String(Base64Utils.encode((keys.get("privateKeyStr")).toString().getBytes())));
            Certificate cert = ks.getCertificate(keyAlias);
            PublicKey pubkey = cert.getPublicKey();
            keys.put("publicKey", pubkey);
            keys.put("publicKeyStr", new String(Base64Utils.encode(pubkey.getEncoded())));
            System.out.println("publicKeyStr:::\n"+keys.get("publicKeyStr"));
            System.out.println("encode again publicKeyStr:::\n"+new String(Base64Utils.encode((keys.get("publicKeyStr")).toString().getBytes())));

            //  System.out.println("cert class = " + cert.getClass().getName());
           // System.out.println("cert = " + cert);
           // System.out.println("public key = " + pubkey);
           // System.out.println("private key = " + prikey);
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
//        //加载公钥
//        String cerName = "sha256-T.cer";
//        System.out.println("==========="+cerName+"==============");
//        try {
//            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//            InputStream fis = RSAUtil.class.getClassLoader().getResourceAsStream(cerName);
//            X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(fis);
//            fis.close();
//            System.out.println("读取Cer证书信息...");
//            //System.out.println("x509Certificate_SerialNumber_序列号___:" + x509Certificate.getSerialNumber());
//           // System.out.println("x509Certificate_getIssuerDN_发布方标识名___:" + x509Certificate.getIssuerDN());
//            //System.out.println("x509Certificate_getSubjectDN_主体标识___:" + x509Certificate.getSubjectDN());
//            //System.out.println("x509Certificate_getSigAlgOID_证书算法OID字符串___:" + x509Certificate.getSigAlgOID());
//           // System.out.println("x509Certificate_getNotBefore_证书有效期___:" + x509Certificate.getNotAfter());
//           // System.out.println("x509Certificate_getSigAlgName_签名算法___:" + x509Certificate.getSigAlgName());
//           // System.out.println("x509Certificate_getVersion_版本号___:" + x509Certificate.getVersion());
//           // System.out.println("x509Certificate_getPublicKey_公钥___:" + x509Certificate.getPublicKey());
//            keys.put("publicKey", x509Certificate.getPublicKey());
//            keys.put("publicKeyStr", new String(Base64Utils.encode(x509Certificate.getPublicKey().getEncoded())));
//            System.out.println("publicKeyStr:::\n"+keys.get("publicKeyStr"));
//            System.out.println("encode again publicKeyStr:::\n"+new String(Base64Utils.encode((keys.get("publicKeyStr").toString().getBytes()))));
//        } catch (CertificateException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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

    public static String signDataWithStrKey(String data) {

        try {
            Signature sign = Signature.getInstance("SHA256WithRSA");//SHA256WithRSA

            sign.initSign(restorePrivateKey(Base64Utils.decodeFromString((String) keys.get("privateKeyStr"))));//设置私钥
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

    public static boolean verify(String sign, String origContext) {


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
    public static boolean verifyWithStrKsy(String sign, String origContext) {


        try {
            Signature publicVerify = Signature.getInstance("SHA256WithRSA");//SHA256WithRSA
            publicVerify.initVerify(restorePublicKey(Base64Utils.decodeFromString((String) keys.get("publicKey1Str"))));//设置公钥
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
    /**
     * 还原公钥
     *
     * @param keyBytes
     * @return
     */
    public static PublicKey restorePublicKey(byte[] keyBytes) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            return publicKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 还原私钥
     *
     * @param keyBytes
     * @return
     */
    public static PrivateKey restorePrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = factory
                    .generatePrivate(pkcs8EncodedKeySpec);
            return privateKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public  void main(String[] args) {
//        String sign = signData("1111");
//        String sign2 = signDataWithStrKey("1111");
//
//        System.out.println("签名串sign------> " + sign);
//        System.out.println("签名串sign2------> " + sign2);
//        System.out.println(verify(sign, "1111"));
//        System.out.println(verifyWithStrKsy(sign2, "1111"));
        init();

    }
}
