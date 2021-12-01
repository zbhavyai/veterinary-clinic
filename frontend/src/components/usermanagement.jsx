import React, { Component } from 'react';
import axios from 'axios';
import { getUsers } from './../services/fakeUserService';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";

class UserManagement extends React.Component {
    state = {
        //users: [],
        users:getUsers(),
        filterOption: 0,
        filterText: "",
        pageSize: 4
    };

    handleFilter =(e)=>{
        this.setState({filterOption:e.target.value});
        //this.setState({filterOption: 1});
        console.log("ID clicked", this.state.filterOption);

    };

    handleFilterText =(e)=>{
        this.setState({filterText:e.target.value});
        //this.setState({filterOption: 1});
        console.log("tedt changed", this.state.filterText);

    };

    async componentDidMount() {
        
      //const {data: users} = await axios.get('http://localhost:8080/api/v1/users/', {headers: {'Access-Control-Allow-Origin': true,},});
      
      //this.setState({users}); 
          

      
          
      //const promise = axios.get('http://localhost:8080/api/v1/users/')
      
    }



    render() { 
       console.log(this.state.users);
        let filtered = this.state.users;
        if (this.state.filterOption == 1) {
            filtered = 1?this.state.users.filter(m=>m["userId"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.users;
          } else if (this.state.filterOption == 2) {
            filtered = 1?this.state.users.filter(m=>m["firstName"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.users;
          } else if (this.state.filterOption == 3) {
            filtered = 1?this.state.users.filter(m=>m["lastName"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.users;
          } else if (this.state.filterOption == 4) {
            filtered = 1?this.state.users.filter(m=>m["role"].toString().toLowerCase().includes(this.state.filterText.toLowerCase())):this.state.users;
          } else if (this.state.filterOption == 5) {
            filtered = 1?this.state.users.filter(m=>m["emailId"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.users;
        } else if (this.state.filterOption == 6) {
            filtered = 1?this.state.users.filter(m=>m["status"].toString().toLowerCase().includes(this.state.filterText.toLowerCase()) ):this.state.users;
          } else{
            filtered = this.state.users;
          }
        return <React.Fragment>
            <div class="container">
                  
                  </div>
              
              
             
                  
                  
                  <div className="input-group mb-3">
                      
                      <label className="input-group-text" htmlFor ="inputGroupSelect01">Search For</label>
                      <select   onChange = {(e) => this.handleFilter(e)} value = {this.state.filterOption}  className="form-select" id="selectFilter" >
                          <option value = "0">No Filter</option>
                          <option value="1">ID</option>
                          <option value="2">First Name</option>
                          <option value="3">Last Name</option>
                          <option value="4">Role</option>
                          <option value="5">Email</option>
                          <option value="5">Status</option>
                      </select>
                  </div>
  
                  <div className="input-group mb-3">
                      < input type="text" id="inputFilter" onChange = {(e) => this.handleFilterText(e)} value = {this.state.filterText} className="form-control" placeholder="Enter Your Search Term Here" aria-label="Enter Your Search Term Here" aria-describedby="basic-addon2" />
                      
                  </div>
          
          <table className="table">
              <thead>
                  <tr>
                      <th>ID</th>
                      <th>First Name</th>
                      <th>Last Name</th>
                      <th>Role</th>
                      <th>Email</th>
                      <th>Status</th>
                      <th></th>
                  </tr>
              </thead>
              <tbody>
                  {filtered.map(user =>(
                      <tr key = {user["userId"].toString()}>
                      <td>{user["userId"].toString()}</td>
                      <td>{(user["firstName"]==null) ? 'na' : user["firstName"].toString()}</td>
                      <td>{(user["lastName"]==null) ? 'na' : user["lastName"].toString()}</td>
                      <td>{(user["role"]==null) ? 'na' : user["role"].toString()}</td>
                      <td>{(user["emailId"]==null) ? 'na' : user["emailId"].toString()}</td>
                      <td>{(user["status"]==null) ? 'na' : user["status"].toString()}</td>
                      
                      <td><Link to={"/users/"+user["userId"].toString()} className="btn btn-primary btn-sm">Details</Link></td>
                      
                      </tr>
  
                  ))}
              
  
                  
                  
              </tbody>
          </table>
          <div class="row">
                        <div class="col-sm">
                          
                        </div>
                        <div class="col-sm">
                          
                        </div>
                        <div class="col-sm">
                          
                        </div>
                        <div class="col-sm">
                          
                        </div>
                        <div class="col-sm">
                          <button type="button" class="btn btn-secondary">Add User</button>
                        </div>
                        
          </div>

        </React.Fragment>;
    }
}
 
export default UserManagement;