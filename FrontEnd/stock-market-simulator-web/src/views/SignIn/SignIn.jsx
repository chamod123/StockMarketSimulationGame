import React from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { useState } from 'react';
import FormControl from '@material-ui/core/FormControl';
import FormHelperText from '@material-ui/core/FormHelperText';
import { signIn } from 'server/server';

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
      // backgroundImage:"url("+"https://i.ytimg.com/vi/JMzfgPHLTxY/maxresdefault.jpg"+")"
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
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

export default function SignIn(props) {
  const classes = useStyles();
  const [userName, setuserName] = useState("");
  const [password, setpassword] = useState("");
  const [invalid, setInvalid] = useState(false);


  const handleChangeEmail = (event) => {
    setuserName(event.target.value)
    setInvalid(false)
  }

  const handleChangepassword = (event) => {
    setpassword(event.target.value)
    setInvalid(false)
  }

  const handlePostSignIn = () => {
    // call post  handlePostSignIn(request) API
    signIn(userName, password).then(response => {
      if(response===1){
        props.history.push({pathname:'/admin', state: { userName: 'userName' }})
      }else{
        setInvalid(true)
      }
    })
  }

  const handlePressSignUp=()=>{
    props.history.push('/signUp')
  }

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign in
        </Typography>
          <FormControl error={userName === ""||invalid}>
            <TextField
              variant="outlined"
              margin="normal"
              required
              fullWidth
              id="userName"
              label="User Name"
              type="username"
              name="userName"
              autoComplete="userName"
              autoFocus
              onChange={handleChangeEmail}
            />
            {userName === "" && <FormHelperText>Please enter your user name</FormHelperText>}
            {invalid && <FormHelperText>Invalid user name</FormHelperText>}
          </FormControl>
          <br />
          <FormControl error={password === ""||invalid}>
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
            {password === "" && <FormHelperText>Please enter your password</FormHelperText>}
            {invalid && <FormHelperText>Invalid user name</FormHelperText>}
          </FormControl>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={handlePostSignIn}>
            Sign In
          </Button>
          <Grid container>
            <Grid item>
              <Link href="#" variant="body2" onClick={handlePressSignUp}>
                {"Don't have an account? Sign Up"}
              </Link>
            </Grid>
          </Grid>
      </div>
      <Box mt={5}>
        <Create />
      </Box>
    </Container>
  );
}