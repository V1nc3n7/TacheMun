package univ.etu.tachemun.validators;

import java.util.regex.Pattern;

/**
 * Validateur
 */
public class Validator {


    private final String regex;
    private final Pattern pattern;
    private int limiteMin;
    private int limiteMax;
    private boolean borne;

    /**
     * @param trueregex UNE VRAIE REGEX !
     */
    Validator(String trueregex) {
        borne = false;
        this.regex = trueregex;
        pattern = Pattern.compile(regex);
    }

    /**
     * @param regexmin les valeurs acceptables de la chaine à valider
     * @param min      longueur min de la chaine à valider
     * @param max      longueur max de la chaine à valider
     */
    public Validator(String regexmin, int min, int max) {
        borne = true;

        this.limiteMin = Math.min(min, max);
        this.limiteMax = Math.max(min, max);

        this.regex = "^[" + regexmin + "]{" + String.valueOf(limiteMin) + "," + String.valueOf(limiteMax) + "}$";

        pattern = Pattern.compile(this.regex);
    }

    /**
     * Validate sequence with regular expression
     *
     * @param input String for validation
     * @return true valid s, false invalid s
     */
    public boolean validate(final String input) {

        if (borne) {
            if (input.length() < limiteMin)
                return false;
            else if (input.length() > limiteMax)
                return false;

        }
        return pattern.matcher(input).matches();
    }

}








