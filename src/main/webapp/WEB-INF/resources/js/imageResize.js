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
	
	let images = $(".images");
//	$(".images").each(function(i, e) {
//		console.log(
//			e
//		)
//	})
	for (let i = 0; i < images.length; i++) {
		
		console.log(images)
		console.log($(images[i]).height())
		console.log(images[i].naturalHeight)
		console.log(images[i].offsetHeight)
		console.log(images[i].scrollHeight)
		console.log(images[i].clientHeight)
	}
}

function imgReSize(w, h) {
	let maxSize = 500;
	let boxSize = document.getElementById("boxSize");
	let rightBox = document.getElementById("rightBox");
    if (w > maxSize && h > maxSize) {
        if (w > h) {
            boxSize.style.width = maxSize+"px";
            boxSize.style.height = "auto";
            image.style.width = "100%";
        } else {
	        boxSize.style.width = "auto";
	        boxSize.style.height = maxSize+"px";
	        image.style.height = "100%";
        }
    } else if (w > maxSize && h < maxSize) {
        boxSize.style.width = maxSize+"px";
        image.style.width =  "100%";
    } else if (w < maxSize && h > maxSize) {
        box.Size.style.height = maxSize+"px";
        image.style.height = "100%";
    }
}