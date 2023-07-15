const getOptionChart3 = () => {
  return {
    tooltip: {
      trigger: 'item',
      formatter: '{b}: {c} ({d}%)'
    },
    legend: {
      top: '5%',
      left: 'center'
    },
    series: [
      {
        name: 'Access From',
        type: 'pie',
        radius: ['40%', '70%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2
        },
        label: {
          show: false,
          position: 'inside',
          formatter: '{b}: {d}%'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: 40,
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: false
        },
        data: [
            { value: 3.04, name: 'leve' },
            { value: 95.68, name: 'moderada' },
            { value: 1.28, name: 'fuerte' },
        ]
     }
   ]
 };
}

const initCharts = () => {
  const chart3 = echarts.init(document.getElementById("chart3"));
  chart3.setOption(getOptionChart3());
};

window.addEventListener('load', () => {
  initCharts();
});
