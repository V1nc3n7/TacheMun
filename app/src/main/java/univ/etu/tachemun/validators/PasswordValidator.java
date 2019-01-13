package univ.etu.tachemun.validators;

/**
 * Validateur de mot de passe
 */
public class PasswordValidator extends Validator {

    public PasswordValidator(int min, int max) {
        super("a-zA-Z0-9-_@&é(§è!çà)#$*€%ù", min, max);

    }


}
