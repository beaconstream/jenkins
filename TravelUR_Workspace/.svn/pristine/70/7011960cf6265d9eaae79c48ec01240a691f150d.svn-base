package com.travelur.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by Abhijit.
 */

public class PasswordValidator{

    private Pattern pattern_lc_uc, pattern_uc_sc, pattern_sc_lc, pattern_no_sc, pattern_no_lc, pattern_no_uc;
    private Matcher matcher_lc_uc, matcher_uc_sc, matcher_sc_lc, matcher_no_sc, matcher_no_lc, matcher_no_uc;

   // private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
    private static final String PASSWORD_PATTERN_LC_UC= "(?=.*[a-z])(?=.*[A-Z]).{8,}";
    private static final String PASSWORD_PATTERN_UC_SC = "(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
    private static final String PASSWORD_PATTERN_SC_LC = "(?=.*[@#$%^&+=])(?=\\S+$)(?=.*[a-z]).{8,}";
    private static final String PASSWORD_PATTERN_NO_SC = "(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
    private static final String PASSWORD_PATTERN_NO_LC = "(?=.*[0-9])(?=.*[a-z]).{8,}";
    private static final String PASSWORD_PATTERN_NO_UC = "(?=.*[0-9])(?=.*[A-Z]).{8,}";

    public PasswordValidator(){
        //pattern = Pattern.compile(PASSWORD_PATTERN);
        pattern_lc_uc = Pattern.compile(PASSWORD_PATTERN_LC_UC);
        pattern_uc_sc = Pattern.compile(PASSWORD_PATTERN_UC_SC);
        pattern_sc_lc = Pattern.compile(PASSWORD_PATTERN_SC_LC);
        pattern_no_sc = Pattern.compile(PASSWORD_PATTERN_NO_SC);
        pattern_no_lc = Pattern.compile(PASSWORD_PATTERN_NO_LC);
        pattern_no_uc = Pattern.compile(PASSWORD_PATTERN_NO_UC);

    }

    /**
     * Validate password with regular expression
     * @param password password for validation
     * @return true valid password, false invalid password
     */

    public boolean validate(final String password){

        boolean result = false;
        //matcher = pattern.matcher(password);
        matcher_lc_uc = pattern_lc_uc.matcher(password);
        matcher_uc_sc = pattern_uc_sc.matcher(password);
        matcher_sc_lc = pattern_sc_lc.matcher(password);
        matcher_no_sc = pattern_no_sc.matcher(password);
        matcher_no_lc = pattern_no_lc.matcher(password);
        matcher_no_uc = pattern_no_uc.matcher(password);
        if(matcher_lc_uc.matches() || matcher_uc_sc.matches() || matcher_sc_lc.matches() || matcher_no_sc.matches() || matcher_no_lc.matches() || matcher_no_uc.matches()){
            result = true;
        }
        return result;

    }
}