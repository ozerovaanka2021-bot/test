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

# Только UI-тесты (Chrome или headless)
sh gradlew :ui-test:test

# Конкретный класс
sh gradlew :api-test:test --tests "comsockstests.UsersTest"
```

## CI

У проекта два раннера:

### GitLab CI

Пайплайн описан в `.gitlab-ci.yml` и стартует на каждый пуш в ветку:

1. `verify` — компиляция кода;
2. `test` — прогон тестов и публикация Allure-отчёта;
3. `gate` — финальный контроль качества.

### Jenkins

Пайплайн описан в `Jenkinsfile`: собирает проект, прогоняет
api-test и ui-test, публикует результаты в Allure.
Секреты (пароли, токены) передаются через credentials плагина,
в репозитории не хранятся.