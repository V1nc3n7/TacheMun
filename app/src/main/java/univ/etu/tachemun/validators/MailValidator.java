package univ.etu.tachemun.validators;

/**
 * Validateur Mail
 */

public class MailValidator extends Validator {
    public MailValidator() {

        super("[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})");
    }

    public MailValidator(int min, int max) {

        super("[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})", min, max);
    }

}

