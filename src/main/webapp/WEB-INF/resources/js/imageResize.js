function imageAppend(list, target) {
	// 해당 이미지 리스트를 원한 위치에 append 하면서 이미지 띄움
	for (let i = 0; i < list.length; i++) {
		target.append(`
			<img class="images" src=${list[i]} />
		`)
		// 이미지를 모두 append 하고 나면 슬라이드 시킴
		if (i == list.length-1) {
			target.slick();	
		}
	}
	setTimeout(function () {
		let images = $(".images");
		$.each(images, function(i, e) {
			let maxSize = $(".slick-slide").width();
			imgReSize($(this), maxSize);
		})
	},200)
}

function imgReSize(image, size) {
	let w = image.width();
	let h = image.height();
	let maxSize = size;
	let boxSize = $("#boxSize");
	console.log(w, h, maxSize);
    if (w >= maxSize && h >= maxSize) {
    	if (w > h) {
			image.css({'width':size, 'height':'auto'})
		} else if (h > w) {
			image.css({'height':size, 'width':'auto'})
		} else {
			image.css({'width':size, 'height':size})
		}
    } else if (w >= maxSize && h < maxSize) {
        image.css({'width':size})
    } else if (w < maxSize && h >= maxSize) {
    	image.css({'height':size})
    }
}