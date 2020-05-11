public class Standard52Deck extends DeckOfCards
{
	public Standard52Deck(int nb_cards) {
		super(nb_cards);
		NB_CARDS = 52;
		int i = 0;
		int j = 1;
		int k = 1;
		Card[] Card = new Card[NB_CARDS];

		while (i < NB_CARDS) {
			while (j < 5) {
				k = 1;
				while (k < 14) {
					Card[i] = new Card(k, j);
					i++;
					k++;
				}
				j++;
			}
		}
		this.mCard = Card;
	}
}
