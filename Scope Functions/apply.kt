data class Employee(var firstName:String="John",var lastName:String = "Doe", var age:Int  = 28, var height:Double = 5.4)
fun main() {
    val emp = Employee()
    emp.firstName = "Alice"
    emp.lastName = "Smith"
    emp.age = 20
    emp.height = 5.8
    
    println(emp)
}