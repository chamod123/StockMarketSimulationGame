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


class LeaderBoard extends React.Component {
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
                <h4 className={classes.cardTitleWhite}>Leader Board</h4>
                {/* <p className={classes.cardCategoryWhite}>
                  Top 4 Players Achievements
                </p> */}
              </CardHeader>
              <CardBody>
              <Table
                  tableHeaderColor="warning"
                  tableHead={["ID", "Name", "Won Prize"]}
                  tableData={[
                    ["1", "Mick Peterson", "$36,738"],
                    ["2", "Damon Silvester", "$23,789"],
                    ["3", "Rick Panday", "$56,142"],
                    ["4", "Philip Chaney", "$38,735"]
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

LeaderBoard.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(dashboardStyle)(LeaderBoard);
