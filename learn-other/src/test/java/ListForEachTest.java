import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListForEachTest {
    @Test
    public void forEach(){
        List<String> datas=new ArrayList<>();
        datas.add("123");
        datas.add("45634");
        datas.add("7892");
        datas.add("abch");
        datas.add("sdfhrthj");
        datas.add("mvkd");
        datas.forEach(e->{
            if(e.length()>=5){
                return;
            }
            System.out.println(e);
        });
    }
}
