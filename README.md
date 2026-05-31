## 🧪 DemoQA Student Registration Form - Automated Tests

## 📋 О проекте

Автоматизированные тесты для формы регистрации студентов на сайте **[DemoQA Practice Form](https://demoqa.com/automation-practice-form)**.

![Форма Регистрации](screenshots/studentForm.png)

Проект демонстрирует подходы к тестированию веб-формы с использованием **Selenide** и **JUnit 5**, включая позитивные и негативные сценарии, валидацию полей и проверку модальных окон.

---

## 🎯 Тестируемый функционал

| Сценарий | Описание |
|----------|----------|
| ✅ **Полное заполнение формы** | Все поля формы (обязательные и опциональные) |
| ✅ **Только обязательные поля** | Минимальный набор данных для успешной регистрации |
| ✅ **Пустая форма** | Проверка валидации при отправке незаполненной формы |
| ✅ **Валидация телефона** | Проверка ограничения на количество символов (10 цифр) |
| ✅ **Радиобатоны Gender** | Взаимоисключающий выбор пола |
| ✅ **Закрытие модального окна** | Проверка исчезновения окна после нажатия на кнопку закрытия |

---

## 🛠 Технологический стек

| Технология    | Версия             | Назначение |
|---------------|--------------------|-------------|
| **Java**      | 17                 | Язык программирования |
| **Selenide**  | 7.7.0              | Фреймворк для UI тестирования |
| **JUnit 5**   | 5.10.0             | Тестовый фреймворк |
| **Gradle**    | 8.10               | Система сборки |
| **Окружение** | macOS Sequoia 15.2 |  |
| **Браузер**  | Chrome 148.0.7778.216 |  |

---

## 📊 Результаты тестирования

### Общая статистика

```
✅ PASSED: 5                       
❌ FAILED: 1                      
📊 Total: 6                        
📈 Success Rate: 83.3%            
⏱ Total Time: 21.655 sec            
```

### Детальные результаты

| # | Тест-кейс | Статус | Время | Описание |
|---|-----------|--------|-------|----------|
| 1 | Заполнение всех полей формы | ✅ PASSED | ~3.5s | Полная регистрация со всеми данными |
| 2 | Заполнение только обязательных полей | ✅ PASSED | ~3.2s | Минимальный набор данных |
| 3 | Отправка пустой формы | ✅ PASSED | ~4.1s | Проверка валидации обязательных полей |
| 4 | Ввод недопустимого количества символов в поле 'Mobile' | ✅ PASSED | ~3.8s | 11 символов → обрезается до 10 |
| 5 | В группе радиобатонов 'Gender' можно выбрать только один вариант | ✅ PASSED | ~2.5s | Взаимоисключающий выбор |
| 6 | Модальное окно исчезает после нажатия на кнопку закрытия | ❌ FAILED | ~4.5s | Ожидание/проверка закрытия |

---

## 🐛 Анализ падающего теста

### Тест: `Модальное окно исчезает после нажатия на кнопку закрытия`

**Ожидаемое поведение:**
- Модальное окно появляется после успешной отправки формы
- После клика на кнопку закрытия (`#closeLargeModal`) окно исчезает

**Возможные причины падения:**
- Кнопка закрытия не кликабельна в момент выполнения



---

## 🚀 Запуск тестов

### Локальный запуск

```bash
# Клонирование репозитория
git clone https://github.com/RenataFatykhov/java_demoqa_hw.git

# Переход в директорию
cd java_demoqa_hw

# Запуск всех тестов
./gradlew clean test

# Запуск конкретного теста
./gradlew test --tests StudentRegistrationFormTests.successfulStudentRegistrationFormTest

# Запуск с детальным логированием
./gradlew test --info
```

## 📁 Структура проекта
```
java_demoqa_hw/
├── src/
│   └── test/
│       └── java/
│           ├── StudentRegistrationFormTests.java   # Основной тестовый класс
│           └── TestBase.java                       # Базовый класс с настройками
├── build/
│   └── reports/
│       └── tests/
│           └── test/
│               └── index.html                       # HTML отчет о тестах
├── build.gradle                                     # Конфигурация Gradle
└── README.md                                        # Документация
```

## 💡 Примеры тестов

### Позитивный тест (полное заполнение)

```java
@Test
@DisplayName("Заполнение всех полей формы")
public void successfulStudentRegistrationFormTest() {
    // Personal Information
    $("[id=firstName]").setValue("Renata");
    $("[id=lastName]").setValue("Fatykhova");
    $("[id=userEmail]").setValue("ren.fatykhova@gmail.com");
    $("[id=gender-radio-2]").click();
    $("[id=userNumber]").setValue("1234567890");
    
    // Date of Birth
    $("[id=dateOfBirthInput]").click();
    $(".react-datepicker__month-select").selectOption("November");
    $(".react-datepicker__year-select").selectOption("1997");
    $(".react-datepicker__day--004").click();
    
    // Submit & Verify
    $("button[id=submit]").scrollTo().click();
    $(".modal-content").shouldBe(visible);
    $("[id=example-modal-sizes-title-lg]")
        .shouldHave(text("Thanks for submitting the form"));
}
```


### Негативный тест (пустая форма)

```java
@Test
@DisplayName("Отправка пустой формы")
public void emptyStudentRegistrationFormTest() {
    $("button[id=submit]").scrollTo().click();
    
    // Модальное окно НЕ появляется
    $(".modal-content").shouldBe(not(visible));
    
    // Обязательные поля подсвечиваются красным
    $("[id=firstName]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    $("[id=lastName]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    $("[id=userNumber]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
}
```
## ⏱ Время выполнения тестов

| # | Тест | Время | График |
|---|------|-------|--------|
| 1 | Заполнение всех полей | 3.5s | ████████░░░░░░░░░░ |
| 2 | Только обязательные поля | 3.2s | ███████░░░░░░░░░░ |
| 3 | Отправка пустой формы | 4.1s | ██████████░░░░░░░░ |
| 4 | Валидация телефона | 3.8s | █████████░░░░░░░░ |
| 5 | Радиобатоны Gender | 2.5s | ██████░░░░░░░░░░ |
| 6 | Закрытие модального окна | 4.5s | ███████████░░░░░░ |

**Общее время: 21.7 секунды**



## 👥 Автор

**Renata Fatykhova**  
QA Automation Engineer