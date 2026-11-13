fun myownFunction(){
    println("It's my own function")
}
fun mystringFunction(fname: String){
    if (fname == "Marinka"){
        println(fname + " Coleva")
    } else {
    println(fname + " Colev") }
}
fun myFunction(name: String, age: Int){
    println(name + " is " + age)
}
fun sumFunction(x: Int, y: Int): Int = x - y

fun main() {
  var result = sumFunction(77, 23)
    println(result)
}