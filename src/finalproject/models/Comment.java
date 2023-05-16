package finalproject.models;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private String comment;
    private LocalDateTime postDate;
    private User userId;
    private News newsId;

    public Comment(int id, String comment, LocalDateTime postDate, User userId, News newsId) {
        this.id = id;
        this.comment = comment;
        this.postDate = postDate;
        this.userId = userId;
        this.newsId = newsId;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public News getNewsId() {
        return newsId;
    }

    public void setNewsId(News newsId) {
        this.newsId = newsId;
    }
}
