TRUNCATE TABLE AUTHOR;
ALTER SEQUENCE AUTHOR_ID_SEQ RESTART;

INSERT INTO AUTHOR (name, surname) values ('Стивен', 'Кинг');
INSERT INTO AUTHOR (name, surname) values ('Себастьян', 'Фитцек');
INSERT INTO AUTHOR (name, surname) values ('Артур Конан', 'Дойл');
INSERT INTO AUTHOR (name, surname) values ('Евгений', 'Замятин');
INSERT INTO AUTHOR (name, surname) values ('Лия', 'Арден');
INSERT INTO AUTHOR (name, surname) values ('Фрэнк', 'Герберт');
INSERT INTO AUTHOR (name, surname) values ( 'Дмитрий', 'Глуховский');
INSERT INTO AUTHOR (name, surname) values ('Джоджо', 'Мойес');
INSERT INTO AUTHOR (name, surname) values ('Дэвид', 'Робертс');
INSERT INTO AUTHOR (name, surname) values ('Александр', 'Дюма');
INSERT INTO AUTHOR (name, surname) values ('Дмитрий', 'Клюев');

TRUNCATE TABLE GENRE;
ALTER SEQUENCE GENRE_ID_SEQ RESTART;

INSERT INTO GENRE (name) values ('Триллер');
INSERT INTO GENRE (name) values ('Детектив');
INSERT INTO GENRE (name) values ('Фантастика');
INSERT INTO GENRE (name) values ('Роман');
INSERT INTO GENRE (name) values ('Приключение');

TRUNCATE TABLE BOOK;
ALTER SEQUENCE BOOK_ID_SEQ RESTART;

INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('11/22/63', 1, 928, 2021, 1, 1);
INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('Пациент особой клиники.', 2, 351, 2021, 2, 2);
INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('Клатбище домашних жывотных', 3, 480, 2016, 1, 1);
INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('Весь Шерлок Холмс', 4, 1392, 2021, 3, 1);
INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('Зеленая миля', 5, 384, 2012, 1, 1);
INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('Мы', 6, 288, 2021, 4, 3);
INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('Мара и Морок. 500 лет назад', 7, 448, 2022, 5, 3);
INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('Дюна', 8, 704, 2021, 6, 3);
INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('Метро 2033', 9, 384, 2021, 7, 3);
INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('До встречи с тобой', 10, 480, 2022, 8, 4);
INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('Всё та же я', 11, 576, 2021, 8, 4);
INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('Шантарам', 12, 1270, 2015, 9, 5);
INSERT INTO BOOK (name, description_id, page_volume, book_release_year, author_id, genre_id) values ('Граф Монте-Кристо', 13, 1264, 2021, 10, 5);

TRUNCATE TABLE DESCRIPTION;
ALTER SEQUENCE DESCRIPTION_ID_SEQ RESTART;

