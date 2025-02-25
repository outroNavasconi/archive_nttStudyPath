
public class Main {

	public static void main(String[] args) {
		// dias e total de dias no mês
		int[][] dias = new int[12][1];
		
		dias[0][0] = 31; // janeiro
		dias[1][0] = 28; // fevereiro
		dias[2][0] = 31; // março
		dias[3][0] = 30; // abril
		dias[4][0] = 31; // maio
		dias[5][0] = 30; // junho
		dias[6][0] = 31; // julho
		dias[7][0] = 31; // agosto
		dias[8][0] = 30; // setembro
		dias[9][0] = 31; // outubro
		dias[10][0] = 30; // novembro
		dias[11][0] = 31; // dezembro
		
		for (int i = 0, j = 0; i < dias.length; ++i) {
			System.out.println("... " + dias[i][j]);
		}
	}
}
