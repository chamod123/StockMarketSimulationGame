import React from "react";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// @material-ui/core components
import withStyles from "@material-ui/core/styles/withStyles";
import Icon from "@material-ui/core/Icon";
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogContent from '@material-ui/core/DialogContent';

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
import { getAllStocks } from "server/server.js";
import { getPortofolio } from "server/server.js";
import { getPlayers } from "server/server.js";
import { addPlayer } from "server/server.js";
import { buyStock } from "server/server.js";
import { sellStock } from "server/server.js";
import { getPrediction } from "server/server.js";
import { getBankBalance } from "server/server.js";

class GameBoard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      stockArray: [],
      selectedSectorIndex: 0,
      selectedStock: 0,
      isOnMyStocks: false,
      quantity: 0,
      currentPlayers:[],
      inProgress: true,
      open:false,
      prediction:[],
      accountBalance:0
    };
  }

  componentDidMount() {
    var userName = localStorage.getItem('userName');

    getAllStocks().then(response => {
      this.setState({ 
        stockArray: this.createUISectorArray(response),
        inProgress:false })
    })
    getBankBalance(userName).then(response => {
      this.setState({
        accountBalance:response
      })
    }).catch(error=>{
      console.log(error)
    })
  }

  loadStockDataFromAPI = () => {
    const {isOnMyStocks} = this.state
    var userName = localStorage.getItem('userName');
    if(!isOnMyStocks){
      getPortofolio(userName).then(response => {
        this.setState({
          stockArray: this.createUIPortfolioArray(response),
          isOnMyStocks: true,
          inProgress: false
        })
      })
      .catch(error=>{
        console.log(error)
      })
    }else{
      this.setState({
        inProgress: true
      })
      getAllStocks().then(response => {
        this.setState({ 
          stockArray: this.createUISectorArray(response),
          isOnMyStocks: false,
          inProgress:false
         })
      })
    }
  }

  handleClickOpen = () => {
    getPrediction().then(response=>{
      this.setState({
        open: true,
        prediction:response
      });
    })
  };

  handleClose = () => {
    this.setState({ open: false });
  };

  createUISectorArray = (response) => {
    return [{
      "sector":"Finance",
      "stocks": response.filter(stock => stock.sector==="Finance"),
    },
    {
      "sector":"Technology",
      "stocks": response.filter(stock => stock.sector==="Technology"),
    },
    {
      "sector":"Manufacturing",
      "stocks":response.filter(stock => stock.sector==="Manufacturing")
    },
    {
      "sector":"ConsumerServices",
      "stocks":response.filter(stock => stock.sector==="Consumer Services"),
    }]   
  }

  createUIPortfolioArray = (response) => {
    var rowArray = []
    Object.keys(response).map(function (key, index) {
      rowArray.push({ "companyName": key, "stockPrice": response[key] })
    });
    return [{
      "sector": "Your Stocks",
      "stocks": rowArray,
    }]
  }

  handleBuyStock = () => {
    const { selectedSectorIndex, selectedStock, stockArray } = this.state;
    var stock = stockArray[selectedSectorIndex].stocks[selectedStock].companyName
    const { quantity } = this.state;
    var userName = localStorage.getItem('userName');

    buyStock(userName, stock, quantity).then(response => console.log(response))
  }

  handleSellStock = () => {
    const { selectedSectorIndex, selectedStock, stockArray } = this.state;
    var stock = stockArray[selectedSectorIndex].stocks[selectedStock].companyName
    const { quantity } = this.state;
    var userName = localStorage.getItem('userName');

    sellStock(userName, stock, quantity).then(response => console.log(response))
  }

  handleStockSelect = (selectedstockIndex, selectedSectorIndex) => {
    this.setState({
      selectedStock: selectedstockIndex,
      selectedSectorIndex: selectedSectorIndex,
      quantity:0
    })
  }

  handleToggleOnMyStock = ()=>{
    this.loadStockDataFromAPI()
  }

  handleChangeNoOfSharesToBuy = (event) => {
    this.setState({
      quantity:event.target.value
    })
  }

  handlePressStartGame = () => {
    var playerID = localStorage.getItem('playerID');
    addPlayer(playerID).then(
      getPlayers().then(response => {
        this.setState({
          currentPlayers: response
        })
      })).catch(error => console.error(error)
      )
  }

  getTableData = (array) => {
    var rowArray = []
    if(array.length>0){ 
      array.forEach(function (element) {
        rowArray.push([element.stockId.toString(), element.companyName, element.stockPrice.toString()])
      });
    }
    return rowArray;
  }

  getPortFolioTableData = (array) => {
    var rowArray = []
    if(array.length>0){ 
      array.forEach(function (element) {
        rowArray.push([element.companyName, element.stockPrice.toString()])
      });
    }
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
    if (selectedSectorIndex === 3) {
      return "danger"
    }
  }

  getChartTitle = () => {
    const { selectedSectorIndex, selectedStock, stockArray } = this.state;
    var sector = stockArray[selectedSectorIndex].sector
    var stock = stockArray[selectedSectorIndex].stocks[selectedStock].companyName
    return sector + " : " + stock
  }

  getShareValueToBeBought = () => {
    const { selectedSectorIndex, selectedStock, stockArray, quantity } = this.state;
    var stockPrice = stockArray[selectedSectorIndex].stocks[selectedStock].stockPrice
    return stockPrice*quantity
  }

  render() {
    const { classes } = this.props;
    const { stockArray, chartData, isOnMyStocks, quantity, currentPlayers, inProgress, selectedSectorIndex, selectedStock, accountBalance  } = this.state;
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
                  <small>$</small> {accountBalance}
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
              {!inProgress ? <GridContainer>
                <GridItem xs={12} sm={12} md={4}>
                  <Typography>{isOnMyStocks?"Portfolio":"Stocks"}</Typography>
                  {!isOnMyStocks?<CustomTabs
                    headerColor="warning"
                    tabs={
                      [
                        {
                          tabName: stockArray[0].sector,
                          tabIcon: Money,
                          tabContent: (stockArray[0].stocks.length>0?
                            <StockTable
                              tableHeaderColor="warning"
                              tableHead={["Stock Id", "Company Name", "Stock Price"]}
                              tableData={this.getTableData(stockArray[0].stocks)}
                              handleRowSelect={this.handleStockSelect}
                              selectedSectorIndex={0}
                              isMyStock={isOnMyStocks}
                            />:<Typography>You have no stocks bought under {stockArray[0].sector} section</Typography>
                          )
                        },
                        {
                          tabName: stockArray[1].sector,
                          tabIcon: People,
                          tabContent: (
                            stockArray[1].stocks.length>0?
                            <StockTable
                              tableHeaderColor="success"
                              tableHead={["Stock Id", "Company Name", "Stock Price"]}
                              tableData={this.getTableData(stockArray[1].stocks)}
                              handleRowSelect={this.handleStockSelect}
                              selectedSectorIndex={1}
                              isMyStock={isOnMyStocks}
                            />:
                            <Typography>You have no stocks bought under {stockArray[1].sector} section</Typography>
                          )
                        },
                        {
                          tabName: stockArray[2].sector,
                          tabIcon: Cloud,
                          tabContent: (
                            stockArray[2].stocks.length>0?
                            <StockTable
                              tableHeaderColor="info"
                              tableHead={["Stock Id", "Company Name", "Stock Price"]}
                              tableData={this.getTableData(stockArray[2].stocks)}
                              handleRowSelect={this.handleStockSelect}
                              selectedSectorIndex={2}
                              isMyStock={isOnMyStocks}
                            />:
                            <Typography>You have no stocks bought under {stockArray[2].sector} section</Typography>
                          )
                        },
                        {
                          tabName: stockArray[3].sector,
                          tabIcon: Cloud,
                          tabContent: (
                            stockArray[3].stocks.length>0?
                            <StockTable
                              tableHeaderColor="info"
                              tableHead={["Stock Id", "Company Name", "Stock Price"]}
                              tableData={this.getTableData(stockArray[3].stocks)}
                              handleRowSelect={this.handleStockSelect}
                              selectedSectorIndex={3}
                              isMyStock={isOnMyStocks}
                            />:
                            <Typography>You have no stocks bought under {stockArray[3].sector} section</Typography>
                          )
                        }
                      ]}
                  />:
                    <Card>
                      <CardHeader color="warning">
                        <h4 className={classes.cardTitleWhite}>Portfolio</h4>
                      </CardHeader>
                      <CardBody>
                        <StockTable
                          tableHeaderColor="warning"
                          tableHead={["Company Name", "Stock Price"]}
                          tableData={this.getPortFolioTableData(stockArray[0].stocks)}
                          handleRowSelect={this.handleStockSelect}
                          selectedSectorIndex={0}
                          isMyStock={isOnMyStocks}
                        />
                      </CardBody>
                    </Card>}
                </GridItem>
                <GridItem xs={12} sm={12} md={8}>
                  <Card>
                    <CardBody>
                      {currentPlayers.length === 0 ? <Button color="success" onClick={this.handlePressStartGame}>Start Game</Button>
                        :
                        <GridContainer spacing={3}>{currentPlayers.map(player => { return <GridItem xs>{player.name}</GridItem> })}</GridContainer>}
                    </CardBody>
                  </Card>
                  <Card chart>
                    <CardHeader color={this.getHeaderColor()}>
                      <Chart startChartData={chartData}
                        stockname={stockArray[selectedSectorIndex].stocks[selectedStock].companyName}
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
                            value={quantity}
                            onChange={this.handleChangeNoOfSharesToBuy}
                          />
                        </GridItem>
                        {quantity > 0 ? <GridItem style={{ "align-self": 'center' }}><Typography>$ {this.getShareValueToBeBought()}</Typography></GridItem> : null}
                        <GridItem>
                          <Button disabled={quantity === 0} color="primary" onClick={isOnMyStocks ? this.handleSellStock : this.handleBuyStock}>
                            {isOnMyStocks ? "Sell" : "Buy"}</Button>
                        </GridItem>
                      </GridContainer>
                    </CardBody>
                    <CardFooter chart>
                    </CardFooter>
                  </Card>
                  <GridContainer>
                  <GridItem>
                    <Button color="info" onClick={this.handleToggleOnMyStock}>{isOnMyStocks ? "Buy New shares" : "View your portfolio"}</Button>
                  </GridItem>
                  <GridItem>
                    <Button  color="primary"onClick={this.handleClickOpen} >Get Prediction</Button>
                  </GridItem>
                  </GridContainer>
                </GridItem>
              </GridContainer> 
              : <LinearProgressBar></LinearProgressBar>}
              <br />
              <br />

            </CardBody>
          </Card>
        </GridContainer>
        <Dialog
          onClose={this.handleClose}
          aria-labelledby="customized-dialog-title"
          open={this.state.open}
        >
          <DialogTitle id="customized-dialog-title" onClose={this.handleClose}>
            Recomondation
          </DialogTitle>
          <DialogContent dividers>
            <Typography gutterBottom>
              Best stock to buy 
            </Typography>
            <Typography gutterBottom>
              {this.state.prediction[0]}
            </Typography>
            <Typography gutterBottom>
              Best stock to sell
            </Typography>
            <Typography gutterBottom>
              {this.state.prediction[1]}
            </Typography>
            <Typography gutterBottom>
            </Typography>
          </DialogContent>
        </Dialog>
        </div> 
    );
  }
}

GameBoard.propTypes = {
  classes: PropTypes.object
};

export default withStyles(dashboardStyle)(GameBoard);
