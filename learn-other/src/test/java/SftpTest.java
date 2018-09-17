import com.jcraft.jsch.ChannelSftp;
import com.luckylee.test.SftpClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SftpTest {

    private final static Logger log = LoggerFactory.getLogger(SftpTest.class);

    @Test
    public void sftp() {
        SftpClient client = null;
        ChannelSftp sftp = null;
        try {
            client = SftpClient.builder()
                    .enableProxy(true)
                    .proxyHost("11.8.44.60")
                    .proxyPort(3128)
                    .host("xxxx")
                    .port(22)
                    .username("xxx")
                    .password("xxx")
                    .build();
            sftp = client.connect();
            int fileCount = client.download(sftp, "/upload/", "/Users/lining/billFile");
            log.info("下载文件个数 {}", fileCount);
            //client.upload(sftp, "/Users/lining/billFile", "/data/sftp/mysftp/upload/");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null && sftp != null) {
                client.disconnect(sftp);
            }

        }
    }

}
