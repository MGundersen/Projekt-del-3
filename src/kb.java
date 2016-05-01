/**
 * Created by MGund on 4/27/2016.
 */
public class kb {

    Integer key;
    Integer value;


    public kb(Integer key) {
        this.key = key;
        this.value = 1;
    }

    public Integer getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public void incValue() {
        value++;
    }
}
