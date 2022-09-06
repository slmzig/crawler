## Web crawler

This is base microservice that accept urls and return html body that we can use later as we want

### Problem Statement

Write a scalable microservice in Scala, which exposes an API endpoint that takes a list of URLs, crawls the
data concurrently, and returns the crawled data.
API Details
endpoint: /api/crawl
http method: post
request body:

```json
{
  "urls": [
    "https://google.com",
    "https://github.com"
  ]
}
```

response body:

```json
{
  "result": [
    {
      "url": "https://google.com",
      "data": "..."
    },
    {
      "url": "https://github.com",
      "data": "..."
    }
  ],
  "error": null
}
```

### App start

application starts on localhost:8080 address, you can change that in application.conf file

### locally run

`sbt run`

### run in docker

- First log into sbt
  - `sbt`
- run command to create local image
  - `docker:publishLocal`
- run docker container
  - `docker run -p 8080:8080 crawler:latest`

### How to test api

```
curl --location --request POST 'localhost:8080/api/crawl' \
--header 'Content-Type: application/json' \
--data-raw '{"urls": ["https://google.com", "https://github.com"]}'
```
