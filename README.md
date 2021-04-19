# RESTfulMusic
A small repository system using RESTful API. The repository hosts two collections: Albums and Artists.

# cURL Examples
```Command Line
curl -vX POST 'http://localhost:8080/RESTfulMusic/album/1/Now%20and%20forever/Air%20supply%20album/1982/Russell%20Hitchcock'
curl -vX POST 'http://localhost:8080/RESTfulMusic/album/2/Cant%20live/Air%20supply%20album/1970/Russell%20Hitchcock'
curl -vX POST 'http://localhost:8080/RESTfulMusic/album/3/All%20out%20of%20love/Air%20supply%20album/1971/Russell%20Hitchcock'
curl -v http://localhost:8080/RESTfulMusic/album
curl -vX PUT 'http://localhost:8080/RESTfulMusic/album/1/abc/abc/123/abc'
curl -vX DELETE 'http://localhost:8080/RESTfulMusic/album/1'
curl -vX DELETE 'http://localhost:8080/RESTfulMusic/album/7'
curl -v http://localhost:8081/ArtistServlet
curl -vX POST "http://localhost:8081/ArtistServlet?nickname=Russell%20Hitchcock&firstname=Russell&lastname=Hitchcock&bio=Singer"
curl -vX PUT "http://localhost:8081/ArtistServlet?nickname=Russell%20Hitchcock&firstname=Russell&lastname=Hitchcock&bio=Songwriter"
curl -v "http://localhost:8081/ArtistServlet?nickname=Russell%20Hitchcock"
curl -vX DELETE "http://localhost:8081/ArtistServlet?nickname=RussellHitchcock"
```