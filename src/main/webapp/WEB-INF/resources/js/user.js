$(document).ready(function() {
	let maxSize = 598;
	let boxSize = $("#feedImgWrap")
	let image = $("#feedImg");
	let imgHeight = image.height;
	let imgWidth = image.width;
	if (imgWidth > maxSize && imgHeight > maxSize) {
		if (imgWidth > imgHeight) {
			boxSize.style.width = maxSize + "px";
			boxSize.style.height = "auto";
			image.style.width = "100%";
		} else {
			boxSize.style.width = "auto";
			boxSize.style.height = maxSize + "px";
			image.style.height = "100%";
		}
	} else if (imgWidth > maxSize && imgHeight < maxSize) {
		boxSize.style.width = maxSize + "px";
		image.style.width = "100%";
	} else if (imgWidth < maxSize && imgHeight > maxSize) {
		boxSize.style.height = maxSize + "px";
		image.style.height = "100%";
	}
})

$(".myBoard").click(() => {
    $("#modalBoard").css("display","block");
});
$(".boardClose").click(() => {
    $("#modalBoard").css("display","none");
    // $("#modalBoard").addClass("aaaa");
});



// comment
