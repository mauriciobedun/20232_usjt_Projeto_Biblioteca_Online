package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.model.Loan;

public class LoanDAO {
    private Connection connection;

    public LoanDAO(Connection connection) {
        this.connection = connection;
    }

    public void registrarRetirada(Loan loan) throws SQLException {
        String sql = "INSERT INTO emprestimos (livro_id, usuario_id, data_retirada) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, loan.getBookId());
            statement.setInt(2, loan.getUserId());
            statement.setDate(3, new java.sql.Date(loan.getStartDate().getTime()));
            statement.executeUpdate();
        }
    }

    public void registrarEntrega(Loan loan) throws SQLException {
        String sql = "UPDATE emprestimos SET data_entrega = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(loan.getEndDate().getTime()));
            statement.setInt(2, loan.getId());
            statement.executeUpdate();
        }
    }
}
