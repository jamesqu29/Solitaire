
class SuitPile extends CardPile {

	SuitPile (int x, int y) { //provide code here
		super (x, y);
	}

	public boolean canTake (Card aCard) {//provide code here
		if (isEmpty()){
			return aCard.rank() == 0; //only ace can be place on a empty suitPile
		}
		Card topCard = top();
		return (aCard.suit() == topCard.suit()) && (aCard.rank() == 1 + topCard.rank());
	}
}