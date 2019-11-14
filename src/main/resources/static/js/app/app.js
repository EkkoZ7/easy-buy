var App = function () {
   var handlerLoadProductCategoriesNav = function () {
       $(".fj").bind("mouseover",function () {
           var $this = $(this)
           var $current_zj_l = $this.next().children(":first")
           $.ajax({
               url: "/products/categories/" + $(this).attr("data"),
               type: "get",
               success: function (data) {
                   $.each(data,function (i, o) {
                       var $zj_l_c = $("<div class='zj_l_c'></div>")
                       $zj_l_c.append("<h2 onclick='location.href=\"/product/list/"+o.id+"\"'>"+o.name+"</h2>")
                       $current_zj_l.append($zj_l_c)
                       $.ajax({
                           url: "/products/categories/" + o.id,
                           type: "get",
                           success: function (data) {
                               $.each(data,function (index, obj) {
                                   $zj_l_c.append(`<a href="/product/list/${obj.id}">${obj.name}</a>`)
                               })
                           }
                       })
                   })
                   $this.unbind("mouseover")
               }
           })
       })
   }

   var handlerFlashCart = function flashCart() {
       $.ajax({
           url: "/cart_info",
           type: "get",
           success: function (result) {
               $(".cars").html("")
               $(".price_sum").html(``)
               $(".price_sum").next().remove();
               $(".login-tip").html("")
               if (result.code == 200) {
                   var totalPrice = 0;
                   $.each(result.data.products, function (i, o) {
                       totalPrice += o.price * result.data.quantity[o.id];
                       var $li = `<li>
                        <div class="img">
                            <a href="javascript:void(0)">
                                <img src="/images/${o.filename}" width="58" height="58">
                            </a>
                        </div>
                        <div class="name">
                            <a href="javascript:void(0)">${o.name}</a>
                        </div>
                        <div class="price">
                            <font color="#ff4e00">￥${o.price}</font>X${result.data.quantity[o.id]}
                        </div>
                    </li>`
                       $(".cars").append($li)
                   })
                   $(".price_sum").html(`共计&nbsp; <font color="#ff4e00">￥</font><span>${totalPrice}</span>`)
                   $(`<div class="price_a"><a href="javascript:location.href='/cart'">去购物车结算</a></div>`).insertAfter(".price_sum")
               } else if (result.code == 404){
                   $(".cars").append(`<span>您尚未购买任何物品，是否进入<a
                        href="/index">商品页</a>进行购买！</span>`)
               } else if(result.code == 403) {
                   $(".login-tip").html(`还未登录！<a href="/login" style="color:#ff4e00;">马上登录</a> 查看购物车！`)
               }
           }
       })
   }

   var handlerAddCart = function addCart(productId, quantity) {
       $.ajax({
           url: "/cart",
           type: "post",
           data: {
               productId: productId,
               quantity: quantity
           },
           success: function (result) {
               if (result.code == 200) {
                   var info = result.data
                   var typeCount = info.products.length;
                   var totalQuantity = 0;
                   var totalPrice = 0;
                   $(".car_t>span").text(info.products.length)
                   $.each(info.products, function (i, o) {
                       var quantity = info.quantity[o.id];
                       var price = o.price;
                       totalPrice += quantity * price;
                       totalQuantity += quantity
                   })
                   showMessage(`添加成功，您共选择了${typeCount}种商品(共${totalQuantity}件),共计${totalPrice}元！`)
               } else {
                   alert("添加失败！");
               }
           }
       })
   }

   return {
       loadProductCategoriesNav: function () {
           handlerLoadProductCategoriesNav();
       },
       addCart: function (productId, quantity) {
           handlerAddCart(productId, quantity)
       },
       flashCart: function () {
           handlerFlashCart()
       }
   }
}()