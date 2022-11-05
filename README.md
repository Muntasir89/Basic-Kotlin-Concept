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
```</br></br>
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
``` </br> 
Then output will be **Hello World!**. Every suspend function needs to run inside a coroutine.Then output will be **World.** Every suspend function needs to run inside a coroutine.
