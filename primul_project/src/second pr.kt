fun birthdayGreeting(name: String, age: Int): String {
    return ("Happy Birthday, $name, You are now, $age years old")
}
fun main() {
    println(birthdayGreeting("Rover", 5))
    println(birthdayGreeting("Rex", 2))
}