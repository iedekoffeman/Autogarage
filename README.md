## Autogarage applicatie
Met deze applicatie kan een autogarage het proces van keuringen en reparaties rondom auto's en klanten automatiseren. De applicatie biedt o.a. de mogelijkheid om klanten en auto's aan te maken. Keuringen en reparaties aan auto's te koppelen en meer. De applicatie kent Authenticatie en Authorizatie zodat data beschermd is.


## Inhoudsopgave
- [Vooraf](#vooraf)
- [Users die worden toegevoegd aan de database bij het opstarten van het project](#users-die-worden-toegevoegd-aan-de-database-bij-het-opstarten-van-het-project)
- [Installatie](#Installatie)
 * [Endpoints](#endpoints)
      + [Authorization](#authorization)
      + [Users](#users)
      + [Customers](#customers)
      + [Cars](#cars)
      + [Inspections](#inspections)
      + [Repairs](#repairs)
      + [Deficiencies](#deficiencies)
      + [Items](#items)
      
## Vooraf

1. Java version 11
    jdk-11.0.12.7-hotspot

2. Database PostgreSQL

3. Cross-Origin is aangezet voor alle endpoints en alle origins.

4. Authorization verplicht. (JWT token) zie sectie endpoints. 
<br/>
<br/>

#### Users die worden toegevoegd aan de database bij het opstarten van het project

| Username | Password | Role                      |
|----------|----------|---------------------------|
| Jan      | password | Administratief medewerker |
| Piet     | password | Monteur                   |

<br>

## Installatie

* Dit project is het beste te runnen met de IDE IntelIj van Netbrains. <br/>
* De applicatie maakt gebruik van een PostgresSQL database. Zorg dus dat je PostgreSQL geinstalleerd hebt.

1. Installeer de InteliJ IDE als dit nog niet gedaan is.<br/>
    Dit kun je doen door in de menu bar naar Git > Clone te gaan, daar de URL van deze repository in te vullen en op clone te klikken. 
   <br/>
   </br>
2. Laad het project in Intelij. Maven zal het synchroniseren van dependencies starten.
   <br/>
   </br>
3. Verander de PostgreSQL credentials in application-dev.properties naar jouw eigen local settings:<br/>
   server.port=8081</br>
   spring.datasource.url=jdbc:postgresql://localhost:5432/autogarage<br/>
   spring.datasource.username=autogarage_admin<br/>
   spring.datasource.password=12345<br/>
   <br/>
   </br>
4. Maak een uploads directory aan in de root directoy van de project folder of kijk of deze al bestaat.<br/> 
   v.b. ../Autogarage/uploads
<br/>
</br>
5. Run het project<br/>
   Als alles klopt zijn de endpoints beschikbaar. 
<br/>
<br/>

### Endpoints

De endpoints kunnen getest worden met een applicatie als Postman.<br/>

De authenticatie van de applicatie werkt met een Bearer token. Deze token moet mee gegeven worden aan elk request. Dit doe je in Postman door onder Authorization, Bearer Token in te stellen en de token in te vullen. 

De token verkrijg je door eerst het Authorization endpoint uit te voeren. De token is vervolgens 10 dagen geldig. 

#### Authorization
* POST /api/v1/authenticate
  <br/><br/>
  In de body geef je de username en password mee. Hiervoor kan een user uit bovenstaande tabel gebruikt worden:
  <br/><br/>

  voorbeeld:
```json
 {
  "username": "admin",
  "password": "password"
 }
```


#### Users
* GET   /api/v1/users
* POST /api/v1/users
* DELETE /api/v1/users/{username}
* GET /api/v1/users/{username}
* PUT /api/v1/users/{username}
* GET /api/v1/users/{username}/authorities
* POST /api/v1/users/{username}/authorities
* DELETE /api/v1/users/{username}/authorities/{authority}
* PATCH /api/v1/users/{username}/password

#### Customers
* GET   /api/v1/customers
* POST /api/v1/customers
* DELETE /api/v1/customers/{id}
* GET /api/v1/customers?lastname={lastname}
* GET /api/v1/customers/{id}
* PUT /api/v1/customers/{id}
* PATCH /api/v1/customers/{id}

#### Cars
* GET   /api/v1/cars
* POST /api/v1/cars
* DELETE /api/v1/cars/{id}
* GET /api/v1/cars/{id}
* PUT /api/v1/cars/{id}
* PATCH /api/v1/cars/{id}
* POST /api/v1/cars/{id}/licenseregistrationfile/upload
* GET /api/v1/cars/{id}/licenseregistrationfile/download

#### Inspections
* GET   /api/v1/inspections
* POST /api/v1/inspections
* DELETE /api/v1/inspections/{id}
* GET /api/v1/inspections/{id}
* PUT /api/v1/inspections/{id}
* PATCH /api/v1/inspections/{id}

#### Repairs
* GET   /api/v1/repairs
* POST /api/v1/repairs
* DELETE /api/v1/repairs/{id}
* GET /api/v1/repairs/{id}
* PUT /api/v1/repairs/{id}
* PATCH /api/v1/repairs/{id}

#### Deficiencies
* GET   /api/v1/deficiencies
* POST /api/v1/deficiencies
* DELETE /api/v1/deficiencies/{id}
* GET /api/v1/deficiencies/{id}
* PUT /api/v1/deficiencies/{id}
* PATCH /api/v1/deficiencies/{id}

#### Items
* GET   /api/v1/items
* POST /api/v1/items
* DELETE /api/v1/items/{id}
* GET /api/v1/items/{id}
* PUT /api/v1/items/{id}
* PATCH /api/v1/items/{id}




#### Er is een export van postman toegevoegd in de documentation directory van dit project.