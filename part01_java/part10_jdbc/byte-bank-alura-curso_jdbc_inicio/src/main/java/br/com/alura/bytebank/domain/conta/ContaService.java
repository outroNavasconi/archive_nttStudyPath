package br.com.alura.bytebank.domain.conta;

import br.com.alura.bytebank.ConnectionFactory;
import br.com.alura.bytebank.domain.RegraDeNegocioException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

public class ContaService {

    private Set<Conta> contas = new HashSet<>();
    private ConnectionFactory connectionFactory;

    public ContaService() {
        connectionFactory = new ConnectionFactory();
    }

    public Set<Conta> listarContasAbertas() {
        Connection connection = connectionFactory.getConnection();
        return new ContaDAO(connection).listar();
    }

    public BigDecimal consultarSaldo(Integer numeroDaConta) {
        var conta = buscarContaPorNumero(numeroDaConta);
        return conta.getSaldo();
    }

    public void abrir(DadosAberturaConta dadosDaConta) {
        Connection connection = connectionFactory.getConnection();
        new ContaDAO(connection).salvar(dadosDaConta);
    }

    public void realizarSaque(Integer numeroDaConta, BigDecimal valor) {
        var conta = buscarContaPorNumero(numeroDaConta);
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do saque deve ser superior a zero!");
        }

        if (valor.compareTo(conta.getSaldo()) > 0) {
            throw new RegraDeNegocioException("Saldo insuficiente!");
        }

        conta.sacar(valor);
    }

    public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) {
        var conta = buscarContaPorNumero(numeroDaConta);
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do deposito deve ser superior a zero!");
        }

        Connection connection = connectionFactory.getConnection();
        new ContaDAO(connection).alterar(conta.getNumero(), valor);
    }

    public void encerrar(Integer numeroDaConta) {
        var conta = buscarContaPorNumero(numeroDaConta);
        if (conta.possuiSaldo()) {
            throw new RegraDeNegocioException("Conta não pode ser encerrada pois ainda possui saldo!");
        }

        Connection connection = connectionFactory.getConnection();
        new ContaDAO(connection).deletar(conta.getNumero());
    }

    public Conta buscarContaPorNumero(Integer numero) {
        Connection connection = connectionFactory.getConnection();
        Conta conta = new ContaDAO(connection).recuperarConta(numero);
        if (conta == null)
            throw new RegraDeNegocioException("Não existe conta cadastrada com esse número!");
        return conta;
    }
}
