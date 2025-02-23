
public class Main {

	public static void main(String[] args) {
		double salario = 6000;

		String resultado;
		String mensagemDoar = "Eu vou doar 500";
		String mensagemNaoDoar = "Eu não vou doar 500";
		
		if (salario > 5000) {
			resultado = mensagemDoar;
		} else {
			resultado = mensagemNaoDoar;
		}

		// operador ternário
		resultado = salario > 5000 ? mensagemDoar : mensagemNaoDoar;

		System.out.println(resultado);
	}
}
