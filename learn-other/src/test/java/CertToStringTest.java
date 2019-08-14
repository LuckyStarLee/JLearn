import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CertToStringTest {

    @Test
    public void convert() throws Exception {
        InputStream certStream = CertToStringTest.class.getClassLoader().getResourceAsStream("apiclient_cert.p12");
        byte[] byes = IOUtils.toByteArray(certStream);
        String certStr = Base64Utils.encodeToString(byes);
        ByteArrayInputStream _certStream = new ByteArrayInputStream(Base64Utils.decodeFromString(certStr));
        System.out.println(certStr);
    }
}
