class Carr(var brand: String, var model: String, var year: Int) {
    // Class function
    fun drive() {
        println("Wrooom!")
    }
    fun speed(maxSpeed: Int){
        println("Max speed = " + maxSpeed)
    }
}

fun main() {
    val c1 = Carr("Ford", "Mustang", 1969)

    // Call the function
    c1.drive()
    c1.speed(333)
}