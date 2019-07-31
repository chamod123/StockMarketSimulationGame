
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

  componentDidMount() {
    getLeaderBoard().then(response => {
      console.log(response)
      this.setState({ tableData: this.getTableData(response) })
    })
  }
  
  handleChangeIndex = index => {
    this.setState({ value: index });
  };

  getTableData = (response) => {
    var rowArray = []
    response.forEach(function (element) {
      rowArray.push([element.id, element.name, element.price])
    });

    console.log(rowArray)
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
                  Total Sold Stock : <small> 20</small>
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
                Total Sold Stock : <small> 20</small>
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
                Total Sold Stock : <small> 20</small>
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
                Total Sold Stock : <small> 20</small>
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
                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining
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
                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                </p>
              </CardBody>

              
              <CardFooter>
                
              </CardFooter>
            </Card>
          </GridItem>
          <GridItem xs={12} sm={12} md={12}>
            <Card>
              <CardBody>
                <h4 className={classes.cardTitle}>Looking Forward to Play? Then Let's Start</h4>
                <p className={classes.cardCategory}>
                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining
                </p>
              </CardBody>

             
              <CardFooter>
               

                <Button color="primary">Let's Start</Button>
                
              </CardFooter>
            </Card>
          </GridItem>

          <GridItem xs={12} sm={12} md={12}>
         
        <Card>
            <CardBody>
              <h4 className={classes.cardTitle}>Testimonial</h4>
              <p className={classes.cardCategory}>
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining"
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
