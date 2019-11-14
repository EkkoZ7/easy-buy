/**
 * Created by bdqn on 2016/5/3.
 */
//登录的方法
function login(){

    var loginName=$("#loginName").val();
    var password=$("#password").val();
    $.ajax({
        url:"/login",
        method:"post",
        data:"loginName="+loginName+"&password="+password+"&action=login",
        success:function(result){
            if(result.code == 200){
                window.location.href="/index";
            }else{
                alert(result.message)
            }
        }
    })

}