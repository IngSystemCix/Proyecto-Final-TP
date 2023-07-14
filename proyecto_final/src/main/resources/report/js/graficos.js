// Gráficos (Creador: Juan Bladimir Romero Collazos)
const getOptionChart1 = () => {
  return {
    xAxis: {
      type: 'category',
      data: ['3', '4', '5', '6', '7', '8', '9']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [3, 4, 5, 6, 7, 8, 9],
        type: 'bar'
      }
    ]
  };
};
const getOptionChart2 = () => {
  return  {
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: ['3', '4', '5', '6', '7', '8', '9']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [3, 4, 5, 6, 7, 8, 9],
        type: 'line',
        areaStyle: {}
      }
    ]
  };
};

const getOptionChart3 = () => {
  return {
    tooltip: {
      trigger: 'item'
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
          position: 'center'
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
          { value: 1048, name: 'Número de coincidencias' },
          { value: 735, name: 'Horas más frecuentes' },
          { value: 484, name: 'Magnitud más recurrente' },
        ]
      }
    ]
  };
}

const getOptionChart4 = () => {
  return {
    series: [
      {
        type: 'gauge',
        progress: {
          show: true,
          width: 18
        },
        axisLine: {
          lineStyle: {
            width: 18
          }
        },
        axisTick: {
          show: false
        },
        splitLine: {
          length: 15,
          lineStyle: {
            width: 2,
            color: '#999'
          }
        },
        axisLabel: {
          distance: 25,
          color: '#999',
          fontSize: 20
        },
        anchor: {
          show: true,
          showAbove: true,
          size: 25,
          itemStyle: {
            borderWidth: 10
          }
        },
        title: {
          show: false
        },
        detail: {
          valueAnimation: true,
          fontSize: 50,
          offsetCenter: [0, '70%']
        },
        data: [
          {
            value: porcentaje.toFixed(2)
          }
        ]
      }
    ]
  };
}

const initCharts = () => {
  const chart1 = echarts.init(document.getElementById("chart1"));
  const chart2 = echarts.init(document.getElementById("chart2"));
  const chart3 = echarts.init(document.getElementById("chart3"));
  const chart4 = echarts.init(document.getElementById("chart4"));
  chart1.setOption(getOptionChart1());
  chart2.setOption(getOptionChart2());
  chart3.setOption(getOptionChart3());
  chart4.setOption(getOptionChart4());
};

window.addEventListener('load', () => {
  initCharts();
});
