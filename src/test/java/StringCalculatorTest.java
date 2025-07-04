import com.example.Calculator;
import com.example.StringCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCalculatorTest {

        @Test
        void nullStringShouldReturnZero() {
            StringCalculator stringCalculator = new StringCalculator();
            assertEquals(0,stringCalculator.add(""));
        }


}
