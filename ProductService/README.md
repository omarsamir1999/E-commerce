# E-Commerce Website

- [Product ERD OnlineEditor](https://www.mermaidchart.com/app/projects/a361ddde-5d1b-4959-93e0-c6c6df48e3c0/diagrams/9a33ac08-c1a0-4295-a0b6-9953ecefa5d2/version/v0.1/edit)

## Abstraction about Service

This is Product service that have three Entities:

1. Product
2. Brand
3. Category

Product have `ManyToOne` relationship between each other.

## API Docs

Run Product Service by maven:

```cmd
mvn spring-boot:run
```

and then go to swagger host:

- [Swagger Docs](http://localhost:9090/swagger-ui/index.html)
