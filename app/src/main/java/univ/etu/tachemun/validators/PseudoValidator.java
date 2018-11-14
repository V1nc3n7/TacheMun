package univ.etu.tachemun.validators;

public class PseudoValidator extends Validator {
    public PseudoValidator() {
        super("0-9a-zA-Z@-_", 3, 120);
    }
}
