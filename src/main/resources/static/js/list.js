$(function(){
    // 페이징 헤더 동작
    $("[name='pageRows']").change(function(){
//        let frm = $("[name='frmPageRows']");
//        frm.attr("method", "POST")
//        frm.attr("action", "pageRows")
//        frm.submit();

        $("[name='frmPageRows']").attr({
            "method": "POST",
            "action": "pageRows",
        }).submit();
    });
});