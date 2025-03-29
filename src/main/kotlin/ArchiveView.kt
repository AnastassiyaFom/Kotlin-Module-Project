

class ArchiveView(var archive: Archive) : Menu (
    mutableListOf(
        "Создание заметки.",
    ), false
) {
    // var notes: MutableList<Note> = mutableListOf()
    override fun start(menuName: String) {

        if (points.size == 1) println("В архиве ${archive.name} еще нет ни одной заметки\n")
        else println("Мы находимся в архиве ${archive.name} \n")
        points.addAll(archive.notesInArchive())
        super.start(menuName)
    }

    /// эту функцию вынести как общий функционал c помощью лямбд
    override fun realiseUChoice(choice: Int) {
        when (choice) {
            1 -> itemCreation(archive.notes)
            in 2..points.size -> viewNote(archive.notes[choice - 2])
            0 -> return
            else -> println("Неизвестная команда")
        }
    }


    override fun <T> itemCreation(items: MutableList<T>): String where T : Item {
        val name = super.itemCreation(archive.notes)
        if (name != "" && archive.notes.add(Note(archive.name, name))) {
            println("Заметка $name успешно создана")
            points.add(name)
        } else if (name != "") {
            println("Не удалось создать заметку $name")
        }
        returnBack("меню архва ${archive.name}")
        return name
    }


    fun viewNote(note: Note) {
        println("Смотрим заметку ${note.name}.\n")
        println("---*---*---*---*---*---\n")
        println("$note")
        println("---*---*---*---*---*---\n")
        returnBack("меню архива ${archive.name}")
    }

}