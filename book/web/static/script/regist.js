function $(id){
    return document.getElementById(id);
}

function preRegist(){

    //用户名不能为空，而且是6~16位数字和字母组成
    var unameReg = /[0-9a-zA-Z]{6,16}/;
    var unameTxt = $("unameTxt");
    var uname = unameTxt.value ;
    var unameSpan = $("unameSpan");

    if(!unameReg.test(uname)){
        unameSpan.style.visibility="visible";
        return false ;
    }else{
        unameSpan.style.visibility="hidden";
    }

    //密码的长度至少为8位
    var pwdReg=/[\w]{8,}/;
    let pwdTxt = $("pwdTxt");
    var pwd=pwdTxt.value;
    var pwdSpan=$("pwdSpan")

    if(!pwdReg.test(pwd)){
        pwdSpan.style.visibility="visible";
        return false;
    }else{
        pwdSpan.style.visibility="hidden";
    }
    //密码两次输入不一致
    // var pwdSpan2=$("pwdTxt2");
    // var pwd2=pwdSpan2.value
    if($("pwdTxt2").value!=pwd){
        $("pwdSpan2").style.visibility="visible";
        return false;
    }else{
        $("pwdSpan2").style.visibility="hidden";
    }

    //请输入正确的邮箱格式
    var emailTxt=$("emailTxt");
    var email=emailTxt.value;
    var emailSpan=$("emailSpan");
    var emailReg=/^[a-zA-Z0-9_\.-]+@([a-zA-Z0-9-]+[\.]{1})+[a-zA-Z]+$/;

    if(!emailReg.test(email)){
        emailSpan.style.visibility="visible";
        return false;
    }else{
        emailSpan.style.visibility="hidden";
    }

    return true;
}

