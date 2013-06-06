package chapter09

import scala.io.Source
import java.io._

/**
 * Date: 28/05/13
 * Time: 22:24
 */
object Files extends App{

  var inputSource = Source.fromFile("resources/words.txt")
  var input = inputSource.getLines().toList
  inputSource.close()
  //Write to file
  var writer = newWriter
  for(line <- input.reverse) writer.println(line)
  writer.close()
  //Get output
  var outputSource = Source.fromFile("resources/output.txt")
  var output = outputSource.getLines().toList
  outputSource.close()
  println("1. input last 4 lines: ")
  input.takeRight(4).foreach(println(_))
  println("1. output fist 4 lines: ")
  output.take(4).foreach(println(_))

  val n = 10
  inputSource = Source.fromFile("resources/tabs.txt")
  writer = newWriter
  for(line <- inputSource.getLines()) {
    val words = line.split( """\t""")
    for(word <- words) {
      val truncatedWord = word.take(n)
      writer.print(truncatedWord + " " * (n - truncatedWord.length))
    }
    writer.println()
  }
  writer.close()
  printOutput(2)
  inputSource.close()

  //Words too short, hence used 8 instead. Used 3 lines to close source.
  inputSource = Source.fromFile("resources/words.txt")
  println("3. " + inputSource.mkString.split( """\s+""").filter(_.length > 8).toList)
  inputSource.close()

  inputSource = Source.fromFile("resources/floatingpoint.txt")
  val values = inputSource.mkString.split("""\s+""").toList.map(_.toDouble)
  println("4. sum = " + values.sum)
  println("4. average = " + values.sum / values.length)
  println("4. max = " + values.max)
  println("4. min = " + values.min)

  writer = newWriter
  for(i <- 0 to 20) {
    val value = math.pow(2, i).toInt.toString
    writer.println(value + " " * (20 - value.length) + math.pow(2, -i))
  }
  writer.close()
  printOutput(5)

  using (Source.fromFile("src/chapter09/StringsJava.java")) { source =>
    for (string <- """"(\\"|.)*?"""".r.findAllIn(source.mkString)) println("6. " + string)
  }

  //TODO Fix regex
  using (Source.fromFile("resources/numbersletters.txt")) { source =>
    for (string <- """(\d*\.\d+|\.\d+)""".r.findAllIn(source.mkString)) println("7. " + string)
  }

  //TODO exercise 8

  def countClass(dir: File): Int = {
    dir.listFiles().filter(_.getName.endsWith(".class")).size + subdirs(dir).map(f => countClass(f)).sum
  }
  def subdirs(dir: File): Array[File] = {
    val children = dir.listFiles.filter(_.isDirectory)
    children ++ children.flatMap(subdirs(_))
  }
  println("9. Class counter = " + countClass(new File("out")))

  import scala.Serializable
  class Person(val name: String) extends Serializable {
    private var friends = Array[Person]()
    def addFriends(person: Person*) {
      friends = friends ++ person
    }

    override def toString: String = {
      val friendsString = if (friends.size > 0) "Friends = " + friends.mkString("{",",","}") else ""
      s"Name= $name $friendsString"
    }
  }
  val person1 = new Person("Luis")
  val person2 = new Person("Fernando")
  val person3 = new Person("Jose")
  val person4 = new Person("Carlos")
  val person5 = new Person("Jesus")
  person5.addFriends(person1, person2, person3,person4)
  using (new ObjectOutputStream(new FileOutputStream("/tmp/test.obj"))) {
    out => out.writeObject(person5)
  }
  val in = new ObjectInputStream(new FileInputStream("/tmp/test.obj"))
  println("10. " + in.readObject().asInstanceOf[Person])


  // From the book, Beginning Scala, by David Pollak.
  def using[A <: { def close() }, B](closable: A)(body: A => B): B = try body(closable) finally closable.close()

  def newWriter: PrintWriter = {
    new PrintWriter("resources/output.txt")
  }

  def printOutput(exercise: Int) {
    outputSource = Source.fromFile("resources/output.txt")
    output = outputSource.getLines().toList
    output.foreach(s => println(s"$exercise. $s"))
  }

}
