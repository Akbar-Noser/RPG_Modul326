package ch.module.cardgame.test.card;

import ch.module.cardgame.card.Card;
import ch.module.cardgame.card.CardDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardDeckTest {
    private CardDeck deck;

    @BeforeEach
    void setUp() {
        deck = new CardDeck();
    }

    @Test
    void checkThatTheDeckContainsAllCards() {
        assertEquals(deck.getCards().size(), deck.getMAX_CARDS());
    }

    @Test
    void checkThatTheDeckCardsAreCorrectlyFormed() {
        Card card = deck.getCards().pop();
        assertTrue(card.getAttackPoints() > -1 && card.getHealthPoints() > 0);
    }
}