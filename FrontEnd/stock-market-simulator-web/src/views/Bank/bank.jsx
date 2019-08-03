/*eslint-disable*/
import React, { useState, useEffect } from 'react';
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
import bankStyle from "assets/jss/material-dashboard-react/views/bankStyle.jsx";
import Danger from "components/Typography/Danger";
import { getPaymentInfo } from 'server/server';
import { getBankBalance } from 'server/server';


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

function bank(props) {
  const { classes } = props;
  const [hname, setHname] = useState('');
  const [cnumber, setCnumber] = useState("");
  const [edate, setEdate] = useState("");
  const [cvv, setCvv] = useState("");
  const[bankbalane,setBbalance] =useState("");

  useEffect(() => { 
    if(hname===''&&cnumber==='')
      getPaymentInfo(1).then(response => {
      console.log(response)
       setHname(response.name)
       setCnumber(response.cardNo)
       setEdate(response.expierDate)
       setCvv(response.cvv)
     })
    getBankBalance(1).then(response => {
      console.log(response)
      setBbalance(response.balance)
     })
   });

   const UpdatePaymentInfo=()=>{
    let request={
      name:{hname},
      cardNo:{cnumber},
      expierDate:{edate},
      cvv:{cvv},
    }
    console.log(request)
   // call post UpdatePaymentInfo(request) API
  }

  const UpdateBankBalance=()=>{
    let request={
   //   name:{bankbalane},
        amount:{bankbalane},
    }
    console.log(request)
   // call post UpdatePaymentInfo(request) API
  }

   const handleChangeHname=(event)=>{
    setHname(event.target.value)
  }
  const handleChangeCnumber=(event)=>{
    setCnumber(event.target.value)
  }
  const handleChangeEdate=(event)=>{
    setEdate(event.target.value)
  }
  const handleChangeCvv=(event)=>{
    setCvv(event.target.value)
  }
  
  return (
    <div>
      
      <GridContainer>
        <GridItem xs={12} sm={12} md={6}>
              <Card>
              <CardBody>
              <CardHeader color="success">
              <h5 className={classes.cardTitleWhite}>Edit Credit Card Info</h5>
            </CardHeader>
            <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                  <CustomInput
                    labelText="Holder's Name"
                    id="holder_name"
                    type="text"
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      value:hname,
                      onChange: handleChangeHname
                  }}      
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                  <CustomInput
                    labelText="Card Number"
                    id="card_number"
                    type="number"
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      value:cnumber,
                      onChange: handleChangeCnumber
                  }}   
                  />
                </GridItem>
              </GridContainer>
                <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                  <CustomInput
                    labelText="Expiry Date"
                    id="expiry_date"
                    type="date"
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      value:edate,
                      onChange: handleChangeEdate
                  }}   
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                  <CustomInput
                    labelText="CVV"
                    id="cvv"
                    type="number"
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      value:cvv,
                      onChange: handleChangeCvv
                  }}   
                  />
                </GridItem>
              </GridContainer>
              </CardBody>
              <CardFooter>
              <Button color="success" onClick={UpdatePaymentInfo}>Update Credit Card Info</Button>
             </CardFooter>
           </Card>      
        </GridItem>
      
      <GridItem xs={12} sm={12} md={5}>
      <Card>
              <CardBody>
              <CardHeader color="danger">
              <h5 className={classes.cardTitleWhite}>Current Account Balance</h5>
            </CardHeader>
            <GridContainer>
            <h4 className={classes.cardCategory}><Danger>{bankbalane}</Danger></h4>
         </GridContainer>
            </CardBody>
        </Card>   
        <Card>
        <CardBody>
              <CardHeader color="primary">
              <h5 className={classes.cardTitleWhite}>Transfer Money</h5>
            </CardHeader>
            <GridContainer> 
                <GridItem xs={12} sm={12} md={5}>
                  <CustomInput
                    labelText="Amount"
                    id="amount"
                    type="number"
                    formControlProps={{
                      fullWidth: true
                    }}
                  />
                </GridItem>
                </GridContainer>
            </CardBody>
            <CardFooter>
              <Button color="primary"onClick={UpdateBankBalance}>Transfer</Button>
             </CardFooter>
            </Card>  
      </GridItem>
      
   </GridContainer>
    
    </div>
  );
}

bank.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(bankStyle)(bank);
