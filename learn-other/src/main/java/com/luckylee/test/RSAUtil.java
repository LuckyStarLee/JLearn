package com.luckylee.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {
    //RSA验签公钥  银行提供（一般情况不变更）
    //public static final String RSA_PUBLICKEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKX/5YWeGNmOtSc2C52882EYV92BAllyiM8o+3mmfVmejkeW+ZTsBGjAQL7Cl8EjXaA+iU97EhzEgw1cEJRnpcu6N6h0DVaoC7KL50ea7fgwhKSOlf5CyltEh2KK2OMvrGjlfYNQE5wBhU4jbSUQfVoZ2xIflhpZy15tRBPxQo7QIDAQAB";
    public static final String RSA_PUBLICKEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiz1bvCiZtlOz5LNq0vkoOffVKhn4PkofuC4vf0LCWsEmYlFqv3qHyqiFW3Kdgg2ex9D24/Z3pGla1M6l9R0Zl9xZzNCYCKjyfzE8SVWTuMngBriCnC2bi01gAtx2fZ0tKhBUcUFyLtA+PYsG6Smgt8pTLF8EfFkAzjmYC05el8wIDAQAB";
    //RSA加密私钥  商户留存
    //public static final String RSA_PRIVATEKEY="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIyY1IvAm9Sfact33x8JbCFcpCzbwWUM1YdA2AH2s0T4AWZPblvDbZ4LppwwmtaKm8nRvbg9LH3t/V955nwKJvahJ1aUniReD9Z1MiYvH21DWR7RLldCAD/YcLNOk5B/xzbgx9bN8k34OV3stqi3g182QHEm729Le+4vuTUm1mhzAgMBAAECgYA9awHmbuRj9KONm7bfYwlWZZbedb08/GdG51+1M+TKKyDJZceK1ItNMD+/GUh6kiBOAI7FLgwCkvVNQP4bj3/HLzQ7AMrpkgm1zfR07AOujdhqtqneYAlSFXBrgG8tlPHWa8kzhW9pdv/lVWkvL3RlPxb+s4ZU5q0kOS5W7Q7NaQJBAPn10RNhTYe57Ax1e/Go2qqNgtuUcrSFWYH6kr7NZKkvjyIHNR8i9AqNK9u7TG7SMe2veh3O+vtcPcYAvt1F928CQQCP/oYF9I7T8hImeXwoaImfktl2LDXIT+WNkP1LteohqBZcXgH5LlfFC8X/7w9XZ8ilZA0ga5ufMoWWQWqfmz09AkEA8WLjEl0gGxeADhQSqrAO4y+flF+KWiyA4NXTivEc6YmlJw5gzX3awKnOFfeNe2P/lXKlck3k8IMGoMUtjNA5fwJAIYdGdcSILVnEWCkZe987jGiJMjcBHu9bqCcGk8Z8CfI1orRRCVB9J2iiZgGoNNX5pmkxlUb5KYx+Boqc5SUkkQJBALuz20z6zyNz8SwcD8orxaHh5SonIGWSHE1BKYqjtXBeZptaJn3Xp6RqOK79O1VlP6Gg8/3gisVPJnP5a7b2A+E=";//银行私钥
    public static final String RSA_PRIVATEKEY="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKLPVu8KJm2U7Pks2rS+Sg599UqGfg+Sh+4Li9/QsJawSZiUWq/eofKqIVbcp2CDZ7H0Pbj9nekaVrUzqX1HRmX3FnM0JgIqPJ/MTxJVZO4yeAGuIKcLZuLTWAC3HZ9nS0qEFRxQXIu0D49iwbpKaC3ylMsXwR8WQDOOZgLTl6XzAgMBAAECgYAJvFpXewTnQqOVFvCRJFvrV+7mIPrC1aFZQ8l+fc1BhZMxE5YEtEx85V2PAyzjIZh+1LsoJ8pcmR2cQlWdTzjPYWsK1dN3Qi6xGcE0jgOyTJt2fdmNMfvAIpakMXZ5N7X3QDocDx4jO6O1lgyEMOxncHh+PoPAILpMuhZ6vuVGOQJBAO6wMWLJBjA/plv+sfgAJIueBxVWlKHQ3/XK25/Irnqei6B2eOAShJEapOY4QID12XLZYBEHJ9r8wKZs6Vws630CQQCunkmZjtHufp8iKv3X/PgkSWnBe40NvuVAh6wKjODX0yhwK7eLDwS78knO5wCVm1Q+77Hmpz3QbsshxS3B4jIvAkBSM2eup6Bz+Venkv1jdVkgpBm4ZYrmd3LJyUT9sDU2kWdwqZxTPs1c05o6luDQCNsMTIRjGN3G8wi9yABN+EJZAkEAjRgzilO/x8Einn40jMDIVLHJNWC60f+MyH6YAhJhX6Msiq6YaVGiVqdpAjjf3QMcqJoPmrtKFFBh7JCh74bxWwJBALFpeiLWaG5j7YnjBo4lhyyXOsi2Z7XFftRnPMSl4wtQtEEe2AJgGVdvbu50E3ddcq4pI8z3V3rJbFYV24NVlpk=";

    public static final int MAX_ENCRYPT_BLOCK = 117;
    public static final int MAX_DECRYPT_BLOCK = 128;
    public static final String KEY_ALGORTHM="RSA";//
    public static final String SIGNATURE_ALGORITHM="MD5withRSA";



    public static void main(String[] args) {

        //用此工具生成一对公私钥
        // RSAUtil.getKeyPairs();

        String plain = "{Mch_id:010020170628111959,Mch_name:星巴克,MerSeqNo:111111901709099111109,TransAmt:1,Subject:星冰乐}";
        plain = getBASE64(plain);
        System.out.println("plain数据转成base64编码： "+plain);

        String sign = "{Mch_id:010020170628111959,Mch_name:星巴克,MerSeqNo:111111901709099111109,TransAmt:1,Subject:星冰乐}";
        String signed = sign(sign,RSA_PRIVATEKEY);
        System.out.println("商户私钥签名结果： "+signed);

        System.out.println("url: mtest.cbhb.com.cn/payhpgate/static/index2.html?Plain="+plain+"&Sign="+signed);

        System.out.println("--------"+verify(plain,RSA_PUBLICKEY,signed));



    }

    /**
     * 用私钥对信息生成数字签名
     */
    @SuppressWarnings("restriction")
    public static String sign(String data,String privateKey){
        try {
            byte[]dataBytes = new BASE64Decoder().decodeBuffer(data);
            byte[] keyBytes =new BASE64Decoder().decodeBuffer(privateKey);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory key = KeyFactory.getInstance(KEY_ALGORTHM);
            PrivateKey privateKey2 = key.generatePrivate(pkcs8EncodedKeySpec);
            //用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey2);
            signature.update(dataBytes);
            return new BASE64Encoder().encode(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 校验数字签名
     */
    public static boolean verify(String data,String publicKey,String sign){
        try {
            byte[] dataBytes = new BASE64Decoder().decodeBuffer(data);
            byte[] keyBytes =new BASE64Decoder().decodeBuffer(publicKey);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory key = KeyFactory.getInstance(KEY_ALGORTHM);
            PublicKey publicKey2 = key.generatePublic(x509EncodedKeySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey2);
            signature.update(dataBytes);

            return signature.verify(new BASE64Decoder().decodeBuffer(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 产生密钥对
     */
    public static void getKeyPairs(){
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORTHM);
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            //公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            System.out.println("公钥："+new BASE64Encoder().encode(publicKey.getEncoded()));
            System.out.println("-----------------------------------------------------------------------");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            //私钥
            RSAPrivateKey privateKey =  (RSAPrivateKey) keyPair.getPrivate();
            System.out.println("私钥："+new BASE64Encoder().encode(privateKey.getEncoded()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /**
     *  将 s 进行 BASE64 编码
     * @param s
     * @return
     */
    public static String getBASE64(String s) {
        if (s == null) return null;
        return (new sun.misc.BASE64Encoder()).encode( s.getBytes() );
    }

    /**
     *  将 BASE64 编码的字符串 s 进行解码
     * @param str
     * @return
     */
    public static String getFromBASE64(String str) {
        if (str == null) return null;
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(str);
            return new String(b);
        } catch (Exception e) {
            return null;
        }

    }


}
