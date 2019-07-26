import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import { LinearProgress } from '@material-ui/core';

import {
    primaryColor,
    infoColor
  } from "assets/jss/material-dashboard-react.jsx";

const styles = props => ({
    colorPrimary: {
      backgroundColor: primaryColor[0],
    },
    barColorPrimary: {
      backgroundColor: infoColor[0],
    }
  });
  
class LinearProgressBar extends Component{
    render(){
        const { classes } = this.props;
        return (
          <div className={classes.root}>
            <LinearProgress  {...this.props} classes={{colorPrimary: classes.colorPrimary, barColorPrimary: classes.barColorPrimary}} />           
           </div>
          )
    }
}

export default  withStyles(styles)(LinearProgressBar);