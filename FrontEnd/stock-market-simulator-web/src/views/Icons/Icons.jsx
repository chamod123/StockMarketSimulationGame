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
/*eslint-disable*/
import React from "react";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// @material-ui/core components
import withStyles from "@material-ui/core/styles/withStyles";
// core components
import GridItem from "components/Grid/GridItem.jsx";
import GridContainer from "components/Grid/GridContainer.jsx";
import CustomInput from "components/CustomInput/CustomInput.jsx";
import Button from "components/CustomButtons/Button.jsx";
import Card from "components/Card/Card.jsx";
import CardHeader from "components/Card/CardHeader.jsx";
import CardFooter from "components/Card/CardFooter.jsx";
import CardBody from "components/Card/CardBody.jsx";

import iconsStyle from "assets/jss/material-dashboard-react/views/iconsStyle.jsx";

const styles = {
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

function Icons(props) {
  const { classes } = props;
  return (
    <div>
      
      <GridContainer>
        <GridItem xs={12} sm={12} md={6}>
              <Card>
              <CardBody>
              <CardHeader color="primary">
              <h5 className={classes.cardTitleWhite}>Edit Credit Card Info</h5>
            </CardHeader>
            <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                  <CustomInput
                    labelText="Holder's Name"
                    id="holder_name"
                    formControlProps={{
                      fullWidth: true
                    }}
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                  <CustomInput
                    labelText="Card Number"
                    id="card_number"
                    formControlProps={{
                      fullWidth: true
                    }}
                  />
                </GridItem>
              </GridContainer>
                <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                  <CustomInput
                    labelText="Expire Date"
                    id="expire_date"
                    formControlProps={{
                      fullWidth: true
                    }}
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                  <CustomInput
                    labelText="CVV"
                    id="cvv"
                    formControlProps={{
                      fullWidth: true
                    }}
                  />
                </GridItem>
              </GridContainer>
              </CardBody>
              <CardFooter>
              <Button color="primary">Update Credit Card Info</Button>
             </CardFooter>
           </Card>      
        </GridItem>
      
      <GridItem xs={12} sm={12} md={6}>
      <Card>
              <CardBody>
              <CardHeader color="primary">
              <h5 className={classes.cardTitleWhite}>Transfer Money</h5>
            </CardHeader>
            <GridContainer>
            <h4 className={classes.cardTitle}>Current Balance In Your Account :<small>$20</small></h4>
                <GridItem xs={12} sm={12} md={6}>
                  <CustomInput
                    labelText="Amount"
                    id="amount"
                    formControlProps={{
                      fullWidth: true
                    }}
                  />
                </GridItem>
                </GridContainer>
            </CardBody>
            <CardFooter>
              <Button color="primary">Transfer</Button>
             </CardFooter>
            </Card>  
      </GridItem>
      
   </GridContainer>
    
    </div>
  );
}

Icons.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(iconsStyle)(Icons);
