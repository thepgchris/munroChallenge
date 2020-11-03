# munroChallenge
An API to search a given CSV file with Munro Data. Created as a Spring Boot application.

This API can be initialised by running the **webservices** .jar through the command prompt with the command
**java -jar target/webservices-0.0.1-SNAPSHOT.jar**

One **POST** API is exposed, /searchMunro

## Request Body
Accepts JSON

#### Available fields
    "hillCategory": "MUN" || "TOP" --Optional
    "sortColumn": "NAME" || "HEIGHT" --Optional
    "sortPriority": "ASC" || "DESC" --Optional
    "maximumResults": Integer --Optional
    "minimumHeight": Double --Optional
    "maximumHeight": Double --Optional

## Response
Produces JSON

#### Sample Response
    [
        {
            "name": "Ben Chonzie",
            "heightMeters": "931.0",
            "hillCategory": "MUN",
            "gridReference": "NN773308"
        },
        {
            "name": "Ben Vorlich",
            "heightMeters": "985.0",
            "hillCategory": "MUN",
            "gridReference": "NN629189"
        }
     ]



