# DevCrab

Uma API RESTful para auxiliar pessoas interessadas em competições e oportunidades de melhorar o currículo

## Diagrama de classes

``` mermaid
classDiagram
    class Contest {
        +Long id
        +String name
        +String description
        +List<Tematic> tematic
        +Long participants
        +List<Scholarity> scholarity
        +String icon
        +String url
    }
    
    class User {
        +Long id
        +String username
        +String password
        +Set<Contest> contests
    }
    
    class Tematic {
        +Long id
        +String name
    }
    
    class Scholarity {
        +Long id
        +String level
    }
    
    Contest "*" -- "*" Tematic : possui
    Contest "*" -- "*" Scholarity : exige
    User "*" -- "*" Contest : participa
```

## Planos futuros

- Melhora a integração entre as classes
- Adicionar mais formas de categorizar as competições
- Adicionar filtro de competições por categoria
