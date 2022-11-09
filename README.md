# Basic-Kotlin-Concept
## kotlin Coroutines
**What is Coroutines?**</br>
A function is a sequence of instructions that takes inputs and gives us outputs.</br><br>
**What is thread?**</br>
A Thread describes in which context this sequence of instructions should executed.</br>
```
  println("Hello World")
  var x = 3
  x *= x
  println("The result is $x")
  ```
  </br>  In the above code all instruction will execute one after another. One instruction will execute at a time. When we use kotlin in android developement, there is only one 
  thread which is called ***main thread***. </br>
  So, multithreading is needed in android developement like-</br>
  - Network Calls
  - Database Operations
  - Complex Calculations
  - File Download</br>
  But multithreading is expensive in case of memory consumption. That is why **Coroutines** comes in.</br>
  - It is a light-weight threads
  - Like threads, coroutines can run in parallel, wait for each other, and communicate with each other.
  - But Coroutine != Thread
```
  fun main() {
    task1()
    task2()
}
fun task1(){
    print("Hello")
}
fun task2(){
    print("World")
}
```
</br></br>
In the above code the output will be ***Hello World***. If we modify the code as-</br>
```
  import kotlinx.coroutines.*
  fun main() {
    GlobalScope.launch{
        task2()
    }
    task1()
    Thread.sleep(2000L)
}
fun task1(){
    print("Hello")
}
suspend fun task2(){
    delay(1000L)
    print("World")
}
```
Then output will also be ***Hello World*** Every suspend function needs to run inside a coroutine.
## Kotlin Constructor
```kotlin
class Person(val name:String){
    init{
        println("Person is: "+name)
    }
}
fun main() {
    val person:Person = Person("Muntasir")
}
```
The code inside the init block is the first to be executed when the class is instantiated. The init block is run every time the class is instantiated, with any kind of constructor.
</br>
```
output:
Person is: Muntasir
```
If there are multiple init block then all init block will be executed in the order they were written.
```kotlin
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
```
In the above example, the output will be
```
output:
1. Person is: Muntasir
2. Person is: Muntasir
```
The primary constructors definition goes inside the class header. Unless stated as a var, by default, constructor arguments are val.</br>
Secondary Constructors are written inside the body of the class by prefixing with the keyword **constructor**. Following example demonstrates the same.
```kotlin
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
```
The following code will print in the console:
```
output:
Name is Muntasir and Age is 24
```
The most common usage of secondary constructors comes in subclasses when you need to initialize the class in different ways. If the class contains a primary constructor, the secondary constructor must refer to it in its declaration.
