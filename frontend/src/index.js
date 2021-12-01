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

import {Route} from 'react-router-dom';
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



reactDom.render(


<BrowserRouter>

<NavBar/>
<Routes>
      <Route path="/" element={<MainMenu />} />
      <Route path="/animalprofile" element={<AnimalProfile />} />
      <Route path="/animals" element={<AnimalManagement />}/>
      <Route path="/users" element={<UserManagement />}/>
      <Route path="/login" element={<Login/>}/>
      <Route path='/register' element={<RegistrationPage/>}/>
      <Route path='/youraccount' element={<YourAccount/>}/>
      <Route path='/userdetail' element={<UserDetails/>}/>
    </Routes>


</BrowserRouter>,document.getElementById('root'));

