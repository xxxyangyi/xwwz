/**
 * Created by Y on 2016/9/8.
 */
$(document).ready(function(){
    $(".top_line_more,#more").mouseenter(function () {
        $(".top_line_more").slideDown(0);
        $("#jiantou").removeClass().addClass("glyphicon glyphicon-chevron-up");
    });
    $(".top_line_more,#more").mouseleave(function () {
        $(".top_line_more").slideUp(0);
        $("#jiantou").removeClass().addClass("glyphicon glyphicon-chevron-down");
    });
    $("#login_btn").click(function () {
        $(".login_box").css("display","block");
    });
    $(".glyphicon-remove").click(function () {
        $(".login_box").css("display","none");
    })
});