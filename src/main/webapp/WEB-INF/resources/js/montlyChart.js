


$(function () {

	//date객체 YYYY-MM-DD 변환함수
//	function dateToYYYYMMDD(date) {
//		function pad(num) {
//			num = num + '';
//			return num.length < 2 ? '0' + num : num;
//		}
//		return date.getFullYear() + '-' + pad(date.getMonth() + 1) + '-' + pad(date.getDate());
//	}
	
	
	/*
	
	이제부터 뭘해야 되냐?
	자바 스크립트의 현재날짜를 가져와서, 현재 날짜의 총 일수를 구해서
	그 일수에 해당하는 날짜들을 라벨s에 담는다. 이게 y축
	a
	그 날짜의 그 번째의 그 x축은 그 날짜의 그 항목의 그날의 그 숫자.
	
	DB에서 가져와야 하는 것 - 리스트
	
	리스트의 크기 = 30
	
	리스트의 인덱스 순서 = log_dt desc;
	
	이 리스트의 구성요소
	
	그냥 데일리 로그 행 하나씩. 4개의 데이터와 1개의 등록일을 가지고 있음.
	
	방문,조회,회원가입,글작성 숫자
	
	그런데 특이점은 항목별로 30개를 한배열에 담아야 한다는 것
	
	마치 리스트를 받아서 30번의 반복을 돌면서 새로만든 배열 4개에 차례대로 각각의 값을 넣어주는 작업이 필요한 느낌.
	
	그럴빠엔 백단에서 데일리로그 리스트 셀렉한다음에
	
	서비스단에서 데일리로그 dao.어쩌고 해서 셀렉하고, 바로 가공처리 하는 메소드를 만들어놓고
	
	컨트롤러에서 그 메소드를 호출하는 거지.
	
	그러면 그 메소드를 통해 리턴받은 값은 
	
	
	
	
	 */

//	let dt = new Date();
//	let year = dt.getFullYear();
//	let month = dt.getMonth();
//	let day = dt.getDate();
//	console.log(dt.setDate(dt.getDate() - 1));
//	console.log('디티쩜셋데이트디티쩜겟데이트마이나스 1의 타입 : ', typeof (dt.setDate(dt.getDate() - 1)));
//	console.log('그 작업이 끝난후의 dt 그 자체의 타입 : ', typeof (dt));
//	let labels = new Array();
//	for (let i = 0; i < 30; i++) {
//		dt.setDate(dt.getDate() - 1);
//		labels.push(dateToYYYYMMDD(dt));
//	}
//	console.log(labels,);
	chart.defaults.global.animation
	var ctx = document.getElementById('myChart').getContext('2d');
	var myChart = new Chart(ctx, {
		type: 'horizontalBar',
		data: {
			labels: monthlyStats.logDtList,
			datasets: [
				{
					responsive: true,
					maintainAspectRatio: true,
					label: '방문자수',
					data: monthlyStats.visitCntList,
					backgroundColor: '#FF6384',
					borderColor: '#FF6384',
					borderWidth: 1,
				}
				,
				{
					responsive: true,
					maintainAspectRatio: true,
					label: '글 조회수',
					data: monthlyStats.viewCntList,
					backgroundColor: '#FF9F40',
					borderColor: '#FF9F40',
					borderWidth: 1,
				}
				, {
					responsive: true,
					maintainAspectRatio: true,
					label: '회원 가입',
					data: monthlyStats.joinCntList,
					backgroundColor: '#4BC0C0',
					borderColor: '#4BC0C0',
					borderWidth: 1,
				}
				,
				{
					responsive: true,
					maintainAspectRatio: true,
					label: '글작성수',
					data: monthlyStats.postCntList,
					backgroundColor: '#C9CBCF',
					borderColor: '#C9CBCF',
					borderWidth: 1,
				}
			]
		},
		options: 
		{
			
			scales: {
				yAxes: [{
					ticks: {
						beginAtZero: true
					},
				}]
			}
		}
	});
	
});
