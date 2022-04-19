package ch.module.cardgame.player.ai;

import ch.module.cardgame.card.Card;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CardCombinator {

    private CardCombinator() {
    }

    /**
     * Returns a list of unique possible combinations.
     *
     * @param cards The cards which can be used in the combinations.
     * @return the possible combinations.
     */
    public static List<List<Card>> getUniqueCombinationsOfCards(List<Card> cards) {
        List<List<Card>> combinations = new LinkedList<>();

        // Add combination where all cards are played
        combinations.add(List.copyOf(cards));

        for (int i = 1; i < cards.size(); i++) {
            addCombinationSubsetToList(combinations, cards, new Card[i], 0, cards.size() - 1, 0, i);
        }
        return combinations;
    }

    /**
     * Implementation of the k combinations algorithm, check it out here:
     * https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
     *
     * @param combinations th list to which the card combinations will get added
     * @param cards the cards which can possibly be played
     * @param data temporary data set, represents one possible combination
     * @param start the index at which the algorithm starts
     * @param end the index to which the algorithm will get the combinations
     * @param index the starting index, should be 0 in the most cases
     * @param r the size of the subset.
     */
    public static void addCombinationSubsetToList(List<List<Card>> combinations, List<Card> cards, Card[] data, int start,
                                                  int end, int index, int r) {
        // Current combination is ready add combination to total combination
        if (index == r) {
            combinations.add(Arrays.asList(data.clone()));
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = cards.get(i);
            addCombinationSubsetToList(combinations, cards, data, i + 1, end, index + 1, r);
        }
    }
}
