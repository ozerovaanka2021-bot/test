
# test

Тестовый проект по автоматизации API и UI для интернет-магазина.

## Модули

- **api-test** — тесты REST API (регистрация, логин, оплата, корзина)
- **ui-test** — тесты пользовательского интерфейса на Selenide
- **common / shared** — общие утилиты, пейлоады, конфиги

## Предварительные требования

- JDK 17+
- Git
- Локальный файл с секретами: `~/.test-secrets.properties`

## Запуск локально

```bash
# Все тесты
sh gradlew test

# Только API-тесты
sh gradlew :api-test:test

# Только UI-тесты (нужен запущенный Chrome)
sh gradlew :ui-test:test

# Конкретный класс
sh gradlew :api-test:test --tests "comsockstests.UsersTest"
CI
Проект запускается в Jenkins: каждый пуш в main запускает полный прогон тестов,
результаты публикуются в Allure. Локальные секреты в CI подставляются из
credentials плагина.
