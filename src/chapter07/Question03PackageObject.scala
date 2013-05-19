/**
 * Date: 19/05/13
 * Time: 21:54
 */
package object random {
  private val a = 1664525
  private val b = 1013904223
  private val n = 32

  private var seed = 0
  private var previous = seed

  def nextInt() : Int = {
    nextDouble().toInt
  }

  def nextDouble() : Double = {
    previous = next().toInt
    previous
  }

  def setSeed(seed: Int) {
    this.seed = seed
  }

  private def next() : Double = {
    previous * a + b % math.pow(2, n)
  }

}

package random {

object Question03PackageObject extends App {

  setSeed(25)
  printf("3. Using random from package object. nextInt = %d nextDouble = %.02f %n", nextInt(), nextDouble())
}

}
