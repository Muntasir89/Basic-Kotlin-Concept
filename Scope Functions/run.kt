data class Employee(var firstName:String="John",var lastName:String = "Doe", var age:Int  = 28, var height:Double = 5.4)
fun main(){
    val emp:Employee = Employee()
    val quote = emp?.run{
        println(firstName)
    	println(lastName)
    	age+5
        "The night is as darkest as before the dawn."
    }
    println(quote)
}