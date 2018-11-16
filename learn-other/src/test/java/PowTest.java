import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class PowTest {
    @Test
    public void test(){
        long sum=0;
        for (int i = 6; i <=15; i++) {
            sum+=Math.pow(37,i);

        }
        log.info("sum={}",sum);
    }
}
