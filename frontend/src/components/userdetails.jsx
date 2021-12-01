import React, { Component } from 'react';
import axios from "axios";
import {getUserbyId} from './../services/fakeUserService'; 
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
  } from "react-router-dom";

class UserDetails extends React.Component {
    state = {
        user: {},
   
    };

    async componentDidMount() {
        
        const {data: posts} = await axios.get('http://localhost:8080/api/v1/users/1', {headers: {'Access-Control-Allow-Origin': true,},});
        
        this.setState({posts});
        

        

        
            
        //const promise = axios.get('https://jsonplaceholder.typicode.com/posts')
        
    }

    //WARNING! To be deprecated in React v17. Use componentDidMount instead.
    componentWillMount() {
        const id = this.props.match.params.id;
        console.log(id);


        this.setState({user: getUserbyId(id), 
            
        });
    }

    render() { 
        let label = "";
        if(this.state.user["role"]===0){
            label = "Student";

        } else if(this.state.user["role"]===1){
            label = "Technician";

        } else if(this.state.user["role"]===2){
            label = "Teacher";

        } else if(this.state.user["role"]===3){
            label = "Admin";

        }

        return <React.Fragment>
            <div class="card" style={this.styles}>
                
                <div class="card-body">
                    <h5 class="card-title">User Details</h5>
                    <div class="row">
                      <div class="col-sm">
                      < input type="text" id="inputFilter"  className="form-control" placeholder={this.state.user["firstName"]} aria-label="First Name" aria-describedby="basic-addon2" />
                    
                      </div>
                      <div class="col-sm">
                      < input type="text" id="inputFilter" className="form-control" placeholder={this.state.user["middleName"]} aria-label="Middle Name" aria-describedby="basic-addon2" />
                    
                      </div>
                      <div class="col-sm">
                      < input type="text" id="inputFilter" className="form-control" placeholder={this.state.user["lastName"]} aria-label="Last Name" aria-describedby="basic-addon2" />
                    
                      </div>
                      
                      
                    </div>
                    <div class="row">
                    < input type="text" id="inputFilter" className="form-control" placeholder={this.state.user["userId"]} aria-label="Your Unique ID" aria-describedby="basic-addon2" />
                    </div>

                    <div class="row">
                    <select   className="form-select" id="selectFilter" defaultValue={{ label: label, value: this.state.user["role"].toString() }}>
                        <option value = "0">Student</option>
                        <option value="1">Technician</option>
                        <option value="2">Teacher</option>
                        <option value="3">Admin</option>
                        
                    </select>

                    </div>
                    <div class="row">
                    < input type="text" id="inputFilter" className="form-control" placeholder={this.state.user["emailId"]} aria-label="Email Address" aria-describedby="basic-addon2" />
                    </div>
                    <div class="row">
                    < input type="text" id="inputFilter" className="form-control" placeholder={this.state.user["status"]} aria-label="Status" aria-describedby="basic-addon2" />
                    </div>

                   

                    <a href="#" class="btn btn-primary">Save</a><a href="#" class="btn btn-primary">Remove</a><a href="\users" class="btn btn-primary">Cancel</a>
                </div>
            </div>

        </React.Fragment>;
    }
}
 
export default UserDetails;