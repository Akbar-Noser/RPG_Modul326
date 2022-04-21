package ch.module.cardgame.player.ai;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CardCombinatorTest {
    private List<Card> cards;

    @BeforeEach
    void setUp() {
        CardBuilder builder = new CardBuilder();
        cards = List.of(builder.setSummonEnergyPoints(3).build(), builder.setSummonEnergyPoints(2).build(),
                builder.setSummonEnergyPoints(1).build());
    }

    @Test
    void getUniqueCombinationsOfCards() {
        Card card1 = cards.get(0);
        Card card2 = cards.get(1);
        Card card3 = cards.get(2);
        List<List<Card>> uniqueCombinations = List.of(
                List.of(card1, card2, card3),
                List.of(card1),
                List.of(card2),
                List.of(card3),
                List.of(card1, card2),
                List.of(card1, card3),
                List.of(card2, card3)
        );
        assertEquals(uniqueCombinations, CardCombinator.getUniqueCombinationsOfCards(cards));
    }

    @Test
    void getAllPermutations() {
        List<int[]> correctPermutations = List.of(
                new int[]{1, 2, 3},
                new int[]{2, 1, 3},
                new int[]{3, 1, 2},
                new int[]{1, 3, 2},
                new int[]{2, 3, 1},
                new int[]{3, 2, 1}
        );
        List<int[]> result = new ArrayList<>(correctPermutations.size());
        CardCombinator.getAllPermutations(result, new int[]{1, 2, 3}, 3, 3);
        for (int i = 0; i < result.size(); i++) {
            assertArrayEquals(correctPermutations.get(i), result.get(i));
        }
    }
}