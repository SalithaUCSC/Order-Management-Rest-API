// @GENERATOR:play-routes-compiler
// @SOURCE:/home/pool/Desktop/order-management-rest-api/conf/routes
// @DATE:Wed Apr 01 18:23:29 IST 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:2
package controllers {

  // @LINE:22
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:22
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:4
  class ReverseCustomerController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:4
    def getCustomers(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "customers")
    }
  
    // @LINE:6
    def getCustomer(id:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "customers/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:7
    def removeCustomer(id:String): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "customers/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:5
    def addNewCustomer(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "customers/add")
    }
  
  }

  // @LINE:9
  class ReverseProductController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def removeProduct(id:String): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "products/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:10
    def addNewProduct(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "products/add")
    }
  
    // @LINE:11
    def getProduct(id:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "products/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:9
    def getProducts(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "products")
    }
  
  }

  // @LINE:2
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:2
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:14
  class ReverseOrderController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def getOrdersOfCustomer(id:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "customers/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)) + "/orders")
    }
  
    // @LINE:19
    def removeOrder(id:String): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "orders/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:16
    def placeNewOrder(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "order")
    }
  
    // @LINE:18
    def changeStatusOfOrder(id:String): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "orders/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)) + "/change_status")
    }
  
    // @LINE:15
    def getOrder(id:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "orders/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
    // @LINE:14
    def getOrders(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "orders")
    }
  
  }


}
