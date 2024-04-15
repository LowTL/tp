package ui;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class TextUiTest {
    @Test
    public void testReplyToUser() {
        String message = "Message 1 to display";
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        TextUi.replyToUser(message);
        assert outputStreamCaptor.toString().contains((message));
    }

    @Test
    public void testValidInput() {
        provideInput("add Item");
        assertEquals("add Item", TextUi.getUserInput());

        provideInput("   ");
        assertEquals("Invalid Command", TextUi.getUserInput());
    }

    @Test
    public <T> void testShowInventoryList() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("test 1");
        itemList.add("test 2");
        TextUi.showList(itemList);
        String[] output = outputStreamCaptor.toString().split("\\r?\\n");
        String line1 = "List: ";
        String line2 = "1. test 1";
        String line3 = "2. test 2";
        List<String> expectedOutput = Arrays.asList(line1, line2, line3);
        assertLinesMatch(expectedOutput, Arrays.asList(output));
    }

    @Test
    public <T> void testShowEmptyInventoryList() { //only test for empty arrayList as parser will trim away empty inputs
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
        ArrayList<String> itemList = new ArrayList<>(); //empty arrayList
        TextUi.showList(itemList);
        String[] output = outputStreamCaptor.toString().split("\\r?\\n");
        String line = "There is nothing here! Time to spend some money and stock em up!";
        List<String> expectedOutput = List.of(line);
        assertLinesMatch(expectedOutput, Arrays.asList(output));
    }

    void provideInput(String input) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
    }
}
