fun main(){
    val cars = arrayOf("BMW", "Lada", "Dacia", "Mercedes", "Volvo", "Haval")
    cars[1] = "Opel"
    println(cars[1]) // out put BMW
    println(cars.size)
    if ("Haval" in cars) {
        println("The car is in the list")
    } else {
        println("The car is not in the list")
    }
    for (x in cars){
        println(x)
    }
}