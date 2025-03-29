import java.util.Scanner

abstract class Menu(var points: MutableList<String>, var isMain: Boolean) {

    private var userChoice: Int = 0

    open fun start(menuName: String) {
        do {
            println("\nМы находимся в $menuName\n")
            printMenu()
            inputMenuPoint()
            realiseUChoice(userChoice)
        } while (userChoice != 0)

    }

    abstract fun realiseUChoice(choice: Int)

    private fun printMenu() {
        println("Вы можете выполнить одно из действий:")
        for (index in 0..points.size - 1) {
            println("${index + 1}. ${points[index]}")
        }
        if (isMain) println("0. Выход из программы")
        else println("0. Возврат в предыдущее меню")
    }

    private fun inputMenuPoint() {
        //var userChoice: Int = 0
        while (true) {
            println("Введите номер, соответствующий Вашему выбору:")
            var input = Scanner(System.`in`).nextLine()
            try {
                userChoice = input.toInt()
            } catch (e: Exception) {
                println("\nВы ввели не целое число!!! повторите ввод!!!\n")
                continue
            }
            if ((userChoice >= 0) && (userChoice <= points.size)) break
            else println("\nТакого пункта в меню нет, повторите ввод!!!\n")
        }
        //return userChoice

    }

    open fun returnBack(menuName: String) {

        println("Для возврата в $menuName введите любой символ")
        Scanner(System.`in`).nextLine()
    }


    open fun <T> itemCreation(items: MutableList<T>): String where T : Item {
        println("Давайте создадим новый элемент:)\nВведите имя: ")
        var name: String
        do {
            name = Scanner(System.`in`).nextLine()
            if (name == "") println("Вы забыли ввести имя, попробуйте снова: ")
        } while (name == "")

        items.forEach {
            if (it.name == name) {
                println("Элемент c именем $name уже существует")
                return ""
            }
        }
        return name
    }

}