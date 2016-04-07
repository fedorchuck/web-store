# web-store

#### Before running the gradle script (when you first open project at your PC)

Copy **./gradle.default.properies** to **./gradle.properies** .

Also, do not forget to change copy **./src.main.resources/jdbc.default.properties** to
 **./src.main.resources/jdbc.properties** and chage your DB connection settings

For local test with heroku
 gradle wrapper
 ./gradlew stage
 heroku local web