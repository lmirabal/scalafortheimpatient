package chapter02

/**
 * Date: 05/05/13
 * Time: 23:42
 */
object ControlStructure extends App{

  println("Chapter 02 - Control Structures and Functions")
  var x = 0
  var s = ""

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

  var x1 :Unit = ()
  var y :Int  = 0
  x1 = y = 1
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

  println("6. " + (for (c <- "Hello") yield c.toLong).product)

  // (((((1L * H) * e) * l) * l) * o)
  println("7. fold left = " + "Hello".foldLeft(1L)(_.toInt * _)) //((value, char) => value * char))
  println("7. fold left = " + (1L /: "Hello")(_ * _))
  // (H * (e * (l * (l * (o * 1L)))))
  println("7. fold right = " + "Hello".foldRight(1L)(_.toInt * _))
  println("7. fold right = " + ("Hello" :\ 1L)(_.toInt * _))
  println("7. map = " + "Hello".map (_.toLong).product)

  def product(text: String) =
  {
    //(1L /: text)(_ * _)
    text.foldLeft(1L)(_ * _)
  }
  s = "Hello"
  printf("8. Text = [%s] Product = %d\n", s, product(s))
  s = "World"
  printf("8. Text = [%s] Product = %d\n", s, product(s))

  def recursiveProduct(text: String): Long =
  {
    if (text.length > 0) text.head.toLong * recursiveProduct(text.tail)
    else 1
  }
  s = "Hello"
  printf("9. Text = [%s] Recursive = %d\n", s, recursiveProduct(s))

  def customPow(x: Double, n: Int): Double = {
    if      (n > 0 && n % 2 == 0) math.pow(customPow(x, n / 2), 2)
    else if (n > 0 && n % 2 != 0) x * customPow(x, n - 1)
    else if (n == 0) 1
    else /*if (n < 0)*/ 1 / customPow(x, -n)
  }
  x = 2
  val n = 10
  printf("10. x = %d n = %d, pow = %.0f", x, n, customPow(x,n))
}
