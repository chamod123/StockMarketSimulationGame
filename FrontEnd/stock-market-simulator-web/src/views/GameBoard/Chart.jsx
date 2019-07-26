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
      fetch('https://api.myjson.com/bins/1b82ht'
        , {
          method: 'get'
        }).then(function (response) {
          return response.json();
        }).then(function (data) {
          setChartData(data.data)
        });
    }, 1000);
    return () => clearInterval(interval);
  });
  

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