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
import Table from "components/Table/Table.jsx";
import Card from "components/Card/Card.jsx";
import CardHeader from "components/Card/CardHeader.jsx";

import CardBody from "components/Card/CardBody.jsx";

import dashboardStyle from "assets/jss/material-dashboard-react/views/dashboardStyle.jsx";


class TransactionHistory extends React.Component {
  state = {
    value: 0
  };
  handleChange = (event, value) => {
    this.setState({ value });
  };

  handleChangeIndex = index => {
    this.setState({ value: index });
  };
  render() {
    const { classes } = this.props;
    return (
      <div>
       
        <GridContainer>
          
          <GridItem xs={12} sm={12} md={12}>
            <Card>
              <CardHeader color="warning">
                <h4 className={classes.cardTitleWhite}>Transaction History</h4>
                {/* <p className={classes.cardCategoryWhite}>
                  Top 4 Players Achievements
                </p> */}
              </CardHeader>
              <CardBody>
                <Table
                  tableHeaderColor="warning"
                  tableHead={["Buyer/Seller", "Turn", "Type", "Stock Name","Quantity","Transaction"]}
                  tableData={[
                    ["Computer", "0", "Buy","Samsung","1","Transaction"],
                    ["Computer", "0", "Buy","Samsung","1","Transaction"],
                    ["Computer", "0", "Buy","Samsung","1","Transaction"],
                    ["Computer", "0", "Buy","Samsung","1","Transaction"],
                    ["Computer", "0", "Buy","Samsung","1","Transaction"],
                    ["Computer", "0", "Buy","Samsung","1","Transaction"],
                    ["Computer", "0", "Buy","Samsung","1","Transaction"],
                    ["Computer", "0", "Buy","Samsung","1","Transaction"],
                    ["Computer", "0", "Buy","Samsung","1","Transaction"],
                    ["Computer", "0", "Buy","Samsung","1","Transaction"]
                   
                  ]}
                />
              </CardBody>
            </Card>
          </GridItem>
        </GridContainer>
      </div>
    );
  }
}

TransactionHistory.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(dashboardStyle)(TransactionHistory);
