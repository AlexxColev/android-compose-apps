class Car(var brand: String, var model: String, var year: Int, var color: String)

fun main(){
    val c1 = Car("Ford", "Mustang", 1969, "Red")
    val c2 = Car("BMW", "X5", 1999, "Black")
    val c3 = Car("Tesla", "Model S", 2020, "Blue")
println(c1.brand + " " + c1.model + " " + c1.year + " " + c1.color)
    println(c2.brand + " " + c2.model + " " + c2.year + " " + c2.color)
    println(c3.brand + " " + c3.model + " " + c3.year + " " + c3.color)
}
