var serverIP = "http://192.168.1.4";
var serverPORT = "8000";
if (user_id != "") {
    var socket = io.connect(serverIP + ":" + serverPORT);

    socket.on('reciveNotification', function (data) {
        var u_id = user_id;
        var list_id = data.split(",");
        var flag = 0;
        for (var i = 0; i < list_id.length; i++) {
            if (list_id[i] == u_id) {
                flag = 1;
            }
        }
        if (flag == 1) {
            var cout = 0;
            if ($('.notifications-number').html() == '') {
                cout = 1;
            } else {
                cout = parseInt($('.notifications-number').html()) + 1;
            }


            $('.notifications-number').html(cout.toString());
            $('.notifications-number').show();
        }
    });
}
