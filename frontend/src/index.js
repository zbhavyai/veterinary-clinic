import React from "react";
import reactDom from "react-dom";
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import { BrowserRouter } from "react-router-dom";

import 'font-awesome/css/font-awesome.css';
import './index.css';
import App from "./App";
import AnimalManagement from "./components/animalmanagement";

import Login from "./components/login";

import {Route, Switch} from 'react-router-dom';
import { Routes } from 'react-router';
import NavBar from "./components/navbar";
import AnimalProfile from "./components/animalprofile";
import NavBarStart from './components/navbarstart';
import RegistrationPage from "./components/registerpage";
import UserManagement from './components/usermanagement';
import YourAccount from "./components/youraccount";
import UserDetails from "./components/userdetails";
import MainMenu from "./components/mainmenu";
import axios from "axios";
import ErrorPage from './components/errorpage';
import HomePage from './components/homepage';
import CommentList from './components/commentlist';
import RequestSubmission from './components/submitrequest';
import RequestManagement from "./components/requestmanagement";
import TreatmentList from './components/treatmentlist';
import IssueList from './components/issuelist';
import AnimalStatus from './components/animalstatus';
import AddComment from './components/addcomment';
import EditComment from './components/editcomment';



reactDom.render(


<BrowserRouter>


    <Switch>
    <Route path='/:user/animals/:id/comments/:cid/edit' component={EditComment}/>
    <Route path='/:user/animals/:id/comments/add' component={AddComment}/> 
    <Route path='/:user/animals/:id/status' component={AnimalStatus}/> 
    <Route path='/:user/animals/:id/issues' component={IssueList}/> 
    <Route path='/:user/animals/:id/treatments' component={TreatmentList}/>
    <Route path='/:user/animals/:id/comments' component={CommentList}/>
    <Route path='/:user/requestmanagment' component={RequestManagement}/>
    <Route path='/:user/requestsubmission' component={RequestSubmission}/>
      <Route path='/:user/users/:id' component={UserDetails}/>
      <Route path="/:user/animals/:id" component={AnimalProfile} />
      <Route path="/:user/animals" component={AnimalManagement}/>
      <Route path="/login" component={Login}/>
      <Route path="/:user/users" component={UserManagement}/>
      
      <Route path='/register' component={RegistrationPage}/>
      <Route path='/:user/youraccount' component={YourAccount}/>
      <Route path="/:user/menu" component={MainMenu} />
      <Route path="/" component={HomePage} />
      <Route path='*' component={ErrorPage}/>
      </Switch>
    


</BrowserRouter>,document.getElementById('root'));

