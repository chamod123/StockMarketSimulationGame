import React from "react";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// @material-ui/core components
import withStyles from "@material-ui/core/styles/withStyles";
// @material-ui/icons
import People from "@material-ui/icons/People";
import Cloud from "@material-ui/icons/Cloud";
import Money from "@material-ui/icons/Money";
// import AddAlert from "@material-ui/icons/AddAlert";
// core components
import GridItem from "components/Grid/GridItem.jsx";
import GridContainer from "components/Grid/GridContainer.jsx";
// import Button from "components/CustomButtons/Button.jsx";
import StockTable from "components/Table/StockTable.jsx";
import CustomTabs from "components/CustomTabs/CustomTabs.jsx";
import Card from "components/Card/Card.jsx";
import CardHeader from "components/Card/CardHeader.jsx";
import CardBody from "components/Card/CardBody.jsx";
import CardFooter from "components/Card/CardFooter.jsx";
import ArrowUpward from "@material-ui/icons/ArrowUpward";
import AccessTime from "@material-ui/icons/AccessTime";

// react plugin for creating charts
import ChartistGraph from "react-chartist";
import {
  dailySalesChart,
} from "variables/charts.jsx";
import { getAllStocks } from "server/server";


const styles = {
  cardCategoryWhite: {
    "&,& a,& a:hover,& a:focus": {
      color: "rgba(255,255,255,.62)",
      margin: "0",
      fontSize: "14px",
      marginTop: "0",
      marginBottom: "0"
    },
    "& a,& a:hover,& a:focus": {
      color: "#FFFFFF"
    }
  },
  cardTitleWhite: {
    color: "#FFFFFF",
    marginTop: "0px",
    minHeight: "auto",
    fontWeight: "300",
    fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
    marginBottom: "3px",
    textDecoration: "none",
    "& small": {
      color: "#777",
      fontSize: "65%",
      fontWeight: "400",
      lineHeight: "1"
    }
  }
};

const chartData = {
  "labels": ["M", "T", "W", "T", "F", "S", "S"],
  "series": [[12, 17, 7, 17, 23, 18, 38]]
}

class Stocks extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      stockArray:[],
      selectedSectorIndex:0,
      selectedStock: 0,
      chartData:chartData,
      Finance:[],
      Technology:[],
      Manufacturing:[],
      ConsumerServices:[]
    };
  }

  componentDidMount() {
  getAllStocks()
  .then((response) => {
    console.log(response)
      this.setState({stockArray:this.createUISectorArray(response)});
    })
 
  }
  createUISectorArray = (response) => {
    return [{
      "sector":"Finance",
      "stocks": response.filter(stock => stock.sector==="Finance"),
    },
    {
      "sector":"Technology",
      "stocks":response.filter(stock => stock.sector==="Technology"),
    },
    {
      "sector":"Manufacturing",
      "stocks":response.filter(stock => stock.sector==="Manufacturing")
    },
    {
      "sector":"ConsumerServices",
      "stocks":response.filter(stock => stock.sector==="ConsumerServices"),
    }]   
  }
  
  // to stop the warning of calling setState of unmounted component
  componentWillUnmount() {
    var id = window.setTimeout(null, 0);
    while (id--) {
      window.clearTimeout(id);
    }
  }
  handleStockSelect = (selectedstockIndex, selectedSectorIndex ) => {
    this.setState({
      selectedStock:selectedstockIndex,
      selectedSectorIndex:selectedSectorIndex
    })
  }

  getTableData = (array) => {
    var rowArray=[]
    array.forEach(function (element) {
      rowArray.push([element.stockId.toString(), element.companyName, element.stockPrice.toString()])
    });
    return rowArray;
  }

  getChartData = () => {
    const { chartData, selectedStock, selectedSectorIndex } = this.state;
      //API Call to recieve the data lable and series from backend 
      //params sector, name of stock

  }

  getHeaderColor = () => {
    const { selectedSectorIndex } = this.state;
    
    if (selectedSectorIndex === 0) {
      return "warning"
    }
    if (selectedSectorIndex === 1) {
      return "success"
    }
    if (selectedSectorIndex === 2) {
      return "info"
    }
    if (selectedSectorIndex === 3) {
      return "danger"
    }
  }

  getChartTitle = () => {
    const { selectedSectorIndex, selectedStock, stockArray } = this.state;
    var sector = stockArray[selectedSectorIndex].sector
    var stock = stockArray[selectedSectorIndex].stocks[selectedStock].companyName
    return sector+" : "+stock
  }

  render() {
    const { classes } = this.props;
    const { stockArray, chartData } = this.state;
    return (
      <Card>
        <CardHeader color="primary">
          <h4 className={classes.cardTitleWhite}>Stocks</h4>
        </CardHeader>
        <CardBody>
        {stockArray.length===4?<GridContainer>
           <GridItem xs={12} sm={12} md={6}>
              <h5>Sectors</h5>
              <br />
              <CustomTabs
              // title="Sector:"
              headerColor="primary"
              tabs={                
                [
                {
                  tabName: stockArray[0].sector,
                  tabIcon: Money,
                  tabContent:  (
                    <StockTable
                    tableHeaderColor="warning"
                    tableHead={["Stock ID", "Company Name", "Stock Price"]}
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
                    tableHead={["Stock ID", "Company Name", "Stock Price"]}
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
                    tableHead={["Stock ID", "Company Name", "Stock Price"]}
                    tableData={this.getTableData(stockArray[2].stocks)}
                    handleRowSelect={this.handleStockSelect}
                    selectedSectorIndex={2}
                  />
                  )
                },
                {
                  tabName: stockArray[3].sector,
                  tabIcon: Cloud,
                  tabContent: (
                    <StockTable
                    tableHeaderColor="info"
                    tableHead={["Stock ID", "Company Name", "Stock Price"]}
                    tableData={this.getTableData(stockArray[2].stocks)}
                    handleRowSelect={this.handleStockSelect}
                    selectedSectorIndex={3}
                  />
                  )
                }
              ]}
            />
            </GridItem>
            <GridItem xs={12} sm={12} md={6}>
              <h5>Stocks Status</h5>
              <br />
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
                <p className={classes.cardCategory}>
                  <span className={classes.successText}>
                    <ArrowUpward className={classes.upArrowCardCategory} /> 55%
                  </span>{" "}
                  increase in today sales.
                </p>
              </CardBody>
              <CardFooter chart>
                <div className={classes.stats}>
                  <AccessTime /> updated 4 minutes ago
                </div>
              </CardFooter>
            </Card>
            </GridItem>
          </GridContainer>:null}
          <br />
          <br />
          <GridContainer justify="center">
            <GridItem xs={12} sm={12} md={6} style={{ textAlign: "center" }}>
              <h5>
                Stocks info
                <br />
                <small>Current stocks information</small>
              </h5>
            </GridItem>
          </GridContainer>
        </CardBody>
      </Card>
    );
  }
}

Stocks.propTypes = {
  classes: PropTypes.object
};

export default withStyles(styles)(Stocks);
