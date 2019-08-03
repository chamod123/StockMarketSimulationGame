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
import CardAvatar from "components/Card/CardAvatar.jsx";
import CardBody from "components/Card/CardBody.jsx";
import CardFooter from "components/Card/CardFooter.jsx";
import Primary from "components/Typography/Primary";
import Danger from "components/Typography/Danger.jsx";
import avatar from "assets/img/faces/SMG_Web.png";
import { getPlayerByID } from 'server/server';

 
  
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

function UserProfile(props) {
  const { classes } = props;
  const [fname, setFname] = useState('');
  const [lname, setLname] = useState("");
  const [email, setEmail] = useState("");
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  useEffect(() => {
    if(userName===''&&password==='')
   getPlayerByID(7).then(response => {
     console.log(response)
      setFname(response.name)
      setLname(response.secondName)
      setEmail(response.email)
      setUserName(response.userName)
      setPassword(response.password)
    })
  });


  const UpdateProfile=()=>{
    let request={
      name:{fname},
      secondName:{lname},
      email:{email},
      userName:{userName},
      password:{password}
    }
    console.log(request)
   // call post UpdateProfile(request) API
  }

  const handleNavigateToBank=()=>{
     props.history.push('/admin/bank')
  }
  
  const handleChangeFname=(event)=>{
    setFname(event.target.value)
  }
  const handleChangeLname=(event)=>{
    setLname(event.target.value)
  }
  const handleChangeEmail=(event)=>{
    setEmail(event.target.value)
  }
  const handleChangeUname=(event)=>{
    setUserName(event.target.value)
  }
  const handleChangePassword=(event)=>{
    setPassword(event.target.value)
  }


  return (
    <div>
      <GridContainer>
        <GridItem xs={12} sm={12} md={8}>
          <Card>
            <CardHeader color="danger">
              <h4 className={classes.cardTitleWhite}>Edit Profile</h4>
              <p className={classes.cardCategoryWhite}>Edit your profile</p>
            </CardHeader>
            <CardBody>
            <GridContainer>
                <GridItem xs={12} sm={12} md={6}>
                  <CustomInput
                    labelText="First Name"
                    id="first-name"
                    type="text"
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      value:fname,
                      onChange: handleChangeFname
                  }}                  
                />
                </GridItem>
                <GridItem xs={12} sm={12} md={6}>
                  <CustomInput
                    labelText="Last Name"
                    id="last-name"
                    type="text"
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      value:lname,
                      onChange: handleChangeLname
                  }}
                  />
                  
                </GridItem>
              </GridContainer>
              <GridContainer>
                <GridItem xs={12} sm={12} md={4}>
                  <CustomInput
                    labelText="Email address"
                    id="email-address"
                    type="text"
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      value:email,
                      onChange: handleChangeEmail
                  }}
                  />
                </GridItem>
              </GridContainer>
              
              <GridContainer>
                <GridItem xs={12} sm={12} md={4}>
                  <CustomInput
                    labelText="User Name"
                    id="user-name"
                    type="text"
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      value:userName,
                      onChange : handleChangeUname
                  }}
                  />
                </GridItem>
                <GridItem xs={12} sm={12} md={4}>
                  <CustomInput
                    labelText="Password"
                    id="password"
                    type="number"
                    formControlProps={{
                      fullWidth: true
                    }}
                    inputProps={{
                      value:password,
                      onChange: handleChangePassword
                  }}
                  />
                </GridItem>
              </GridContainer>
            </CardBody>
            <CardFooter>
              <Button color="danger" onClick={UpdateProfile}>Update Profile</Button>
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={12} sm={12} md={4}>
          <Card profile>
            <CardAvatar profile>
              <a href="#pablo" onClick={e => e.preventDefault()}>
                <img src={avatar} alt="..." />
              </a>
            </CardAvatar>
            <CardBody profile>
              <h6 className={classes.cardCategory}><Danger>---GAME PLAYER---</Danger></h6>
              <h6 className={classes.cardTitle}><Primary>User Name: {userName}</Primary></h6>
              <h6 className={classes.cardTitle}><Primary>Full Name: {fname} {lname}</Primary></h6>
              <h6 className={classes.cardTitle}><Primary>Email:{email}</Primary></h6>
              <Button color="primary" round onClick={handleNavigateToBank}>
                Bank Info
              </Button>
            </CardBody>
          </Card>
        </GridItem>
      </GridContainer>
    </div>
  );
}

UserProfile.propTypes = {
  classes: PropTypes.object
};

export default withStyles(styles)(UserProfile);
