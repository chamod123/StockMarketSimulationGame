import React, { useState } from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import FormControl from '@material-ui/core/FormControl';
import FormHelperText from '@material-ui/core/FormHelperText';
import { SignUP } from 'server/server';

const useStyles = makeStyles(theme => ({
  '@global': {
    body: {
      backgroundColor: theme.palette.common.white,
      backgroundImage: "url(" + "https://t3.ftcdn.net/jpg/01/83/04/80/500_F_183048068_bbktrsuhkLhGPLoijDQTt24hDQyHVFpx.jpg" + ")",
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
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

export default function SignUp(props) {
  const classes = useStyles();
  const [firstName, setFirstName] = useState(false);
  const [lastName, setLastName] = useState(false);
  const [Email, setEmail] = useState(false);
  const [password, setpassword] = useState(false);
  const [username, setusername] = useState(false);

  const handlePressSignUp = () => {
    SignUP(username, lastName, Email, username, password).then(
      props.history.push({ pathname: '/signIn' })
    ).catch(error => console.log(error))
  }

  const handlePressAddPaymentInfo = () => {
    props.history.push({ pathname: '/paymentinfo' })
  }

  const handlePressSignIn = () => {
    props.history.push({ pathname: '/signIn' })

  }

  const handleChangeFistName = (event) => {
    setFirstName(event.target.value)
  }

  const handleChangeLastName = (event) => {
    setLastName(event.target.value)
  }

  const handleChangeEmail = (event) => {
    setEmail(event.target.value)
  }
  const handleChangeusername = (event) => {
    setusername(event.target.value)
  }
  const handleChangepassword = (event) => {
    setpassword(event.target.value)
  }
  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign up
        </Typography>
        <form className={classes.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
              <FormControl error={firstName === ""}>
                <TextField
                  autoComplete="fname"
                  name="firstName"
                  variant="outlined"
                  required
                  fullWidth
                  id="firstName"
                  label="First Name"
                  autoFocus
                  onChange={handleChangeFistName}
                />
                {firstName === "" && <FormHelperText  >Please enter your first name</FormHelperText>}
              </FormControl>
            </Grid>
            <Grid item xs={12} sm={6}>
              <FormControl error={lastName === ""}>
                <TextField
                  variant="outlined"
                  required
                  fullWidth
                  id="lastName"
                  label="Last Name"
                  name="lastName"
                  autoComplete="lname"
                  autoFocus
                  onChange={handleChangeLastName}
                />
                {lastName === "" && <FormHelperText  >Please enter your last name</FormHelperText>}
              </FormControl>
            </Grid>
            <Grid item xs={12}>
              <FormControl error={Email === ""}>
                <TextField
                  variant="outlined"
                  required
                  fullWidth
                  id="Email"
                  label="Email Address"
                  name="Email"
                  autoComplete="email"
                  type="email"
                  autoFocus
                  onChange={handleChangeEmail}
                />
                {lastName === "" && <FormHelperText  >Please enter your email</FormHelperText>}
              </FormControl>
            </Grid>
            <Grid item xs={12}>
              <FormControl error={username === ""}>
                <TextField
                  variant="outlined"
                  required
                  fullWidth
                  id="username"
                  label="User Name"
                  name="username"
                  autoComplete="username"
                  type="username"
                  autoFocus
                  onChange={handleChangeusername}
                />
                {username === "" && <FormHelperText  >Please enter your username</FormHelperText>}
              </FormControl>
            </Grid>
            <Grid item xs={12}>
              <FormControl error={password === ""}>
                <TextField
                  variant="outlined"
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="current-password"
                  autoFocus
                  onChange={handleChangepassword}
                />
                {password === "" && <FormHelperText  >Please enter your password</FormHelperText>}
              </FormControl>
            </Grid>
            <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            onClick={handlePressSignUp}
          >Sign Up</Button>
          </Grid>
          <br />
            <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            onClick={handlePressAddPaymentInfo}
          >Add Bank Info</Button>
          <Grid container justify="flex-end">          
            <Grid item>
              <Link href="#" variant="body2" onClick={handlePressSignIn}>
                Already have an account? Sign in
              </Link>
            </Grid>
          </Grid>
        </form>
      </div>
    </Container>
  );
}