function checkLogin() {
    var flag = true;
    $.ajax({
        url: context_path + "/qanda/checkLoginToAction",
        type: "post",
        async: false,
        dataType: "text",
        data: {
            action: 'add-question'
        },
        success: function (result) {
            var resultJson = JSON.parse(result);
            if (resultJson[0].type == "ERROR") {
                flag = false;
            }
        }
    });
    return flag;
}
