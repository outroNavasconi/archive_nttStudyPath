
public class Main {

	public static void main(String[] args) {
		String[] nomes = new String[3];

		nomes[0] = "fulano";
		nomes[1] = "cicrano";
		nomes[2] = "beltrano";

		for (int i = 0; i < nomes.length; i++) {
			System.out.println(nomes[i]);
		}

		for (String nome : nomes) {
			System.out.println(nome);
		}
	}
}
