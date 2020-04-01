// @GENERATOR:play-routes-compiler
// @SOURCE:/home/pool/Desktop/order-management-rest-api/conf/routes
// @DATE:Wed Apr 01 18:23:29 IST 2020

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:2
  HomeController_4: controllers.HomeController,
  // @LINE:4
  CustomerController_1: controllers.CustomerController,
  // @LINE:9
  ProductController_2: controllers.ProductController,
  // @LINE:14
  OrderController_0: controllers.OrderController,
  // @LINE:22
  Assets_3: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:2
    HomeController_4: controllers.HomeController,
    // @LINE:4
    CustomerController_1: controllers.CustomerController,
    // @LINE:9
    ProductController_2: controllers.ProductController,
    // @LINE:14
    OrderController_0: controllers.OrderController,
    // @LINE:22
    Assets_3: controllers.Assets
  ) = this(errorHandler, HomeController_4, CustomerController_1, ProductController_2, OrderController_0, Assets_3, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_4, CustomerController_1, ProductController_2, OrderController_0, Assets_3, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """customers""", """controllers.CustomerController.getCustomers"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """customers/add""", """controllers.CustomerController.addNewCustomer(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """customers/""" + "$" + """id<[^/]+>""", """controllers.CustomerController.getCustomer(id:String)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """customers/""" + "$" + """id<[^/]+>""", """controllers.CustomerController.removeCustomer(id:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """products""", """controllers.ProductController.getProducts"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """products/add""", """controllers.ProductController.addNewProduct(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """products/""" + "$" + """id<[^/]+>""", """controllers.ProductController.getProduct(id:String)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """products/""" + "$" + """id<[^/]+>""", """controllers.ProductController.removeProduct(id:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """orders""", """controllers.OrderController.getOrders"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """orders/""" + "$" + """id<[^/]+>""", """controllers.OrderController.getOrder(id:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """order""", """controllers.OrderController.placeNewOrder(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """customers/""" + "$" + """id<[^/]+>/orders""", """controllers.OrderController.getOrdersOfCustomer(id:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """orders/""" + "$" + """id<[^/]+>/change_status""", """controllers.OrderController.changeStatusOfOrder(id:String, request:Request)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """orders/""" + "$" + """id<[^/]+>""", """controllers.OrderController.removeOrder(id:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:2
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_4.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """""",
      Seq()
    )
  )

  // @LINE:4
  private[this] lazy val controllers_CustomerController_getCustomers1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("customers")))
  )
  private[this] lazy val controllers_CustomerController_getCustomers1_invoker = createInvoker(
    CustomerController_1.getCustomers,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerController",
      "getCustomers",
      Nil,
      "GET",
      this.prefix + """customers""",
      """""",
      Seq()
    )
  )

  // @LINE:5
  private[this] lazy val controllers_CustomerController_addNewCustomer2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("customers/add")))
  )
  private[this] lazy val controllers_CustomerController_addNewCustomer2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      CustomerController_1.addNewCustomer(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerController",
      "addNewCustomer",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """customers/add""",
      """""",
      Seq()
    )
  )

  // @LINE:6
  private[this] lazy val controllers_CustomerController_getCustomer3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("customers/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CustomerController_getCustomer3_invoker = createInvoker(
    CustomerController_1.getCustomer(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerController",
      "getCustomer",
      Seq(classOf[String]),
      "GET",
      this.prefix + """customers/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_CustomerController_removeCustomer4_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("customers/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CustomerController_removeCustomer4_invoker = createInvoker(
    CustomerController_1.removeCustomer(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CustomerController",
      "removeCustomer",
      Seq(classOf[String]),
      "DELETE",
      this.prefix + """customers/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_ProductController_getProducts5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("products")))
  )
  private[this] lazy val controllers_ProductController_getProducts5_invoker = createInvoker(
    ProductController_2.getProducts,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ProductController",
      "getProducts",
      Nil,
      "GET",
      this.prefix + """products""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_ProductController_addNewProduct6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("products/add")))
  )
  private[this] lazy val controllers_ProductController_addNewProduct6_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      ProductController_2.addNewProduct(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ProductController",
      "addNewProduct",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """products/add""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_ProductController_getProduct7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("products/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ProductController_getProduct7_invoker = createInvoker(
    ProductController_2.getProduct(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ProductController",
      "getProduct",
      Seq(classOf[String]),
      "GET",
      this.prefix + """products/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_ProductController_removeProduct8_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("products/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ProductController_removeProduct8_invoker = createInvoker(
    ProductController_2.removeProduct(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ProductController",
      "removeProduct",
      Seq(classOf[String]),
      "DELETE",
      this.prefix + """products/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_OrderController_getOrders9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("orders")))
  )
  private[this] lazy val controllers_OrderController_getOrders9_invoker = createInvoker(
    OrderController_0.getOrders,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.OrderController",
      "getOrders",
      Nil,
      "GET",
      this.prefix + """orders""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_OrderController_getOrder10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("orders/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_OrderController_getOrder10_invoker = createInvoker(
    OrderController_0.getOrder(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.OrderController",
      "getOrder",
      Seq(classOf[String]),
      "GET",
      this.prefix + """orders/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_OrderController_placeNewOrder11_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("order")))
  )
  private[this] lazy val controllers_OrderController_placeNewOrder11_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      OrderController_0.placeNewOrder(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.OrderController",
      "placeNewOrder",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """order""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_OrderController_getOrdersOfCustomer12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("customers/"), DynamicPart("id", """[^/]+""",true), StaticPart("/orders")))
  )
  private[this] lazy val controllers_OrderController_getOrdersOfCustomer12_invoker = createInvoker(
    OrderController_0.getOrdersOfCustomer(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.OrderController",
      "getOrdersOfCustomer",
      Seq(classOf[String]),
      "GET",
      this.prefix + """customers/""" + "$" + """id<[^/]+>/orders""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_OrderController_changeStatusOfOrder13_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("orders/"), DynamicPart("id", """[^/]+""",true), StaticPart("/change_status")))
  )
  private[this] lazy val controllers_OrderController_changeStatusOfOrder13_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      OrderController_0.changeStatusOfOrder(fakeValue[String], fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.OrderController",
      "changeStatusOfOrder",
      Seq(classOf[String], classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """orders/""" + "$" + """id<[^/]+>/change_status""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_OrderController_removeOrder14_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("orders/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_OrderController_removeOrder14_invoker = createInvoker(
    OrderController_0.removeOrder(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.OrderController",
      "removeOrder",
      Seq(classOf[String]),
      "DELETE",
      this.prefix + """orders/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private[this] lazy val controllers_Assets_versioned15_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned15_invoker = createInvoker(
    Assets_3.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:2
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_4.index)
      }
  
    // @LINE:4
    case controllers_CustomerController_getCustomers1_route(params@_) =>
      call { 
        controllers_CustomerController_getCustomers1_invoker.call(CustomerController_1.getCustomers)
      }
  
    // @LINE:5
    case controllers_CustomerController_addNewCustomer2_route(params@_) =>
      call { 
        controllers_CustomerController_addNewCustomer2_invoker.call(
          req => CustomerController_1.addNewCustomer(req))
      }
  
    // @LINE:6
    case controllers_CustomerController_getCustomer3_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_CustomerController_getCustomer3_invoker.call(CustomerController_1.getCustomer(id))
      }
  
    // @LINE:7
    case controllers_CustomerController_removeCustomer4_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_CustomerController_removeCustomer4_invoker.call(CustomerController_1.removeCustomer(id))
      }
  
    // @LINE:9
    case controllers_ProductController_getProducts5_route(params@_) =>
      call { 
        controllers_ProductController_getProducts5_invoker.call(ProductController_2.getProducts)
      }
  
    // @LINE:10
    case controllers_ProductController_addNewProduct6_route(params@_) =>
      call { 
        controllers_ProductController_addNewProduct6_invoker.call(
          req => ProductController_2.addNewProduct(req))
      }
  
    // @LINE:11
    case controllers_ProductController_getProduct7_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ProductController_getProduct7_invoker.call(ProductController_2.getProduct(id))
      }
  
    // @LINE:12
    case controllers_ProductController_removeProduct8_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_ProductController_removeProduct8_invoker.call(ProductController_2.removeProduct(id))
      }
  
    // @LINE:14
    case controllers_OrderController_getOrders9_route(params@_) =>
      call { 
        controllers_OrderController_getOrders9_invoker.call(OrderController_0.getOrders)
      }
  
    // @LINE:15
    case controllers_OrderController_getOrder10_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_OrderController_getOrder10_invoker.call(OrderController_0.getOrder(id))
      }
  
    // @LINE:16
    case controllers_OrderController_placeNewOrder11_route(params@_) =>
      call { 
        controllers_OrderController_placeNewOrder11_invoker.call(
          req => OrderController_0.placeNewOrder(req))
      }
  
    // @LINE:17
    case controllers_OrderController_getOrdersOfCustomer12_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_OrderController_getOrdersOfCustomer12_invoker.call(OrderController_0.getOrdersOfCustomer(id))
      }
  
    // @LINE:18
    case controllers_OrderController_changeStatusOfOrder13_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_OrderController_changeStatusOfOrder13_invoker.call(
          req => OrderController_0.changeStatusOfOrder(id, req))
      }
  
    // @LINE:19
    case controllers_OrderController_removeOrder14_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_OrderController_removeOrder14_invoker.call(OrderController_0.removeOrder(id))
      }
  
    // @LINE:22
    case controllers_Assets_versioned15_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned15_invoker.call(Assets_3.versioned(path, file))
      }
  }
}
