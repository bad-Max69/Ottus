package com.example.ottus.Model

import com.example.ottus.Model.Network.ResultsItem
import com.example.ottus.R
import com.example.ottus.RecyclerView.FilmsItem

object Films {

    val filmList = mutableListOf<FilmsItem>(
        FilmsItem( "Лига справедливости", "Вдохновившись самопожертвованием Супермена, Брюс Уэйн вновь обретает веру в человечество. Он заручается поддержкой новой союзницы Дианы Принс, чтобы сразиться с ещё более могущественным противником. Бэтмен и Чудо-женщина набирают команду сверхлюдей для борьбы с пробудившейся угрозой",
            R.drawable.liga
        ),
        FilmsItem("Мортал Комбат", "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии и почему император Внешнего Мира Шан Цзун посылает своего лучшего воина, могущественного криомансера Саб-Зиро, на охоту за Коулом. Янг боится за безопасность своей семьи, и майор спецназа Джакс, обладатель такой же отметки в виде дракона, как и у Коула, советует ему отправиться на поиски Сони Блейд. Вскоре он оказывается в храме Лорда Рейдена, Старшего Бога и защитника Земного Царства, который дает убежище тем, кто носит метку. Здесь Коул тренируется с опытными воинами Лю Каном, Кун Лао и наемником-изгоем Кано, готовясь вместе с величайшими чемпионами Земли противостоять врагам из Внешнего Мира в битве за вселенную. Но сможет ли Коул приложить все усилия и раскрыть в себе аркану — могущественную силу его души, чтобы не только спасти свою семью, но и остановить Внешний Мир раз и навсегда?",
            R.drawable.mk
        ),
        FilmsItem("Охотник на монстров", "Лейтенант Артемис и её солдаты с Земли случайно попадают в параллельный мир, где обитают разнообразные монстры. Закалённые бойцы оказываются в необычной ситуации и вынуждены использовать все свои навыки, чтобы пережить встречу с фантастическими тварями. Спасти их может только загадочный Охотник, который как никто умеет убивать монстров.",
            R.drawable.hunter
        ),
        FilmsItem("Скайлайн 3", "Через 15 лет после вторжения неизвестный вирус угрожает хрупкому сосуществованию дружественных инопланетных гибридов и людей. Родившаяся на космическом корабле мутант Роуз Корли сама испытывает трудности с управлением огневой рукой, когда на неё выходят члены сопротивления. Они предлагают девушке в составе хорошо подготовленного отряда, куда входит и её брат-инопланетянин Трент, отправиться на вражескую планету и захватить сердечник от разрушенного корабля, который поможет спасти человечество.",
            R.drawable.skyline3
        ),
        FilmsItem("Веном", "Что если в один прекрасный день в тебя вселяется существо-симбиот, которое наделяет тебя сверхчеловеческими способностями? Вот только Веном — симбиот совсем недобрый, и договориться с ним невозможно. Хотя нужно ли договариваться?.. Ведь в какой-то момент ты понимаешь, что быть плохим вовсе не так уж и плохо. Так даже веселее. В мире и так слишком много супергероев! Мы — Веном!",
            R.drawable.venomimg
        ),
        FilmsItem("Вторжение", "Падение инопланетного объекта разделило жизни на «до» и «после». Обычная девушка из московского Чертанова - Юлия Лебедева - вынуждена смириться с ролью подопытного кролика в лаборатории, ведь она единственная была в контакте с пришельцем. Ученые и военные разбирают на атомы её чувства, эмоции и переживания, пытаясь разгадать природу растущей в ней силы. Но страшнее всего, что её сверхъестественные способности волнуют не только землян. Над планетой в буквальном смысле нависла угроза вторжения. И победить в грядущем столкновении можно только одним способом: найти в себе силы остаться людьми. Когда каждый ради общего спасения должен сделать выбор, от которого зависит жизнь и судьба миллионов, - смогут ли любовь, верность и милосердие стать сильнее безжалостной силы и инопланетных технологий?",
            R.drawable.invasionimg
        ),
        FilmsItem("Андройд", "Симпатичная студентка Анна просыпается после вечеринки и обнаруживает на своем смартфоне загадочное приложение «Айрис»… Активируя приложение, Анна запускает головокружительную и трагическую череду событий в жизни своих друзей и знакомых. Кто за этим стоит?",
            R.drawable.androidimg
        ),
        FilmsItem("Мстители", "Пока Мстители и их союзники продолжают защищать мир от различных опасностей, с которыми не смог бы справиться один супергерой, новая угроза возникает из космоса: Танос. Межгалактический тиран преследует цель собрать все шесть Камней Бесконечности — артефакты невероятной силы, с помощью которых можно менять реальность по своему желанию. Всё, с чем Мстители сталкивались ранее, вело к этому моменту — судьба Земли никогда ещё не была столь неопределённой.",
            R.drawable.avengerswarimg
        )


    )

    var favoriteSet = mutableSetOf<ResultsItem>()

    var moviesTMDB: MutableList<ResultsItem?> = mutableListOf()


}