# Тестовое задание IT Bootcamp

## Описание

Этот проект представляет собой REST API.
Проект представляет собой многомодульный maven проект, реализующий REST API. 
Реализованое логирование. Архитектурный стиль: REST. Вывод данных в формте JSON
Docker Compose для контейнеризации проекта.
Unit и Integration тесты.
Flyway для миграции базы данных.


Эндпоинты:
    добавление пользователя
    получения списка всех пользователей
    постраничный вывод списка пользователей по 10 записей

## Запуск

Установите корректные данные для подключения к базе данных в файле application.yaml (database/src/main/resources)
Запустите RestApplication в модуле rest
Структура таблиц базы данных и демо данные загружаются автоматически

Для запуска swagger
http://localhost:8080/swagger-ui/index.html


## Инструкция для запуска через Docker:

Установите корректные данные для подключения к базе данных в файле application.yaml (database/src/main/resources)

Заменить
flyway:
    enabled: true
    locations: classpath:db/migration
на:
flyway:
    enabled: false

Выполните команду
docker-compose up -d

## Добавление пользователя
POST /users

# Example
{
    "firstName": "Katerina",
    "surname": "Yaroshevich",
    "middleName": "Vladimirovna",
    "email": "katerina@gmail.com",
    "role": "ADMINISTRATOR"
}

# Пример ошибки
{
    "timeStamp": "2024-01-22T21:30:36.273+00:00",
    "status": 409,
    "message": "User email not unique: katerina@gmail.com"
}

## Получение списка всех пользователей
GET /users

# Пример ответа
[{
"fullName": "Beier Amely Greer",
"email": "Alyce80@hotmail.com",
"role": "SALE_USER"
},
...
,
{
"fullName": "Yaroshevich Katerina Vladimirovna",
"email": "katerina@gmail.com",
"role": "ADMINISTRATOR"
},
...]
