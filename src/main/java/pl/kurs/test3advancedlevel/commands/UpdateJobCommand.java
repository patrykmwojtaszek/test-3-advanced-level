package pl.kurs.test3advancedlevel.commands;

public class UpdateJobCommand {

    private Long id;
    private Character letter;
    private double quantity;
    private double delay;

    public Long getId() {
        return id;
    }

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
