# HELIX  - SUPER API

The 3 original apis merged into a single one.

## Paths :
- #### Endpoint : /account
    - /register (POST)
    - /login (POST)
    - /id (GET, POST, PUT, DELETE) ON JWT PROTECT.
  
On GET Method, the endpoint return all accounts ONLY for admins.  
- #### Endpoint : /stock
    - /warehouse (GET, POST, PUT, DELETE)
- #### Endpoint : /event
    - /id (GET, PUT, DELETE)
    - /type (NOT FOR NOW, sort by type of event)

On GET Method, the endpoint return all events ONLY for members.
  
  
- #### Endpoint : /camions
    - /id (GET, PUT, DELETE), Get, Put, Delete ONLY for USER_ADMIN.

On GET Method, the endpoint return all trucks.



© 2024 CALLIDOS GROUP