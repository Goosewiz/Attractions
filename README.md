Необходимо реализовать REST API путеводителя по городу.
В БД сервиса хранятся достопримечательности городов с указанными названием, категорией и географическими координатами. Также для каждой достопримечательности пользователи могут написать свой отзыв, а также выставить свою оценку от 1 до 5.
У пользователя должна быть возможность:
Получить список достопримечательностей, находящихся в указанном радиусе от него (координаты пользователя передаются в запросе) с возможностью их фильтрации по категории и(или) средней оценке. Максимальное количество предлагаемых достопримечательностей и параметр, по которым они должны быть отсортированы также может быть указан (по умолчанию - 10 достопримечательностей, отсортированных по близости к пользователю)
Получить список достопримечательностей в указанном городе (возможности фильтрации и сортировки должны быть такими же, как и в пункте 1 )
Выставить оценку достопримечательности
Написать отзыв о посещении достопримечательности
Посмотреть информацию и среднюю оценку по конкретной достопримечательности
Посмотреть отзывы о конкретной достопримечательности
Сервис должен быть написан на Spring или Spring Boot, код должен содержать unit-тесты, сервис должен собираться при помощи Maven или Gradle, код сервиса должен содержать скрипты развертывания БД (в идеале - скрипты Liquibase или Flyway).

# Attractions
БД реализовано так же, как и в прошлом проекте - запущена локально через Open server panel.
В самой программе не реализована сортировка данных, только фильтрация.
При выводе отзывов и оценки о конкретном месте я хотел схитрить и добавить название достопримечательности и среднюю оценку в один map вместе с текстами отзывов,
но при дублировании ключевых значений будут затираться отзывы, не придумал решение этой проблемы.
Unit-тесты функционала присутствуют.
