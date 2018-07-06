

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import org.apache.commons.codec.binary.Base64;

public class ConvertPfx {
	
	public static void main(String[] args) {
		
	}
	
	public static void getPrikey(){
		String pfxkeyfile ="D:\\certs\\allcerts\\00010000new2048.pfx";
		System.out.println("加载签名证书==>" + pfxkeyfile);
		FileInputStream fis = null;
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			fis = new FileInputStream(pfxkeyfile);
			char[] nPassword = "000000".toCharArray();
			if (null != keyStore) {
				keyStore.load(fis, nPassword);
			}
			Enumeration<String> aliasenum = keyStore.aliases();
			String keyAlias = null;
			if (aliasenum.hasMoreElements()) {
				keyAlias = aliasenum.nextElement();
				if (keyStore.isKeyEntry(keyAlias)) {
					X509Certificate  x509Certificate = (X509Certificate) keyStore.getCertificate(keyAlias);
					System.out.println("签名私钥-公钥内容："+Base64.encodeBase64String(x509Certificate.getPublicKey().getEncoded()));
		        }
			}
			PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias,"000000".toCharArray());
			System.out.println("签名私钥-私钥内容：" + Base64.encodeBase64String(privateKey.getEncoded()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null!=fis)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public static void getPubkey(){
		String pubkeyfile ="D:\\certs\\acp_test_verify_sign.cer";
		System.out.println("加载验签证书==>" + pubkeyfile);
		CertificateFactory cf = null;
		FileInputStream in = null;
		try {
			cf = CertificateFactory.getInstance("X.509");
			in = new FileInputStream(pubkeyfile);
			X509Certificate validateCert = (X509Certificate) cf.generateCertificate(in);
			System.out.println("公钥内容："+Base64.encodeBase64String(validateCert.getPublicKey().getEncoded()));
		}catch (CertificateException e) {
			e.printStackTrace();
			return ;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
