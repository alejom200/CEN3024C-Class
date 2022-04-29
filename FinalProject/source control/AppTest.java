import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

    private WordOccurences wordOccurences;

    @Before
    public void setUp() throws Exception {
        wordOccurences = new WordOccurences();
    }

    @Test
    public void testWordCount() {
        String testString = "Hi are you okay I hope you are We are planning a trip this weekend, is that okay with you";
        Map<String, Integer> m = wordOccurences.getOccurences(testString);
        assertEquals(3, m.get("are"), 1);
    }

    @Test
    public void testSpacesHaveNoEffect() {
        String testString = "yes yes      we can yes          we   can";
        Map<String, Integer> m = wordOccurences.getOccurences(testString);
        assertEquals(3, m.get("yes"), 1);
    }

    @Test
    public void testWithCommaOrFullStop() {
        String testString1 = "yes, yes      we can yes          we   can";
        Map<String, Integer> m1 = wordOccurences.getOccurences(testString1);

        assertNotEquals("Should not be equal", 3, m1.get("yes"), 0);
    }
}