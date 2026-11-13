fun main(){
    val x = 88
    val y = 77
    println(x <= y)
    println(x == 88)
    println(x == 77)
    println(x != 88 || y == 77)
    if (x > y){
        println("x is greater then y")
    }
    else{
        println("x is lesser then y")
    }
    val time = 22
    if (time < 12){
        println("Good morning")
    } else if (time < 17) {
        println("Good day")
    } else{
        println("Good evening")
    }
}