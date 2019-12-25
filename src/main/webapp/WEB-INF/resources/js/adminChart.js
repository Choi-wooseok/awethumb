
//$(function () {
	
	function recentMonthBarChart() {
		var ctx = document.getElementById('recentMonthChart').getContext('2d');
		var myChart = new Chart(ctx, {
			type: 'horizontalBar',
			data: {
				labels: recentMonthStats.logDtList,
				datasets: [
					{
						responsive: true,
						maintainAspectRatio: true,
						label: '방문자수',
						data: recentMonthStats.visitCntList,
						backgroundColor: '#FF6384',
						borderColor: '#FF6384',
						borderWidth: 1,
					}
					,
					{
						responsive: true,
						maintainAspectRatio: true,
						label: '글 조회수',
						data: recentMonthStats.viewCntList,
						backgroundColor: '#FF9F40',
						borderColor: '#FF9F40',
						borderWidth: 1,
					}
					, {
						responsive: true,
						maintainAspectRatio: true,
						label: '회원 가입',
						data: recentMonthStats.joinCntList,
						backgroundColor: '#4BC0C0',
						borderColor: '#4BC0C0',
						borderWidth: 1,
					}
					,
					{
						responsive: true,
						maintainAspectRatio: true,
						label: '글작성수',
						data: recentMonthStats.postCntList,
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
	
	
	//카테고리 비중 파이 차트 //카테고리 라벨순서별 글작성된 숫자를 가져온다.
	//자바에서 카테고리 이름:작성수 이게 18개가 들어간 맵 객체를 던진다.
	// 라벨은 그 맵객체의 키값 나열
	// 데이타는 그 작성수값 나열
	// 카테고리는 자체적으로 조회수값을 가지고 있지 않다.
	// 카테고리 타이틀을 집어넣고 그에 해당하는 보드를 카운트해서 받아온후 넣어주는 작업이 필요하다.
	
	
	function colorize(opaque, hover, ctxx) {
		var v = ctxx.dataset.data[ctxx.dataIndex];
		var c = v < -50 ? '#D60000'
			: v < 0 ? '#F46300'
			: v < 50 ? '#0358B6'
			: '#44DE28';

		var opacity = hover ? 1 - Math.abs(v / 150) - 0.2 : 1 - Math.abs(v / 150);

		return opaque ? c : utils.transparentize(c, opacity);
	}
	
	function hoverColorize(ctxx) {
		return colorize(false, true, ctxx);
	}

	function categoryChart(){
		console.log('categoryChart진입 신호');
		var ctxx = document.getElementById('cetegoryCanvas').getContext('2d');
		var myDoughnutChart = new Chart(ctxx, {
		    type: 'doughnut',
		    data: {
		    	datasets: [{
		        data: categoryAndProjectCnt.projectCnt,
		    }],

		    labels: categoryAndProjectCnt.categoryList,
		    },
		    
		    options: {
		    	elements: {
					arc: {
						backgroundColor: colorize.bind(null, false, false),
						hoverBackgroundColor: hoverColorize
					}
				}
		    }
		});
	}

	
	


	
//});