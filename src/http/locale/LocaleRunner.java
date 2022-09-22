package http.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleRunner {

    public static void main(String[] args) {
        Locale locale = new Locale("ru", "RU");
        System.out.println(Locale.US);
        System.out.println(Locale.getDefault());

        // получу дефолтную локаль
        var translations = ResourceBundle.getBundle("translations");
        System.out.println(translations.getString("page.login.password"));

        // передам локаль нужную
        var translations2 = ResourceBundle.getBundle("translations", locale);
        System.out.println(translations2.getString("page.login.password"));
    }
}
