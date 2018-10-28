package univ.etu.tachemun.validators;

public class PasswordValidator extends Validator {

    public PasswordValidator(int min, int max) {
        super("a-zA-Z0-9-_@&é(§è!çà)#$*€%ù", min, max);

    }


}
