function isTrue() {
    var roleId = document.getElementById("roleId").value;
    if (Math.floor(roleId) != roleId) {
        alert('角色不是整数');
        document.getElementById("roleId").focus();
        return false;
    }else {
        return true;
    }
}

function logout(){
    if(window.confirm("您确定要删除吗？"))
    {
        return true;
    }else{
        return false;
    }
}

function logout2(){
    if(window.confirm("您确定要退出吗？"))
    {
        return true;
    }else{
        return false;
    }
}

// function shopping(){
//
//
//     if(window.confirm("您确定要加入购物车吗？")) {
//         // $.ajax({
//         //     url: "/value", //后端地址（含参数）
//         //     type: "get",       //提交方式
//         //     dataType: "JSON",       //规定请求成功后返回的数据
//         // });
//         return true;
//     }else{
//         return false;
//     }
// }