INSERT INTO DESCRIPTION (description) values ('...Убийство президента Кеннеди стало самым трагическим событием американской истории ХХ века. Тайна его до сих пор не раскрыта. Но что, если случится чудо? Если появится возможность отправиться в прошлое и предотвратить катастрофу? Это предстоит выяснить обычному учителю из маленького городка Джейку Эппингу, получившему доступ к временному порталу. Его цель — спасти Кеннеди. Но какова будет цена спасения?');
INSERT INTO DESCRIPTION (description) values ('У Тилля Беркхоффа бесследно пропадает сын Макс. Все улики свидетельствуют о том, что мальчик стал жертвой маньяка Гвидо Трамница. Преступник арестован полицией и признался в похищении и убийстве нескольких маленьких детей. Место их захоронения серийный убийца указал, однако о судьбе Макса хранит упорное молчание. Суд признает садиста невменяемым и отправляет его в строго охраняемую психиатрическую лечебницу. Следствию известно о том, что психопат вел дневник, где описывал все, что сделал с жертвами, но найти записи не удалось. Отчаявшийся отец обращается за содействием к знакомому сотруднику полиции, и тот делает ему невероятное предложение: стать пациентом этой особой клиники. Так он сможет оказаться ближе к детоубийце и заставить его признаться. Того, чем обернется эта отчаянная попытка, не ожидал никто.');
INSERT INTO DESCRIPTION (description) values ('Казалось бы, семейство Крид — это настоящее воплощение "американской мечты": отец — преуспевающий врач, красавица мать, прелестные дети. Для полной идиллии им не хватает лишь большого старинного дома, куда они вскоре и переезжают. Но идиллия вдруг стала превращаться в кошмар. Потому что в окружающих их новое жилище вековых лесах скрывается НЕЧТО, более ужасное, чем сама смерть и… более могущественное.');
INSERT INTO DESCRIPTION (description) values ('Артур Конан Дойл (1859–1930) — английский писатель, отдавший дань практически всем литературным жанрам, но наиболее известный как автор детективных, историко‑приключенческих и фантастических произведений. И, конечно же, как создатель знаменитого тандема — Шерлока Холмса и доктора Уотсона. Книги о гениальном сыщике и его простоватом напарнике переведены практически на все языки мира, Холмс и Уотсон стали героями бесчисленных литературных подражаний, экранизаций и театральных постановок.');
INSERT INTO DESCRIPTION (description) values ('Стивен Кинг приглашает читателей в жуткий мир тюремного блока смертников, откуда уходят, чтобы не вернуться, приоткрывает дверь последнего пристанища тех, кто преступил не только человеческий, но и Божий закон. По эту сторону электрического стула нет более смертоносного местечка! Ничто из того, что вы читали раньше, не сравнится с самым дерзким из ужасных опытов Стивена Кинга - с историей, что начинается на Дороге Смерти и уходит в глубины самых чудовищных тайн человеческой души...');
INSERT INTO DESCRIPTION (description) values ('Герой романа «Мы» — математик и инженер — живет в гигантском городе-государстве, отделенном стеной от внешнего мира. Ему кажется, что все здесь устроено правильно и разумно: люди вместо имен имеют номера, носят одинаковую одежду, их жизнь строго регламентирована. Но постепенно герой начинает понимать, что в этом идеально устроенном обществе нет места свободе и любви. Роман «Мы», написанный в 1921 году, считался сатирой на коммунистический строй; лишь в 1988 году российские читатели получили возможность познакомиться с главным произведением Евгения Замятина.');
INSERT INTO DESCRIPTION (description) values ('Долгожданная предыстория дилогии «Мара и Морок». Суммарный тираж трилогии о Марах и Мороках превышает 200 000 экземпляров! В прежние времена герои сказок были реальны и встречались смертным едва ли не каждый день. Я много путешествовал, собирая легенды и слухи о Марах и Мороках. Однажды я наткнулся на историю одной Мары, которая жила приблизительно за три сотни лет до исчезновения всех служительниц богини Смерти Мораны. Её настоящее имя намеренно скрывали, и я уверен, что эта история вышла особенной, потому что семья девочки не смогла выполнить главное правило – покинуть родные места.');
INSERT INTO DESCRIPTION (description) values ('Роман «Дюна», первая книга прославленной саги, знакомит читателя с Арракисом — миром суровых пустынь, исполинских песчаных червей, отважных фрименов и таинственной специи. Безграничная фантазия автора создала яркую, почти осязаемую вселенную, в которой есть враждующие Великие Дома, могущественная Космическая Гильдия, загадочный Орден Бинэ Гессерит и неуловимые ассасины.');
INSERT INTO DESCRIPTION (description) values ('2033 год. Весь мир лежит в руинах. Человечество почти полностью уничтожено. Москва превратилась в город‑призрак, отравленный радиацией и населённый чудовищами. Немногие выжившие люди прячутся в московском метро — самом большом противоатомном бомбоубежище на земле. Его станции превратились в города‑государства, а в туннелях царит тьма и обитает ужас. Артему, жителю ВДНХ, предстоит пройти через все метро, чтобы спасти от страшной опасности свою станцию, а может быть, и всё человечество. ');
INSERT INTO DESCRIPTION (description) values ('Лу Кларк знает, сколько шагов от автобусной остановки до ее дома. Она знает, что ей очень нравится работа в кафе и что, скорее всего, она не любит своего бойфренда Патрика. Но Лу не знает, что вот-вот потеряет свою работу и что в ближайшем будущем ей понадобятся все силы, чтобы преодолеть свалившиеся на нее проблемы. Уилл Трейнор знает, что сбивший его мотоциклист отнял у него желание жить. И он точно знает, что надо сделать, чтобы положить конец всему этому. Но он не знает, что Лу скоро ворвется в его мир буйством красок. И они оба не знают, что навсегда изменят жизнь друг друга.');
INSERT INTO DESCRIPTION (description) values ('Луиза Кларк приезжает в Нью-Йорк, готовая начать новую жизнь. И попадает в другой мир, в чужой дом, полный секретов. Радужные мечты разбиваются о жестокую реальность, но Луиза со свойственным ей чувством юмора не унывает. Она твердо знает, что рано или поздно найдет способ обрести себя. А еще обязательно получит ответ на вопрос: кого же она на самом деле любит?..');
INSERT INTO DESCRIPTION (description) values ('Представляем читателю один из самых поразительных романов начала XXI века (в 2015 году получивший долгожданное продолжение – «Тень горы»). Эта преломленная в художественной форме исповедь человека, который сумел выбраться из бездны и уцелеть, разошлась по миру тиражом четыре миллиона экземпляров (из них полмиллиона – в России) и заслужила восторженные сравнения с произведениями лучших писателей Нового времени, от Мелвилла до Хемингуэя. Подобно автору, герой этого романа много лет скрывался от закона. Лишенный после развода с женой родительских прав, он пристрастился к наркотикам, совершил ряд ограблений и был приговорен австралийским судом к девятнадцати годам заключения. Бежав на второй год из тюрьмы строгого режима, он добрался до Бомбея, где был фальшивомонетчиком и контрабандистом, торговал оружием и участвовал в разборках индийской мафии, а также нашел свою настоящую любовь, чтобы вновь потерять ее, чтобы снова найти…');
INSERT INTO DESCRIPTION (description) values ('Граф Монте-Кристо – приключенческий роман Александра Дюма, классика французской литературы, написанный в 1844—1845 годах. Имя своему герою писатель придумал во время путешествия по Средиземному морю, когда он увидел остров Монтекристо и услышал легенду о зарытых там несметных сокровищах. Автор всего лишь немного изменил название острова. Увлекательную историю любви и верности, коварства и предательства прочел для вас заслуженный актер России Алексей Алексеевич Борзунов.');

TRUNCATE TABLE COMMENT;
ALTER SEQUENCE COMMENT_ID_SEQ RESTART;

INSERT INTO COMMENT (book_id, comment) values (1, 'Одна из лучших книг Кинга. Читать!');
INSERT INTO COMMENT (book_id, comment) values (1, 'Как и все книги серии разочаровало золотое тиснение на обложке, которое к концу книги практически исчезло, так что за это большой минус оформлению.');
INSERT INTO COMMENT (book_id, comment) values (1, 'Безумно захватывающая книга!');
INSERT INTO COMMENT (book_id, comment) values (1, 'Это самое лучшее произведение Стивена Кинга! Хотя у Кинга все произведения шикарные. Вообщем перечитывал данное произведение ни раз,и всегда открывал для себе что-то новое и неизведанное! Очень шикарное произведение,советую!!!');