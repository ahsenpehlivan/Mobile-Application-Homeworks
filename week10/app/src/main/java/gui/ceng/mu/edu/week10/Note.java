package gui.ceng.mu.edu.week10;

import java.util.Date;
import java.io.Serializable;

public class Note implements Serializable {
    private String header;
    private Date date;
    private String filePath;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
