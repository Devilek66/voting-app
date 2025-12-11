# Voting App

**Application developed as part of a recruitment process.**

## Task Description
Przygotuj prostą aplikację w Spring Boot, która umożliwia rejestrację głosów w wielu wyborach.
Aplikacja powinna spełniać następujące wymagania:

Zarządzanie wyborcami

1. Dodawanie nowych wyborców
2. Blokowanie/odblokowywanie wyborców

Zarządzanie wyborami

1. Każde wybory to osobna instancja (np. „Wybory na Wójta w 2025”)
2. Możliwość definiowania opcji wyboru (np. kandydatów)

Głosowanie

1. Wyborca może oddać głos w danej instancji wyborów tylko raz
2. Każda instancja wyborów ma własne opcje głosowania

API REST

1. Aplikacja powinna wystawiać odpowiednie endpointy REST
2. Dane zapisywane w bazie danych (dowolnej relacyjnej, np. PostgreSQL)

Dodatkowe wskazówki:

1. Zastosuj dobre praktyki i wzorce projektowe (np. warstwowa architektura, DTO, walidacje).
2. Kod powinien być czytelny i dobrze zorganizowany.
3. Nie oczekujemy pełnej implementacji zabezpieczeń czy frontendu – skup się na logice i strukturze aplikacji. 

## Description
This application uses **Docker Compose**, so Docker must be installed on your machine to run it.

Once the application is running, it exposes a basic **OpenAPI/Swagger** interface at:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## Things to Add or Improve

### Election Dates
Currently, the application does not store the start and end dates of elections. In a full-scale version, this would be useful to restrict voting to a specific time window.

### Architecture
The current architecture is simple and layered. With significant growth in scale, it might be worth considering a **hexagonal architecture**, which would better separate layers and make development and testing easier.

### Scaling and Performance
For a larger number of users, possible improvements include:
- Converting the application to a **reactive approach** or using **virtual threads**.
- Adding a **Kafka setup** to collect events when votes are cast.

### Vote Anonymization
User anonymity for voting was not implemented, as it was not part of the requirements. In the future, one could, for example, **hash the user ID** at the time of voting to preserve privacy.

### Security and Authorization
When adding security, it would be natural to:
- Require **roles for specific endpoints**.
- Retrieve the user ID from a **JWT token**.

### Extending the User Entity
The current `User` entity is minimal. As the frontend evolves, it might be useful to add more fields needed for displaying user profiles or other frontend requirements.

### Extending Election Data
Election entities could also store:
- A brief **description** of the event.
- **Links to images** or other materials related to the election.

### Optional Enhancements
- **Server-Sent Events (SSE)**: If we wanted to track election results in real-time, SSE could be implemented. This would allow live updates, e.g., displaying results on dashboards or smart TVs.