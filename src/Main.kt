fun main() {
    println("Укажите числом количество философов за круглым столом, указав целое положительное число:")
    var philosopherCount: Int?

    while (true) {
        print("Введите количество философов: ")
        philosopherCount = readLine()?.toIntOrNull()

        if (philosopherCount == null || philosopherCount < 1) {
            println("Ошибка: Количество философов должно быть больше нуля. Попробуйте снова.")
            continue
        }
        break
    }

    val philosophers = mutableListOf<Philosopher>()
    for (i in 1..philosopherCount) {
        print("Имя философа номер $i - ")
        val name = readLine()?.ifBlank { "$i".padStart(2, '0') } ?: "$i".padStart(2, '0')
        philosophers.add(Philosopher(name, i - 1))
    }

    val forks = MutableList(philosopherCount) { Fork(it) }

    val randomOrder = philosophers.toMutableList()

    while (randomOrder.isNotEmpty()) {
        randomOrder.shuffle()

        val philosopher = randomOrder.removeFirst()
        val leftFork = forks[philosopher.leftForkIndex(philosopherCount)]
        val rightFork = forks[philosopher.rightForkIndex()]

        val forksOrder = if ((0..1).random() == 0) listOf(leftFork to "слева", rightFork to "справа")
        else listOf(rightFork to "справа", leftFork to "слева")

        val fork = forksOrder.firstOrNull { !it.first.isTaken }

        if (fork != null) {
            fork.first.isTaken = true
            println("${philosopher.name} взял вилку ${fork.second} и обедает")
        } else {
            println("${philosopher.name} размышляет")
        }
    }
}