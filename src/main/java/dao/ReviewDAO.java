package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.model.Review;

public class ReviewDAO {
    private Connection connection;

    public ReviewDAO(Connection connection) {
        this.connection = connection;
    }

    public void cadastrarAvaliacao(Review review) throws SQLException {
        String sql = "INSERT INTO avaliacoes (livro_id, usuario_id, classificacao, comentario) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, review.getBookId());
            statement.setInt(2, review.getUserId());
            statement.setInt(3, review.getRating());
            statement.setString(4, review.getComment());
            
            statement.executeUpdate();
        }
    }
}
