import java.time.LocalDateTime;

public class ClientTxt implements Comparable<ClientTxt> {
    public String content;
    LocalDateTime time;
    Time t=new Time();
    
    public ClientTxt(String content, LocalDateTime time) {
        this.content = content;
        this.time = LocalDateTime.now();
    }
    @Override
    public int compareTo(ClientTxt o) {
        return time.compareTo(o.time);
    }
    public String gettime(LocalDateTime time){
       return t.returnTime(time);
    }
    @Override
    public String toString() {
        return "content=" + content + "Time=" + gettime(time);
    }
    

}
