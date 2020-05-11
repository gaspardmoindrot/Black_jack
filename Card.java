public class Card
{
	private int	mRank;
	private String	mSuit;

	public Card(int rank, int suit) {
		if ((suit > 0 && suit < 5) && (rank > 0 && rank < 14)) {
			this.mRank = rank;
			if (suit == 1)
				this.mSuit = "♣";
			else if (suit == 2)
				this.mSuit = "♥";
			else if (suit == 3)
				this.mSuit = "♠";
			else if (suit == 4)
				this.mSuit = "♦";
			else
				this.mSuit = "0";
		}
		else {
			this.mRank = 0;
			this.mSuit = "0";
		}
	}

	public int get_rank() {
		return (this.mRank);
	}

	public String get_suit() {
		return (this.mSuit);
	}

	public void setter(int rank, int suit) {
		if ((suit > 0 && suit < 5) && (rank > 0 && rank < 14)) {
			this.mRank = rank;
			if (suit == 1)
				this.mSuit = "♣";
			else if (suit == 2)
				this.mSuit = "♥";
			else if (suit == 3)
				this.mSuit = "♠";
			else if (suit == 4)
				this.mSuit = "♦";
			else
				this.mSuit = "0";
		}
		else {
			this.mRank = 0;
			this.mSuit = "0";
		}
	}
}
