package hexlet.code;

//import lombok.AllArgsConstructor;
//import lombok.Getter;

//@AllArgsConstructor //почему Компоратор не понимает?
//@Getter //проблема Градле?
public final class Status {
    //зачем final?
    public static final String DELETED = "deleted"; //можно упростить до boolean или int
    public static final String ADDED = "added";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    private String statusName;
    private String mapKey;
    private Object oldValue;
    private Object newValue;

    Status(String statusname, String mapkey, Object oldvalue, Object newvalue) {
        statusName = statusname; //this? хитро через малые буквы
        mapKey = mapkey;
        oldValue = oldvalue;
        newValue = newvalue;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getMapKey() {
        return mapKey;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

}
