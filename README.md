# RESTful Wishful Music
A small repository system for storing a wish list of music albums. The repository hosts two collections: albums for the 
wish list and users to be able to login and get personalized wish lists. The MusicBrainz API is used to be able to 
search for album information.

## Requirements
- Java 8+
- MySQL Server 8.0.22
- Apache HTTP Components 4.5.13
- GlassFish Jersey Containers 2.17
- GlassFish Jersey Media 2.17
- Jakarta XML Binding API 2.3.2
- JAXB Runtime 2.3.2
- JUnit 4.9
- Jackson Core 2.12.1
- Grizzly HTTP Server 2.2.5
- JSON.simple 1.1.1

## Installation
1. Clone the repository to your machine.
2. With MySQL Workbench installed and opened, run the data.sql script in src/main/resources to create the required 
   tables. The following steps can be done in your favorite IDE or the command line.
3. Use Maven to install the required dependencies. Gradle is compatible but not supported.
4. In src/main/java/com/spicecrispies/service, run Main.java to start the program.

## Usage
Requests can be sent from a GUI or from software such as Postman or cURL. Note that the following examples are using
localhost:8080 as the URI where your usage might be with a different URI.

### Examples
The following represents a series of requests to log in to the API and add albums to a wish list.

#### Authentication
This example is the POST request needed to register a user (with the 'username' and 'password' included as a URL encoded
www form):
```HTTP POST
http://localhost:8080/RESTfulMusic/user/register
```
It will return a 200 response if the registration was successful.
To log in, another POST request is required, similar to the previous example but with this URL:
```HTTP POST
http://localhost:8080/RESTfulMusic/user/login
```
Logging out simply requires including the username as a form parameter:
```HTTP POST
http://localhost:8080/RESTfulMusic/user/logout
```
Additionally, one extra method is included to validate a login session:
```HTTP POST
http://localhost:8080/RESTfulMusic/user/authentication
```
It will return a 200 response if the session is still valid.

#### AlbumREST
This GET request is used to query the MusicBrainz API for a particular album and artist:
```HTTP GET
http://localhost:8080/RESTfulMusic/album/search/Take%20Time/Russell%20Hitchcock
```
It will return the following:
```JSON
[
    {
        "artist": "Russell Hitchcock",
        "id": "d32f200c-4ea9-4777-92d2-b579dd43cfe4",
        "releaseYear": 2006,
        "title": "Take Time"
    }
]
```
The following requests require authentication, otherwise the client will receive a 401 unauthorized response. The
session key that a client gets when a successful login occurs must be included as a header with the key 'x-api-key'. 

This POST request is used to add an album to a user's wish list. The album is sent as a JSON attachment.
```HTTP POST
http://localhost:8080/RESTfulMusic/album
```
This DELETE request is used to delete the album with ID 7 from a user's wish list:
```HTTP DELETE
http://localhost:8080/RESTfulMusic/album/7
```
To get the information of an album with a certain ID (in this case, ID 7) in a user's wish list, send this request:
```HTTP GET
http://localhost:8080/RESTfulMusic/album/7
```
To get the information of all albums in a user's wish list, simply exclude the ID:
```HTTP GET
http://localhost:8080/RESTfulMusic/album
```