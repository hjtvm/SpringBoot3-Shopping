
function isInt() {
    var pId = document.getElementById("pId").value;
    var pPrice = document.getElementById("pPrice").value;
    var pStock = document.getElementById("pStock").value;
    if (Math.floor(pId) != pId){
        alert('商品账号不是整数');
        document.getElementById("pId").focus();
        return false;
    }
    else if (isNaN(pPrice)){
        alert('商品价格不是小数');
        document.getElementById("pPrice").focus();
        return false;
    }else if (Math.floor(pStock) != pStock){
        alert('商品库存不是整数');
        document.getElementById("pStock").focus();
        return false;
    }
    else{
        return true;
    }
}
