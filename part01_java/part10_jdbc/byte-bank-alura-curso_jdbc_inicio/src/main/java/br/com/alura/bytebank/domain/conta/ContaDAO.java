package br.com.alura.bytebank.domain.conta;

import br.com.alura.bytebank.domain.cliente.Cliente;
import br.com.alura.bytebank.domain.cliente.DadosCadastroCliente;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ContaDAO {
    private Connection conn;

    public ContaDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvar(DadosAberturaConta dadosDaConta) {
        var cliente = new Cliente(dadosDaConta.dadosCliente());
        var conta = new Conta(dadosDaConta.numero(), cliente, BigDecimal.ZERO);

        String insert = "INSERT INTO conta(numero, saldo, cliente_nome, cliente_cpf, cliente_email) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = conn.prepareStatement(insert);

            statement.setInt(1, conta.getNumero());
            statement.setBigDecimal(2, BigDecimal.ZERO);
            statement.setString(3, dadosDaConta.dadosCliente().nome());
            statement.setString(4, dadosDaConta.dadosCliente().cpf());
            statement.setString(5, dadosDaConta.dadosCliente().email());

            statement.execute();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Conta> listar() {
        Set<Conta> contas = new HashSet<>();
        String select = "SELECT * FROM conta";

        try {
            PreparedStatement statement = conn.prepareStatement(select);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer numero = resultSet.getInt(1);
                BigDecimal saldo = resultSet.getBigDecimal(2);
                String nome = resultSet.getString(3);
                String cpf = resultSet.getString(4);
                String email = resultSet.getString(5);

                DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nome, cpf, email);
                Cliente cliente = new Cliente(dadosCadastroCliente);
                contas.add(new Conta(numero, cliente, saldo));
            }

            statement.close();
            conn.close();

            return contas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Conta recuperarConta(Integer numero) {
        Conta conta = null;
        String select = "SELECT * FROM conta WHERE numero = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(select);
            statement.setInt(1, numero);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                BigDecimal saldo = resultSet.getBigDecimal(2);
                String nome = resultSet.getString(3);
                String cpf = resultSet.getString(4);
                String email = resultSet.getString(5);

                DadosCadastroCliente dadosCadastroCliente = new DadosCadastroCliente(nome, cpf, email);
                Cliente cliente = new Cliente(dadosCadastroCliente);
                conta = new Conta(numero, cliente, saldo);
            }

            statement.close();
            conn.close();

            return conta;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Integer numero, BigDecimal valor) {
        String update = "UPDATE conta SET SALDO = ? WHERE numero = ?";

        try {
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(update);

            statement.setBigDecimal(1, valor);
            statement.setInt(2, numero);

            statement.execute();
            conn.commit();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void deletar(Integer numero) {
        String delete = "DELETE FROM conta WHRERE conta = ?";

        try {
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(delete);

            statement.setInt(1, numero);

            statement.execute();
            conn.commit();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

    }

}
