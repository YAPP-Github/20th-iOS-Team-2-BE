# 20th-iOS-Team-2-BE
> [20th] iOS 2팀 BackEnd  
> README last modified 220529
 
## 👨‍👩‍👧‍👦 SOFA: 우리 가족만의 SNS 👨‍👩‍👧‍👦
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
1. (동사)_(목적어)
2. (동사)\_(목적어)\_(전치사)_(목적어)

> ex)  
> createFactory()
> searchFactoryBySorting()

**repository method**  
Spring Data JPA 의 표준 네이밍을 참고한다.  
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation


**test method**  

테스트 명세  
https://www.notion.so/daehwan2yo/Test-Convention-89cc1b5722f84acea9654411aa7c1daf

- 단위 테스트

- 통합 테스트

## Work Conventions

### branch
> 브랜치 전략을 소개합니다.  

기본적으로 develop 브랜치를 통해 개발이 이루어집니다.  
``develop`` 하위로는 ```feature```, ```fix```, ```test```, ```refactor```, ```chore``` 브랜치를 생성해 각자 작업을 수행합니다.  
1. ``develop`` 하위 브랜치에서 작업 중에 develop branch 가 renew 된다면, rebase 를 통해 conflict를 방지합니다.
2. ``develop`` 에서 개발이 완료되고 client server에 테스트 서버 제공시에는 ``staging`` 을 통해 인스턴스에서 서버를 구동합니다.
3. 버전에 대한 배포 준비가 완료되면, ``release`` 를 통해 사용자에게 배포를 합니다.
![yapp-ios2-backend-branch](https://user-images.githubusercontent.com/26921986/167069092-c659467d-68ad-4f07-b87c-da244e621cba.png)


### task
> 작업 전략을 소개합니다.

backend 팀은 매주 스프린트 방식으로 개발을 수행합니다.  
1. github project 와 issue 를 바탕으로 매주 해야할 task 를 자동 등록합니다.
2. issue 내에는 sub task 를 배치하여 작업을 수행합니다.
3. 작업이 완료되고 ``develop``으로 올릴 준비가 되었다면 review 를 통해 점검을 수행하고 PR을 진행합니다.


## Architecture
![sofa_infra](https://user-images.githubusercontent.com/15176192/169679477-d35a0026-7779-4c61-904a-8466e17c96a8.png)


## ER Diagram
![sofa_erd_v1](https://user-images.githubusercontent.com/15176192/169679439-9caff376-7264-45c1-bf74-2a4ca982d6bb.png)
