$( document ).ready(function() {
	
    $(".criteria").click(function() {

        if($("#sort-list").is(":visible")) {
            $(".closefilter i").removeClass("fa fa-sort-desc");
            $("#sort-list").css("display", "none");
            $(".closefilter i").addClass("fa fa-sort-asc");
        } else {
            $(".closefilter i").removeClass("fa fa-sort-asc");
            $("#sort-list").css("display", "block");
            $(".closefilter i").addClass("fa fa-sort-desc");
        }
    })
});