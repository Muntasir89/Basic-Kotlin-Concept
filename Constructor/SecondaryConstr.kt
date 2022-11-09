class Student {
    var name: String
    val age : Int
    
    constructor(name: String, age: Int){
        this.name = name
        this.age = age
    }
    fun printDetails(){
        println("Name is $name and Age is $age")
    }
}