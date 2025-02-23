
public class Main {

	public static void main(String[] args) {
		boolean condition = true;

		if (condition) {
			System.out.println("O valor é verdadeiro");
		}

		int idade = 20;
		boolean altorizado = idade >= 18;

		if (altorizado) {
			System.out.println("Pode comprar bebida alcóolica");
		}

		if (!altorizado) {
			System.out.println("Não pode comprar bebida alcóolica");
		}
	}
}
