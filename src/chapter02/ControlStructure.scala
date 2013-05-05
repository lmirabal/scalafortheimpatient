package chapter02

/**
 * Date: 05/05/13
 * Time: 23:42
 */
object ControlStructure extends App{

  println("Chapter 02 - Control Structures and Functions")

  def signum(number: Int) = {
    if(number > 0) 1
    else if (number < 0) -1
    else 0
  }
  println("1. signum 10  -> " + signum(10))
  println("1. signum -10 -> " + signum(-10))
  println("1. signum 0   -> " + signum(0))

  def emptyBlock(){}
  println("2. empty block = " + emptyBlock())

  var x :Unit = ()
  var y :Int  = 0
  x = y = 1
  printf("3. x = %s y = %s\n", x, y)

  println("""4. for(i <- (1 to 10).reverse) printf("%d ", i)""")
  for(i <- (1 to 10).reverse) printf("%d ", i)
  println("\n4. for(i <- 10 to 1 by -1) printf(\"%d \", i)")
  for(i <- 10 to 1 by -1) printf("%d ", i)
  println()

  def countdown(n: Int) {
    val step = if(n > 0 ) -1 else 1
    for(i <- n to 0 by step) printf("%d ", i)
  }
  print("5. countdown(8) = ")
  countdown(8)
  print("\n5. countdown(-5) = ")
  countdown(-5)
  println()
}
