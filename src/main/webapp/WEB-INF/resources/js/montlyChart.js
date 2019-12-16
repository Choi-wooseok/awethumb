//date객체 YYYY-MM-DD 변환함수



$(function () {

	function dateToYYYYMMDD(date) {
		function pad(num) {
			num = num + '';
			return num.length < 2 ? '0' + num : num;
		}
		return date.getFullYear() + '-' + pad(date.getMonth() + 1) + '-' + pad(date.getDate());
	}
	/*
	
	이제부터 뭘해야 되냐?
	자바 스크립트의 현재날짜를 가져와서, 현재 날짜의 총 일수를 구해서
	그 일수에 해당하는 날짜들을 라벨s에 담는다. 이게 y축
	
	그 날짜의 그 번째의 그 x축은 그 날짜의 그 항목의 그날의 그 숫자.
	
	
	
	
	 */

	let dt = new Date();
	let year = dt.getFullYear();
	let month = dt.getMonth();
	let day = dt.getDate();
	console.log(dt.setDate(dt.getDate() - 1));
	console.log('디티쩜셋데이트디티쩜겟데이트마이나스 1의 타입 : ', typeof (dt.setDate(dt.getDate() - 1)));
	console.log('그 작업이 끝난후의 dt 그 자체의 타입 : ', typeof (dt));
	let labels = new Array();
	for (let i = 0; i < 30; i++) {
		dt.setDate(dt.getDate() - 1);
		labels.push(dateToYYYYMMDD(dt));
	}
	console.log(labels);

	var ctx = document.getElementById('myChart').getContext('2d');
	var myChart = new Chart(ctx, {
		type: 'horizontalBar',
		data: {
			labels: labels,
			datasets: [
				{
					responsive: true,
					maintainAspectRatio: true,
					label: '방문자수',
					data: [12, 19, 3, 5, 2, 3],
					backgroundColor: '#FF0000',
					borderColor: '#FF0000',
					borderWidth: 1					
				}
				,
				{
					label: '글 조회수',
					data: [12, 19, 3, 5, 2, 3],
					backgroundColor: '#3736FF',
					borderColor: '#3736FF',
					borderWidth: 1
				}
				, {
					label: '회원 가입',
					data: [12, 19, 3, 5, 2, 3],
					backgroundColor: '#005000',
					borderColor: '#005000',
					borderWidth: 1
				}
				,
				{
					label: '글작성수',
					data: [12, 19, 3, 5, 2, 3],
					backgroundColor: '#5F00FF',
					borderColor: '#5F00FF',
					borderWidth: 1
				}
			]
		},
		options: {
			scales: {
				yAxes: [{
					ticks: {
						beginAtZero: true
					}
				}]
			}
		}
	});

});
