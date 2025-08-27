package hexlet.code;

//import lombok.AllArgsConstructor;
//import lombok.Getter;

//@AllArgsConstructor //почему Компоратор не понимает?
//@Getter //проблема Градле?
public final class Status {
    //зачем final?
    public static final int UNCHANGED = 0; //можно упростить до двух boolean (~)
    public static final int DELETED = -1;
    public static final int ADDED = 1;
    public static final int CHANGED = 10;

    private int statusName;
    private String mapKey;
    private Object oldValue;
    private Object newValue;

    Status(int statusname, String mapkey, Object oldvalue, Object newvalue) {
        statusName = statusname; //this? хитро через малые буквы
        mapKey = mapkey;
        oldValue = oldvalue;
        newValue = newvalue;
    }

    public int getStatusName() {
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
