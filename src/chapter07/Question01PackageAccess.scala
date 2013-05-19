import com.hortsmann.impatient.DoesNotHaveAccessToParentPackage
import com.hortsmann.impatient.HaveAccessToParentPackage

/**
 * This is not very nice written. I'd rather have packages following the directory structure but it would
 * have made it more confusing than having all the classes in the same file.
 * Date: 19/05/13
 * Time: 21:16
 */

package com.hortsmann {

object ParentObject {
  def parentFunction() {
    println("Function from parent package")
  }
}

}


package com.hortsmann.impatient {

//Import is needed to access the parent class members
import com.hortsmann.ParentObject

object DoesNotHaveAccessToParentPackage {
  def function() { ParentObject.parentFunction() }
}

}


package com {
package hortsmann {
package impatient {

object HaveAccessToParentPackage {
  def function() { ParentObject.parentFunction() }
}

}
}
}


object Question01PackageAccess extends App {
  println("1. Shows how nested packages have access to parent members and how chain ones not")
  DoesNotHaveAccessToParentPackage.function()
  HaveAccessToParentPackage.function()
}
