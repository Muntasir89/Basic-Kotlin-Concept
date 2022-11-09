fun main() {
    val noun:String? = "Kotlin"
    val nounlength = noun?.let{
        println(it.reversed())
    	println(it.capitalize())
    	it.length   //<---------------- It is the return value
    }
    println("nounlength: "+nounlength)
}