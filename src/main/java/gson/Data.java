package gson;

import java.io.Serializable;
import java.util.List;

import java.util.List;
import java.util.Date;

/**
 * Auto-generated: 2023-02-13 14:37:16
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Data {

    private List<Occurrences> occurrences;
    private String join_url;
    private boolean option_jbh;
    private String updateDate;
    private String meeting_id;
    private boolean enable_webinar;
    private String start_url;
    private String user_key;
    private int ready_time;
    private int meeting_capacity;
    private String uuid;
    private String host_id;
    private String token;
    private int duration;
    private Date start_time;
    private String host_key;
    private String play_addr;
    private String live_id;
    private String watch_url;
    private boolean mute_upon_entry;
    private String host_email;
    private String createDate;
    public void setOccurrences(List<Occurrences> occurrences) {
        this.occurrences = occurrences;
    }
    public List<Occurrences> getOccurrences() {
        return occurrences;
    }

    public void setJoin_url(String join_url) {
        this.join_url = join_url;
    }
    public String getJoin_url() {
        return join_url;
    }

    public void setOption_jbh(boolean option_jbh) {
        this.option_jbh = option_jbh;
    }
    public boolean getOption_jbh() {
        return option_jbh;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    public String getUpdateDate() {
        return updateDate;
    }

    public void setMeeting_id(String meeting_id) {
        this.meeting_id = meeting_id;
    }
    public String getMeeting_id() {
        return meeting_id;
    }

    public void setEnable_webinar(boolean enable_webinar) {
        this.enable_webinar = enable_webinar;
    }
    public boolean getEnable_webinar() {
        return enable_webinar;
    }

    public void setStart_url(String start_url) {
        this.start_url = start_url;
    }
    public String getStart_url() {
        return start_url;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }
    public String getUser_key() {
        return user_key;
    }

    public void setReady_time(int ready_time) {
        this.ready_time = ready_time;
    }
    public int getReady_time() {
        return ready_time;
    }

    public void setMeeting_capacity(int meeting_capacity) {
        this.meeting_capacity = meeting_capacity;
    }
    public int getMeeting_capacity() {
        return meeting_capacity;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUuid() {
        return uuid;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }
    public String getHost_id() {
        return host_id;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public int getDuration() {
        return duration;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }
    public Date getStart_time() {
        return start_time;
    }

    public void setHost_key(String host_key) {
        this.host_key = host_key;
    }
    public String getHost_key() {
        return host_key;
    }

    public void setPlay_addr(String play_addr) {
        this.play_addr = play_addr;
    }
    public String getPlay_addr() {
        return play_addr;
    }

    public void setLive_id(String live_id) {
        this.live_id = live_id;
    }
    public String getLive_id() {
        return live_id;
    }

    public void setWatch_url(String watch_url) {
        this.watch_url = watch_url;
    }
    public String getWatch_url() {
        return watch_url;
    }

    public void setMute_upon_entry(boolean mute_upon_entry) {
        this.mute_upon_entry = mute_upon_entry;
    }
    public boolean getMute_upon_entry() {
        return mute_upon_entry;
    }

    public void setHost_email(String host_email) {
        this.host_email = host_email;
    }
    public String getHost_email() {
        return host_email;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "Data{" +
                "occurrences=" + occurrences +
                ", join_url='" + join_url + '\'' +
                ", option_jbh=" + option_jbh +
                ", updateDate='" + updateDate + '\'' +
                ", meeting_id='" + meeting_id + '\'' +
                ", enable_webinar=" + enable_webinar +
                ", start_url='" + start_url + '\'' +
                ", user_key='" + user_key + '\'' +
                ", ready_time=" + ready_time +
                ", meeting_capacity=" + meeting_capacity +
                ", uuid='" + uuid + '\'' +
                ", host_id='" + host_id + '\'' +
                ", token='" + token + '\'' +
                ", duration=" + duration +
                ", start_time=" + start_time +
                ", host_key='" + host_key + '\'' +
                ", play_addr='" + play_addr + '\'' +
                ", live_id='" + live_id + '\'' +
                ", watch_url='" + watch_url + '\'' +
                ", mute_upon_entry=" + mute_upon_entry +
                ", host_email='" + host_email + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
