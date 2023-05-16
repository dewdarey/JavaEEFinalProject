package finalproject.models;

import java.time.LocalDateTime;

public class News {
    private int id;
    private NewsCategory categoryId;
    private LocalDateTime postDate;
    private NewsContent title;
    private NewsContent content;


    public News(int id, NewsCategory categoryId, LocalDateTime postDate, NewsContent title, NewsContent content) {
        this.id = id;
        this.categoryId = categoryId;
        this.postDate = postDate;
        this.title = title;
        this.content = content;
    }

    public NewsContent getTitle() {
        return title;
    }

    public void setTitle(NewsContent title) {
        this.title = title;
    }

    public NewsContent getContent() {
        return content;
    }

    public void setContent(NewsContent content) {
        this.content = content;
    }

    public News() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NewsCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(NewsCategory categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }
}
