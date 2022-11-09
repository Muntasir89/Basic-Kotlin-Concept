class Person(val name:String){
    init{
        println("1. Person is: "+name)
    }
    init{
        println("2. Person is: "+name)
    }
}
fun main() {
    val person:Person = Person("Muntasir")
}