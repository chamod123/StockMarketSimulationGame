import React from "react";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// @material-ui/core components
import withStyles from "@material-ui/core/styles/withStyles";
import Icon from "@material-ui/core/Icon";

// @material-ui/icons
import People from "@material-ui/icons/People";
import Cloud from "@material-ui/icons/Cloud";
import Money from "@material-ui/icons/Money";
import CardIcon from "components/Card/CardIcon.jsx";

// import AddAlert from "@material-ui/icons/AddAlert";
// core components
import GridItem from "components/Grid/GridItem.jsx";
import GridContainer from "components/Grid/GridContainer.jsx";
import Button from "components/CustomButtons/Button.jsx";
import StockTable from "components/Table/StockTable.jsx";
import CustomTabs from "components/CustomTabs/CustomTabs.jsx";
import Card from "components/Card/Card.jsx";
import CardHeader from "components/Card/CardHeader.jsx";
import CardBody from "components/Card/CardBody.jsx";
import CardFooter from "components/Card/CardFooter.jsx";
import TextField from '@material-ui/core/TextField';
import InputAdornment from '@material-ui/core/InputAdornment';


// react plugin for creating charts
import ChartistGraph from "react-chartist";

import dashboardStyle from "assets/jss/material-dashboard-react/views/dashboardStyle.jsx";

import {
  dailySalesChart,
} from "variables/charts.jsx";


const response = [{
  "sector": "Finance",
  "stocks": [{
    "companyName": "google",
    "stockPrice": 123,
    "rate": 1.7
  },
  {
    "companyName": "facebook",
    "stockPrice": 123,
    "rate": 1.7
  },
  {
    "companyName": "amazon",
    "stockPrice": 123,
    "rate": 1.7
  }
  ]

},
{
  "sector": "Human Resources",
  "stocks": [{
    "companyName": "google",
    "stockPrice": 123,
    "rate": 1.7
  }, {
    "companyName": "google",
    "stockPrice": 123,
    "rate": 1.7
  }, {
    "companyName": "google",
    "stockPrice": 123,
    "rate": 1.7
  }]
}
  ,
{
  "sector": "Sector 3",
  "stocks": [{
    "companyName": "google",
    "stockPrice": 123,
    "rate": 1.7
  }, {
    "companyName": "fb",
    "stockPrice": 123,
    "rate": 1.7
  }, {
    "companyName": "fb",
    "stockPrice": 123,
    "rate": 1.7
  }, {
    "companyName": "fb",
    "stockPrice": 123,
    "rate": 1.7
  }, {
    "companyName": "fb",
    "stockPrice": 123,
    "rate": 1.7
  }, {
    "companyName": "google",
    "stockPrice": 123,
    "rate": 1.7
  }]
}
]
const chartData = {
  "labels": ["M", "T", "W", "T", "F", "S", "S"],
  "series": [[12, 17, 7, 17, 23, 18, 38]]
}

