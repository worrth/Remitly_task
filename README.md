# Remitly assessment task

## Description

The app was created with Java + Spring and uses a Mongo database.
The app is fully containerized with Docker.

### How to run
Steps to run the project:
1. Clone the repository
2.Run the command
```shell
    docker-compose up -d
```

Docker compose will download the image from Docker repository, build the image and create the container for the database.
### Tests
The tests will automatically run in the building process with the commands included in the [Dockerfile](Dockerfile).
### Available endpoints
Below you can find the list of available endpoints at <localhost:8080/api>.
#### GET: /v1/swift-codes/{swift-code}
Response structure for a headquarters.
```json
{
    "address": "string",
    "bankName": "string",
    "countryISO2": "string",
    "countryName": "string",
    "isHeadquarter": "bool",
    "swiftCode": "string",
    "branches": [
        {
        "address": "string",
        "bankName": "string",
        "countryISO2": "string",
        "isHeadquarter": "bool",
        "swiftCode": "string"
        },
        {
        "address": "string",
        "bankName": "string",
        "countryISO2": "string",
        "isHeadquarter": "bool",
        "swiftCode": "string"
        }
    ]
}
```
Response structure for a branch.
```json
{
    "address": "string",
    "bankName": "string",
    "countryISO2": "string",
    "countryName": "string",
    "isHeadquarter": "bool",
    "swiftCode": "string"
}

```
#### GET:  /v1/swift-codes/country/{countryISO2code}
Response structure.
```json
{
    "countryISO2": "string",
    "countryName": "string",
    "swiftCodes": [
        {
            "address": "string",
    		 "bankName": "string",
    		 "countryISO2": "string",
    		 "isHeadquarter": "bool",
    		 "swiftCode": "string"
        },
        {
            "address": "string",
    		 "bankName": "string",
    		 "countryISO2": "string",
    		 "isHeadquarter": "bool",
    		 "swiftCode": "string"
        }
    ]
}

```
#### POST:  /v1/swift-codes
Request structure
```json
{
    "address": "string",
    "bankName": "string",
    "countryISO2": "string",
    "countryName": "string",
    "isHeadquarter": "bool",
    "swiftCode": "string"
}
```
```json
{
    "message": "string",
    "statusCode": "int"
}
```
#### DELETE:  /v1/swift-codes/{swift-code}
```json
{
    "message": "string",
    "statusCode": "int"
}
```