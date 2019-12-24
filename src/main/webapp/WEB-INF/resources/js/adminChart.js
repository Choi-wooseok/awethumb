
//$(function () {
	
	function monthlyBarChart() {
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
				animation: {
					easing : 'linear',
					duration : 1500,
					},
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero: true
						},
					}]
				}
			}
		});
	}

	
//});
