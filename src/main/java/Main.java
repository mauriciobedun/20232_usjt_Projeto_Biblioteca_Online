package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import main.java.dao.BookDAO;
import main.java.dao.LoanDAO;
import main.java.dao.ReviewDAO;
import main.java.model.Book;
import main.java.model.Loan;
import main.java.model.Review;

public class Main {
    public static void main(String[] args) {
        // Configuração da conexão com o banco de dados (substitua pelos seus próprios detalhes de conexão)
        String jdbcUrl = "jdbc:mysql://localhost:3306/biblioteca";
        String dbUser = "seu_usuario";
        String dbPassword = "sua_senha";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            
            // Crie objetos DAO com a conexão
            BookDAO bookDAO = new BookDAO(connection);
            LoanDAO loanDAO = new LoanDAO(connection);
            ReviewDAO reviewDAO = new ReviewDAO(connection);

            Scanner scanner = new Scanner(System.in);

            // Loop principal do menu
            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Cadastrar Livro");
                System.out.println("2. Realizar Empréstimo");
                System.out.println("3. Registrar Entrega");
                System.out.println("4. Cadastrar Avaliação");
                System.out.println("5. Sair");

                System.out.print("Escolha uma opção: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Título do livro: ");
                        String title = scanner.next();
                        System.out.print("Autor do livro: ");
                        String author = scanner.next();
                        System.out.print("Ano de publicação: ");
                        int year = scanner.nextInt();

                        Book newBook = new Book();
                        newBook.setTitle(title);
                        newBook.setAuthor(author);
                        newBook.setYear(year);

                        bookDAO.cadastrarLivro(newBook);
                        System.out.println("Livro cadastrado com sucesso!");

                        break;
                    case 2:
                        System.out.print("ID do usuário: ");
                        int userId = scanner.nextInt();
                        System.out.print("ID do livro: ");
                        int bookId = scanner.nextInt();

                        Loan newLoan = new Loan();
                        newLoan.setUserId(userId);
                        newLoan.setBookId(bookId);

                        loanDAO.registrarRetirada(newLoan);
                        System.out.println("Empréstimo registrado com sucesso!");

                        break;
                    case 3:
                        // Lógica para registrar a entrega
                        System.out.print("ID do empréstimo a ser entregue: ");
                        int loanId = scanner.nextInt();

                        Loan loanToReturn = new Loan();
                        loanToReturn.setId(loanId);

                        loanDAO.registrarEntrega(loanToReturn);
                        System.out.println("Entrega registrada com sucesso!");

                        break;
                    case 4:
                        // Lógica para cadastrar uma avaliação
                        System.out.print("ID do livro a ser avaliado: ");
                        int reviewBookId = scanner.nextInt(); // Renomeada para reviewBookId
                        System.out.print("Classificação (de 1 a 5): ");
                        int rating = scanner.nextInt();
                        scanner.nextLine();  // Consuma a quebra de linha
                        System.out.print("Comentário (opcional): ");
                        String comment = scanner.nextLine();

                        Review newReview = new Review();
                        newReview.setBookId(reviewBookId); // Use reviewBookId para o ID do livro na avaliação
                        newReview.setRating(rating);
                        newReview.setComment(comment);

                        reviewDAO.cadastrarAvaliacao(newReview);
                        System.out.println("Avaliação cadastrada com sucesso!");

                        break;

                    case 5:
                        System.out.println("Saindo da aplicação. Até logo!");
                        scanner.close();
                        connection.close();
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}