import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class JsonDomain {
    @JSONField(name = "a_a")
    private String aa;

    @JSONField(name = "b_a")
    private String ba;
}
