
// @material-ui/icons
import Dashboard from "@material-ui/icons/Dashboard";
import Person from "@material-ui/icons/Person";
import ListAlt from "@material-ui/icons/ListAlt";
import StarBorder from "@material-ui/icons/StarBorder";
import Assessment from "@material-ui/icons/Assessment";
import CreditCard from "@material-ui/icons/CreditCard";

// core components/views for Admin layout
import DashboardPage from "views/Dashboard/Dashboard.jsx";
import UserProfile from "views/UserProfile/UserProfile.jsx";
import GameBoard from "views/GameBoard/GameBoard.jsx";
import TransactionHistory from "views/TransactionHistory/TransactionHistory.jsx";
import LeaderBoard from "views/LeaderBoard/LeaderBoard.jsx";
import Bank from "views/Bank/bank";
import StocksPage from "views/Stocks/Stocks.jsx";
import SignIn from "views/SignIn/SignIn.jsx";
import SignUp from "views/SignUp/SignUp.jsx";
import Payment from "views/SignUp/PaymentForm.jsx";

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
    component: SignUp,
    layout: "/admin"
  },
  {
    path: "/game",
    name: "Game Board",
    icon: "content_paste",
    component: GameBoard,
    layout: "/admin"
  },
  {
    path: "/portfolio",
    name: "Leader Board",
    icon: StarBorder,
    component: LeaderBoard,
    layout: "/admin"
  },
  {
    path: "/TransactionHistory",
    name: "Transaction History",
    icon: ListAlt,
    component: TransactionHistory,
    layout: "/admin"
  },
  {
    path: "/bank",
    name: "Bank",
    icon: CreditCard,
    component: Bank,
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
