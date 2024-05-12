
![Logo](https://i.ibb.co/1GGHbnw/Image1.png)

<h1 align="center" style="font-size: 40px">SUPER API</h1>
<p align="center">API for Au temps donné, a final school project.</p>

The 5 original apis merged into a single one.
- `Vehicle API`
- `Account API`
- `Ticketing API`
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

| Body                                                                                                 | Description | JSON                                                                                                                                                                                                                        |
|:-----------------------------------------------------------------------------------------------------|:------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `username`<br/>`password`<br/>`email`<br/>`phone`<br/>`name`<br/>`lastName`<br/>`location`<br/>`sex` | SignUp      | {<br/>"username": "ben",<br/>"password": "@nakin###!",<br/>"email": "okenobi@jeditemple.com",<br/>"phone": "0123456789",<br/>"name": "obi-wan",<br/>"lastName": "Kenobi",<br/>"location": "Coruscant",<br/>"sex": "M"<br/>} |

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

#### Get all informations of himself

```http
  GET /account/me
```

| Description                |
|:---------------------------|
| Get all self informations. |


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

#### Join an event

```http
  POST /event/{id}/join
```

| Parameter | Type  | Description                                                                                      |
|:----------|:------|:-------------------------------------------------------------------------------------------------|
| `{id}`    | `int` | **Required**. JWT Token with ROLE_USER<br/>Join an event. It's impossible to overlapping events. |

#### Leave an event

```http
  DELETE /event/{id}/quit
```

| Parameter | Type       | Description                                                                                          |
|:----------|:-----------|:-----------------------------------------------------------------------------------------------------|
| `{id}`    | `int`<br/> | **Required**. JWT Token with ROLE_USER<br/>Leave an event.                                           |

#### Create an event


```http
  POST /event
```

| Body                                                                                         | Description                                                   | JSON                                                                                                                                                                                                      |
|:---------------------------------------------------------------------------------------------|:--------------------------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `eventName`<br/>`eventType`<br/>`eventStart`<br/>`eventEnd`<br/>`location`<br/>`description` | **Required**. JWT Token with ROLE_ADMIN<br/> Create an event. | {<br/>"eventName": "Starfleet com",<br/>"eventType": "Conference",<br/>"eventStart": "2024-05-09", <br/>"eventEnd": "2024-05-09", <br/>"location": "USS-1701", <br/>"description" : "Spock speech" <br/>} |

#### Filter by type ( NOT WORKING FOR NOW )

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

| Parameter | Type  | Body                                                                                         | Description                                                                 |
|:----------|:------|:---------------------------------------------------------------------------------------------|:----------------------------------------------------------------------------|
| `{id}`    | `int` | `eventName`<br/>`eventType`<br/>`eventStart`<br/>`eventEnd`<br/>`location`<br/>`description` | **Required**. JWT Token with ROLE_ADMIN<br/> Modify an event information's. |


```http
  DELETE /event/{id}
```

| Parameter | Type  | Description                                                 |
|:----------|:------|:------------------------------------------------------------|
| `{id}`    | `int` | **Required**. JWT Token with ROLE_ADMIN <br/> Delete event. |

#### Request a new event

```http
  POST /event/request
```

| JSON BODY                                                                                    | Description                                                                |
|:---------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------|
| `eventName`<br/>`eventType`<br/>`eventStart`<br/>`eventEnd`<br/>`location`<br/>`description` | **Required**. JWT Token with ROLE_ADMIN<br/> Create a new event's request. |

#### Get all requests

```http
  GET /event/request
```

| Description                                                     |
|:----------------------------------------------------------------|
| **Required**. JWT Token with ROLE_ADMIN <br/> Get all requests. |

#### Accept request

```http
  POST /event/accept/{request_id}
```

| Parameter      | Type  | Description                                                     |
|:---------------|:------|:----------------------------------------------------------------|
| `{request_id}` | `int` | **Required**. JWT Token with ROLE_ADMIN <br/> Accept a request. |

## TICKETING - API

#### Create a new ticket

```http
  POST /tickets
```

| Body               | Description                                                        | JSON                                                                             |
|:-------------------|:-------------------------------------------------------------------|:---------------------------------------------------------------------------------|
| `title`<br/>`desc` | New ticket with JWT username session. Receiver is only ROLE_ADMIN. | { <br/>"title": "Rebels attack", <br/>"desc": "Rebels attack on Coruscant"<br/>} |

#### Delete a ticket

```http
  DELETE /tickets/{ticket_id}
```
| Parameter     | Type  | Description                                                  |
|:--------------|:------|:-------------------------------------------------------------|
| `{ticket_id}` | `int` | **Required**. JWT Token with ROLE_ADMIN <br/> Delete ticket. |


#### Get all tickets

```http
  GET /tickets
```
| Description                                                                           |
|:--------------------------------------------------------------------------------------|
| **Required**. JWT Token with ROLE_ADMIN <br/> Show and list all not resolved tickets. |

#### Show a ticket

```http
  GET /tickets/{ticket_id}
```
| Parameter     | Type  | Description                                                     |
|:--------------|:------|:----------------------------------------------------------------|
| `{ticket_id}` | `int` | **Required**. JWT Token with ROLE_USER <br/> Show ticket by ID. |

#### Send messages

```http
  POST /tickets/{ticket_id}/messages
```
| Parameter     | Type  | Description                                                                   | JSON                                                 |
|:--------------|:------|:------------------------------------------------------------------------------|:-----------------------------------------------------|
| `{ticket_id}` | `int` | **Required**. JWT Token with ROLE_USER <br/> Write a message inside a ticket. | {<br/>"message": "may the force be with you !"<br/>} |

#### Show all messages of a ticket

```http
  GET /tickets/{ticket_id}/messages
```
| Parameter     | Type  | Description                                                                 |
|:--------------|:------|:----------------------------------------------------------------------------|
| `{ticket_id}` | `int` | **Required**. JWT Token with ROLE_USER <br/> Show all messages of a ticket. |

#### Show all tickets not resolved for the user

```http
  GET /tickets/mytickets
```
| Description                                                                                                  |
|:-------------------------------------------------------------------------------------------------------------|
| **Required**. JWT Token with ROLE_USER <br/> Show all tickets not resolved of the user who made the request. |

#### Resolve a ticket

```http
  PUT /tickets/{ticket_id}/resolve
```
| Parameter     | Type  | Description                                                                                |
|:--------------|:------|:-------------------------------------------------------------------------------------------|
| `{ticket_id}` | `int` | **Required**. JWT Token with ROLE_ADMIN. <br/> Resolve a ticket by set "resolved" to true. |

## VEHICLE - API

#### List all vehicles
```http
  GET /vehicle
```

| Description                                                     |
|:----------------------------------------------------------------|
| **Required**. JWT Token with ROLE_ADMIN <br/> Get all vehicles. |

#### Create a vehicle
```http
  POST /vehicle
```

| Body                                                            | Description                                                  | JSON                                                                                                                           |
|:----------------------------------------------------------------|:-------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------|
| `id_plate`<br/>`fret_capacity`<br/>`human_capacity`<br/>`model` | **Required**. JWT Token with ROLE_ADMIN<br/> Create vehicle. | {<br/>"id_plate": "123456789",<br/>"fret_capacity": "500", <br/>"human_capacity": "6", <br/>"model": "millennium falcon"<br/>} |

#### Vehicle selection

```http
  GET /vehicle/{id}
```

| Parameter | Type  | Description                                                                |
|:----------|:------|:---------------------------------------------------------------------------|
| `{id}`    | `int` | **Required**. JWT Token with ROLE_ADMIN<br/>Get all infos about a vehicle. |


```http
  PUT /vehicle/{id}
```

| Parameter | Type  | Body                                                            | Description                                                                |
|:----------|:------|:----------------------------------------------------------------|:---------------------------------------------------------------------------|
| `{id}`    | `int` | `id_plate`<br/>`fret_capacity`<br/>`human_capacity`<br/>`model` | **Required**. JWT Token with ROLE_ADMIN<br/> Modify vehicle information's. |


```http
  DELETE /vehicle/{id}
```

| Parameter | Type  | Description                                                         |
|:----------|:------|:--------------------------------------------------------------------|
| `{id}`    | `int` | **Required**. JWT Token with ROLE_ADMIN <br/> Delete vehicle by id. |

## WAREHOUSE - API
- Saint-Quentin : Total capacity = 90 racks
- Laon : Total capacity = 59 racks
- **1 rack = 120 pallets && 1 pallet = 4 cartons**

#### List all warehouses with infos

```http
  GET /warehouse
```

| Description                                                                        | Response                                                                              |
|:-----------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------|
| **Required**. JWT Token with ROLE_USER<br/>Get and show all warehouses with infos. | `warehouse_id`<br/>`location`<br/>`capacity`<br/>`itemName`<br/>`count`<br/>`item_id` |
#### Show a warehouse by id

```http
  GET /warehouse/{warehouse_id}
```

| Parameter        | Type  | Description                                                                       | Response                                                                                        |
|:-----------------|:------|:----------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------|
| `{warehouse_id}` | `int` | **Required**. JWT Token with ROLE_USER<br/>Get and show all infos of a warehouse. | `warehouse_id`<br/>`location`<br/>`rack_capacity`<br/>`current_stock`<br/>`utilization`         |


#### Modify a warehouse current stock.

```http
  PUT /warehouse/{warehouse_id}
```

| Parameter        | Type  | Description                                                                  | Response                                                                                        |
|:-----------------|:------|:-----------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------|
| `{warehouse_id}` | `int` | **Required**. JWT Token with ROLE_USER<br/>Modify the stock of an warehouse. | `warehouse_id`<br/>`location`<br/>`rack_capacity`<br/>`current_stock`<br/>`utilization`         |

#### Add items 

```http
  POST /warehouse/{warehouse_id}
```

| Parameter        | Type  | Body                   |                                                                             
|:-----------------|:------|:-----------------------|
| `{warehouse_id}` | `int` | `itemName`<br/>`count` |

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