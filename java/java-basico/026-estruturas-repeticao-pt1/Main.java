
public class Main {

	public static void main(String[] args) {

		int count = 0;
		while (count++ < 10) {
			System.out.println("While Contador: " + count);
		}

		count = 0;
		do {
			System.out.println("Do-While Contador: " + ++count);
		} while (count < 10);

		for (count = 0; count < 10; count++) {
			System.out.println("For Contador: " + (count + 1));
		}
	}
}
