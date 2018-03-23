/**
 * 基础js，所有页面均要引入
 */

// anonymous function to check all elements with class .fixMinMaxwidth
var fixMinMaxwidth = function() {
	// only apply this fix to browsers without native support
	if (typeof document.body.style.maxHeight !== "undefined"
			&& typeof document.body.style.minHeight !== "undefined")
		return false;

	// loop through all elements
	$('.fixMinMaxwidth').each(function() {
		// get max and minwidth via jquery
		var maxWidth = parseInt($(this).css("max-width"));
		var minWidth = parseInt($(this).css("min-width"));

		// if min-/maxwidth is set, apply the script
		if (maxWidth > 0 && $(this).width() > maxWidth) {
			$(this).width(maxWidth);
		} else if (minWidth > 0 && $(this).width() < minWidth) {
			$(this).width(minWidth);
		}
	});
}

// initialize on domready
$(document).ready(function() {
	fixMinMaxwidth();
});

// check after every resize
$(window).bind("resize", function() {
	fixMinMaxwidth();
});

// 自适应js
var windowsWidth = $(window).width();
// if (windowsWidth > 768) {
$("#container").css("min-width", "971px");
// $("#container").get(0).style.minWidth = "970" + "px";
$("#miniNav").css("display", "none");
$("#webFoot").css("display", "");
$("#webTop").css("display", "");
$("#appFoot").css("display", "none");
$("#appTop").css("display", "none");
/**
 * 未调试号不用
 */
// } else {
// $("#normalNav").css("display", "none");
// $("#webFoot").css("display", "none");
// $("#webTop").css("display", "none");
// $("#appFoot").css("display", "");
// $("#appTop").css("display", "");
// $("#container").css("min-width", "");
// }
var height = $('.panel-success').eq(0).height();
$('.pathInfo').height(height);

$(function() {
	$(".active").parent().parent().parent().addClass("in");
});