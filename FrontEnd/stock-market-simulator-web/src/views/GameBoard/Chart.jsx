import React, { useState, useEffect } from 'react';
import ChartistGraph from "react-chartist";
import {
    dailySalesChart,
  } from "variables/charts.jsx";
  

export default function Chart(props) {
  const [chartData, setChartData] = useState([]);

  useEffect(() => {
    const interval = setInterval(() => {
      //use the props passed as url parrams for API
      // fetch('https://api.myjson.com/bins/1b82ht'
      fetch('https://api.myjson.com/bins/156gmd'
        // fetch('http://localhost:8081/graph'
        , {
          method: 'get'
        }).then(function (response) {
          return response.json();
        }).then(function (data) {
          setChartData(SortChartData(data))
        });
    }, 2000);
    return () => clearInterval(interval);
  });

  const SortChartData=(data)=>{
    var sortedArray ={"lablels":[], "series":[[]]}
    data.forEach(element => {
      if(props.stockname===element[2]){
        sortedArray.lablels.push(element[0])
        sortedArray.series[0].push(element[1])
      }
    })
    console.log(sortedArray)
    return sortedArray
  }
  

  return (
    <ChartistGraph
    className="ct-chart"
    data={chartData}
    type="Line"
    options={dailySalesChart.options}
    // listener={dailySalesChart.animation}
  />
  );
}