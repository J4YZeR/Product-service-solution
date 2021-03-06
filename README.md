# Products service
Сервис по хранению и доступу к продуктам и их ценам
## Описание решения
Все функции были реализованы. Используемую конфигурацию можно увидеть в файле `pom.xml`
## Api  
[Документация](docs/api-docs.yaml) для api была сгенерированна с помощью Swagger и следует OpenAPI
v3 спецификации. Можно использовать Swagger UI для изучения api. 
Например, api можно изучить с помощью [Swagger editor](https://editor.swagger.io/),
поместив в него файл документации.
## Csv
Csv считывается в формате, описанном в ТЗ.  
Пример корректного csv:   
```text
product_id;product_name;price_id;price;price_datetime
1;item1;2;33;2022-08-22
1;item1;3;33;2022-08-22
2;item2;4;33;2022-08-22
```
Конфигурация для считывания данных из csv описана в секции [Конфигурация/csv](#csv-1)
## Конфигурация
### База данных
В файле `application.properties` должны быть указаны параметры для подключения к Postgres БД.
Пример параметров содержится в исходном `application.properties`.  
Также, в `application.properties` содержатся следующие параметры:
```
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create-drop
```
Эти параметры реализуют поведение сброса всех таблиц в базе данных и автоматического создания новых таблиц
на основе Hibernate сущностей.
### Csv
Для настройки поведения считывания csv файла необходимо указать значения
следующих параметров в `application.properties` файле
* `custom.csv-loader.path`
  * Путь к csv файлу  
  * Пример: `products.csv`  
* `custom.csv-loader.initial-read-date`
  * Дата, начиная с которой файл будет периодически считываться из указанного пути
  * Формат: `yyyy-MM-dd HH:mm:ss`  
* `custom.csv-loader.read-rate`
  * Период (в миллисекундах), с которым файл считывается по указанному пути.
  * Пример: `10000`  
