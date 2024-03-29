package ui;

import item.Item;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ui.TextUi.getUserInput;
import static ui.TextUi.showInventoryList;

public class TextUiTest {


    @Test
    public void testValidInput() {

        provideInput("add Item");
        assertEquals("add Item", getUserInput());

        provideInput("   ");
        assertEquals("Invalid Command", getUserInput());
    }

    @Test
    public <T> void testShowInventoryList() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("test 1");
        itemList.add("test 2");
        showInventoryList(itemList);
        String[] output = outputStreamCaptor.toString().split("\\r?\\n");
        String line1 = "List: ";
        String line2 = "1. test 1";
        String line3 = "2. test 2";
        List<String> expectedOutput = Arrays.asList(line1, line2, line3);
        assertLinesMatch(expectedOutput, Arrays.asList(output));
    }

    void provideInput(String input) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
    }
}
