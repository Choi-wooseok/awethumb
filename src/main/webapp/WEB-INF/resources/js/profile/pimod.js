$uploadCrop = $('#upload').croppie({
    enableExif: true,
    viewport: {
        width: 500,
        height: 500,
        type: 'circle'
    },
    boundary: {
        width: 500,
        height: 500
    }
});


// 업로드 이미지 result
$("#upload-result").click(()=>{
   	let formData = new FormData();
	// 섬네일을 인풋에 지정
    $uploadCrop.croppie('result', {
        type: 'blob',
        size: 'viewport'
    }).then(function (resp) {
    	formData.append("userNo", 2);
    	formData.append("userFile", resp, "image.png");

    	// ajax로 넘김
		uploadProfileImgAjax(formData);
		// 모달창 닫음
    	togglePImodal();
    });
	
})
// 이미지를 업로드 하는 ajax
function uploadProfileImgAjax(data){
	$.ajax({
        cache : false,
        url : "updateprofileimg.do",
        processData: false,
        contentType: false,
        type : 'POST',
        dataType: 'text',
        data
    })
    .done(() => {
    	// 프로필 이미지를 변경시킴
    	getProfileImgAjax();
    })
}
//프로필 이미지를 갖고오는 ajax
function getProfileImgAjax(){
	$.ajax({
		url: "getprofileimg.do",
		dataType: "text",
		data: {userNo},
		success: function(data) {
			// 메인 프로필 이미지
			$("#main_profile_img").attr("src", data);
			// 정보 수정 모달 이미지
			$("#usermod_img").attr("src", data);
		}
	})	
}
getProfileImgAjax();


// 파일 업로드하는 함수 
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        
        reader.onload = function (e) {
            $uploadCrop.croppie('bind', {
                url: event.target.result
            })
        };

        reader.readAsDataURL(input.files[0]);
    }
}
// 모달창 토글하는 함수
function togglePImodal() {
	$(".pi_mod").toggleClass("hidden");
}