# ATTRACTION REST API
## REST API Dummy with methods for create, read, update and delete of an attraction object

This REST API is developed as part of the team project course at Harz University of Applied Sciences.

### Endpoints

| Method | URL                   | Description                   | Sample Response                   | Sample Request Body       |
|--------|-----------------------|-------------------------------|-----------------------------------|---------------------------|
| GET    | /api/attractions      | Find all existing attractions | [JSON](#getAllAttractions)        | none                      |
| GET    | /api/attractions/{id} | Find an attraction            | [JSON](#getAttractionById)        | none                      |
| POST   | /api/attractions/     | Create an attraction          | [JSON](#createAttractionResponse) | [JSON](#createAttraction) |
| PUT    | /api/attractions/{id} | Update an attraction          | [JSON](#updateAttractionResponse) | [JSON](#updateAttraction) |
| DELETE | /api/attractions/{id} | Delete an attraction          | none                              | none                      |

---
### Sample Responses

##### <a id="getAllAttractions">Find all existing attractions -> GET /api/attractions</a>
```json
[
  {
    "attractionId": 1,
    "attractionName": "Schloss Wernigerode",
    "attractionLocation": "Wernigerode"
  },
  {
    "attractionId": 2,
    "attractionName": "Schierker Feuerstein Arena",
    "attractionLocation": "Schierke"
  }
]
```

##### <a id="getAttractionById">Find an attraction by id -> GET /api/attractions/{id}</a>
```json
{
  "attractionId": 2,
  "attractionName": "Schierker Feuerstein Arena",
  "attractionLocation": "Schierke"
}
```

##### <a id="createAttractionResponse">Create an attraction -> POST /api/attractions</a>
```json
{
  "attractionId": 3,
  "attractionName": "Baumwipfelpfad Harz",
  "attractionLocation": "Bad Harzburg"
}
```

##### <a id="updateAttractionResponse">Update an attraction -> PUT /api/attractions</a>
```json
{
  "attractionId": 3,
  "attractionName": "Baumwipfelpfad Harz",
  "attractionLocation": "Bad Harzburg"
}
```
---
### Sample Requests Bodies

##### <a id="createAttraction">Create an attraction -> POST /api/attractions</a>
```json
{
  "attractionName": "Your name",
  "attractionLocation": "Your location"
}
```

##### <a id="updateAttraction">Update an attraction -> PUT /api/attractions</a>
```json
{
  "attractionName": "Your updated name",
  "attractionLocation": "Your updated location"
}
```

