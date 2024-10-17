const chartByData = (tag)=>{

    // Bg 정보 담는 배열
    const bg_color =['rgba(255, 99, 132, 0.5)',
    'rgba(54, 162, 235, 0.5)',
    'rgba(255, 198, 86, 0.5)',
    'rgba(75, 192, 192, 0.5)',
    'rgba(153, 102, 255, 0.5)',
    'rgba(255, 159, 64, 0.5)',
    'rgba(255, 99, 132, 0.5)',
    'rgba(54, 162, 235, 0.5)',
    'rgba(255, 206, 86, 0.5)']
    const border_color =['rgba(255, 99, 132, 0.5)',
    'rgba(54, 162, 235, 0.5)',
    'rgba(255, 206, 86, 0.5)',
    'rgba(75, 192, 192, 0.5)',
    'rgba(153, 102, 255, 0.5)',
    'rgba(255, 159, 64, 0.5)',
    'rgba(255, 99, 132, 0.5)',
    'rgba(54, 162, 235, 0.5)',
    'rgba(255, 206, 86, 0.5)']
    // 차트 데이터
    var data = {
            // 카테고리명
            labels: ['col1','col2','col3','col4','col5','col6','col7','col8'],
            datasets: [{
                label: 'Example Dataset',
                data: [10,20,30,40,50,60,70,80],
                //색상 고정 적용
                backgroundColor: bg_color,
                borderColor: border_color,
                borderWidth: 1
            }]
        };

        // 차트 옵션
        var options = {  
            scales: {
                y: {
                    beginAtZero: true
                }
            },
            //정렬
            plugins: {
                legend: {
                  align: 'start'
                }
              } 
        };

        // 캔버스 요소 선택
        var ctx = document.getElementById(tag).getContext('2d');

        // 막대 그래프 그리기
        var myChart = new Chart(ctx, {
            type: 'pie',    //line,pie,doughnut,radar(꺽은선),bubble(버블차트),scatter(산점도),heatmap(히트맵)
            data: data,
            options: options
        });
    }