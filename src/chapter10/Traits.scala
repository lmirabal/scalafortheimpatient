package chapter10

import scala.collection.mutable.ListBuffer
import java.io.FileInputStream

/**
 * Date: 08/06/13
 * Time: 15:20
 */
object Traits extends App{

  import java.awt.geom.Ellipse2D
  trait RectangleLike
  {
    def translate(x: Int, y: Int) {
      setFrame(getX + x, getY + y, getWidth, getHeight)
    }
    def grow(h: Int, v: Int) {
      setFrame(getX - h, getY - v, getWidth + 2*h, getHeight + 2*v)
    }

    def setFrame(x: Double, y: Double, w: Double, h: Double)
    def getX: Double
    def getY: Double
    def getWidth: Double
    def getHeight: Double

    override def toString: String = s"[x=$getX,y=$getY,width=$getWidth,height=$getHeight]"
  }
  val rectangle = new java.awt.Rectangle(5,10,20,30)
  rectangle.translate(10,-10)
  rectangle.grow(10,20)
  val egg = new Ellipse2D.Double(5,10,20,30) with RectangleLike
  egg.translate(10,-10)
  egg.grow(10,20)
  println(s"1. rectangle = $rectangle egg = $egg")

  import java.awt.Point
  class OrderedPoint(x: Int, y: Int) extends Point(x, y) with scala.math.Ordered[OrderedPoint]{
    def compare(that: OrderedPoint): Int = {
      if(this.x == that.x && this.y == that.y) 0
      if (this.x < that.x || this.x == that.x && this.y < that.y) -1
      else 1
    }
  }
  object OrderedPoint {
    def apply(x: Int, y: Int) = {
      new OrderedPoint(x, y)
    }
  }
  val points = Array(OrderedPoint(10, 0), OrderedPoint(15, 0), OrderedPoint(0, 10), OrderedPoint(0, 0))
  println("2. initial order = " + points.toList)
  scala.util.Sorting.quickSort(points)
  println("2. sorted = " + points.toList)

  //TODO Exercise 3 BitSet linearization is huge

  trait Logger {
    def log(message: String)
  }
  trait ConsoleLogger extends Logger {
    def log(message: String) { println(message) }
  }
  class CryptoLogger(private val shift: Int) extends ConsoleLogger {
    val upperMax = 'Z'
    val lowerMax = 'z'

    def this() { this(3) }

    override def log(message: String) {
      val encrypted = message.map { c: Char =>
        if (c.isLetter && c.isLower) caesarCypher(c,lowerMax)
        else if (c.isLetter && !c.isLower) caesarCypher(c,upperMax)
        else c
      }
      super.log(encrypted)
    }
    def caesarCypher(c: Char, max: Char) = {
      ((c - max - shift) % 26 + max).toChar
    }
  }
  val message = "4. the quick brown fox jumps over the lazy dog"
  val logger = new ConsoleLogger {}
  logger.log(message)
  val cryptoLogger = new CryptoLogger()
  cryptoLogger.log(message)
  val cryptoLogger10 = new CryptoLogger(10)
  cryptoLogger10.log(message)

  class PropertyChangeEvent(val source: Any, val propertyName: String , val oldValue: Any, val newValue: Any)
  trait PropertyChangeListener {  def propertyChange(event: PropertyChangeEvent) }
  trait PropertyChangeSupport {
    var listeners = ListBuffer[PropertyChangeListener]()

    def addPropertyChangeListener(listener: PropertyChangeListener) {
      listeners.append(listener)
    }
    def removePropertyChangeListener(listener: PropertyChangeListener) {
      //TODO How to keep this field immutable
      listeners = listeners - listener
    }
    def firePropertyChange(propertyName: String, oldValue: Any, newValue: Any){
      listeners.foreach(_.propertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue)))
    }
  }
  class APoint extends java.awt.Point with PropertyChangeSupport {
    override def setLocation(x: Int, y: Int) {
      if (x != getX) firePropertyChange("x", getX, x)
      if (y != getY) firePropertyChange("y", getY, y)
      super.setLocation(x, y)
    }
  }
  val point = new APoint
  point.addPropertyChangeListener(new PropertyChangeListener {
    def propertyChange(event: PropertyChangeEvent) {
      println(s"5. Point field ${event.propertyName} has been updated from ${event.oldValue} to ${event.newValue}")}
  })
  point.setLocation(2,3)
  point.setLocation(10,15)

  println("6. In Java is not possible due to the lack of multiple inheritance support, " +
    "which is needed by JContainer as have to inherit from Container as well as JComponent.")
  println("6. This would be possible certainly possible with traits by converting The whole base hierarchy in traits," +
    "as neither (J)Container nor (J)Component need to be classes.")

  //TODO Exercise 7

  import java.io.InputStream
  trait BufferedInputStream extends InputStream {
    val LOGGER = new ConsoleLogger{}
    val buffer = new Array[Byte](50)
    var bytesInBuffer = 0
    var position = 0
    var eof = false

    override def read(): Int = {
      if(bytesInBuffer == 0 && !eof) readToBuffer

      if (bytesInBuffer > 0) readFromBuffer
      else -1
    }

    private def readToBuffer: (Boolean, Int) = {
      val localRead = read(buffer, 0, buffer.length)
      if (localRead == -1) eof = true
      else {
        position = 0
        bytesInBuffer = localRead
        LOGGER.log(s"9. Buffering $bytesInBuffer bytes")
      }
      (eof, bytesInBuffer)
    }

    private def readFromBuffer: Int = {
      bytesInBuffer -= 1
      position += 1
      buffer(position - 1)
    }
  }
  val stream = new FileInputStream("resources/words.txt") with BufferedInputStream
  var result = ""
  var byte = stream.read()
  while(byte != -1) {
    result += byte.toChar
    byte = stream.read()
  }
  println("8. " + result.replaceAll("\n", " "))

  class IterableInputStream(val in: InputStream) extends java.io.InputStream with Iterable[Byte] {

    def iterator: Iterator[Byte] = new Iterator[Byte]{
      def hasNext = in.available() > 0
      def next() = in.read().asInstanceOf[Byte]
    }

    def read() = in.read()
  }
  val in = new IterableInputStream(new FileInputStream("resources/words.txt"))
  println ("10. " + in.iterator.map(_.toChar).mkString.replaceAll("\n", " "))
}
