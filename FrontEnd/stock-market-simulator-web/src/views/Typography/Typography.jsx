/*!

=========================================================
* Material Dashboard React - v1.7.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-dashboard-react
* Copyright 2019 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/material-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React from "react";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// @material-ui/core components
import withStyles from "@material-ui/core/styles/withStyles";
// core components
import Quote from "components/Typography/Quote.jsx";
import Muted from "components/Typography/Muted.jsx";
import Primary from "components/Typography/Primary.jsx";
import Info from "components/Typography/Info.jsx";
import Success from "components/Typography/Success.jsx";
import Warning from "components/Typography/Warning.jsx";
import Danger from "components/Typography/Danger.jsx";
import Card from "components/Card/Card.jsx";
import CardHeader from "components/Card/CardHeader.jsx";
import CardBody from "components/Card/CardBody.jsx";
import GridItem from "components/Grid/GridItem.jsx";
import GridContainer from "components/Grid/GridContainer.jsx";
import TextField from '@material-ui/core/TextField';
import InputAdornment from '@material-ui/core/InputAdornment';
import Table from "components/Table/Table.jsx";
import { Button } from "@material-ui/core";
const style = {
  typo: {
    paddingLeft: "25%",
    marginBottom: "40px",
    position: "relative"
  },
  note: {
    fontFamily: '"Roboto", "Helvetica", "Arial", sans-serif',
    bottom: "10px",
    color: "#c0c1c2",
    display: "block",
    fontWeight: "400",
    fontSize: "13px",
    lineHeight: "13px",
    left: "0",
    marginLeft: "20px",
    position: "absolute",
    width: "260px"
  },
  cardCategoryWhite: {
    color: "rgba(255,255,255,.62)",
    margin: "0",
    fontSize: "14px",
    marginTop: "0",
    marginBottom: "0"
  },
  cardTitleWhite: {
    color: "#FFFFFF",
    marginTop: "0px",
    minHeight: "auto",
    fontWeight: "300",
    fontFamily: "'Roboto', 'Helvetica', 'Arial', sans-serif",
    marginBottom: "3px",
    textDecoration: "none"
  }
};
function TypographyPage(props) {
  const { classes } = props;
  return (
    <Card>
      <CardHeader color="primary">
        <h4 className={classes.cardTitleWhite}>You Can Upload and Download Your Portfolio File</h4>
      </CardHeader>
      <CardBody>
        <h4 className={classes.cardTitle}>Upload your portfolio here...</h4>
        <GridContainer>
                        <GridItem>
                          <TextField
                            id="outlined-adornment-amount"
                            variant="outlined"
                            // label="Amount"
                            type="file"
                            inputProps={{ min: "0", max: "100", step: "1" }}
                            // value={values.amount}
                            // onChange={handleChange('amount')}
                            InputProps={{
                              startAdornment: <InputAdornment position="start"></InputAdornment>,
                            }}
                          />
                          <Button color="primary">Browser</Button>
                          <h4 className={classes.cardTitle}>Available Files</h4>
                   <Table
           tableHeaderColor="warning"
           tableHead={["File Name", "Size","Uploaded Date"]}
           tableData={[
             ["Test file", "36kb","2019-07-01"],
             ["Test file", "36kb","2019-07-01"],
             ["Test file", "36kb","2019-07-01"],
             ["Test file", "36kb","2019-07-01"]
           ]}
         />
                        </GridItem>
                       </GridContainer>
                       

      </CardBody>
    </Card>
    //    <Card>
    //            <CardHeader color="warning">
    //      <h4 className={classes.cardTitleWhite}>Leader Board</h4>
    //      <p className={classes.cardCategoryWhite}>
    //        Top 4 Players Achievements
    //      </p>
    //    </CardHeader>
    //    <CardBody>
    //      <Table
    //        tableHeaderColor="warning"
    //        // tableHead={["ID", "Name", "Won Prize", "Country"]}
    //        tableHead={["ID", "Name", "Won Prize"]}
    //        tableData={[
    //          ["1", "Mick Peterson", "$36,738"],
    //          ["2", "Damon Silvester", "$23,789"],
    //          ["3", "Rick Panday", "$56,142"],
    //          ["4", "Philip Chaney", "$38,735"]
    //        ]}
    //      />
    //      </CardBody>
    //  </Card>
   
  );
}


         

TypographyPage.propTypes = {
  classes: PropTypes.object
};

export default withStyles(style)(TypographyPage);
