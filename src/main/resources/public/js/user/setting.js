layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     * 用户登录 表单提交
     */
    form.on("submit(saveBtn)", function (data) {
        // 获取表单元素的值 （用户名 + 密码）
        var fieldData = data.field;
        // 发送 ajax 请求，请求用户登录
        $.ajax({
            type: "post",
            url: ctx + "/user/setting",
            data: {
                userName: fieldData.username,
                phone: fieldData.phone,
                email: fieldData.email,
                trueName: fieldData.trueName,
                id: fieldData.id,
            },
            dataType: "json",
            success: function (data) {
                // 判断是否登录成功
                if (data.code == 200) {
                    layer.msg("修改成功！", function () {
                        // 清空cookie
                        $.removeCookie("userIdStr", {domain:"localhost",path:"/crm"});
                        $.removeCookie("userName", {domain:"localhost",path:"/crm"});
                        $.removeCookie("trueName", {domain:"localhost",path:"/crm"});
                        // 跳转到登录页面 (父窗口跳转)
                        window.parent.location.href = ctx + "/index";
                    });
                } else {
                    // 提示信息
                    layer.msg("信息输入有误，请重新修改");
                }
            }
        });
        // 阻止表单跳转
        return false;
    });
});