import React from "react";
import reactDom from "react-dom";
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';
import { BrowserRouter } from "react-router-dom";

import 'font-awesome/css/font-awesome.css';
import './index.css';

import AnimalManagement from "./components/animalmanagement";

import Login from "./components/login";

import {Route, Switch} from 'react-router-dom';

import AnimalProfile from "./components/animalprofile";

import RegistrationPage from "./components/registerpage";
import UserManagement from './components/usermanagement';
import YourAccount from "./components/youraccount";
import UserDetails from "./components/userdetails";
import MainMenu from "./components/mainmenu";

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
import AddTreatment from './components/addtreatment';
import AddIssue from './components/addissue';
import AddUser from './components/adduser';
import IsAnimalOwner from './components/isanimalowner';
import AddAnimalExisting from './components/addanimalexisting';
import AddAnimalNew from './components/addanimalnew';
import EditUser from './components/edituser';
import UploadPhoto from './components/uploadphoto';
import EditTreatment from './components/edittreatment';
import EditIssue from "./components/editissue";
import AddWeight from "./components/addweight";



reactDom.render(


<BrowserRouter>


    <Switch>
    <Route path='/:user/:uid/animals/addnanimal' component={AddAnimalNew}/> 
    <Route path='/:user/:uid/animals/addoanimal' component={AddAnimalExisting}/> 
    <Route path='/:user/:uid/animals/isowner' component={IsAnimalOwner}/> 
    <Route path='/:user/:uid/users/:id/edit' component={EditUser}/>
    <Route path='/:user/:uid/users/add' component={AddUser}/>
    <Route path='/:user/:uid/animals/:id/issues/:cid/edit' component={EditIssue}/>
    <Route path='/:user/:uid/animals/:id/issues/add' component={AddIssue}/> 
    <Route path='/:user/:uid/animals/:id/treatments/:cid/edit' component={EditTreatment}/>
    <Route path='/:user/:uid/animals/:id/treatments/add' component={AddTreatment}/>
    <Route path='/:user/:uid/animals/:id/comments/:cid/edit' component={EditComment}/>
    <Route path='/:user/:uid/animals/:id/comments/add' component={AddComment}/> 
    <Route path='/:user/:uid/animals/:id/addphoto' component={UploadPhoto}/> 
    <Route path='/:user/:uid/animals/:id/status' component={AnimalStatus}/> 
    <Route path='/:user/:uid/animals/:id/issues' component={IssueList}/> 
    <Route path='/:user/:uid/animals/:id/treatments' component={TreatmentList}/>
    <Route path='/:user/:uid/animals/:id/addweight' component={AddWeight}/>
    <Route path='/:user/:uid/animals/:id/comments' component={CommentList}/>
    <Route path='/:user/:uid/requestmanagment' component={RequestManagement}/>
    <Route path='/:user/:uid/requestsubmission' component={RequestSubmission}/>
      <Route path='/:user/:uid/users/:id' component={UserDetails}/>
      <Route path="/:user/:uid/animals/:id" component={AnimalProfile} />
      <Route path="/:user/:uid/animals" component={AnimalManagement}/>
      <Route path="/login" component={Login}/>
      <Route path="/:user/:uid/users" component={UserManagement}/>
      
      <Route path='/register' component={RegistrationPage}/>
      <Route path='/:user/:uid/youraccount' component={YourAccount}/>
      <Route path="/:user/:uid/menu" component={MainMenu} />
      <Route path="/" component={HomePage} />
      <Route path='*' component={ErrorPage}/>
      </Switch>
    


</BrowserRouter>,document.getElementById('root'));

