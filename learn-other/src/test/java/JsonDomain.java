import com.alibaba.fastjson.annotation.JSONField;

public class JsonDomain {
    @JSONField(name = "a_a")
    private String aa;

    @JSONField(name = "b_a")
    private String ba;

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getBa() {
        return ba;
    }

    public void setBa(String ba) {
        this.ba = ba;
    }
}
