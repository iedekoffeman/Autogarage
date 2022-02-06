## Autogarage
Met dit project kan een autogarage het proces van keuringen en reparaties rondom auto's en klanten automatiseren. De uiteindelijke applicatie biedt o.a. de mogelijkheid om klanten en auto's aan te maken. Keuringen en reparaties aan auto's te koppelen en meer. De applicatie kent Authenticatie en Authorizatie zodat data beschermd is.


## Inhoudsopgave
* [Vooraf](#vooraf)
  + [Users die worden toegevoegd aan de database bij het starten van het project](#users-die-worden-toegevoegd-aan-de-database-bij-het-opstarten-van-het-project)
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

1. Het project maakt gebruik van Java version 11
    jdk-11.0.12.7-hotspot

2. Het project maakt gebruik van een PostgreSQL database

3. Cross-Origin is aangezet voor alle endpoints en alle origins.

4. Authorization verplicht. (Bearer(JWT) token) zie sectie endpoints. 

#### Er is een export met endpoints vanuit postman toegevoegd in de documentation directory van dit project.
<br/>

#### Users die worden toegevoegd aan de database bij het opstarten van het project

| Username                  | Password | Role                     | Endpoints persmissons                                          |
|---------------------------|----------|--------------------------|----------------------------------------------------|
| monteur                   | password | MONTEUR                  | repairs** <br/>inspections**<br/> deficiencies**<br/> items**     |
| administratief_medewerker | password | ADMINISTRATIEFMEDEWERKER | users**<br/> customers**<br/> cars**<br/> repairs**<br/> inspections** |
<br>

## Installatie

* Dit project is het beste te runnen met de IDE IntelIj van Jetbrains. <br/> Download de community versie hier: 
  https://www.jetbrains.com/idea/download/  <br/><br/>

* De applicatie maakt gebruik van een PostgresSQL database. PostgreSQL kun je hier downloaden: https://www.postgresql.org/download/ <br/>
  <br/>

1. Installeer de InteliJ IDE en PostGreSQL.<br/>
    <br/>
    Hier vind je een makkelijke en korte tutorial om PostGreSQL te installeren.<br/>
    https://www.postgresqltutorial.com/install-postgresql/ <br/><br/>
2. Laad het project in Intelij. Maven zal het synchroniseren van dependencies starten en zo wordt alle dependcies die benodigd zijn binnen voor je binnen gehaald.<br/><br/>
   Het inladen van het project kun je doen door in InteliJ via de menu bar naar Git > Clone te gaan, daar de URL van deze repository in te vullen en op clone te klikken.
   <br/>
   </br>
3. Verander de PostgreSQL credentials in  resources > application-dev.properties naar jouw eigen local settings:<br/>
   <br/>
   De huidige instellingen zijn als volgt:<br/><br/>
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
   Als alle stappen zijn genomen zijn de endpoints beschikbaar en kun je deze via Postman aanroepen. <br/><br/> Je kunt de bijgeleverde export van de endpoints uit postman importeren zodat deze direct beschikbaar zijn. Deze staan in de documents folder van dit project. 
<br/>
<br/>

### Endpoints

De endpoints kunnen getest worden met een applicatie als Postman.<br/>

>  **_NOTE:_**
<b>Authentication endpoints:</b><br/><br/>
De authenticatie van de applicatie werkt met een Bearer token. Deze token moet mee gegeven worden aan elk request. Dit stel je in binnen Postman onder 'Authorization'<br/> 
<br/>Bij een Request endpoint (binnen postman) kies je op de tab 'Authorization' voor het Type: Bearer token, onder 'Token' vul je het 'JWT token' in welke je via het 'Authorization' endpoint kunt verkrijgen.<br/><br/> Deze token geeft toegang voor elk endpoint waar deze user voor geauthorizeerd is. De rechten per user vind je in de tabel bovenaan deze handleiding.







De token verkrijg je door eerst het 'Authorization' endpoint uit te voeren. De token is vervolgens 10 dagen geldig. 

#### Authorization
* POST /api/v1/authenticate
  <br/><br/>
  In de body geef je de username en password mee. Hiervoor kan een user uit eerder genoemde tabel (monteur/administratief_medewerker) gebruikt worden:
  <br/><br/>
  Voor de demo gebruiken we 'password' als wachtwoord, maar normaliter is dit uiteraard niet veilig en zou je dit niet zo instellen.

<br/>Voorbeeld Request body:
```json
 {
  "username": "administratief_medewerker",
  "password": "password"
 }
```
```json
 {
  "username": "monteur",
  "password": "password"
 }
```
Voorbeeld Response:
```json
 {
    "jwt":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbmlzdHJhdGllZl9tZWRld2Vya2VyIiwiZXhwIjoxNjQ0Nzc5OTA2LCJpYXQiOjE2NDM5MTU5MDZ9.zgI-uMAMRuNId8VaIvS2KfeBMOK9qQZJwG6GezbFosQ"
 }
```

#### Users
* GET   /api/v1/users

Voorbeeld Response:
```json
[
  {
    "username": "monteur",
    "password": "$2y$10$pn4mvxgRMiFSelR/8LbTE.VgPVF4eYQFR0bd.vmkznvnesWfnUwxK",
    "enabled": true,
    "email": "dummy@novi.nl",
    "authorities": [
      {
        "username": "monteur",
        "authority": "ROLE_MONTEUR"
      }
    ]
  },
  {
    "username": "administratief_medewerker",
    "password": "$2y$10$pn4mvxgRMiFSelR/8LbTE.VgPVF4eYQFR0bd.vmkznvnesWfnUwxK",
    "enabled": true,
    "email": "dummy@novi.nl",
    "authorities": [
      {
        "username": "administratief_medewerker",
        "authority": "ROLE_ADMINISTRATIEFMEDEWERKER"
      }
    ]
  }
]
```

* POST /api/v1/users <br/><br/>
  Voorbeeld Request body:
```json
{
  "username": "Jari",
  "password": "password",
  "enabled": "true",
  "email": "i.koffeman@gmail.com",
  "authorities": []
}
```  

* DELETE /api/v1/users/{username}
* GET /api/v1/users/{username}
* PUT /api/v1/users/{username}
* GET /api/v1/users/{username}/authorities
* POST /api/v1/users/{username}/authorities
* DELETE /api/v1/users/{username}/authorities/{authority}
* PATCH /api/v1/users/{username}/password

#### Customers
* GET   /api/v1/customers


  Voorbeeld Response :
```json
[
  {
    "id": 1,
    "firstname": "Iede",
    "lastname": "Koffeman",
    "phonenumber": "0681036012",
    "cars": [
      {
        "id": 1,
        "licenseplate": "24-XZ-ZG",
        "licenseRegistrationFileName": null
      },
      {
        "id": 2,
        "licenseplate": "GT-512-Z",
        "licenseRegistrationFileName": null
      }
    ]
  }
]
```  
* POST /api/v1/customers


  Voorbeeld Request body:
```json
{
  "firstname": "Pieter" ,
  "lastname": "Post",
  "phonenumber": "0612345678"
}
```  
* DELETE /api/v1/customers/{id}
* GET /api/v1/customers?lastname={lastname}


  Voorbeeld Response:
```json
{
  "id": 1,
  "firstname": "Iede",
  "lastname": "Koffeman",
  "phonenumber": "0681036012",
  "cars": [
    {
      "id": 1,
      "licenseplate": "24-XZ-ZG",
      "licenseRegistrationFileName": null
    },
    {
      "id": 2,
      "licenseplate": "GT-512-Z",
      "licenseRegistrationFileName": null
    }
  ]
}
```  

* GET /api/v1/customers/{id}


  Voorbeeld Response:
```json
{
  "id": 1,
  "firstname": "Iede",
  "lastname": "Koffeman",
  "phonenumber": "0681036012",
  "cars": [
    {
      "id": 1,
      "licenseplate": "24-XZ-ZG",
      "licenseRegistrationFileName": null
    },
    {
      "id": 2,
      "licenseplate": "GT-512-Z",
      "licenseRegistrationFileName": null
    }
  ]
}
``` 
* PUT /api/v1/customers/{id}


  Voorbeeld Request body:
```json
{
  "firstname": "Sandra",
  "lastname": "Koffeman"
}
``` 

* PATCH /api/v1/customers/{id}


  Voorbeeld Request body:
```json
{
  "lastname": "van Schelt"
}
``` 

* GET /api/v1/customers/{id}/cars

  Voorbeeld Response:
```json
[
  {
    "id": 1,
    "licenseplate": "24-XZ-ZG",
    "licenseRegistrationFileName": null,
    "owner": {
      "id": 1,
      "firstname": "Iede",
      "lastname": "Koffeman",
      "phonenumber": "0681036012"
    }
  },
  {
    "id": 2,
    "licenseplate": "GT-512-Z",
    "licenseRegistrationFileName": null,
    "owner": {
      "id": 1,
      "firstname": "Iede",
      "lastname": "Koffeman",
      "phonenumber": "0681036012"
    }
  }
]
``` 

* POST /api/v1/customers/{id}/cars


  Voorbeeld Request body:
```json
{
  "licenseplate": "24-XZ-ZG"
}
``` 

#### Cars
* GET   /api/v1/cars


  Voorbeeld Response:
```json
[
  {
    "id": 1,
    "licenseplate": "24-XZ-ZG",
    "licenseRegistrationFileName": null,
    "owner": {
      "id": 1,
      "firstname": "Iede",
      "lastname": "Koffeman",
      "phonenumber": "0681036012"
    }
  },
  {
    "id": 2,
    "licenseplate": "GT-512-Z",
    "licenseRegistrationFileName": null,
    "owner": {
      "id": 1,
      "firstname": "Iede",
      "lastname": "Koffeman",
      "phonenumber": "0681036012"
    }
  }
]
``` 
* POST /api/v1/cars


Voorbeeld Request body:
```json
{

  "licenseplate": "24-ZF-DD"

}
``` 
* DELETE /api/v1/cars/{id}
* GET /api/v1/cars/{id}


Voorbeeld Response:
```json
{
  "id": 1,
  "licenseplate": "24-XZ-ZG",
  "licenseRegistrationFileName": null,
  "owner": {
    "id": 1,
    "firstname": "Iede",
    "lastname": "Koffeman",
    "phonenumber": "0681036012"
  }
}
``` 
* PUT /api/v1/cars/{id}

Voorbeeld Request body:
```json
{

  "licenseplate": "GT-512-Z"

}
``` 
* PATCH /api/v1/cars/{id}

Voorbeeld Request body:
```json
{
  "licenseplate": "69-XT-GR"
}
``` 
* POST /api/v1/cars/{id}/licenseregistrationfile/upload<br/>
>  **_NOTE:_**
  Voor upload files worden alleen PDF bestanden geaccepteerd die 500KB of kleiner zijn.


> **_NOTE:_** <br/>Body: form-data <br/> key: file <br/>value: pdf file 
* GET /api/v1/cars/{id}/licenseregistrationfile/download

>  **_NOTE:_**
   Een preview van de file wordt getoond wanneer het request succesvol is uitgevoerd.



* GET api/v1/cars/{id}/carInvoice
>  **_NOTE:_**
  Invoice wordt opgehaald voor Cars met repairs welke status REPAIR_COMPLETED hebben.<br/>
  Dummy data is ingevoerd voor een car met id 1, api/v1/car/1/carInvoice

Voorbeeld Response:
```json
{
  "Car: ": {
    "id": 1,
    "licenseplate": "24-XZ-ZG",
    "licenseRegistrationFileName": "examplepdf.pdf",
    "owner": {
      "id": 1,
      "firstname": "Iede",
      "lastname": "Koffeman",
      "phonenumber": "0681036012"
    }
  },
  "Repairs: ": {
    "Items: ": [
      {
        "id": 13,
        "name": "Luchtfilter Bosch volvo",
        "price": 8.82
      },
      {
        "id": 14,
        "name": "Carburateur vervangen",
        "price": 160.25
      },
      {
        "id": 15,
        "name": "Carburatuer Type 24mm",
        "price": 45.45
      },
      {
        "id": 16,
        "name": "Koppakking vervangen",
        "price": 230.25
      },
      {
        "id": 17,
        "name": "Koppakking Elring",
        "price": 64.74
      },
      {
        "id": 18,
        "name": "Remschijven vervangen",
        "price": 30.25
      },
      {
        "id": 19,
        "name": "Remschijven Bosch",
        "price": 20.25
      },
      {
        "id": 20,
        "name": "Vloeistoffen bijgevuld",
        "price": 15.99
      }
    ],
    "Repair date: ": "2022-02-05",
    "TotalRepairCosts: ": "576.00"
  }
}
``` 


#### Inspections
* GET   /api/v1/inspections
>  **_NOTE:_**
  Optioneel parameter: 'date' <br/> voorbeeld: repairs?date=2022-01-28

Voorbeeld Response:
```json
[
  {
    "id": 1,
    "appointmentDate": "2021-12-28",
    "appointmentStatus": "APPOINTMENT_SCHEDULED",
    "deficiencies": [
      {
        "id": 1,
        "description": "Remvloeistof op"
      },
      {
        "id": 2,
        "description": "Lamp rechtsvoor kapot"
      },
      {
        "id": 3,
        "description": "Luchtfilter aan vervanging toe"
      },
      {
        "id": 4,
        "description": "Carburateur moet vervangen worden"
      }
    ]
  }
]
``` 

* POST /api/v1/inspections


Voorbeeld Request body:
```json
{

  "appointmentDate": "2022-04-10",
  "appointmentStatus": "APPOINTMENT_SCHEDULED",
  "deficiencies": []

}
``` 
* DELETE /api/v1/inspections/{id}
* GET /api/v1/inspections/{id}


Voorbeeld Request body:
```json
{
  "appointmentDate": "2022-03-03",
  "appointmentStatus": "CUSTOMER_APPROVED"
}
``` 
* PUT /api/v1/inspections/{id}

Voorbeeld Request body:
```json
{
  "appointmentDate": "2022-03-03",
  "appointmentStatus": "CUSTOMER_APPROVED"
}
``` 

* PATCH /api/v1/inspections/{id}

Voorbeeld Request body:
```json
{
  "appointmentStatus": "DO_NOT_CARRY_OUT"
}
``` 

#### Repairs
* GET   /api/v1/repairs
>  **_NOTE:_**
Optioneel parameter: 'date' <br/> voorbeeld: repairs?date=2022-01-28

Voorbeeld Response:
```json
[
  {
    "id": 1,
    "appointmentDate": "2022-01-28",
    "appointmentStatus": "APPOINTMENT_SCHEDULED",
    "items": [
      {
        "id": 1,
        "name": "Remvloeistof vervangen",
        "price": 11.99
      },
      {
        "id": 2,
        "name": "Remvloeistof Motul 100948",
        "price": 10.00
      },
      {
        "id": 3,
        "name": "Lamp rechtsvoor vervangen",
        "price": 5.30
      },
      {
        "id": 4,
        "name": "Lamp 5v",
        "price": 1.99
      },
      {
        "id": 5,
        "name": "Luchtfilter Bosch volvo",
        "price": 8.82
      },
      {
        "id": 6,
        "name": "Carburateur vervangen",
        "price": 160.25
      },
      {
        "id": 7,
        "name": "Carburatuer Type 24mm",
        "price": 45.45
      }
    ]
  }
]
``` 

* POST /api/v1/repairs

Voorbeeld Request body:
```json
{

  "appointmentDate": "2022-03-31",
  "appointmentStatus": "APPOINTMENT_SCHEDULED",
  "items": []

}
``` 

* DELETE /api/v1/repairs/{id}
* GET /api/v1/repairs/{id}

Voorbeeld Response:
```json
{
  "id": 1,
  "appointmentDate": "2022-01-28",
  "appointmentStatus": "APPOINTMENT_SCHEDULED",
  "items": [
    {
      "id": 1,
      "name": "Remvloeistof vervangen",
      "price": 11.99
    },
    {
      "id": 2,
      "name": "Remvloeistof Motul 100948",
      "price": 10.00
    },
    {
      "id": 3,
      "name": "Lamp rechtsvoor vervangen",
      "price": 5.30
    },
    {
      "id": 4,
      "name": "Lamp 5v",
      "price": 1.99
    },
    {
      "id": 5,
      "name": "Luchtfilter Bosch volvo",
      "price": 8.82
    },
    {
      "id": 6,
      "name": "Carburateur vervangen",
      "price": 160.25
    },
    {
      "id": 7,
      "name": "Carburatuer Type 24mm",
      "price": 45.45
    }
  ]
}
``` 
* PUT /api/v1/repairs/{id}

Voorbeeld Request body:
```json
{
  "appointmentDate": "2022-03-28",
  "appointmentStatus": "CUSTOMER_APPROVED"
}
``` 

* PATCH /api/v1/repairs/{id}

Voorbeeld Request body:
```json
{
  "appointmentStatus": "DO_NOT_CARRY_OUT"
}
``` 

#### Deficiencies
* GET   /api/v1/deficiencies

Voorbeeld Response:
```json
[
  {
    "id": 1,
    "description": "Remvloeistof op"
  },
  {
    "id": 2,
    "description": "Lamp rechtsvoor kapot"
  },
  {
    "id": 3,
    "description": "Luchtfilter aan vervanging toe"
  },
  {
    "id": 4,
    "description": "Carburateur moet vervangen worden"
  },
  {
    "id": 5,
    "description": "Koppakking is lek"
  },
  {
    "id": 6,
    "description": "Remschijven moeten vervangen worden"
  },
  {
    "id": 7,
    "description": "Vloeistoffen moeten bijgevuld worden"
  }
]
``` 

* POST /api/v1/deficiencies

Voorbeeld Request body:
```json
{
  "description": "Defecte achterlichten"
}
``` 

* DELETE /api/v1/deficiencies/{id}
* GET /api/v1/deficiencies/{id}

Voorbeeld Response:
```json
{
  "id": 1,
  "description": "Remvloeistof op"
}
``` 
* PUT /api/v1/deficiencies/{id}

Voorbeeld Request:
```json
{
  "description": "Defecte achterlichten + bedrading"
}
``` 
* PATCH /api/v1/deficiencies/{id}

Voorbeeld Request:
```json
{
  "description": "Defecte achterlichten + bedrading"
}
``` 

#### Items
* GET   /api/v1/items

Voorbeeld Response:
```json
[
  {
    "id": 1,
    "name": "Remvloeistof vervangen",
    "price": 11.99
  },
  {
    "id": 2,
    "name": "Remvloeistof Motul 100948",
    "price": 10.00
  },
  {
    "id": 3,
    "name": "Lamp rechtsvoor vervangen",
    "price": 5.30
  },
  {
    "id": 4,
    "name": "Lamp 5v",
    "price": 1.99
  },
  {
    "id": 5,
    "name": "Luchtfilter Bosch volvo",
    "price": 8.82
  },
  {
    "id": 6,
    "name": "Carburateur vervangen",
    "price": 160.25
  },
  {
    "id": 7,
    "name": "Carburatuer Type 24mm",
    "price": 45.45
  },
  {
    "id": 8,
    "name": "Koppakking vervangen",
    "price": 230.25
  },
  {
    "id": 9,
    "name": "Koppakking Elring",
    "price": 64.74
  },
  {
    "id": 10,
    "name": "Remschijven vervangen",
    "price": 30.25
  },
  {
    "id": 11,
    "name": "Remschijven Bosch",
    "price": 20.25
  },
  {
    "id": 12,
    "name": "Vloeistoffen bijgevuld",
    "price": 15.99
  },
  {
    "id": 13,
    "name": "Luchtfilter Bosch volvo",
    "price": 8.82
  },
  {
    "id": 14,
    "name": "Carburateur vervangen",
    "price": 160.25
  },
  {
    "id": 15,
    "name": "Carburatuer Type 24mm",
    "price": 45.45
  },
  {
    "id": 16,
    "name": "Koppakking vervangen",
    "price": 230.25
  },
  {
    "id": 17,
    "name": "Koppakking Elring",
    "price": 64.74
  },
  {
    "id": 18,
    "name": "Remschijven vervangen",
    "price": 30.25
  },
  {
    "id": 19,
    "name": "Remschijven Bosch",
    "price": 20.25
  },
  {
    "id": 20,
    "name": "Vloeistoffen bijgevuld",
    "price": 15.99
  }
]
``` 
* POST /api/v1/items


Voorbeeld Request:
```json
{
  "name": "Olie verversen",
  "price": 12.99
}
```

* DELETE /api/v1/items/{id}
* GET /api/v1/items/{id}

Voorbeeld Response:
```json
{
  "id": 1,
  "name": "Remvloeistof vervangen",
  "price": 11.99
}
```
* PUT /api/v1/items/{id}

Voorbeeld Request:
```json
{
  "name": "Olie verversen + filter vervangen",
  "price": 13.99
}
```
* PATCH /api/v1/items/{id}

Voorbeeld Request:
```json
{
  "price": 15.99
}
```




