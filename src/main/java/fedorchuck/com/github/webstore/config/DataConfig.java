package fedorchuck.com.github.webstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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

    private ResourceLoader resourceLoader;
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private final static Logger logger = LoggerFactory.getLogger(DataConfig.class);

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        readConfig();
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
     * annotation and xml don't want work, like i want. but wrote configuration data in code i don't want. so.. :(
     */
    @SuppressWarnings("unchecked")
    private void readConfig() {
        Resource res= resourceLoader.getResource("classpath:../jdbc.properties");
        if(!res.exists()){
            res= resourceLoader.getResource("classpath:jdbc.properties");
            if(!res.exists()){
                String massageError = "Can't read configuration for jdbc.properties";
                logger.error("problem read config. reason: ", massageError);
                throw new IllegalStateException(massageError);
            }
        }
        BufferedReader br = null;
        try (InputStream is = res.getInputStream()){
            br = new BufferedReader(new InputStreamReader(is));
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

            setFieldsValues(config);

        } catch (IOException |
                IllegalAccessException |
                NullPointerException e) {
            logger.error("problem read config. reason: ", e);
        } finally {
            try {
                br.close();
            } catch (IOException ignore) { }
        }
    }

    private void setFieldsValues(Map data) throws IllegalAccessException {
        Field[] fields = this.getClass().getSuperclass().getDeclaredFields();

        for (Field field : fields) {
            if (data.containsKey(field.getName())) {
                field.setAccessible(true);
                field.set(this, data.get(field.getName()));
            }
        }
    }
}
