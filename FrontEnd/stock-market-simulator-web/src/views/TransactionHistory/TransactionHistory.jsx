import React from "react";
// nodejs library to set properties for components
import PropTypes from "prop-types";
// react plugin for creating charts
import ChartistGraph from "react-chartist";
// @material-ui/core
import withStyles from "@material-ui/core/styles/withStyles";
import Icon from "@material-ui/core/Icon";
// @material-ui/icons
import Store from "@material-ui/icons/Store";
import Accessibility from "@material-ui/icons/Accessibility";
// core components
import GridItem from "components/Grid/GridItem.jsx";
import GridContainer from "components/Grid/GridContainer.jsx";
import Table from "components/Table/Table.jsx";
import Card from "components/Card/Card.jsx";
import CardHeader from "components/Card/CardHeader.jsx";
import CardIcon from "components/Card/CardIcon.jsx";
import CardBody from "components/Card/CardBody.jsx";
import CardFooter from "components/Card/CardFooter.jsx";
import dashboardStyle from "assets/jss/material-dashboard-react/views/dashboardStyle.jsx";
import { Button } from "@material-ui/core";

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
          <GridItem xs={12} sm={6} md={3}>
            <Card>
              <CardHeader color="warning" stats icon>
                <CardIcon color="warning">
                  <Icon>content_copy</Icon>
                </CardIcon>
                <p className={classes.cardCategory}>Finance</p>
                <h3 className={classes.cardTitle}>
                  This Week Sold Stock : <small> 20</small>
                </h3>
              </CardHeader>
              {/* <CardFooter stats>
                <div className={classes.stats}>
                  <Danger>
                    <Warning />
                  </Danger>
                  <a href="#pablo" onClick={e => e.preventDefault()}>
                    Get More Details...
                  </a>
                </div>
              </CardFooter> */}
            </Card>
          </GridItem>
          <GridItem xs={12} sm={6} md={3}>
            <Card>
              <CardHeader color="success" stats icon>
                <CardIcon color="success">
                  <Store />
                </CardIcon>
                <p className={classes.cardCategory}>Technology</p>
                <h3 className={classes.cardTitle}>
                  This Week Sold Stock : <small> 20</small>
                </h3>
              </CardHeader>
              {/* <CardFooter stats>
                <div className={classes.stats}>
                  <DateRange />
                  Get More Details...
                </div>
              </CardFooter> */}
            </Card>
          </GridItem>
          <GridItem xs={12} sm={6} md={3}>
            <Card>
              <CardHeader color="danger" stats icon>
                <CardIcon color="danger">
                  <Icon>info_outline</Icon>
                </CardIcon>
                <p className={classes.cardCategory}>Consumer Service</p>
                <h3 className={classes.cardTitle}>
                  This Week Sold Stock : <small> 20</small>
                </h3>
              </CardHeader>
              {/* <CardFooter stats>
                <div className={classes.stats}>
                  <LocalOffer />
                  Get More Details...
                </div>
              </CardFooter> */}
            </Card>
          </GridItem>
          <GridItem xs={12} sm={6} md={3}>
            <Card>
              <CardHeader color="info" stats icon>
                <CardIcon color="info">
                  <Accessibility />
                </CardIcon>
                <p className={classes.cardCategory}>Manufacturing</p>
                <h3 className={classes.cardTitle}>
                  This Week Sold Stock : <small> 20</small>
                </h3>
              </CardHeader>
              {/* <CardFooter stats>
                <div className={classes.stats}>
                  <Update />
                  Get More Details...
                </div>
              </CardFooter> */}
            </Card>
          </GridItem>
        </GridContainer>
        <GridContainer>
          <GridItem xs={12} sm={12} md={12}>
            <Card>
              <CardBody>
                <h4 className={classes.cardTitle}>What is Stock Market Fantacy League?</h4>
                <p className={classes.cardCategory}>
                  <span className={classes.successText}>
                    {/* <ArrowUpward className={classes.upArrowCardCategory} /> 55% */}
                  </span>{" "}
                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining
                </p>
              </CardBody>
              <CardFooter>
                
              </CardFooter>
            </Card>
          </GridItem>
          <GridItem xs={12} sm={12} md={12}>
            <Card>
              <CardBody>
                <h4 className={classes.cardTitle}>How To Play?</h4>
                <p className={classes.cardCategory}>
                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                </p>
              </CardBody>

              {/* <CardBody>
                <h4 className={classes.cardTitle}>Email Subscriptions</h4>
                <p className={classes.cardCategory}>
                  Last Campaign Performance
                </p>
              </CardBody> */}
              <CardFooter>
                
              </CardFooter>
            </Card>
          </GridItem>
          <GridItem xs={12} sm={12} md={12}>
            <Card>
              <CardBody>
                <h4 className={classes.cardTitle}>Looking Forward to Play? Then Let's Start</h4>
                <p className={classes.cardCategory}>
                  Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining
                </p>
              </CardBody>

              {/* <CardBody>
                <h4 className={classes.cardTitle}>Completed Tasks</h4>
                <p className={classes.cardCategory}>
                  Last Campaign Performance
                </p>
              </CardBody> */}
              <CardFooter>
               

                <Button color="primary">Let's Start</Button>
                
              </CardFooter>
            </Card>
          </GridItem>

          <GridItem xs={12} sm={12} md={12}>
         
        <Card>
            <CardBody>
              <h4 className={classes.cardTitle}>Testimonial</h4>
              <p className={classes.cardCategory}>
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining"
                </p>
                
            </CardBody>
            </Card>
          </GridItem>
        </GridContainer>
        <GridContainer>
          {/* <GridItem xs={12} sm={12} md={12}> */}
            {/* <CustomTabs
              title="Tasks:"
              headerColor="primary"
              tabs={[
                {
                  tabName: "Bugs",
                  tabIcon: BugReport,
                  tabContent: (
                    <Tasks
                      checkedIndexes={[0, 3]}
                      tasksIndexes={[0, 1, 2, 3]}
                      tasks={bugs}
                    />
                  )
                },
                {
                  tabName: "Website",
                  tabIcon: Code,
                  tabContent: (
                    <Tasks
                      checkedIndexes={[0]}
                      tasksIndexes={[0, 1]}
                      tasks={website}
                    />
                  )
                },
                {
                  tabName: "Server",
                  tabIcon: Cloud,
                  tabContent: (
                    <Tasks
                      checkedIndexes={[1]}
                      tasksIndexes={[0, 1, 2]}
                      tasks={server}
                    />
                  )
                }
              ]}
            /> */}
          {/* </GridItem> */}
          <GridItem xs={12} sm={12} md={12}>
            <Card>
              <CardHeader color="warning">
                <h4 className={classes.cardTitleWhite}>Leader Board</h4>
                <p className={classes.cardCategoryWhite}>
                  Top 4 Players Achievements
                </p>
              </CardHeader>
              <CardBody>
                <Table
                  tableHeaderColor="warning"
                  // tableHead={["ID", "Name", "Won Prize", "Country"]}
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

TransactionHistory.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(dashboardStyle)(TransactionHistory);
