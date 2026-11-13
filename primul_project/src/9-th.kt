fun main(){
    var day = 5
    val result = when (day){
        1 -> "Monday"
        2 -> "Tuesday"
        3 -> "Wednesday"
        4 -> "Thursday"
        5 -> "Friday"
        6 -> "Saturday"
        7 -> "Sunday"
        else -> "Invalid day"
    }
    println(result)

    var i = 0
    while (i <   10) {
        println(i)
        i++
        if (i == 4){
            break
        }
    }
    var j = 0
    while (j < 10){
        if (j == 6) {
            j++
            continue
         }
        println(j)
        j++
    }
}