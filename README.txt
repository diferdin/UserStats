
INSTRUCTIONS

The application is called UserStats, and can be run both through an IDE such as Intellij IDEA, after
importing and configuring the project as Maven project, or through a standalone JAR.
In this latter case, the application will need to be packaged as a JAR via Maven, with the command:

mvn clean package (please note: 'clean' is optional)

Once the build succeeds, the application can be run with:

java -jar target/UserStats-1.0-SNAPSHOT.jar server config.yml

Once the application is running, the endpoints requested in the description can be tested either via a browser
or via curl at the following URLs:

http://localhost:8080/user/[1-6]/bookings for the number of bookings for a user
http://localhost:8080/user/[1-6]/bookings/value for the total value for a user
http://localhost:8080/user/[1-6]/bookings/average-value for the average length of stay for a user


A few notes below:
- I added a basic health check, exclusively to remove the annoying Dropwizard warning that no healthchecks were in place when Maven-building.
- The DAO object is tailored for user_features.txt. That is a specific choice, purposely to the extent of saving time.
  In a real scenario, UserDao would be an interface, implemented by a concretion, for instance FileBasedUserDao, which would load data from file.
  Injection might be different in that case.
