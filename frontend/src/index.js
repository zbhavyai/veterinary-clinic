import React from "react";
import reactDom from "react-dom";
import 'bootstrap/dist/css/bootstrap.css';

import AnimalManagement from "./components/animalmanagement";
import 'font-awesome/css/font-awesome.css';
import './index.css';
import App from "./App";
const element = <h1>Hello World</h1>

reactDom.render(<AnimalManagement />,document.getElementById('root'));

