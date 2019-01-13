package univ.etu.tachemun.validators;

/**
 * Validateur de pseudo
 */
public class PseudoValidator extends Validator {
    public PseudoValidator() {
        super("0-9a-zA-Z@-_", 3, 120);
    }
}
