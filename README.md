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
## @Volatile
In kotlin in order to force changes in a variable to be immediately visible to other threads, we can use the annotation @Volatile. If a variable is not shared between multiple threads, you don't need to use keyword with that variable.</br>
When you apply volatile to a field of a class, it instructs the CPU to always read it from RAM and not from the CPU cache. It also prevents instructions reordering, it acts as memory barrier.
## Scope Function
### apply
```kotlin
data class Employee(var firstName:String="John",var lastName:String = "Doe", var age:Int  = 28, var height:Double = 5.4)
fun main() {
    val emp = Employee()
    emp.firstName = "Alice"
    emp.lastName = "Smith"
    emp.age = 20
    emp.height = 5.8
    
    println(emp)
}
```
Consider, the above example. Here the output is
```
Employee(firstName=Alice, lastName=Smith, age=20, height=5.8)
```
But **apply** keyword works here like below which will give us same output.
```kotlin
data class Employee(var firstName:String="John",var lastName:String = "Doe", var age:Int  = 28, var height:Double = 5.4)
fun main() {
    val emp = Employee()
    var x = emp.apply{
        firstName = "Alice"
        lastName = "Smith"
        age = 20
        height = 5.8
    }
    
    println(x)
}
```
### also
***also*** function is generally used where we want to perform some additional operation on a particular object after we have initialized it. It works like ***apply***. It executess a given block and returns the objcet called. Inside the block, the object is referenced by ***it***, so it's easier to pass it as an argument. This function is handy for embedding additional actions, such as logging in call chains.
```kotlin
data class Person(var name: String, var age:Int, var about:String){
    constructor(): this("", 0, "")
}
fun writeCreationLog(p:Person){
    println("A new person ${p.name} was created")
}
fun main(){
    val jake = Person("Jake", 30, "android developer").also{writeCreationLog(it)}
    println(jake)
}
```
The above code does three task:
1. Creates a ```Person``` object with the given property values
2. Applies the given code block to the object. The return value is the object itself.
3. Calls the logging function passing the object as an argument.</br>
Output:
```
A new person Jake was created
Person(name=Jake, age=30, about=android developer)
```
### let
***let*** function returns the lamda result and we can refer to its objects by using **_it_** identifier. Let's see an example...
```kotlin
fun main() {
    val noun:String? = null
    println(noun!!.reversed())
    println(noun.capitalize())
    println(noun.length)
}
```
Above code will result ```NullPointerException``` To avoid NULLPointerException ***let*** function is used.
```kotlin
fun main(){
  val noun:String? = null
  noun.let{
    println(it.reversed())
  	println(it.capitalize())
  	println(it.length)
  }
}
```
***let*** function is used like this. Wait..wait... don't think it will run :stuck_out_tongue_winking_eye::stuck_out_tongue_winking_eye::stuck_out_tongue_winking_eye: It will also show ```NullPointerException``` To remove this we have to use ***Safe Call [?.]***. By using it after our ***noun*** string code will like this
```kotlin
fun main() {
    val noun:String? = null
    noun?.let{
        println(it.reversed())
    	println(it.capitalize())
    	println(it.length)
    }
}
```
If ***noun*** is null then let block won't be executed. Again...
```kotlin
fun main() {
    val noun:String? = "Kotlin"
    val nounlength = noun?.let{
        println(it.reversed())
    	println(it.capitalize())
    	it.length   //<---------------- It is the return value
    }
    println("nounlength: "+nounlength)
}
```
output:
```
niltoK
Kotlin
nounlength: 6
```
### run
***run*** is a combination of ***with*** and ***let***. If you want to operate on a Nullable object and avoid NullPointerException then use ***run***.
```kotlin
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
```
Output:
```
John
Doe
The night is as darkest as before the dawn.
```
