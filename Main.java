import java.util.Scanner;
import java.io.*;

public class Main
{
	public static void main(String[] args) {
		try {
			Menu menu = new Menu();
			Game game = new Game(menu);
			Scanner sc = new Scanner(System.in);
			int i = 1;
			String mdp;
			String str;

			while (i == 1) {
				game.oneTurnForAllPlayers();
				System.out.print("\nDo you want to continue playing ? (yes = 1) : ");
				i = sc.nextInt();
				game.cleanOneTurn();
			}
			System.out.print("Do you want to save the game ? Type 1 for yes : ");
			if (sc.nextInt() == 1) {
				System.out.println("Where do you want to save your file ? : ");
				sc.nextLine();
				str = sc.nextLine();
				System.out.println("What is your password ? : ");
				mdp = sc.nextLine();
				File file = new File(str);
				if (!file.exists())
					file.createNewFile();
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(cryptage("isepcvutISEP", mdp) + "\n\n");
				bw.write(cryptage(menu.get_nb_players() + "", mdp) + "\n\n");
				i = 0;
				while (i < menu.get_nb_players()) {
					bw.write(cryptage(menu.get_name(i) + "", mdp) + "\n");
					bw.write(cryptage(menu.get_money(i) + "", mdp) + "\n");
					i++;
				}
				bw.close();
			}
			game.close_scan();
			menu.close_scan();
			sc.close();
		}
		catch (Exception e) {
			System.out.println("There is a problem");
		}
	}

	private static String cryptage(String str, String mdp) {
		char data[] = new char[str.length()];
		int h;
		int i = 0;

		while (i < str.length()) {
			h = (int)str.charAt(i) + (int)mdp.charAt(i % mdp.length());
			data[i] = (char)h;
			i++;
		}
		return (new String(data));
	}
}
