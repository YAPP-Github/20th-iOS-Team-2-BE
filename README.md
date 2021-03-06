# 20th-iOS-Team-2-BE
> [20th] iOS 2ν BackEnd  
> README last modified 220529
 
## π¨βπ©βπ§βπ¦ SOFA: μ°λ¦¬ κ°μ‘±λ§μ SNS π¨βπ©βπ§βπ¦
Java 11  
Spring Boot  
Spring WebFlux  
multi-module with gradle  
CDN  
MySQL  
MongoDB  
docker / docker-compose

## how to run
1. clone repository  
2. set env
3. ```gradle build``` (need fast, use --exclude-task test)
4. run ```java -jar``` or ```run docker``` with PORT 8080

## Code Conventions

**yapp ios 2 backend team Java 11 code convention**  
[yapp_backend_java_convention_v1.xml.zip](https://github.com/YAPP-Github/20th-iOS-Team-2-BE/files/8637600/yapp_backend_java_convention_v1.xml.zip)

### naming

**method**  
default : Camel Case  
1. (λμ¬)_(λͺ©μ μ΄)
2. (λμ¬)\_(λͺ©μ μ΄)\_(μ μΉμ¬)_(λͺ©μ μ΄)

> ex)  
> createFactory()
> searchFactoryBySorting()

**repository method**  
Spring Data JPA μ νμ€ λ€μ΄λ°μ μ°Έκ³ νλ€.  
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation


**test method**  

νμ€νΈ λͺμΈ  
https://www.notion.so/daehwan2yo/Test-Convention-89cc1b5722f84acea9654411aa7c1daf

- λ¨μ νμ€νΈ

- ν΅ν© νμ€νΈ

## Work Conventions

### branch
> λΈλμΉ μ λ΅μ μκ°ν©λλ€.  

κΈ°λ³Έμ μΌλ‘ develop λΈλμΉλ₯Ό ν΅ν΄ κ°λ°μ΄ μ΄λ£¨μ΄μ§λλ€.  
``develop`` νμλ‘λ ```feature```, ```fix```, ```test```, ```refactor```, ```chore``` λΈλμΉλ₯Ό μμ±ν΄ κ°μ μμμ μνν©λλ€.  
1. ``develop`` νμ λΈλμΉμμ μμ μ€μ develop branch κ° renew λλ€λ©΄, rebase λ₯Ό ν΅ν΄ conflictλ₯Ό λ°©μ§ν©λλ€.
2. ``develop`` μμ κ°λ°μ΄ μλ£λκ³  client serverμ νμ€νΈ μλ² μ κ³΅μμλ ``staging`` μ ν΅ν΄ μΈμ€ν΄μ€μμ μλ²λ₯Ό κ΅¬λν©λλ€.
3. λ²μ μ λν λ°°ν¬ μ€λΉκ° μλ£λλ©΄, ``release`` λ₯Ό ν΅ν΄ μ¬μ©μμκ² λ°°ν¬λ₯Ό ν©λλ€.
![yapp-ios2-backend-branch](https://user-images.githubusercontent.com/26921986/167069092-c659467d-68ad-4f07-b87c-da244e621cba.png)


### task
> μμ μ λ΅μ μκ°ν©λλ€.

backend νμ λ§€μ£Ό μ€νλ¦°νΈ λ°©μμΌλ‘ κ°λ°μ μνν©λλ€.  
1. github project μ issue λ₯Ό λ°νμΌλ‘ λ§€μ£Ό ν΄μΌν  task λ₯Ό μλ λ±λ‘ν©λλ€.
2. issue λ΄μλ sub task λ₯Ό λ°°μΉνμ¬ μμμ μνν©λλ€.
3. μμμ΄ μλ£λκ³  ``develop``μΌλ‘ μ¬λ¦΄ μ€λΉκ° λμλ€λ©΄ review λ₯Ό ν΅ν΄ μ κ²μ μννκ³  PRμ μ§νν©λλ€.


## Architecture
![sofa_infra](https://user-images.githubusercontent.com/15176192/169679477-d35a0026-7779-4c61-904a-8466e17c96a8.png)


## ER Diagram
![sofa_erd_v1](https://user-images.githubusercontent.com/15176192/169679439-9caff376-7264-45c1-bf74-2a4ca982d6bb.png)
