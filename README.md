Presentation (UI/API)
Отвечает за взаимодействие с пользователем (например, консольное меню для ввода команд).

Application (Сервисный слой)
Инкапсулирует сценарии использования – получает команды от UI и вызывает методы бизнес-логики из слоя домена.

Domain (Бизнес-логика)
Содержит основные сущности и правила, например, классы Product (продукт) и Inventory (инвентарь). Здесь реализуются операции добавления продукта, списания, проведения инвентаризации, корректировки запасов и т.д.

Infrastructure (Хранение данных)
Отвечает за реализацию репозитория – например, можно создать реализацию для работы с in-memory данными (или подключением к СУБД).
