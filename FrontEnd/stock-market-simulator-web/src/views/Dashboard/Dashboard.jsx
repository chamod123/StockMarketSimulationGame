
import React from "react";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// react plugin for creating charts

// @material-ui/core
import withStyles from "@material-ui/core/styles/withStyles";
import Icon from "@material-ui/core/Icon";
// @material-ui/icons
import Store from "@material-ui/icons/Store";

import Accessibility from "@material-ui/icons/Accessibility";

// core components
import GridItem from "components/Grid/GridItem.jsx";
import GridContainer from "components/Grid/GridContainer.jsx";
import Table from "components/Table/Table.jsx";
import Card from "components/Card/Card.jsx";
import CardHeader from "components/Card/CardHeader.jsx";
import CardIcon from "components/Card/CardIcon.jsx";
import CardBody from "components/Card/CardBody.jsx";
import CardFooter from "components/Card/CardFooter.jsx";
import dashboardStyle from "assets/jss/material-dashboard-react/views/dashboardStyle.jsx";
import { Button } from "@material-ui/core";
import { getLeaderBoard } from "server/server";


class Dashboard extends React.Component {
  state = {
    value: 0,
    tableData:[]
  };
  handleChange = (event, value) => {
    this.setState({ value });
  };
  handleNavigateToGame=()=>{
    this.props.history.push('/admin/game')
 }
  componentDidMount() {
    getLeaderBoard().then(response => {
      var sortedData=this.sortData(response)
      this.setState({ tableData: this.getTableData(sortedData.slice(0,2)) })
    })
    
  }
  
  handleChangeIndex = index => {
    this.setState({ value: index });
  };

  sortData=(data)=>
  {
    return data.sort((a, b) => (a.balance > b.balance) ? 1 : -1)

  }

  

  getTableData = (response) => {      
    var rowArray = []
    response.forEach(function (element) {
      rowArray.push([element.accountId, element.name, element.balance])
    });
    return rowArray;
  }


  render() {
    const { classes } = this.props;
    const { tableData } = this.state;
    return (
      <div>
        <GridContainer>
          <GridItem xs={12} sm={6} md={3}>
            <Card>
              <CardHeader color="warning" stats icon>
                <CardIcon color="warning">
                  <Icon>content_copy</Icon>
                </CardIcon>
                <p className={classes.cardCategory}>Finance</p>
                <h3 className={classes.cardTitle}>
                  Total Sold Stock : <small> 10</small>
                </h3>
              </CardHeader>
           
            </Card>
          </GridItem>
          <GridItem xs={12} sm={6} md={3}>
            <Card>
              <CardHeader color="success" stats icon>
                <CardIcon color="success">
                  <Store />
                </CardIcon>
                <p className={classes.cardCategory}>Technology</p>
                <h3 className={classes.cardTitle}>
                Total Sold Stock : <small> 50</small>
                </h3>
              </CardHeader>
           
            </Card>
          </GridItem>
          <GridItem xs={12} sm={6} md={3}>
            <Card>
              <CardHeader color="danger" stats icon>
                <CardIcon color="danger">
                  <Icon>info_outline</Icon>
                </CardIcon>
                <p className={classes.cardCategory}>Consumer Service</p>
                <h3 className={classes.cardTitle}>
                Total Sold Stock : <small> 05</small>
                </h3>
              </CardHeader>
             
            </Card>
          </GridItem>
          <GridItem xs={12} sm={6} md={3}>
            <Card>
              <CardHeader color="info" stats icon>
                <CardIcon color="info">
                  <Accessibility />
                </CardIcon>
                <p className={classes.cardCategory}>Manufacturing</p>
                <h3 className={classes.cardTitle}>
                Total Sold Stock : <small> 40</small>
                </h3>
              </CardHeader>
             
            </Card>
          </GridItem>
        </GridContainer>
        <GridContainer>
          <GridItem xs={12} sm={12} md={12}>
            <Card>
              <CardBody>
                <h4 className={classes.cardTitle}>What is Stock Market Fantacy League?</h4>
                <p className={classes.cardCategory}>
                  <span className={classes.successText}>
                  
                  </span>{" "}
                  Stock Market simulation Game is a multi-player stock market trading game that allows players to play the game with some of the market simulation features (e.g. stock level price changes).
                </p>
              </CardBody>
              <CardFooter>
                
              </CardFooter>
            </Card>
          </GridItem>
          <GridItem xs={12} sm={12} md={12}>
            <Card>
              <CardBody>
                <h4 className={classes.cardTitle}>How To Play?</h4>
                <p className={classes.cardCategory}>
                Looking Forward to play? Then Let's start. You have already faced to the registration process. Looking Great! While the registartion process you will get a 1000 amount. So now we can start the game. You should go to the Game Board to start the game. You can go to the Game Board by tapping on below 'Let's Start' button or tapping on 'Game Board' in side navigation bar. After redirect to the gameboard you should enter stock quantity to the share amount to buy.Then you can tap on the protfolio to sell the item which you have bought. In that case you should enter quantity and sell. You can do this process rapidly and finaly you can view transaction history and the leaderboard. The player who has the maximum bank balance will be the winner! 
                </p>
              </CardBody>

              
              <CardFooter>
              <Button color="primary" onClick={this.handleNavigateToGame}>
              Let's Start</Button>
              </CardFooter>
            </Card>
          </GridItem>

          <GridItem xs={12} sm={12} md={12}>
         
        <Card>
            <CardBody>
              <h4 className={classes.cardTitle}>Testimonial</h4>
              <p className={classes.cardCategory}>
                "You’re really doing the research. Even if you’re not actually buying the stock, you’re doing the work"
                - Bhubinder
                </p>
                <p className={classes.cardCategory}>
                "Playing the Stock Market Game is different than learning in a class because you’re not learning in class anymore, you’re in the computer room, and suddenly it’s not the computer room anymore, it’s the Stock Market Game room."
                - Parina, 5th grade
                </p>
            </CardBody>
            </Card>
          </GridItem>
        </GridContainer>
        <GridContainer>
         <GridItem xs={12} sm={12} md={12}>
            <Card>
              <CardHeader color="warning">
                <h4 className={classes.cardTitleWhite}>Leader Board</h4>
                <p className={classes.cardCategoryWhite}>
                  Top 4 Players Achievements
                </p>
              </CardHeader>
              <CardBody>
                {tableData.length>0?
                <Table
                  tableHeaderColor="warning"
                  tableHead={["ID", "Name", "Won Prize"]}
                  tableData={tableData}
                />:null}
              </CardBody>
            </Card>
          </GridItem>
        </GridContainer>
      </div>
    );
  }

}
Dashboard.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(dashboardStyle)(Dashboard);
