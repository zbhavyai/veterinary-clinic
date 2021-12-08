import React, { Component } from 'react';
import axios from "axios";
import ReactApexChart from 'react-apexcharts';
import { useParams } from "react-router-dom";
import { withRouter } from "react-router";
import NavBar from './navbar';
import { Redirect } from 'react-router';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import NavBarStart from './navbarstart';
class RegistrationPage extends React.Component {
    state = {
        
        filterOption: 0,
        
        firstName: "",
        middleName: "",
        lastName: "",
        role: "ADMIN",
        emailId: "",
        password: "",
        status: "INACTIVE"
        
        
    };

    handleFilter = (e) => {
        let pos = "";
        this.setState({ 
            filterOption: e.target.value,
            role: pos, 
        });

        if(e.target.value==0){
            pos = "ADMIN";

        }else if(e.target.value==1){
            pos = "ATTENDANT";

        }else if(e.target.value==2){
            pos = "TECHNICIAN";
            
        }else if(e.target.value==3){
            pos = "TEACHER";
            
        }else if(e.target.value==4){
            pos = "STUDENT";
            
        }

        this.setState({ 
            filterOption: e.target.value,
            role: pos, 
        });
        //this.setState({filterOption: 1});
        console.log("Role Selected", this.state.role);

    };

    handleFnChange(event) {
        this.setState({firstName: event.target.value})
        
      }

      handleMnChange(event) {
        this.setState({middleName: event.target.value})
        
      }
    
      handleLnChange(event) {
        this.setState({lastName: event.target.value})
        
      }

      handlePsChange(event) {
        this.setState({password: event.target.value})
        
      }

      handleEmChange(event) {
        this.setState({emailId: event.target.value})
        
      }


      handleRegister =(e)=>{
        
        
        

        let message = {
            "userId": 3,
            "firstName": this.state.firstName,
            "middleName": this.state.middleName,
            "lastName": this.state.lastName,
            "role": this.state.role,
            "emailId": this.state.emailId,
            "password": this.state.password,
            "status": this.state.status
        }
        
        message = {
            "userId": 3,
            "firstName": "Batman",
            "middleName": null,
            "lastName": "Joker",
            "role": "STUDENT",
            "emailId": "batman@gmail.com",
            "password": "batbat",
            "status": "ACTIVE"
        }
       
        
        const link = "http://localhost:8080/api/v1/users/register";
        axios.post(link, message,{headers:{}}).then(res => {
            console.log(res);
            console.log(res.data);
          });
        //window.location.reload(false);

        this.props.history.push("/");
        





    };


    render() { 
        return <React.Fragment>
            <NavBarStart/>
            <div class="container">
            <div class="card" style={this.styles}>
                
                <div class="card-body">
                    <h5 class="card-title">Register for an Account Now</h5>
                    <div class="row">
                      <div class="col-sm">
                      < input type="text" id="inputFilter"  className="form-control" placeholder="First Name" value={this.state.firstName} onChange={(e) =>this.handleFnChange(e)} aria-label="First Name" aria-describedby="basic-addon2" />
                    
                      </div>
                      <div class="col-sm">
                      < input type="text" id="inputFilter" className="form-control" placeholder="Middle Name" value={this.state.middleName} onChange={(e) =>this.handleMnChange(e)} aria-label="Middle Name" aria-describedby="basic-addon2" />
                    
                      </div>
                      <div class="col-sm">
                      < input type="text" id="inputFilter" className="form-control" placeholder="Last Name" value={this.state.lastName} onChange={(e) =>this.handleLnChange(e)} aria-label="Last Name" aria-describedby="basic-addon2" />
                    
                      </div>
                      
                      
                    </div>

                    <div class="row">
                    <select   onChange={(e) => this.handleFilter(e)} className="form-select" id="selectFilter" value={this.state.filterOption}>
                        <option value = "0">ADMIN</option>
                        <option value="1">ATTENDANT</option>
                        <option value="2">TECHNICIAN</option>
                        <option value="3">TEACHER</option>
                        <option value="4">STUDENT</option>
                        
                    </select>

                    </div>
                    <div class="row">
                    < input type="text" id="inputFilter" className="form-control" placeholder="Email Address" value={this.state.emailId} onChange={(e) =>this.handleEmChange(e)} aria-label="Email Address" aria-describedby="basic-addon2" />
                    </div>
                    <div class="row">
                    < input type="text" id="inputFilter" className="form-control" placeholder="Create Password" value={this.state.password} onChange={(e) =>this.handlePsChange(e)} aria-label="Create Password" aria-describedby="basic-addon2" />
                    </div>

                    <button onClick={(e) => this.handleRegister(e)} className="btn btn-primary">Register</button>
                    <p>Note: Upon sign up, you will need to wait for the Admint to activate your account before you can login</p>
                </div>
            </div>
            </div>
        </React.Fragment>;
    }
}
 
export default RegistrationPage;