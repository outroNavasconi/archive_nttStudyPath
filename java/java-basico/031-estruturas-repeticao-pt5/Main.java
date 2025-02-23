
public class Main {

	public static void main(String[] args) {
		int total = 0;
		for (int i = 100; i <= 150; i++) {
			if (i % 2 == 0) {
				continue;
			}
			total += i;
		}

		System.out.println(total);
	}
}
