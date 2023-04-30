function drawLineChart(joblists) {
    Highcharts.chart('container', {
        chart: {
            type: 'line',
            width: 500
        },
        title: {
            text: 'Annual Salary Increase Graph'
        },
        xAxis: {
            categories: ['Year 1', 'Year 2', 'Year 3', 'Year 4', 'Year 5']
        },
        tooltip: {
            formatter: function() {
                return '<strong>'+this.x+': </strong>'+ this.y;
            }
        },
        series: joblists.map(function(job) {
            var annualSalary = job.annualSalary;
            var seriesData = [annualSalary];
            for (var i = 1; i <= 4; i++) {
                annualSalary = annualSalary * 1.05;
                seriesData.push(annualSalary);
            }
            return {
                name: job.occupation + ' - ' + job.occupationLevel,
                data: seriesData
            };
        })
    });
}