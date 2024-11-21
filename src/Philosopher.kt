data class Philosopher(
    val name: String,
    val index: Int
) {
    fun leftForkIndex(philosopherCount: Int): Int = (index + 1) % philosopherCount
    fun rightForkIndex(): Int = index
}
