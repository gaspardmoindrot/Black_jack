import java.util.Scanner;

public class Game
{
	private Scanner sc = new Scanner(System.in);
	private Menu menu;

	public Game(Menu menu) {
		this.menu = menu;
	}

	public void close_scan() {
		sc.close();
	}

	public void cleanOneTurn() {
		int i = 0;

		while (i < menu.get_nb_players()) {
			while (menu.player_decks[i].NB_CARDS > 0) {
				menu.deck.AddBottom(menu.player_decks[i].getter_card(0));
				menu.player_decks[i].removeBottom();
			}
			i++;
		}
		while (menu.croupier_deck.NB_CARDS > 0) {
			menu.deck.AddBottom(menu.croupier_deck.getter_card(0));
			menu.croupier_deck.removeBottom();
		}
	}

	public void oneTurnForAllPlayers() {
		int i = 0;
		System.out.println("You have this money : ");
		while (i < menu.get_nb_players()) {
			System.out.println(menu.get_name(i) + " -> " + menu.get_money(i));
			i++;
		}
		System.out.println();
		i = 0;
		while (i < menu.get_nb_players()) {
			oneGame(i);
			i++;
		}
		oneGameCroupier();
		i = 0;
		while (i < menu.get_nb_players()) {
			System.out.println(menu.player_decks[i].calcul_total_cards() + " against " + menu.croupier_deck.calcul_total_cards());
			if ((menu.player_decks[i].calcul_total_cards() > menu.croupier_deck.calcul_total_cards()
						|| menu.croupier_deck.calcul_total_cards() > 21)
					&& menu.player_decks[i].calcul_total_cards() < 22) {
				System.out.println(menu.get_name(i) + " has won !");
				menu.set_money(i, menu.get_mise(i) + menu.get_money(i));
					}
			else if (menu.player_decks[i].calcul_total_cards() == menu.croupier_deck.calcul_total_cards()
					&& menu.player_decks[i].calcul_total_cards() < 22) {
				System.out.println(menu.get_name(i) + " is tied with the croupier");
			}
			else {
				System.out.println(menu.get_name(i) + " has lost");
				menu.set_money(i, menu.get_money(i) - menu.get_mise(i));
			}
			i++;
		}
	}

	public void oneGame(int player) {
		int i;

		System.out.println("Turn of " + menu.get_name(player) + " :");
		System.out.print("What bet did you want to put ? : ");
		i = sc.nextInt();
		while (i < 0 || i > menu.get_money(player)) {
			System.out.print("You don't have enough money or you have written a bad number : ");
			i = sc.nextInt();
		}
		System.out.println();
		menu.set_mise(player, i);
		menu.player_decks[player].addTop(menu.deck.getter_top_card());
		menu.deck.removeTop();
		menu.player_decks[player].addTop(menu.deck.getter_top_card());
		menu.deck.removeTop();
		i = 1;
		while (i == 1 && menu.player_decks[player].calcul_total_cards() <= 21) {
			System.out.println("You have :");
			menu.player_decks[player].print();
			System.out.print("Do you want another card ? (yes = 1 / no = everything else) : ");
			i = sc.nextInt();
			if (i == 1) {
				menu.player_decks[player].addTop(menu.deck.getter_top_card());
				menu.deck.removeTop();
			}
			System.out.println();
		}

		System.out.println("You have all this card : ");
		menu.player_decks[player].print();
		System.out.println();
	}

	public void oneGameCroupier() {
		int i;

		menu.croupier_deck.addTop(menu.deck.getter_top_card());
		menu.deck.removeTop();
		menu.croupier_deck.addTop(menu.deck.getter_top_card());
		menu.deck.removeTop();
		while (menu.croupier_deck.calcul_total_cards() < 17) {
			menu.croupier_deck.addTop(menu.deck.getter_top_card());
			menu.deck.removeTop();
		}

		System.out.println("Croupier have all this card : ");
		menu.croupier_deck.print();
		System.out.println();
	}
}
