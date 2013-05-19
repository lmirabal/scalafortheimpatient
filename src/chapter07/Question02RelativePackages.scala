package chapter07

/**
 * Date: 19/05/13
 * Time: 21:37
 */

package util{
  object Random {
    def nextInt() = 1
  }
}

object Question02RelativePackages extends App{
  var fakeRandom = util.Random
  var scalaRandom = scala.util.Random
  printf("2. Fake random class = %s value = %d %n", fakeRandom, fakeRandom.nextInt())
  printf("2. Real random class = %s value = %d %n", scalaRandom, scalaRandom.nextInt())
}
