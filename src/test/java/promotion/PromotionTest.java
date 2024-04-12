package promotion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromotionTest {

        @Test
        public void testToString() {
            Promotion promotion = new Promotion("Item", 0.1f, 1, Month.JAN,
                    2022, 10, Month.FEB, 2023, 800, 1600);

            String expected = "Item have a 10.00% discount\n" +
                    "Period: 1 JAN 2022 to 10 FEB 2023\n" +
                    "Time: 0800 to 1600";
            assertEquals(expected, promotion.toString());
        }
    }

