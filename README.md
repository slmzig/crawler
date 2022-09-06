### App start

application starts on localhost:8080 address, you can change that in application.conf file

### locally run
`sbt "runMain dataengi.crawler.Bootstrap"`

### How to test
```
curl --location --request POST 'localhost:8080/api/crawl' \
--header 'Content-Type: application/json' \
--data-raw '{"urls": ["https://google.com", "https://github.com"]}'
```
