import React from "react";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// react plugin for creating charts

// @material-ui/core
import withStyles from "@material-ui/core/styles/withStyles";

// @material-ui/icons


// core components
import GridItem from "components/Grid/GridItem.jsx";
import GridContainer from "components/Grid/GridContainer.jsx";

import Card from "components/Card/Card.jsx";
import CardHeader from "components/Card/CardHeader.jsx";
import CardBody from "components/Card/CardBody.jsx";
import dashboardStyle from "assets/jss/material-dashboard-react/views/dashboardStyle.jsx";
import { getLeaderBoard } from "server/server";
import Table from "components/Table/Table.jsx";



class LeaderBoard extends React.Component {
  state = {
    value: 0,
     tableData:[]
  };

  handleChange = (event, value) => {
    this.setState({ value });
  };
  componentDidMount() 
  {
    getLeaderBoard().then(response => {
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
    return rowArray;
  }
  render() {
    const { classes } = this.props;
    const { tableData } = this.state;
    return (
      <div>
       
       <GridContainer>
         <GridItem xs={12} sm={12} md={12}>
            <Card>
              <CardHeader color="warning">
                <h4 className={classes.cardTitleWhite}>Leader Board</h4>
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

LeaderBoard.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(dashboardStyle)(LeaderBoard);
