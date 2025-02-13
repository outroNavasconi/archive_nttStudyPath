
public class Main {

	public static void main(String[] args) {
		int a = 18;
		long b = 100;
		double e = 1.09;

		int a = (int) true;

		// correto, tamanho int é menor que long
		long c = a;
		System.out.println(c);

		// erro, tamanho long é maior que int
		// int d = b;
		// funciona fazendo um casting
		int d = (int) b;
		int f = (int) e;
		System.out.println(d);
		System.out.println(f);
	}
}
