package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class DAO {

    private final String drive = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://127.0.0.1:3303/agenda?useTimezone=true&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "92470";

    private Connection conectar() {
        Connection conn = null;
        try {
            Class.forName(drive);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro de conexão com o banco de dados ==>> " + e.getMessage());
        }
        return conn;
    }

    /**
     * Método para adicionar Contato
     * @param contato
     */
    public void add(JavaBeans contato) {
        Connection conn = conectar();
        try {
            String sql = "insert into contatos values (default,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, contato.getNome());
            preparedStatement.setString(2, contato.getTelefone());
            preparedStatement.setString(3, contato.getEmail());
            preparedStatement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro de inserção no banco de dados ==>> " + e.getMessage());
        }
    }

    /**
     *Método para listar todos os Contato
     * @return ArrayList<javaBEans>
     */
    public ArrayList<JavaBeans> listar() {
        ArrayList<JavaBeans> l = new ArrayList<>();
        Connection conn = conectar();
        try {
            String sql = "select * from contatos order by nome";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JavaBeans contato = new JavaBeans();
                contato.setId(resultSet.getLong("id"));
                contato.setNome(resultSet.getString("nome"));
                contato.setTelefone(resultSet.getString("telefone"));
                String email = resultSet.getString("email");
                if (email.equals("")) {
                    email = "Não possui email";
                    contato.setEmail(email);
                } else {
                    contato.setEmail(email);
                }
                l.add(contato);
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro de listagem ==>> " + e.getMessage());
        }
        return l;
    }

    /**
     *Método para procurar contato Contato
     * @param contato
     */
    public void procurarContato(JavaBeans contato) {
        Connection conn = conectar();
        String sql = "select * from contatos where id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, contato.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contato.setId(resultSet.getLong("id"));
                contato.setNome(resultSet.getString("nome"));
                contato.setTelefone(resultSet.getString("telefone"));
                contato.setEmail(resultSet.getString("email"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro de procurar contato por id ==>>  " + e.getMessage());
        }
    }

    /**
     *Método para editar Contato
     * @param contato
     */
    public void editar(JavaBeans contato) {
        Connection con = conectar();
        String sql = "update contatos set nome = ?,telefone = ?,email = ? where id = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, contato.getNome());
            preparedStatement.setString(2, contato.getTelefone());
            preparedStatement.setString(3, contato.getEmail());
            preparedStatement.setLong(4, contato.getId());
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro de update de contato ==>> " + e.getMessage());
        }
    }

    /**
     *Método para deletar Contato
     * @param contato
     */
    public void deletar(JavaBeans contato) {
        Connection conn = conectar();
        String sql = "delete from contatos where id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, contato.getId());
            preparedStatement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro de deletar contato no banco ==>> " + e.getMessage());
        }
    }
}
