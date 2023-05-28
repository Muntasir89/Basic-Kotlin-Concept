data class Mobile(var name: String = "", var price: Int = 0){}
fun main(){
    var amount = 100;
    println("Previous amount is $amount")
    amount = 200
    println("new amount $amount")
    amount = 400
    println("new amount $amount")

    val amount2 = 100
    println("Previous amount is $amount2")
    //val amount2 = 200 //<--------This will show error

    val mobile = Mobile("Pixel", 1000)
    println(mobile)
    mobile.name = "Iphone"
    println(mobile)
    mobile.price = 2000
    println(mobile)
}