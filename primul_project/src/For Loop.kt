fun main(){
    var nums = arrayOf(1, 5, 10, 25, 50)
    for (x in nums){
        println(x)
    }
    if (25 in nums) {
        println("This number exist")
    } else {
            println("This number does not exist")
        }

    for (chars in 'a'..'p'){
        println(chars)
    }
    for (nums in 5..15){
        if (nums == 5 || nums == 15){
            continue
        }
        println(nums)
    }
}