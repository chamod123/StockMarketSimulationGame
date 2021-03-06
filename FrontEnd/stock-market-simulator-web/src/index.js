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
import ReactDOM from "react-dom";
import { createBrowserHistory } from "history";
import { Router, Route, Switch, Redirect } from "react-router-dom";

// core compo nents
import Admin from "layouts/Admin.jsx";
import SignIn from './views/SignIn/SignIn'
import SignUp from './views/SignUp/SignUp'
import PaymentForm from './views/SignUp/PaymentForm'

import "assets/css/material-dashboard-react.css?v=1.7.0";

const hist = createBrowserHistory();

ReactDOM.render(
  <Router history={hist}>
    <Switch>
      <Route path="/signIn" component={SignIn} />
      <Route path="/signUp" component={SignUp} />
      <Route path="/admin" component={Admin} />
      <Route path="/paymentinfo" component={PaymentForm} />
      <Route path="/admin/game" component={Admin} />
      <Redirect from="/" to="/signIn" />
    </Switch>
  </Router>,
  document.getElementById("root")
);
