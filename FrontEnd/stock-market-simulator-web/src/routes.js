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
// @material-ui/icons
import Dashboard from "@material-ui/icons/Dashboard";
import Person from "@material-ui/icons/Person";
import LibraryBooks from "@material-ui/icons/LibraryBooks";
import BubbleChart from "@material-ui/icons/BubbleChart";
import Assessment from "@material-ui/icons/Assessment";
// core components/views for Admin layout
import DashboardPage from "views/Dashboard/Dashboard.jsx";
import UserProfile from "views/UserProfile/UserProfile.jsx";
import TableList from "views/TableList/TableList.jsx";
import Typography from "views/Typography/Typography.jsx";
import Icons from "views/Icons/Icons.jsx";
import StocksPage from "views/Stocks/Stocks.jsx";

const dashboardRoutes = [
  {
    path: "/dashboard",
    name: "Dashboard",
    icon: Dashboard,
    component: DashboardPage,
    layout: "/admin"
  },
  {
    path: "/profile",
    name: "User Profile",
    icon: Person,
    component: UserProfile,
    layout: "/admin"
  },
  {
    path: "/game",
    name: "Game Board",
    icon: "content_paste",
    component: TableList,
    layout: "/admin"
  },
  {
    path: "/portfolio",
    name: "Portfolio",
    icon: LibraryBooks,
    component: Typography,
    layout: "/admin"
  },
  {
    path: "/bank",
    name: "Bank",
    icon: BubbleChart,
    component: Icons,
    layout: "/admin"
  },
  {
    path: "/stocks",
    name: "Stocks",
    icon: Assessment,
    component: StocksPage,
    layout: "/admin"
  },
];

export default dashboardRoutes;
