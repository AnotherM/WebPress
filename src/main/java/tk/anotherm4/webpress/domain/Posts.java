package tk.anotherm4.webpress.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * 主页和管理实体
 */
@Entity
public class Posts {
    private long pid;
    private String title;
    private String content;
    private Date date;
    private String author;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pid", nullable = false)
    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "title", length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content", length = 5000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "author", length = 100)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Posts posts = (Posts) o;
        return pid == posts.pid &&
                Objects.equals(title, posts.title) &&
                Objects.equals(content, posts.content) &&
                Objects.equals(date, posts.date) &&
                Objects.equals(author, posts.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, title, content, date, author);
    }
}
