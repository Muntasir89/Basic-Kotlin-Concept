class Person(val name:String){
    init{
        println("Person is: "+name)
    }
}
fun main(){
    val person:Person = Person("Muntasir")
}
