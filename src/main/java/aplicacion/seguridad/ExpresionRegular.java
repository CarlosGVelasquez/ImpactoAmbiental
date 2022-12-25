package aplicacion.seguridad;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ExpresionRegular implements Verificable {

    private final String PASSWORD_PATTERN ;
    private final Pattern pattern;

    ExpresionRegular (String expReg){
     PASSWORD_PATTERN = expReg;
     pattern = Pattern.compile(PASSWORD_PATTERN);
    }
    public boolean verificar(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
