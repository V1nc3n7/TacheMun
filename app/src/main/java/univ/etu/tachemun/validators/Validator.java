package univ.etu.tachemun.validators;

import java.util.regex.Pattern;

/**
 * Validateur
 */
public class Validator {


    private String regex;
    private int lmin;
    private int lmax;
    private boolean borne;
    private Pattern pattern;

    /**
     * @param trueregex UNE VRAIE REGEX
     */
    public Validator(String trueregex) {
        borne = false;
        this.regex = trueregex;
        pattern = Pattern.compile(regex);
    }

    /**
     * @param regexmin les valeur de la chaine à valider entre []
     * @param min      longueur min de la chaine à valider
     * @param max      longueur max de la chaine à valider
     */
    public Validator(String regexmin, int min, int max) {
        borne = true;

        this.lmin = Math.min(min, max);
        this.lmax = Math.max(min, max);
        ;
        this.regex = "^" + regexmin + "{" + String.valueOf(lmin) + "," + String.valueOf(lmax) + "}$";

        pattern = Pattern.compile(this.regex);
    }

    /**
     * Validate sequence with regular expression
     *
     * @param s String for validation
     * @return true valid s, false invalid s
     */
    public boolean validate(final String s) {

        if (borne) {
            if (s.length() < lmin)
                return false;
            else if (s.length() > lmax)
                return false;

        }
        return pattern.matcher(s).matches();
    }

}








