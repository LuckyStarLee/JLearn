import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class JsonTest {

    @Test
    public void testJson(){
     JsonDomain jd=new JsonDomain();
     jd.setAa("aa");
     jd.setBa("bb");
     log.info(JSON.toJSONString(jd));
    }
}
