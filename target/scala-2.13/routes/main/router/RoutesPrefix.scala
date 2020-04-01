// @GENERATOR:play-routes-compiler
// @SOURCE:/home/pool/Desktop/order-management-rest-api/conf/routes
// @DATE:Wed Apr 01 18:23:29 IST 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
