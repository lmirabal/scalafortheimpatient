package chapter07

/**
 * Date: 19/05/13
 * Time: 20:20
 */

object Packages extends App {

  println("4. As mentioned in the package object section, it was a way to overcome a jvm limitation. " +
    "Having code belonging to a package it's a better approach for specific utility methods than custom " +
    "utility classes.")

  println("5. private[com] def giveRaise(rate: Double). a) It broadens the visibility of the function to " +
    "the com package instead of the usual class visibility. b) I don't think it has many usages but it might " +
    "be handy in some very specific scenarios.")

  import java.util.{HashMap => JavaMap}
  import collection.immutable.{HashMap => ScalaMap}
  import collection.JavaConversions.mapAsScalaMap
  val javaMap = new JavaMap[String, String]()
  javaMap.put("key1", "value1")
  javaMap.put("key2", "value2")
  javaMap.put("key3", "value3")
  val scalaMap = javaMap.foldLeft(ScalaMap[String, String]())((map, pair) => map + pair)
  printf("6. java = %s scala= %s %n", javaMap, scalaMap)

  println("7. Imports are already in the innermost scope possible")

  println("8. Imports every member under the specified package and allow access to subpackages. " +
    "Importing huge packages can cause name clashes, I'd rather import specific members.")

  import java.lang.System
  val username = System getProperty "user.name"
  val password = Console readLine("Password: ")
  if("secret" equals password) System.out println ("9. Hello " + username)
  else  System.err println ("9. Error in password")

  println("10. Primitive wrappers except Integer,Character. e.g Boolean = " + Boolean.getClass)
}
