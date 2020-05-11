import java.util.Scanner;

public class Menu
{
	private Scanner sc = new Scanner(System.in);
	private int nb_players;
	private String[] name_players;
	private int[] money;
	private Standard52Deck deck = new Standard52Deck(0);
	private DeckOfCards[] player_decks;
	private DeckOfCards croupier_deck = new DeckOfCards(0);

	public Menu() {
		show_menu();
		load_file();
		deck.shuffle();
	}

	private void load_file() {
		int i;
		int j = 0;

		System.out.print("Do you want to load a file ? (0 = no / 1 = yes) : ");
		while (true) {
			i = sc.nextInt();
			if (i == 1 || i == 0) {
				if (i == 1)
					; // ecrire le code ici /!\
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
					System.out.println("The game will start soon !");
				}
				break;
			}
			else
				System.out.print("Please type 0 or 1 : ");
		}
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
