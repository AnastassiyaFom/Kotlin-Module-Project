import java.util.Scanner

fun main(args: Array<String>) {
    MainMenu().start("главное меню")
}

interface Item {
    var name: String
}

data class Note(var dirrectory: String, override var name: String, var text: String) : Item {
    constructor(_dirrectory: String, _name: String) : this(_dirrectory, _name, "") {
        do {
            println("Введите текст заметки, для окончания ввода введите c новой строки последовательность '***'")
            var line = ""
            do {
                line = Scanner(System.`in`).nextLine()
                if (line != "***") text += line + "\n"
            } while (line != "***")
            if (text == ""||text == "\n") println("Заметку без текста создать нельзя")
        } while (text == "" || text == "\n")
    }

    override fun toString(): String {
        return "Заметка: $name\nРасположена в архиве: $dirrectory\n\nТекст заметки:\n\n$text"
    }
}

data class Archive(override var name: String) : Item {
    var notes: MutableList<Note> = mutableListOf()
    override fun toString(): String {
        return "Архив: $name\n\nСодержит заметки:\n$notes"
    }

    fun notesInArchive(): MutableList<String> {
        var arr: MutableList<String> = mutableListOf()
        this.notes.forEach { arr.add(it.name) }
        return arr
    }
}
