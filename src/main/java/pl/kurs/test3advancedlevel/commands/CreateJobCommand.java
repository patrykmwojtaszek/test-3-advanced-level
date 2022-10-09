package pl.kurs.test3advancedlevel.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.kurs.test3advancedlevel.model.Letter;
import pl.kurs.test3advancedlevel.validators.DuplicatedLetter;
import pl.kurs.test3advancedlevel.validators.InvalidLetter;
import pl.kurs.test3advancedlevel.validators.InvalidQuantity;
import pl.kurs.test3advancedlevel.validators.JobTooLong;
import javax.validation.constraints.PositiveOrZero;


public class CreateJobCommand {

    @InvalidLetter(message = "INVALID_LETTER")

//    @DuplicatedLetter              - NIE MOGĘ ZROZUMIEC DLACZEGO TA WALIDACJA NIE DZIALA DLATEGO JEST WYLACZONA
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Character letter;
    @InvalidQuantity
//    @JobTooLong(quantity = quantity, delay = delay)
    private double quantity;
    @PositiveOrZero(message = "INVALID_DELAY")
    private double delay;

    public Character getLetter() {
        return letter;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getDelay() {
        return delay;
    }
}
