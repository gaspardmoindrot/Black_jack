import java.util.Scanner;
import java.io.*;

public class Menu
{
	private Scanner sc = new Scanner(System.in);
	private int nb_players;
	private String[] name_players;
	private int[] money;
	private int[] mise;
	public Standard52Deck deck = new Standard52Deck(0);
	public DeckOfCards[] player_decks;
	public DeckOfCards croupier_deck = new DeckOfCards(0);

	public Menu() {
		show_menu();
		load_file();
		deck.shuffle();
	}

	public void close_scan() {
		sc.close();
	}

	public void set_mise(int i_players, int m) {
		this.mise[i_players] = m;
	}

	public int get_mise(int i_players) {
		return (this.mise[i_players]);
	}

	public String get_name(int i_players) {
		return (this.name_players[i_players]);
	}

	public int get_money(int i_players) {
		return (this.money[i_players]);
	}

	public void set_money(int i_players, int money_2) {
		this.money[i_players] = money_2;
	}

	public int get_nb_players() {
		return (this.nb_players);
	}

	private void load_file() {
		int i;
		int j = 0;

		System.out.print("Do you want to load a file ? (0 = no / 1 = yes) : ");
		while (true) {
			i = sc.nextInt();
			if (i == 1 || i == 0) {
				if (i == 1)
					load_with_file();
				else {
					System.out.print("How many players are going to play ? (1-4) : ");
					while (true) {
						this.nb_players = sc.nextInt();
						if (this.nb_players > 0 && this.nb_players < 5) break;
						System.out.print("The number must be between 1 and 4, please retry : ");
					}
					name_players = new String[this.nb_players];
					player_decks = new DeckOfCards[this.nb_players];
					i = 1;
					sc.nextLine();
					while (i <= this.nb_players) {
						System.out.print("Type the name of the " + i + "th player : \n");
						name_players[i - 1] = sc.nextLine();
						i++;
					}
					System.out.print("Players starts with how much money ? (1-400) : ");
					money = new int[this.nb_players];
					mise = new int[this.nb_players];
					while (true) {
						i = sc.nextInt();
						if (i > 0 && i < 401) break;
						System.out.print("The number must be between 1 and 400, please retry : ");
					}
					while (j < this.nb_players) {
						money[j] = i;
						player_decks[j] = new DeckOfCards(0);
						j++;
					}
					System.out.println("The game will start soon !\n");
				}
				break;
			}
			else
				System.out.print("Please type 0 or 1 : ");
		}
	}

	private void load_with_file() {
		sc.nextLine();
		try {
			String str;
			String line;
			String mdp;
			String decrypt;
			int i = 0;

			System.out.println("Which file do you want to load ? : ");
			str = sc.nextLine();
			System.out.println("What is the password ? : ");
			mdp = sc.nextLine();
			File f = new File(str);
			if(f.isFile()) {
				BufferedReader br = new BufferedReader(new FileReader(str));
				line = br.readLine();
				decrypt = check_op_code(line, mdp);
				if (decrypt.equals("isepcvutISEP")) {
					line = br.readLine();
					line = br.readLine();
					this.nb_players = Integer.parseInt(check_op_code(line, mdp));
					line = br.readLine();
					line = br.readLine();
					this.name_players = new String[this.nb_players];
					this.money = new int[this.nb_players];
					while (i < this.nb_players) {
						this.name_players[i] = check_op_code(line, mdp);
						line = br.readLine();
						this.money[i] = Integer.parseInt(check_op_code(line, mdp));
						line = br.readLine();
						i++;
					}
					this.player_decks = new DeckOfCards[this.nb_players];
					this.mise = new int[this.nb_players];
					i = 0;
					while (i < this.nb_players) {
						this.player_decks[i] = new DeckOfCards(0);
						i++;
					}
					System.out.println("The game will start soon !\n");
				}
				else {
					System.out.println("Wrong password or file");
					load_with_file();
				}
			}
			else {
				System.out.println("Wrong file, try again");
				load_with_file();
			}
		}
		catch (Exception e) {
			System.out.println("There is a problem !");
		}
	}

	private String check_op_code(String line, String mdp) {
		char data[] = new char[line.length()];
		int h;
		int i = 0;
		String str;

		while (i < line.length()) {
			h = (int)line.charAt(i) - (int)mdp.charAt(i % mdp.length());
			data[i] = (char)h;
			i++;
		}
		str = new String(data);
		return (str);
	}

	private void show_menu() {
		int i;
		System.out.print("Do you want to know the rules ? (0 = no / 1 = yes) : ");
		while (true) {
			i = sc.nextInt();
			if (i == 1 || i == 0) {
				if (i == 1)
					System.out.println("OBJECT OF THE GAME\nEach participant attempts to beat the dealer by getting a count as close to 21 as possible, without going over 21.\n\nCARD VALUES/SCORING\nIt is up to each individual player if an ace is worth 1 or 11. Face cards are 10 and any other card is its pip value.\n\nBETTING\nBefore the deal begins, each player places a bet, in chips, in front of them in the designated area. Minimum and maximum limits are established on the betting, and the general limits are from $2 to $500.\n\n -> For more details go to : https://bicyclecards.com/how-to-play/blackjack/\n");
				break;
			}
			else
				System.out.print("Please type 0 or 1 : ");
		}
	}
}
