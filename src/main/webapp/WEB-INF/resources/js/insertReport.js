function insertReport(id, postNo, commentNo){
	$(document).on("click", `#${id}`, function(){
		let newWindow = window.open("about:blank");
		newWindow.location.href = `/awethumb/report/insertReportForm.do?postNo=${postNo}&commentNo=${commentNo}`;
	})
}