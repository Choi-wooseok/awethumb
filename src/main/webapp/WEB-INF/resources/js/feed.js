let maxSize = 598;
let boxSize = document.getElementById("feedImgWrap")
let image = document.getElementById("feedImg");
let imgHeight = image.height;
let imgWidth = image.width;
$(document).ready(function() {
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

let boardModal = document.getElementById('modalBoard');
let btn = document.getElementById("myBoard");
let bc = document.getElementsByClassName("boardClose")[0];
btn.onclick = function() {
	boardModal.style.display = "block";
}
bc.onclick = function() {
	boardModal.style.display = "none";
}
window.onclick = function(e) {
	if (e.target == boardModal) {
		boardModal.style.display = "none";
	}
}



// comment
