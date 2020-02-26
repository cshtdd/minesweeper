package io;

import com.tddapps.io.NumericMenu;
import com.tddapps.io.NumericMenuDefault;
import com.tddapps.io.Reader;
import com.tddapps.io.Writer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NumericMenuDefaultTest {
    private final Reader reader = mock(Reader.class);
    private final Writer writer = mock(Writer.class);
    private final NumericMenu menu = new NumericMenuDefault(reader, writer);

    @Test
    void DisplaysTitle(){
        when(reader.read()).thenReturn("2");
        menu.displayMenu("Select Game", new String[]{
                "Lottery",
                "Powerball",
                "Cash3"
        });

        verify(writer).display("Select Game");
    }

    @Test
    void DisplaysOptions(){
        when(reader.read()).thenReturn("2");
        menu.displayMenu("Select Game", new String[]{
                "Lottery",
                "Powerball",
                "Cash3"
        });

        verify(writer).display("1- Lottery");
        verify(writer).display("2- Powerball");
        verify(writer).display("3- Cash3");
    }

    @Test
    void ReturnsTheSelectedOption(){
        when(reader.read()).thenReturn("2");

        var actual = menu.displayMenu("Select Game", new String[]{
                "Lottery",
                "Powerball",
                "Cash3"
        });

        assertEquals(2, actual);
    }

    @Test
    void DisplaysTheErrorMessageWhenTheInputIsNotValid(){
        when(reader.read()).thenReturn("a", "2");

        menu.displayMenu("Select Game", new String[]{
                "Lottery",
                "Cash3"
        });

        verify(writer).display("Invalid Input!");
        verify(writer).display("Please enter a number between 1 and 2");
    }

    @Test
    void RetriesMenuUntilAValidOptionIsSelected(){
        when(reader.read()).thenReturn("a", "0", "3", "1");

        var actual = menu.displayMenu("Select Game", new String[]{
                "Lottery",
                "Cash3"
        });

        verify(writer, times(3)).display("Invalid Input!");
        verify(writer, times(3)).display("Please enter a number between 1 and 2");
        assertEquals(1, actual);
    }

    @Test
    void DisplaysThePromptForANumber(){
        when(reader.read()).thenReturn("6");
        menu.promptForNumber("Select X", 5, 9);

        verify(writer).display("Select X [5-9]");
    }

    @Test
    void ReadsTheSelectedValue(){
        when(reader.read()).thenReturn("7");

        var actual = menu.promptForNumber("Select X", 5, 9);

        assertEquals(7, actual);
    }

    @Test
    void PromptsMultipleTimesUntilAValidNumberInTheRangeIsSelected(){
        when(reader.read()).thenReturn("z", "3", "10", "7");

        var actual = menu.promptForNumber("Select X", 5, 9);

        assertEquals(7, actual);
        verify(writer, times(3)).display("Invalid Input!");
        verify(writer, times(3)).display("Please enter a number between 5 and 9");
    }
}
