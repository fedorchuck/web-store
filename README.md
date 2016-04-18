# web-store

#### This app is deployed at address: https://demo-web-store.herokuapp.com

####  If you want run localy, you should do:
1. Before running the gradle script (when you first open project at your PC)
Copy **./gradle.default.properies** to **./gradle.properies** .
2. Do not forget to change copy **./src.main.resources/jdbc.default.properties** to
 **./src.main.resources/jdbc.properties** and chage your DB connection settings
3. For local run:
 * With [Heroku](https://www.heroku.com/):
  ```
  ./gradlew stage
  heroku local web
  ```
  Will be avalible at [http://localhost:5000/](http://localhost:5000/)
  * Without [Heroku](https://www.heroku.com/):
  ```
  ./gradlew runTomcat
  ```
  Will be avalible at [http://localhost:8080/](http://localhost:8080/)
