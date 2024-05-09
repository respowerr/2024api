
![Logo](https://i.ibb.co/1GGHbnw/Image1.png)

<h1 align="center" style="font-size: 40px">SUPER API</h1>
<p align="center">API for Au temps donné, a final school project.</p>

The 4 original apis merged into a single one.
- `Truck API`
- `Account API`
- `Event API`
- `Warehouse API`

## Installation & run

```bash
  apt-get install docker && apt-get install docker-compose
  git clone https://github.com/respowerr/2024api
  cd 2024api
  docker compose up --build
```

There are 4 roles in total in the API:
- `ROLE_BENEVOLE`
- `ROLE_BENEFICIARE`
- `ROLE_PARTENAIRE`
- `ROLE_ADMIN`

## ACCOUNT - API

#### Register

```http
  POST /account/register
```

| Body                                                                                       | Description | JSON                                                                                                                                                                                                        |
|:-------------------------------------------------------------------------------------------|:------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `username`<br/>`password`<br/>`email`<br/>`phone`<br/>`name`<br/>`lastName`<br/>`location` | SignUp      | {<br/>"username": "ben",<br/>"password": "@nakin###!",<br/>"email": "okenobi@jeditemple.com",<br/>"phone": "0123456789",<br/>"name": "obi-wan",<br/>"lastName": "Kenobi",<br/>"location": "Coruscant"<br/>} |

#### Login

```http
  POST /account/login
```

| Body                      | Description | JSON                                                        |
|:--------------------------|:------------|:------------------------------------------------------------|
| `username`<br/>`password` | SignIn      | {<br/>"username": "ben",<br/>"password": "@nakin###!"<br/>} |

#### Get all accounts

```http
  GET /account/all
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

| Body                                                                                                       | Description                                                   | JSON                                                                                                                                                                                                                                                                             |
|:-----------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `eventName`<br/>`eventType`<br/>`eventStart`<br/>`eventEnd`<br/>`members`<br/>`location`<br/>`description` | **Required**. JWT Token with ROLE_ADMIN<br/> Create an event. | {<br/>"eventName": "Starfleet com",<br/>"eventType": "Conference",<br/>"eventStart": "2024-05-09", <br/>"eventEnd": "2024-05-09", <br/>"location": "USS-1701", <br/>"description" : "Spock speech", <br/>"members": [<br/>{<br/>"id": 2<br/>}<br/>]<br/>}                        |

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

| Body                                                   | Description                                                | JSON                                                                                           |
|:-------------------------------------------------------|:-----------------------------------------------------------|:-----------------------------------------------------------------------------------------------|
| `plaqueImmatriculation`<br/>`capacite`<br/>`tourneeId` | **Required**. JWT Token with ROLE_ADMIN<br/> Create truck. | {<br/>"plaqueImmatriculation": "123456789",<br/>"capacite": "200", <br/>"tourneeId": "2"<br/>} |

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

## WAREHOUSE - API

#### List all warehouses with infos

```http
  GET /warehouse
```

| Description                                                                        | Response                                                                              |
|:-----------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------|
| **Required**. JWT Token with ROLE_USER<br/>Get and show all warehouses with infos. | `warehouse_id`<br/>`location`<br/>`capacity`<br/>`itemName`<br/>`count`<br/>`item_id` |
#### Show a warehouse by id

```http
  GET /warehouse/{location}
```

| Parameter    | Type     | Description                                                                       | Response                                                                              |
|:-------------|:---------|:----------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------|
| `{location}` | `string` | **Required**. JWT Token with ROLE_USER<br/>Get and show all infos of a warehouse. | `warehouse_id`<br/>`location`<br/>`capacity`<br/>`itemName`<br/>`count`<br/>`item_id` |

#### Add items 

```http
  POST /warehouse/{location}
```

| Parameter    | Type     | Body                   |                                                                             
|:-------------|:---------|:-----------------------|
| `{location}` | `string` | `itemName`<br/>`count` |

#### Modify items name or count

```http
  PUT /warehouse/{location}
```

| Parameter    | Type     | Body                                 |                                                                             
|:-------------|:---------|:-------------------------------------|
| `{location}` | `string` | `item_id`<br/>`itemName`<br/>`count` |

#### Delete items

```http
  DELETE /warehouse/{location}
```

| Parameter    | Type     | Body      |                                                                             
|:-------------|:---------|:----------|
| `{location}` | `string` | `item_id` |



## Authors

- [@Respowerr](https://www.github.com/respowerr)
- [@SnikiEP](https://www.github.com/snikiep)
- [@Amlezia](https://www.github.com/amlezia)