class GameBoard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      stockArray: [],
      selectedSectorIndex: 0,
      selectedStock: 0,
      chartData: chartData
    };
  }

  componentDidMount() {
    this.setState(
      {
        stockArray: response
      })

  }

  // to stop the warning of calling setState of unmounted component
  componentWillUnmount() {
    var id = window.setTimeout(null, 0);
    while (id--) {
      window.clearTimeout(id);
    }
  }
  // showNotification(place) {
  //   var x = [];
  //   x[place] = true;
  //   this.setState(x);
  //   this.alertTimeout = setTimeout(
  //     function() {
  //       x[place] = false;
  //       this.setState(x);
  //     }.bind(this),
  //     6000
  //   );
  // }
  handleStockSelect = (selectedstockIndex, selectedSectorIndex) => {
    this.setState({
      selectedStock: selectedstockIndex,
      selectedSectorIndex: selectedSectorIndex
    })
  }

  getTableData = (array) => {
    var rowArray = []
    array.forEach(function (element) {
      rowArray.push([element.companyName, element.stockPrice.toString(), element.rate.toString()])
    });
    return rowArray;
  }

  getChartData = () => {
    const { chartData, selectedStock, selectedSectorIndex } = this.state;
    //API Call to recieve the data lable and series from backend 
    //params sector, companyName of stock

  }

  getHeaderColor = () => {
    const { selectedSectorIndex } = this.state;

    if (selectedSectorIndex == 0) {
      return "info"
    }
    if (selectedSectorIndex == 1) {
      return "success"
    }
    if (selectedSectorIndex == 2) {
      return "info"
    }
  }

  getChartTitle = () => {
    const { selectedSectorIndex, selectedStock, stockArray } = this.state;
    var sector = stockArray[selectedSectorIndex].sector
    var stock = stockArray[selectedSectorIndex].stocks[selectedStock].companyName
    return sector + " : " + stock
  }

  render() {
    const { classes } = this.props;
    const { stockArray, chartData } = this.state;
    return (
      <div>
        <GridContainer>
          <GridItem xs={12} sm={6} md={3}>
            <Card>
              <CardHeader color="warning" stats icon>
                <CardIcon color="warning">
                  <Icon>content_copy</Icon>
                </CardIcon>
                <p className={classes.cardCategory}>Account Balance</p>
                <h3 className={classes.cardTitle}>
                  <small>$</small> 899
                </h3>
              </CardHeader>
            </Card>
          </GridItem>
          <GridItem xs={12} sm={6} md={3}>
            <Card>
              <CardHeader color="success" stats icon>
                <CardIcon color="success">
                <Icon>money</Icon>
                </CardIcon>
                <p className={classes.cardCategory}>Grand Total</p>
                <h3 className={classes.cardTitle}>$34,245</h3>
              </CardHeader>
            </Card>
          </GridItem>
          <GridItem xs={12} sm={6} md={3}>
            <Card>
              <CardHeader color="danger" stats icon>
                <CardIcon color="danger">
                  <Icon>access_time</Icon>
                </CardIcon>
                <p className={classes.cardCategory}>Time slot</p>
                <h3 className={classes.cardTitle}>4 min</h3>
              </CardHeader>
            </Card>
          </GridItem>
          <GridItem xs={12} sm={6} md={3}>
            <Card>
              <CardHeader color="info" stats icon>
                <CardIcon color="info">
                <Icon>contacts</Icon>
                </CardIcon>
                <p className={classes.cardCategory}>Players</p>
                <h3 className={classes.cardTitle}>+245</h3>
              </CardHeader>
            </Card>
          </GridItem>
        </GridContainer>
        <GridContainer>
          <Card>
            <CardHeader color="primary">
              <h4 className={classes.cardTitleWhite}>GameBoard</h4>
            </CardHeader>
            <CardBody>
              {stockArray.length == 3 ? <GridContainer>
                <GridItem xs={12} sm={12} md={4}>
                  <CustomTabs
                    headerColor="primary"
                    tabs={
                      [
                        {
                          tabName: stockArray[0].sector,
                          tabIcon: Money,
                          tabContent: (
                            <StockTable
                              tableHeaderColor="warning"
                              tableHead={["Company Name", "Stock Price", "rate"]}
                              tableData={this.getTableData(stockArray[0].stocks)}
                              handleRowSelect={this.handleStockSelect}
                              selectedSectorIndex={0}
                            />
                          )
                        },
                        {
                          tabName: stockArray[1].sector,
                          tabIcon: People,
                          tabContent: (
                            <StockTable
                              tableHeaderColor="success"
                              tableHead={["Company Name", "Stock Price", "rate"]}
                              tableData={this.getTableData(stockArray[1].stocks)}
                              handleRowSelect={this.handleStockSelect}
                              selectedSectorIndex={1}
                            />
                          )
                        },
                        {
                          tabName: stockArray[2].sector,
                          tabIcon: Cloud,
                          tabContent: (
                            <StockTable
                              tableHeaderColor="info"
                              tableHead={["Company Name", "Stock Price", "rate"]}
                              tableData={this.getTableData(stockArray[2].stocks)}
                              handleRowSelect={this.handleStockSelect}
                              selectedSectorIndex={2}
                            />
                          )
                        }
                      ]}
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={7}>
                  <Card chart>
                    <CardHeader color={this.getHeaderColor()}>
                      <ChartistGraph
                        className="ct-chart"
                        data={chartData}
                        type="Line"
                        options={dailySalesChart.options}
                        listener={dailySalesChart.animation}
                      />
                    </CardHeader>
                    <CardBody>
                      <h4 className={classes.cardTitle}>{this.getChartTitle()}</h4>
                      <h4 className={classes.cardTitle}>Shares</h4>
                      <GridContainer>
                        <GridItem>
                          <TextField
                            id="outlined-adornment-amount"
                            variant="outlined"
                            label="Amount"
                            type="number"
                            inputProps={{ min: "0", max: "100", step: "1" }}
                            // value={values.amount}
                            // onChange={handleChange('amount')}
                            InputProps={{
                              startAdornment: <InputAdornment position="start">$</InputAdornment>,
                            }}
                          />
                        </GridItem>
                        <GridItem><Button color="primary">Buy</Button></GridItem>
                      </GridContainer>
                    </CardBody>
                    <CardFooter chart>
                    </CardFooter>
                  </Card>
                </GridItem>
              </GridContainer> : null}
              <br />
              <br />

            </CardBody>
          </Card>
        </GridContainer>
      </div>
    );
  }
}

GameBoard.propTypes = {
  classes: PropTypes.object
};

export default withStyles(dashboardStyle)(GameBoard);