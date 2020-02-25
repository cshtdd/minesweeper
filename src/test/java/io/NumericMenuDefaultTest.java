package io;

import com.tddapps.io.NumericMenu;
import com.tddapps.io.NumericMenuDefault;
import com.tddapps.io.Reader;
import com.tddapps.io.Writer;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NumericMenuDefaultTest {
    private final Reader reader = mock(Reader.class);
    private final Writer writer = mock(Writer.class);
    private final NumericMenu menu = new NumericMenuDefault(reader, writer);

    @Test
    void DisplaysTitle(){
        menu.displayMenu("Select Game", new String[]{
                "Lottery",
                "Powerball",
                "Cash3"
        });

        verify(writer).display("Select Game");
    }
}
