package fedorchuck.com.github.webstore;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author fedorchuck
 */
public final class Config {
    public static final String DRIVERCLASSNAME  =    String.valueOf(value("DRIVERCLASSNAME"));
    public static final String URL              =    String.valueOf(value("URL"));
    public static final String USERNAME         =    String.valueOf(value("USERNAME"));
    public static final String PASSWORD         =    String.valueOf(value("PASSWORD"));

    private static Object value(String key){
        try(InputStream res =
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties")){

            Properties property = new Properties();
            property.load(res);

            switch(key){
                case "DRIVERCLASSNAME":
                    return property.get("jdbc.driverClassName");
                case "URL":
                    return property.get("jdbc.url");
                case "USERNAME":
                    return property.get("jdbc.username");
                case "PASSWORD":
                    return property.get("jdbc.password");
                default: return null;
            }

        } catch (NullPointerException |
                IOException ex) {
            throw new IllegalArgumentException("Problem with resource.properties.", ex);
        }
    }
}
