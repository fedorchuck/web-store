/*
 *  The GNU General Public Licence
 *
 *  Copyright (c) 2016 by Volodymyr Fedorchuk <vl.fedorchuck@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPL-3.0+ <http://spdx.org/licenses/GPL-3.0+>
 */

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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataConfig {

    private ResourceLoader resourceLoader;
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private final static Logger logger = LoggerFactory.getLogger(DataConfig.class);
    private Resource res;

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
        Map<String, String> env = System.getenv();

        if (env.get("DATABASE_URL")!=null) {
            getConfigEnv(env);
        }
        else {
            getConfig();
            readConfig();
        }
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
        Properties property = new Properties();
        try (InputStream is = res.getInputStream()) {
            property.load(is);
            Field[] fields = this.getClass().getSuperclass().getDeclaredFields();
            String canonicalField;
            String[] parts;

            for (Field field : fields) {

                parts = field.getName().split("DataConfig.");
                canonicalField = parts[0];

                if (property.containsKey("jdbc."+canonicalField)) {
                    field.setAccessible(true);
                    field.set(this, property.get("jdbc."+canonicalField));
                }
            }

        } catch (IOException |
                IllegalAccessException |
                NullPointerException e) {
            logger.error("problem read config. reason: ", e);
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void getConfigEnv(Map<String, String> env){
        URI dbUri = null;
        try {
            dbUri = new URI(System.getenv("DATABASE_URL"));
        } catch (URISyntaxException e) {
            logger.error("problem read config. reason: ", e);
        }

        driverClassName = "org.postgresql.Driver";
        username = dbUri.getUserInfo().split(":")[0];
        password = dbUri.getUserInfo().split(":")[1];
        url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
    }

    private void getConfig() {
        res = resourceLoader.getResource("classpath:../jdbc.properties");
        if(!res.exists()){
            res= resourceLoader.getResource("classpath:jdbc.properties");
            if(!res.exists()){
                String massageError = "Can't read configuration for jdbc.properties";
                logger.error("problem read config. reason: ", massageError);
                throw new IllegalStateException(massageError);
            }
        }
    }
}
