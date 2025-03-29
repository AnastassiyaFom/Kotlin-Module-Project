
class MainMenu : Menu(
    mutableListOf(
        "Создание архива.",
    ), true
) {
    private var archives: MutableList<Archive> = mutableListOf()

    override fun start(menuName: String) {
        archives.forEach {
            points.add(it.name)
        }
        super.start(menuName)
    }

    override fun realiseUChoice(choice: Int) {
        when (choice) {
            1 -> itemCreation(archives)
            in 2..points.size -> ArchiveView(archives[choice - 2]).start("меню архива ${archives[choice - 2].name}")
            0 -> return
            else -> println("Неизвестная команда")
        }
    }

    override fun <T> itemCreation(items: MutableList<T>): String where T : Item {
        val name = super.itemCreation(archives)

        if (name != "" && archives.add(Archive(name))) {
            println("Архив $name успешно создан")
            points.add(name)

        } else if (name != "") {
            println("Не удалось создать архив $name")
        }
        returnBack("главное меню")
        return name
    }


}