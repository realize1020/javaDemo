package gson;

import java.io.Serializable;
import java.util.Date;

public class Occurrences {

    private Date start_time;
    private String uuid;
    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }
    public Date getStart_time() {
        return start_time;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Occurrences{" +
                "start_time=" + start_time +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
