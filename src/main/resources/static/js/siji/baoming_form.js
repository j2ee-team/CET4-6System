$(document).ready(function(){
    $("#regForm").validate({
        rules: {
            username:{
                required: true,
                minlength: 2
            },
            idcard:{
                required: true,
                minlength:18,
                maxlength:18,
            },
            school:{
                required: true,
            },
            email: {
                required: true,
            },
            detailed:{
                required:true,
            }
        },
        messages:{
            username:{
                required: "用户名不能为空",
                minlength: "用户名的最小长度为2"
            },
            idcard:{
                required: "密码不能为空",
                minlength:"身份证号最小长度为18",
                maxlength:"身份证号最大长度为18"
            },
            school:{
                required: "学校不能为空",
                // equalTo: "确认密码和密码不一致"
            },
            email: {
                required: "邮箱不能为空"
            },
            detailed: {
                required:"地址不能为空",
            }
        }
    });
});