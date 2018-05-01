import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Enumeration;

/**
 * @program: JLearn
 * @author: lining
 * @create: 2018-05-01 11:11
 **/
public class ReadPFX {
    public ReadPFX (){
    }

    public static void main(String[] args) throws Exception {
        String strPfx = "00010000new2048.pfx";
        String strPassword ="000000";
        KeyStore ks = KeyStore.getInstance("PKCS12");
       // FileInputStream fis = new FileInputStream(strPfx);
        InputStream fis=ReadPFX.class.getClassLoader().getResourceAsStream(strPfx);
        // If the keystore password is empty(""), then we have to set
        // to null, otherwise it won't work!!!
        char[] nPassword = null;
        if ((strPassword == null) || strPassword.trim().equals("")){
            nPassword = null;
        }
        else
        {
            nPassword = strPassword.toCharArray();
        }
        ks.load(fis, nPassword);
        fis.close();
        System.out.println("keystore type=" + ks.getType());
        // Now we loop all the aliases, we need the alias to get keys.
        // It seems that this value is the "Friendly name" field in the
        // detals tab <-- Certificate window <-- view <-- Certificate
        // Button <-- Content tab <-- Internet Options <-- Tools menu
        // In MS IE 6.
        Enumeration enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements())// we are readin just one certificate.
        {
            keyAlias = (String)enumas.nextElement();
            System.out.println("alias=[" + keyAlias + "]");
        }
        // Now once we know the alias, we could get the keys.
        System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
        PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
        Certificate cert = ks.getCertificate(keyAlias);
        PublicKey pubkey = cert.getPublicKey();
        System.out.println("cert class = " + cert.getClass().getName());
        System.out.println("cert = " + cert);
        System.out.println("public key = " + pubkey);
        System.out.println("private key = " + prikey);
    }
}
