class Cars {
    var brand = " "
    var model = " "
    var year = 0
    var color = " "
}
fun main(){
    val c1 = Cars()
    c1.brand = "Volvo"
    c1.model = "XC90"
    c1.year = 2014
    c1.color = "Black"
    val c2 = Cars()
    c2.brand = "Toyota"
    c2.model = "Avensis"
    c2.year = 2005
    c2.color = "Grey"
    println(c1.brand + " " + c1.model + " " + c1.year + " " + c1.color)
    println(c2.brand + " " + c2.model + " " + c2.year + " " + c2.color)

}