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
import LinearProgressBar from 'components/ProgressIndicators/LinearProgressBar'
import Timer from "./Timer.jsx"
import Chart from './Chart.jsx'
import dashboardStyle from "assets/jss/material-dashboard-react/views/dashboardStyle.jsx";
import { Typography } from "@material-ui/core";
import { getStocks } from "server/server.js";
import { getMyStocks } from "server/server.js";
import { getPlayers } from "server/server.js";

class GameBoard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      stockArray: [],
      selectedSectorIndex: 0,
      selectedStock: 0,
      isOnMyStocks: false,
      noOfSharesToBuy: 0
    };
  }

  componentDidMount() {
    getStocks().then(response => {
      this.setState({ stockArray: response })
    })
  }

  loadStockDataFromAPI = () => {
    const {isOnMyStocks} = this.state
    if(!isOnMyStocks){
      getMyStocks().then(response => {
        this.setState({ 
          stockArray: response,
          isOnMyStocks: true
         })
      })
    }else{
      getStocks().then(response => {
        this.setState({ 
          stockArray: response,
          isOnMyStocks: false
         })
      })
    }
  }

  handleStockSelect = (selectedstockIndex, selectedSectorIndex) => {
    this.setState({
      selectedStock: selectedstockIndex,
      selectedSectorIndex: selectedSectorIndex,
      noOfSharesToBuy:0
    })
  }

  handleToggleOnMyStock = ()=>{
    this.loadStockDataFromAPI()
    getPlayers().then(response => {
        console.log(response)
      })
  }

  handleChangeNoOfSharesToBuy = (event) => {
    this.setState({
      noOfSharesToBuy:event.target.value
    })
  }

  getTableData = (array) => {
    var rowArray = []
    array.forEach(function (element) {
      rowArray.push([element.companyName, element.stockPrice.toString(), element.rate.toString()])
    });
    return rowArray;
  }

  getHeaderColor = () => {
    const { selectedSectorIndex } = this.state;

    if (selectedSectorIndex === 0) {
      return "info"
    }
    if (selectedSectorIndex === 1) {
      return "success"
    }
    if (selectedSectorIndex === 2) {
      return "warning"
    }
  }

  getChartTitle = () => {
    const { selectedSectorIndex, selectedStock, stockArray } = this.state;
    var sector = stockArray[selectedSectorIndex].sector
    var stock = stockArray[selectedSectorIndex].stocks[selectedStock].companyName
    return sector + " : " + stock
  }

  getShareValueToBeBought = () => {
    const { selectedSectorIndex, selectedStock, stockArray, noOfSharesToBuy } = this.state;
    var stockPrice = stockArray[selectedSectorIndex].stocks[selectedStock].stockPrice
    return stockPrice*noOfSharesToBuy
  }

  render() {
    const { classes } = this.props;
    const { stockArray, chartData, isOnMyStocks, noOfSharesToBuy } = this.state;
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
                <Timer className={classes.cardTitle} startCount={5}></Timer>
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
            <CardBody>
              {stockArray.length === 3 ? <GridContainer>
                <GridItem xs={12} sm={12} md={4}>
                  <Typography>{isOnMyStocks?"Portfolio":"Stocks"}</Typography>
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
                              isMyStock={isOnMyStocks}
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
                              isMyStock={isOnMyStocks}
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
                              isMyStock={isOnMyStocks}
                            />
                          )
                        }
                      ]}
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={8}>
                  <Card chart>
                    <CardHeader color={this.getHeaderColor()}>
                      <Chart startChartData={chartData}
                        //send data of selected stock n sector to call getChartData from API 
                      ></Chart>
                    </CardHeader>
                    <CardBody>
                      <h4 className={classes.cardTitle}>{this.getChartTitle()}</h4>
                      <h4 className={classes.cardTitle}>Shares</h4>
                      <GridContainer>
                        <GridItem>
                          <TextField
                            id="outlined-adornment-amount"
                            variant="outlined"
                            label="shares"
                            type="number"
                            inputProps={{ min: "0", max: "100", step: "1" }}
                            value={noOfSharesToBuy}
                            onChange={this.handleChangeNoOfSharesToBuy}
                          />
                        </GridItem>
                        {noOfSharesToBuy > 0 ? <GridItem style={{ "align-self": 'center' }}><Typography>$ {this.getShareValueToBeBought()}</Typography></GridItem> : null}
                        <GridItem><Button disabled={noOfSharesToBuy === 0} color="primary"> {isOnMyStocks ? "Sell" : "Buy"}</Button></GridItem>
                      </GridContainer>
                    </CardBody>
                    <CardFooter chart>
                    </CardFooter>
                  </Card>
                  <Button color="info" onClick={this.handleToggleOnMyStock}>{isOnMyStocks?"Buy New shares":"View your portfolio"}</Button>
                </GridItem>
              </GridContainer> : <LinearProgressBar></LinearProgressBar>}
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
