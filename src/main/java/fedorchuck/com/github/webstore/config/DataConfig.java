package fedorchuck.com.github.webstore.config;

import jdk.nashorn.internal.runtime.OptimisticReturnFilters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by v on 28/01/16.
 */
@Configuration
public class DataConfig {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private final static Logger logger = LoggerFactory.getLogger(DataConfig.class);

    public DataConfig() {
        readConfig();
    }

    @Bean
    public DataSource dataSource(){//String driverClassName, String url, String username, String password) {

        /*DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;*/

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/webstore");;
        dataSource.setUsername("postgres");
        dataSource.setPassword("lucky strike");
        logger.info("INFO: dataSource: " + dataSource.toString());
        logger.error("ERROR: dataSource: " + dataSource.toString());
        logger.warn("WARN: dataSource: " + dataSource.toString());




        return dataSource;
    }

    @Bean
    public JdbcOperations jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    /**
     * temporary code below
     * annotation and xml don't want work, like i want. but wrote configuration data in code i don't want. so.. :(
     */
    @SuppressWarnings("unchecked")
    private void readConfig() {
        String configFile = "/home/v/Documents/projects/web-store/src/main/resources/jdbc.properties";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(configFile)));
            String line;
            String[] parts;
            Map config = new HashMap();

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) break;

                parts = line.split("=");
                parts[0] = parts[0].replace("jdbc.","");
                parts[1] = parts[1].replace("\\","");

                config.put(parts[0], parts[1]);
            }

            setValues(config);

        } catch (IOException |
                IndexOutOfBoundsException |
                IllegalAccessException |
                NullPointerException e) {
            //TODO: log: "ERROR: unable to read file " + configFile.
        } finally {
            try {
                br.close();
            } catch (IOException ignore) {}
        }

    }

    /**
     * temporary code below
     */
    private void setValues(Map data) throws IllegalAccessException {
        PrintWriter writer = null;  // = new PrintWriter("WEB-STORE", "UTF-8");
        // way glassfish4/glassfish/domains/domain1/config

        try {
            writer = new PrintWriter("WEB-STORE", "UTF-8");

            writer.println(" this.getClass().getDeclaredFields().length: " + this.getClass().getDeclaredFields().length);
            /*for (int i = 0; i < this.getClass().getDeclaredFields().length; i++) {
                writer.println(this.getClass().getDeclaredFields()[i].getName());
            }*/

            Field[] fields = this.getClass().getSuperclass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                fields[i].getType();
                fields[i].getName();



                if (data.containsKey(fields[i].getName())) {
                    fields[i].setAccessible(true);
                    fields[i].set(null, data.get(fields[i].getName()));
                }
            }


/*            writer.println(" this.getClass().getDeclaredFields().length: " + this.getClass().getDeclaredFields().toString());

            for (int i = 0; i < this.getClass().getDeclaredFields().length; i++) {
                writer.print("filed " + this.getClass().getDeclaredFields()[i].getType().getName().toString());

                this.getClass().getDeclaredFields()[i].setAccessible(true);

                //->this.getClass().getDeclaredFields()[i].set(null, this.getClass().getDeclaredFields()[i]);
                writer.println("value " + this.getClass().getDeclaredFields()[i] + "\n");
            }*/
        } catch (IOException |
                IndexOutOfBoundsException |
                IllegalAccessException |
                NullPointerException e) {
                writer.println("fatal error" + e + " reason " + e.getMessage());
                //TODO: log: "ERROR: unable to read file " + configFile.
        } finally {
            writer.println("try take value of fild 'url': " + getUrl());
            writer.println("end.");
            writer.close();
        }
    }
}
