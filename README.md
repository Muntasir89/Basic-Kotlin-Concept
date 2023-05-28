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
***let*** function is used like this. Wait..wait... don't think it will run :stuck_out_tongue_winking_eye::stuck_out_tongue_winking_eye::stuck_out_tongue_winking_eye: It will also show ```NullPointerException``` To remove this we have to use ***Safe Call [?.]***. This calls a method if the property is not null or returns null if that property is null without throwing an NPE (null pointer exception). By using it after our ***noun*** string code will like this
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
## Enum Class
```kotlin
enum class Color(val colorShade:String){
    RED("red"),
    GREEN("green"),
    BLUE("blue")
}
```
The hardcodded strings cannot be changed later because this are now constant values. So, enum class cannot hold any kind of dynamic values that can be changed later. </br>
To overcome this kind of restrictions we have something called **_sealed classs_**.
## Sealed Class
**sealed** means **restriction**. But it is more flexible than the enum class. So we can say, **_class > sealed class > enum class_**.
```kotlin
sealed class Shape{
    class Circle(var Float): Shape()
    class Square(var side: Int): Shape()
    class Rectangle(var length: Int, var breadth: Int): Shape()
}
```
Within the sealed class the subclasses are created. We can also add property to these classes.
```kotlin
sealed class Shape{
    
}
class Circle: Shape()
class Square: Shape()
class Rectangle: Shape()
```
Sub classes can be put outside of sealed class. But the subclasses can not be put outside of sealed class file. It is not allowed. Sealed class can have data class also. 
- A sealed class is implicitly an abstract class which cannot be instantiated. 
- By default, the constructor of a sealed class is private and we cannot make it as non-private.
```kotlin
sealed class Shape{
    class Circle(var radius: Float): Shape()
	class Square(var side: Int): Shape()
    
    object NotAShape: Shape()
    //sealed class Line: Shape()
    //sealed interface Draw
}
class Rectangle(var length: Int, var breadth: Int): Shape()

fun main(){
    var circle = Shape.Circle(3.0f)
    var square = Shape.Square(8)
    var rectangle = Rectangle(20, 10)
    
    val noShape = Shape.NotAShape
    
    checkShape(circle)
    checkShape(rectangle)
    checkShape(noShape)
}
fun checkShape(shape: Shape){
    when(shape){
        is Shape.Circle -> println("Circle area is ${3.14 * shape.radius * shape.radius}")
        is Shape.Square -> println("Square area is ${shape.side * shape.side}")
        is Rectangle -> println("Rectangle area is ${shape.length * shape.breadth}")
        Shape.NotAShape -> println("No Shape Found")
    }
}
```
Output:
```
Circle area is 28.259999999999998
Rectangle area is 200
No Shape Found
```
## Val vs Var
### Val
- ***val*** is a general variable
- can be reassigned multiple times
- var is mutable
```kotlin
fun main(){
    val amount = 100
    println("Previous amount is $amount")
    //val amount = 200 //<--------This will show error
}
```
Output:
```
Previous amount is 100
```
### Var
- constant
- only one time initialization
- immutable
```kotlin
fun main(){
    var amount = 100;
    println("Previous amount is $amount")
    amount = 200
    println("new amount $amount")
    amount = 400
    println("new amount $amount")
}
```
Output:
```
Previous amount is 100
new amount 200
new amount 400
```
But if we create val object variable can be reassigned
```kotlin
data class Mobile(var name: String = "", var price: Int = 0){}
fun main(){
    val mobile = Mobile("Pixel", 1000)
    println(mobile)
    mobile.name = "Iphone"
    println(mobile)
    mobile.price = 2000
    println(mobile)
}
```
Output:
```
Mobile(name=Pixel, price=1000)
Mobile(name=Iphone, price=1000)
Mobile(name=Iphone, price=2000)
```
## **_By_** Keyword
In kotlin every variable has an implicit getter and setter. For example,
```kotlin
var hello = "hello"
```
When later we have access to ```hello``` variable. We actually invoke the getter that gives the original value. When changing variable the default property (var only). </br>
In kotlin, we use **_by_** keyword delegates the getter and setter of a variable to another object, called *the delegate*.</br>
So when write 
```kotlin
var myString: String by Delegate()
```
we basicly override the getter and setter method with the **Delegate()** method. 
</br></br>
In Kotlin, the by keyword is used in several contexts, each with a different purpose. Here are some common use cases for the by keyword:

Delegation: The by keyword is frequently used for delegation, where one class delegates some of its functionality to another class. This is known as the delegation pattern. By using the by keyword, you can delegate method calls and property access to another object, which is known as the delegate. The delegate must implement a specific interface or provide specific methods.
Example:
```kotlin
interface Printer {
    fun print(message: String)
}
class ConsolePrinter : Printer {
    override fun print(message: String) {
        println(message)
    }
}
class PrinterController(printer: Printer) : Printer by printer

fun main() {
    val consolePrinter = ConsolePrinter()
    val controller = PrinterController(consolePrinter)
    controller.print("Hello, World!")
}
```
In this example, the PrinterController class delegates the print method to the ConsolePrinter class using the by keyword. When the print method is called on the PrinterController object, it invokes the print method of the ConsolePrinter object.

Property Delegation: The by keyword is also used for property delegation. It allows you to delegate the implementation of property access and modification to a separate object, known as the delegate. The delegate must provide getValue and setValue methods for the delegated property.

Example:
```kotlin
class Example {
    var value: String by Delegate()
}
class Delegate {
    private var backingField = ""

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("Getting value: $backingField")
        return backingField
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("Setting value: $value")
        backingField = value
    }
}

fun main() {
    val example = Example()
    example.value = "Hello, World!"
    println(example.value)
}
```
In this example, the value property of the Example class is delegated to the Delegate class using the by keyword. The Delegate class provides the getValue and setValue methods, which are invoked when the property is accessed or modified.

When the value property is assigned a new value, the setValue method is called. When the value property is accessed, the getValue method is called. In this case, the Delegate class simply stores the value in its backing field and logs the operations.

These are two common use cases of the by keyword in Kotlin. It enables delegation of functionality and property access/modification to other classes, allowing for code reuse and separation of concerns.
