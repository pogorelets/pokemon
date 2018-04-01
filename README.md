# pokemon
test application for show pokemons
Реализовать приложение состоящее из 2 экранов

Экран списка покемонов.
Карточка покемона должна содержать картинку (спрайт) и имя покемона.

Список можно переключать между двумя режимами:

Режим Discover - покемоны запрашиваются через REST API https://pokeapi.co/
Режим Pokedex - покемоны запрашиваются из таблицы в локальной базе данных.

Экран детального описания покемона.

По нажатию на покемона из списка открывается экран деталей покемона.
Детальная информация содержит иконку sprite, name и списки stats и ability.
На экране есть кнопка "Сохранить", по нажатию покемон сохраняется в базе данных и отображается в списке на первом экране Pokedex.
Если покемон уже сохранен кнопка сменяется на "Удалить".

Требования

Для списка обязательно используйте RecyclerView.
В качестве базы данных используйте SQLite.

Рекомендации

Не тратьте время на дизайн.
Используйте любые библиотеки или Фреймворки на свое усмотрение.
Будет плюсом использование RxJava.
Будет плюсом использование Dependency Injection.
Будет плюсом использование любого MV* паттерна или CleanArchetecture.
![Главный экран](https://github.com/pogorelets/pokemon/blob/master/pokrmon1.png) 
![Главный экран](https://github.com/pogorelets/pokemon/blob/master/pokrmon2.png)
![Главный экран](https://github.com/pogorelets/pokemon/blob/master/pokrmon3.png)
![Экран детализации](https://github.com/pogorelets/pokemon/blob/master/pokrmon4.png)
![Экран детализации](https://github.com/pogorelets/pokemon/blob/master/pokrmon5.png) 
