
![Logo](https://i.ibb.co/1GGHbnw/Image1.png)
# HELIX  - SUPER API
The 4 original apis merged into a single one.

## ACCOUNT - API

#### Register

```http
  POST /account/register
```

| Body                                                                                       | Description |
|:-------------------------------------------------------------------------------------------|:------------|
| `username`<br/>`password`<br/>`email`<br/>`phone`<br/>`name`<br/>`lastName`<br/>`location` | SignUp      |

#### Login

```http
  POST /account/login
```

| Body                      | Description |
|:--------------------------|:------------|
| `username`<br/>`password` | SignIn      |

#### Get all accounts

```http
  GET /account
```

| Description                                                           |
|:----------------------------------------------------------------------|
| **Required**. JWT Token with ROLE_ADMIN <br/> Get all Helix accounts. |


#### Account selection

```http
  GET /account/{id}
```

| Parameter | Type  | Description                                                          |
|:----------|:------|:---------------------------------------------------------------------|
| `{id}`    | `int` | **Required**. JWT Token with ROLE_ADMIN<br/>Get all infos of a user. |

```http
  PUT /account/{id}
```

| Parameter | Type  | Body                                                                                       | Description                                                            |
|:----------|:------|:-------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------|
| `{id}`    | `int` | `username`<br/>`email`<br/>`phone`<br/>`name`<br/>`lastName`<br/>`location`<br/>`password` | **Required**. JWT Token with ROLE_ADMIN<br/> Modify user informations. |

```http
  DELETE /account/{id}
```

| Parameter | Type  | Description                                                   |
|:----------|:------|:--------------------------------------------------------------|
| `{id}`    | `int` | **Required**. JWT Token with ROLE_ADMIN <br/> Delete account. |


## EVENT - API

#### Get all active events

```http
  GET /event
```

| Description                                                         |
|:--------------------------------------------------------------------|
| **Required**. JWT Token with ROLE_USER <br/> Get all active events. |

```http
  POST /event
```
#### Create an event

| Body                                                                                                     | Description                                                                |
|:---------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------|
| `eventName`<br/>`eventType`<br/>`eventStart`<br/>`eventEnd`<br/>`users`<br/>`location`<br/>`description` | **Required**. JWT Token with ROLE_ADMIN<br/> Create an event.              |

#### Filter by type

```http
  GET /event/{type}
```

| Parameter | Type     | Description                                                                   |
|:----------|:---------|:------------------------------------------------------------------------------|
| `{type}`  | `string` | **Required**. JWT Token with ROLE_USER<br/>Get all events with the same type. |

#### Event selection

```http
  GET /event/{id}
```

| Parameter | Type  | Description                                                                |
|:----------|:------|:---------------------------------------------------------------------------|
| `{id}`    | `int` | **Required**. JWT Token with ROLE_USER<br/>Get all infos about an event.   |


```http
  PUT /event/{id}
```

| Parameter | Type  | Body                                                                                                     | Description                                                                 |
|:----------|:------|:---------------------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------|
| `{id}`    | `int` | `eventName`<br/>`eventType`<br/>`eventStart`<br/>`eventEnd`<br/>`users`<br/>`location`<br/>`description` | **Required**. JWT Token with ROLE_ADMIN<br/> Modify an event information's. |


```http
  DELETE /event/{id}
```

| Parameter | Type  | Description                                                 |
|:----------|:------|:------------------------------------------------------------|
| `{id}`    | `int` | **Required**. JWT Token with ROLE_ADMIN <br/> Delete event. |

## TRUCK - API

#### List all trucks
```http
  GET /camions
```

| Description                                                   |
|:--------------------------------------------------------------|
| **Required**. JWT Token with ROLE_ADMIN <br/> Get all trucks. |

#### Create a truck
```http
  POST /camions
```

| Body                                                   | Description                                                |
|:-------------------------------------------------------|:-----------------------------------------------------------|
| `plaqueImmatriculation`<br/>`capacite`<br/>`tourneeId` | **Required**. JWT Token with ROLE_ADMIN<br/> Create truck. |

#### Truck selection

```http
  GET /camions/{id}
```

| Parameter | Type  | Description                                                               |
|:----------|:------|:--------------------------------------------------------------------------|
| `{id}`    | `int` | **Required**. JWT Token with ROLE_ADMIN<br/>Get all infos about an truck. |


```http
  PUT /camions/{id}
```

| Parameter | Type  | Body                                                   | Description                                                                 |
|:----------|:------|:-------------------------------------------------------|:----------------------------------------------------------------------------|
| `{id}`    | `int` | `plaqueImmatriculation`<br/>`capacite`<br/>`tourneeId` | **Required**. JWT Token with ROLE_ADMIN<br/> Modify an truck information's. |


```http
  DELETE /camions/{id}
```

| Parameter | Type  | Description                                                 |
|:----------|:------|:------------------------------------------------------------|
| `{id}`    | `int` | **Required**. JWT Token with ROLE_ADMIN <br/> Delete truck. |

## Paths :
- #### Endpoint : /stock
    - /warehouse (GET, POST, PUT, DELETE)
  
© 2024 CALLIDOS GROUP