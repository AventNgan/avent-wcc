# Distance Calculator

A simple project to calculate the distance between two postcodes.
Sample contains a simple database that contains a list of UK postcodes

---

## Setting up the database

To ease setting up a database, this project utilizes docker to create a container with a mysql database.

If you do not have docker installed, you can download it from [here](https://www.docker.com/products/docker-desktop)

Make sure that you have logged in to docker so that you are able to pull the mysql image.

Once you have docker ready and running, refer to the section below to start the database.

### Lightweight Database
As the full database is quite large, the original docker-compose file has been written to only start with only the first 5000 records of the full data.

Run the following command to start the database in lightweight mode:
```bash
docker compose up
```

The container should only take a few seconds to start

### Full Database
Run the following command to start the database in full mode:


```bash
docker compose -f docker-compose.full.yml up
```

The container may take 10-15 minutes to start as it needs to run the full database script.

### Resetting the database
If you have already ran any of the start command , run `docker compose down` to destroy the old container first before running the command again as the mysql container will only run the scripts during first initialization.

### Connecting to the database
The database can be accessed using the following:
- Host: `localhost`
- Port: `3306`
- Username: `root`
- Password: `P@ssw0rd`

### Stopping the database
When you are done with the database, you can stop the container by running the following command:
```bash
docker compose down
```

> If you do not destroy the container, the container will remain and may take up resources on your machine.


---

## Running the application

To run the application, you can use the following command:
```bash
./gradlew clean bootRun
```

By default, the application will start on port 8080. You can access the application by visiting [http://localhost:8080](http://localhost:8080)

If you would like to change the port, you can do so by setting the `server.port` property in the `application.properties` file.

---

## Using the application
The application is a RESTful-API-only application and can be accessed via API calls. Using postman is recommended.

A sample postman collection has been provided in the `postman` directory.

The APIs in the application are protected by a simple username-password authentication. 

Start by using the `POST /auth/signup` API to create a new user. 

### POST /auth/signup
The API is used to create a new user.
This API accepts a JSON request body with the following fields:
- `username`: (String) The username of the user
- `password`: (String) The password of the user

### POST /auth/login
The API is used to log in a user.
This API accepts a JSON request body with the following fields:
- `username`: (String) The username of the user
- `password`: (String) The password of the user

The API to returns a JWT token. Set the `Authorization` header in your postman request to `Bearer <JWT>` to access subsequent APIs.

### GET /postcode/get

This API is used to get the details of a postcode. It accepts a single request parameter `postcode` which is the postcode you want to get the details of.

### POST /postcode/update

This API is used to update the details of a postcode. It accepts a JSON request body with the following fields:
- `postcode`: (String) The postcode you want to update
- `latitude`: (Double) The latitude of the postcode
- `longitude`: (Double) The longitude of the postcode

### GET /distance/postcode

This API is used to get the distance between two postcodes. It accepts two request parameters `postcode1` and `postcode2` which are the postcodes you want to get the distance between.
There is also an optional parameter `unit` which can be set to either `m`, `km`, `foot` or `miles`. If not set, the default unit is `km`

