import React from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { useState } from 'react';
import FormControl from '@material-ui/core/FormControl';
import FormHelperText from '@material-ui/core/FormHelperText';
import { Card } from '@material-ui/core';
import CardBody from 'components/Card/CardBody';
import {  PaymentInfo } from 'server/server';

function Create() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Built by the Stock Fantasy League'}
      <Link color="inherit" href="https://material-ui.com/">
        
      </Link>
      {' team.'}
    </Typography>
  );
}

const useStyles = makeStyles(theme => ({
  '@global': {
    body: {
      backgroundColor: theme.palette.common.white,
       backgroundImage:"url("+"https://t3.ftcdn.net/jpg/01/83/04/80/500_F_183048068_bbktrsuhkLhGPLoijDQTt24hDQyHVFpx.jpg"+")",
       backgroundPosition: 'center',
       backgroundSize: 'cover',
       backgroundRepeat: 'no-repeat'
    },
  },
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
}));

export default function PaymentForm(props) {
  const classes = useStyles();
  const [holdername, setholdername] = useState("");
  const [cardnumber, setcardnumber] = useState("");
  const [expirydate, setexpirydate] = useState("");
  const [cvv, setcvv] = useState("");

  const handlePaymentInfo = () => {
    let request = {
      name: { holdername },
      cardNo: { cardnumber },
      expierDate: { expirydate },
      cvv: { cvv }
    }

    PaymentInfo(request).then(response => {
      props.history.push({ pathname: '/signIn' })
    })
  }
  
  const handlePressSignUp=(props)=>{
   props.history.push({pathname:'/signUp'})
  }

  const handleChangeholdername = (event) => {
    setholdername(event.target.value)
  }

  const handleChangecardnumber = (event) => {
    setcardnumber(event.target.value)
  }
  
  const handleChangeexpirydate = (event) => {
    setexpirydate(event.target.value)
  }
  const handleChangecvv = (event) => {
    setcvv(event.target.value)
  }


  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <Card>
        <CardBody>
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
       {//   <LockOutlinedIcon />
       }
        </Avatar>
        <Typography component="h1" variant="h5">
          Payment Details
        </Typography>

        <FormControl error={holdername===""}>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="holdername"
            label="Holder's Name"
            type="text"
            name="holdername"
            autoComplete="holdername"
            autoFocus
            onChange={handleChangeholdername}
          />
          {holdername==="" && <FormHelperText>Please enter your card holder's name</FormHelperText>}
        </FormControl>
        <FormControl error={cardnumber===""}>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="cardnumber"
            label="Card Number"
        //    type="number"
            id="cardnumber"
            autoComplete="cardnumber"
            autoFocus
            onChange={handleChangecardnumber}
          />
          {cardnumber==="" && <FormHelperText>Please enter your card number</FormHelperText>}
          </FormControl>
          <FormControl error={expirydate===""}>
           <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="expirydate"
            // label="Expiry Date"
            type="date"
            id="expirydate"
            autoFocus
            onChange={handleChangeexpirydate}
          />
          {expirydate==="" && <FormHelperText>Please enter your card expiry date</FormHelperText>}
          </FormControl>
          <FormControl error={cvv===""}>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="cvv"
            label="CVV"
          //  type="number"
            id="cvv"
            autoComplete="cvv"
            autoFocus
            onChange={handleChangecvv}
          />
          {cvv==="" && <FormHelperText>Please enter your card CVV</FormHelperText>}
           </FormControl>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
           // className={classes.submit}
           onClick={handlePaymentInfo}
          >
            Sign In
          </Button>
          <Grid container justify="flex-end">
            <Grid item>
              <Link href="#" variant="body2" onClick={handlePressSignUp}>
                Want go back to Sign Up?
              </Link>
            </Grid>
          </Grid>
      </div>
      </CardBody>
      </Card>
      <Box mt={5}>
        <Create />
        
      </Box>
    </Container>
  );
}