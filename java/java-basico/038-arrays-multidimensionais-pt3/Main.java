
public class Main {

	public static void main(String[] args) {
		int[] arrB = {1, 2, 3};
		int[][] arrC = {{0, 0}, {1, 2, 3}, {1, 2, 3, 4}};
	
		int[][] arr = new int[2][];

		arr[0] = new int[2];
		arr[1] = new int[]{1, 2};

		for (int[] i : arr) {
			for (int e : i) {
				System.out.println(">>> " + e);
			}
		}
	}
}
