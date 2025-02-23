
public class Main {

	public static void main(String[] args) {
		// AND (&&)
		boolean temCopo = true;
		boolean temAguaNaCasa = false;
		boolean temAguaNoVizinho = true;

		System.out.println("Consegue beber água em casa? " + (temCopo && temAguaNaCasa));
		System.out.println("Consegue beber água no vizinho? " + (temCopo && temAguaNoVizinho));
	}
}
