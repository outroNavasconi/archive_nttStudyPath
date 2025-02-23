
public class Main {

	public static void main(String[] args) {
		// OR (||)
		boolean temSucoLaranja = true;
		boolean podeSerComLeite = false;
		boolean temFantaUva = true;

		boolean vaiBeber = (temSucoLaranja && podeSerComLeite) || temFantaUva;
		System.out.println("Se tiver Fanta Uva ou Suco de Laranja com Leite, eu vou beber... " + vaiBeber);
	}
}
