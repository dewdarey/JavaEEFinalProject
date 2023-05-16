package finalproject.db;

import finalproject.models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBM {
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/JavaEEFinal",
                    "postgres",
                    "postgres"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUserByEmail(String email) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from users where email=? ");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setEmail(email);
                user.setId(resultSet.getLong("id"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRoleID(resultSet.getInt("role_id"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static ArrayList<News> getNews() {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select n.id, n.post_date, n.category_id, nc.id as content_id, nc.title, nc.content, nc.language_id from news n " +
                    "inner join news_contents nc on n.id = nc.news_id " +
                    "order by n.post_date desc");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                News novost = new News();
                novost.setId(resultSet.getInt("id"));
                novost.setPostDate(resultSet.getTimestamp("post_date").toLocalDateTime());
                NewsContent contents = new NewsContent();
                contents.setId(resultSet.getInt("content_id"));
                contents.setTitle(resultSet.getString("title"));
                contents.setContent(resultSet.getString("content"));

                novost.setTitle(contents);
                novost.setContent(contents);

                news.add(novost);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "insert into users(email, password, full_name, role_id) " +
                    "values (?, ?, ?, ?)");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4, user.getRoleID());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<News> getNewsByLang(int id) {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select n.id, n.post_date, n.category_id, nc.id as content_id, nc.title, nc.content, nc.language_id from news n " +
                    "inner join news_contents nc on n.id = nc.news_id " +
                    "inner join languages l on l.id = nc.language_id " +
                    "where l.id =? " +
                    "order by n.post_date desc");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                News novost = new News();
                novost.setId(resultSet.getInt("id"));
                novost.setPostDate(resultSet.getTimestamp("post_date").toLocalDateTime());
                NewsContent contents = new NewsContent();
                contents.setId(resultSet.getInt("content_id"));
                contents.setTitle(resultSet.getString("title"));
                contents.setContent(resultSet.getString("content"));
                novost.setTitle(contents);
                novost.setContent(contents);

                news.add(novost);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static void changeFullName(Long id, String fullName) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "update users set full_name = ? " +
                    "where id = ?");
            statement.setString(1, fullName);
            statement.setLong(2, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changePassword(Long id, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "update users set password = ? " +
                    "where id = ?");
            statement.setString(1, password);
            statement.setLong(2, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int addNews(int categoryId) {
        int id = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "insert into news(post_date, category_id) " +
                    "values (NOW(), ?) RETURNING id ");
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void addNewsContent(NewsContent newsContents) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "insert into news_contents(title, content, news_id, language_id) " +
                    "values (?, ?, ?, ?)");
            statement.setString(1, newsContents.getTitle());
            statement.setString(2, newsContents.getContent());
            statement.setInt(3, newsContents.getId());
            statement.setInt(4, newsContents.getLanguageId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<News> getNewsByCategory(int id, int languageId) {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select n.id, n.post_date, n.category_id, nc.id as content_id, nc.title, nc.content, nc.language_id from news n " +
                    "inner join news_contents nc on n.id = nc.news_id " +
                    "inner join news_categories c on c.id = n.category_id " +
                    "where c.id =? and nc.language_id = ?" +
                    "order by n.post_date desc");
            statement.setInt(1, id);
            statement.setInt(2, languageId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                News novost = new News();
                novost.setId(resultSet.getInt("id"));
                novost.setPostDate(resultSet.getTimestamp("post_date").toLocalDateTime());
                NewsContent contents = new NewsContent();
                contents.setId(resultSet.getInt("content_id"));
                contents.setTitle(resultSet.getString("title"));
                contents.setContent(resultSet.getString("content"));
                contents.setLanguageId(resultSet.getInt("language_Id"));
                NewsCategory category = new NewsCategory();
                category.setId(resultSet.getInt("category_id"));
                novost.setCategoryId(category);
                novost.setTitle(contents);
                novost.setContent(contents);
                news.add(novost);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static ArrayList<News> getNewsAll(int categoryId) {
        ArrayList<News> news = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select n.id, n.post_date, n.category_id, nc.id as content_id, nc.title, nc.content, nc.language_id from news n " +
                    "inner join news_contents nc on n.id = nc.news_id " +
                    "inner join news_categories c on c.id = n.category_id " +
                    "where c.id =? " +
                    "order by n.post_date desc");
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                News novost = new News();
                novost.setId(resultSet.getInt("id"));
                novost.setPostDate(resultSet.getTimestamp("post_date").toLocalDateTime());
                NewsContent contents = new NewsContent();
                contents.setId(resultSet.getInt("content_id"));
                contents.setTitle(resultSet.getString("title"));
                contents.setContent(resultSet.getString("content"));
                NewsCategory category = new NewsCategory();
                category.setId(resultSet.getInt("category_id"));
                novost.setCategoryId(category);
                novost.setTitle(contents);
                novost.setContent(contents);
                news.add(novost);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static News getNewsById(int id) {
        News novost = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select n.id, n.post_date, n.category_id, nc.id as content_id, nc.title, nc.content, nc.language_id from news n " +
                    "inner join news_contents nc on n.id = nc.news_id " +
                    "where n.id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                novost = new News();
                novost.setId(resultSet.getInt("id"));
                novost.setPostDate(resultSet.getTimestamp("post_date").toLocalDateTime());
                NewsContent contents = new NewsContent();
                contents.setId(resultSet.getInt("content_id"));
                contents.setTitle(resultSet.getString("title"));
                contents.setContent(resultSet.getString("content"));
                contents.setLanguageId(resultSet.getInt("language_id"));
                NewsCategory category = new NewsCategory();
                category.setId(resultSet.getInt("category_id"));
                novost.setCategoryId(category);
                novost.setTitle(contents);
                novost.setContent(contents);

            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return novost;
    }

    public static ArrayList<Comment> getCommentsByNewsId(int id) {
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(""
                    + "select c.id, c.comment, c.user_id, c.post_date, c.news_id, u.full_name "
                    + "from comments c "
                    + "inner join users u on u.id = c.user_id "
                    + "where c.news_id=? " +
                    "order by c.post_date DESC");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date").toLocalDateTime());
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFullName(resultSet.getString("full_name"));
                comment.setUserId(user);
                comments.add(comment);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static void addComment(Comment comment) {
        try {
            PreparedStatement statement = connection.prepareStatement(""
                    + "insert into comments(comment, post_date, user_id, news_id) "
                    + "values (?, NOW(), ?, ?)");
            statement.setString(1, comment.getComment());
            statement.setLong(2, comment.getUserId().getId());
            statement.setInt(3, comment.getNewsId().getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteNews(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "delete from news where id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteNewsContent(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "delete from news_contents where news_id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteComments(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "delete from comments WHERE news_id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Language getLanguageByNewsId(int id) {
        Language language = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "select n.id, n.post_date, n.category_id, nc.id as content_id, nc.title, nc.content, nc.language_id, l.code from news n " +
                    "inner join news_contents nc on n.id = nc.news_id " +
                    "inner join languages l on nc.language_id = l.id " +
                    "where n.id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                language = new Language();
                language.setId(resultSet.getInt("language_id"));
                language.setCode(resultSet.getString("code"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return language;
    }

    public static void editNewsContent(NewsContent newsContent) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE news_contents SET title = ?, content=? where news_id=? "+
                    "");
            statement.setString(1, newsContent.getTitle());
            statement.setString(2, newsContent.getContent());
            statement.setInt(3, newsContent.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
