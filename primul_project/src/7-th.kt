fun main(){
    var txt = "Hello World it's me"
    println(txt[3])
    println(txt[12])
    println(txt[16]) //space
    println(txt[17])
    println(txt[18])
    var abc = "QWERTYUIOPASDFGHJKLZXCVBNM"
    println("The length of the txt string is:" + abc.length)
    var smth1 = "Hello my name is Alex"
    var smth2 = "Hello my name is Alex"
    var smth3 = "Hello my name is ALex"
    println(smth1.uppercase()) // all letters will be big
    println(smth1.lowercase()) // all letter will be short
    println(smth1.compareTo(smth2))
    println(smth1.compareTo(smth3))
    var text = "Please locate where 'where' occurs!"
    println(text.indexOf("where"))
}