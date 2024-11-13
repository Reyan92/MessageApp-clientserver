import java.time.*;
import java.io.*;
import java.util.*;
public class ServerTxt implements Comparable<ServerTxt> {
    private String content;
    Time t=new Time();
    LocalDateTime dateTime;
    public ServerTxt(String content, LocalDateTime dateTime) {
        this.content = content;
        this.dateTime = dateTime;
    }
    public String gettime(LocalDateTime dateTime) {
       return t.returnTime(dateTime);
    }
    @Override
    public int compareTo(ServerTxt other) {
        return this.dateTime.compareTo(other.dateTime);
    }
    @Override
    public String toString() {
        return "content=" + content + "Time=" + gettime(dateTime);
    }
    

    
    
}
