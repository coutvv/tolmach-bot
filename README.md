Телеграм бот-переводчик, использует Яндекс-API для перевода

Для запуска необходимо прописать в соответствующие поля токен бота и ключик от апи яндекса в файл src/main/resouces/bot.properties, затем компилим мавеном получаем готовый жарник(tolmach-bot-jar-with-dependencies.jar). 
Вдобавок к этому нужно в конфиге hibernate'a(src/main/resources/hibernate.cfg.xml) указать свои данные  к бд(логин/пароль и адрес бд)

Запуск: 
java -Dfile.encoding=utf-8 -jar tolmach-bot-jar-with-dependencies.jar

Всё изи!
