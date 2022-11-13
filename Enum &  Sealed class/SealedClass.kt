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
