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
